package com.ourline.ourlinezuul.filter;

import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ErrorFilter
 * @Description 网关异常统一处理
 * @date 20210317
 */
@Component
public class ErrorFilter extends SendErrorFilter {

	private final static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public boolean shouldFilter() {

		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * (non-javadoc)
	 *
	 * @see SendErrorFilter#run()
	 */
	@Override
	public Object run() {

		String errorMsg = "服务调用失败!错误信息:";

		RequestContext ctx = RequestContext.getCurrentContext();
		//
		ExceptionHolder e = findZuulException(ctx.getThrowable());

		logger.warn("ZUUL 网关异常>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + e.getErrorCause());

		ctx.setResponseStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());

		HttpServletResponse req = ctx.getResponse();

		req.setCharacterEncoding("UTF-8");

		try {

			req.getOutputStream().write((errorMsg + e.getErrorCause()).getBytes("UTF-8"));
		} catch (IOException e1) {

			e1.printStackTrace();
			ReflectionUtils.rethrowRuntimeException(e1);

			return null;
		}

		return null;
	}

	@Override
	public String filterType() {

		return FilterConstants.ERROR_TYPE;
	}

	@Override
	public int filterOrder() {

		return 30;
	}

}
