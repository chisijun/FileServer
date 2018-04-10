/**  
 * @Title: CORSFilter.java
 * @Package com.csun.dryer.config
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2018年1月30日
 */
package org.chisj.file.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: CORSFilter 
 * @Description: 跨域设置
 * @author chisj chisj@foxmail.com
 * @date 2018年1月30日
 */
public class CORSFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
    		FilterChain filterChain) throws IOException, ServletException {

    	HttpServletResponse response = (HttpServletResponse) servletResponse;
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true"); 
        response.setHeader("Access-Control-Allow-Methods", "*");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers",  
                "Origin, X-Requested-With, Content-Type, Accept, authorization");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
