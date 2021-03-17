package com.ourline.ourlinezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CorsFilter
 * @Description 对跨域的欲请求options方法进行过滤
 * @date 20210225
 */
@Component
public class CorsFilter extends ZuulFilter {

	/**
	 * 日志对象
	 */
	private final static Logger logger = LoggerFactory.getLogger(CorsFilter.class);

	@Override
	public String filterType() {

		/*
		 * pre：可以在请求被路由之前调用 route：在路由请求时候被调用 post：在route和error过滤器之后被调用
		 * error：处理请求时发生错误时被调用
		 */
		// 前置过滤器
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {

		//// 优先级为0，数字越大，优先级越低
		return 0;
	}

	@Override
	public boolean shouldFilter() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		/*
		 * 只过滤OPTIONS 请求 跨域请求时，浏览器会先发出options请求校验是否可以跨域
		 */
		if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {

			return true;
		}
		return false;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();

		HttpServletResponse response = ctx.getResponse();
		HttpServletRequest request = ctx.getRequest();

		logger.info("请求地址[" + request.getRequestURI() + "]发送[" + RequestMethod.OPTIONS.name() + "]请求");

		CorsUitl.allowCors(request, response);

		// 不再路由
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(HttpStatus.OK.value());

		return null;
	}
}
