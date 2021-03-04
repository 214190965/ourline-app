package com.ourline.ourlinezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ourline.framework.redis.service.IRedisService;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginFilter
 * @Description
 *              -网关过滤器

 *              -配合corsFilter一起使用，过滤除跨域预请求option之外的其他请求

 *              -编辑ZuulFilter自定义过滤器，用于校验登录

 *              -重写zuulFilter类，有四个重要的方法
 *               1.-
 *                shouldFilter：返回一个Boolean值，判断该过滤器是否需要执行。返回true执行，返回false不执行。</li>
 *               2.- run：过滤器的具体业务逻辑。
 *               3.- filterType：返回字符串，代表过滤器的类型。
 *              -包含以下4种：
 *                pre：请求在被路由之前执行
 *               routing：在路由请求时调用
 *               post：在routing和errror过滤器之后调用
 *               error：处理请求时发生错误调用
 *              4.- filterOrder：通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高
 * @date 20210303
 * @Copyright
 */
@Component
public class LoginFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Resource
	private IRedisService redis;


	@Override
	public String filterType() {

		// 登录校验的过滤级别，肯定是第一层过滤
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {

		/*
		 * 执行顺序为1，值越小执行顺行越靠前 跨域检查循序为0,保证先执行跨域检查，再执行登录验证
		 */
		return 1;
	}

	@Override
	public boolean shouldFilter() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		/*
		 * 不过滤OPTIONS请求
		 */
		if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {

			return false;
		}
		return true;
	}

	/**
	 * (non-javadoc)
	 *
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() throws ZuulException {

		/**
		 * 简单请求时，不发送options请求 此处增加对跨域的判断
		 */
		// 登录校验逻辑
		// 1）获取zuul提供的请求上下文对象（即是请求全部内容）
		RequestContext currentContext = RequestContext.getCurrentContext();

		// 2) 从上下文中获取request对象
		HttpServletRequest request = currentContext.getRequest();
		HttpServletResponse response = currentContext.getResponse();

		logger.info("请求方法[" + request.getMethod() + "]=>[" + request.getRequestURI() + "]");

		CorsUitl.allowCors(request, response);

		// 登录不校验
		String requestURI = request.getRequestURI();

		if (requestURI.endsWith("/login") || requestURI.endsWith("/ssllogin") || requestURI.endsWith("/groups/list")) {

			return null;
		}

		// 从请求头中获取token
		String token = request.getHeader("Authorization");

		if (StringUtils.isBlank(token)) {

			token = request.getParameter("token");
		}

		/**
		 * 当不是登录请求 并且不含有有效token时，响应401状态给客户端
		 */
		if (StringUtils.isBlank(token) || !redis.hashKey(token)) {

			// 没有token，认为登录校验失败，进行拦截
			currentContext.setSendZuulResponse(false);
			// 返回401状态码
			currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			// 通知用户服务进行资源回收
			return null;

		}
		// 更新Redis过期时间
		redis.expire(token, 1800,null);

		return null;

	}
}
