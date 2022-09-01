//package org.example.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class SimpleCorsFilter implements Filter {
//    private final Logger log= LoggerFactory.getLogger(SimpleCorsFilter.class);
//
//    public SimpleCorsFilter(){
//        log.info("SimpleCORSFilter init");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request=(HttpServletRequest) servletRequest;
//        HttpServletResponse response=(HttpServletResponse) servletResponse;
//        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//        response.setHeader("Allow-Control-Allow-Credentials","true");
//        response.setHeader("Access-Control-Allow-Methods","GET,POST,DELETE");
//        response.setHeader("Access-Control-Allow-Headers","origin, content-type, accept,authorization");
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//
//
//}
