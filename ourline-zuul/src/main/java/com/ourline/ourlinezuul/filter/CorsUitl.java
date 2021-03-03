package com.ourline.ourlinezuul.filter;

import io.micrometer.core.instrument.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CorsUitl
 * @Description 跨域
 * @author LUKE.LEE
 * @date 2019-11-28 04:30:56
 * @Copyright
 */
public class CorsUitl {

	/**
	 * @Title allowCors
	 * @Description 配置http请求允许跨域
	 * @param request
	 * @param response
	 */
	public static void allowCors(HttpServletRequest request, HttpServletResponse response) {

		// 跨域请求的源地址，设置为允许源地址跨域
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//		response.setHeader("Access-Control-Allow-Origin", "*");
		// 是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// 自适应所有自定义头
		String headers = request.getHeader("Access-Control-Request-Headers");

		if (StringUtils.isNotBlank(headers)) {

			// 响应预检请求的时候使用.用来指明在实际的请求中,可以使用哪些自定义HTTP请求头
			response.setHeader("Access-Control-Allow-Headers", headers);
			//// 设置浏览器允许访问的服务器的头信息的白名单
			response.setHeader("Access-Control-Expose-Headers", headers);
		}
		// 允许跨域的请求方法类型
		response.setHeader("Access-Control-Allow-Methods", "*");
		// 预请求的结果的有效期,预检命令（OPTIONS）缓存时间，单位：秒
		response.setHeader("Access-Control-Max-Age", "3600");
	}

}
