package org.soin.core.infrastructure.config;

import org.soin.core.infrastructure.utils.RunTimeTool;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置跨域相关配置
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:29
 **/
@Configuration
public class WebMvcConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        RunTimeTool.printDebug("初始化过滤器....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "5000");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization, Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}

}

