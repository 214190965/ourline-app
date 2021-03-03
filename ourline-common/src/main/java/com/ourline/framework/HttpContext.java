package com.ourline.framework;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HttpContext
 * @Description request response获取
 * @date 20210303 04:10:24
 * @Copyright
 */
@Component("httpContext")
public class HttpContext {

	/**
	 * 获取request对象
	 *
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();

		ServletRequestAttributes requestAtt = (ServletRequestAttributes) ra;

		HttpServletRequest request = null;

		if (requestAtt != null) {

			request = requestAtt.getRequest();
		}

		return request;
	}

	/**
	 * 获取response对象
	 *
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {

		ServletRequestAttributes requestAtt = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		return requestAtt == null ? null : requestAtt.getResponse();
	}

}
