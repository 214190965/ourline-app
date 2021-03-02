package com.ourline.ourlinezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现跨域
 * */
@Component
public class CrosFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //前置过滤，在路由请求之前调用
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 优先级为0，数字越大，优先级越低
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
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        // 跨域请求的源地址，设置为允许源地址跨域
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        // 是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
        response.setHeader("Access-Control-Allow-Credentials","true");
        // 自适应所有自定义头
        String headers = request.getHeader("Access-Control-Request-Headers");
        if(StringUtils.isNotBlank(headers)) {
            // 响应预检请求的时候使用.用来指明在实际的请求中,可以使用哪些自定义HTTP请求头
            response.setHeader("Access-Control-Allow-Headers", headers);
            //// 设置浏览器允许访问的服务器的头信息的白名单
            response.setHeader("Access-Control-Expose-Headers", headers);
        }
        // 允许跨域的请求方法类型
        response.setHeader("Access-Control-Allow-Methods", "*");
        // 预请求的结果的有效期,预检命令（OPTIONS）缓存时间，单位：秒
        response.setHeader("Access-Control-Max-Age", "3600");
        //不再路由
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        return null;
    }
}
