package com.loan.app.filter;

import com.loan.app.utils.HolderUtils;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright (C), 2011-2015 ÎÂÖÝ´û
 * FileName: com.loan.app.filter.HolderFilter.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 17:50
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   17:50    1.0          Create
 */
public class HolderFilter implements Filter {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(HolderFilter.class);
    public static final String RESPONSE_KEY="HTTP_SERVLET_RESPONSE";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse servletResponse = response instanceof AutoResetHttpServletResponse
                ? (HttpServletResponse) response
                :new AutoResetHttpServletResponse((HttpServletResponse)response);
        try {
            request.setAttribute(RESPONSE_KEY,servletResponse);
            HolderUtils.setRequest((HttpServletRequest) request);
            HolderUtils.setResponse(servletResponse);
        }catch (Exception e){
            if( logger.isWarnEnabled() ){
                logger.warn(e.getMessage(),e);
            }
        }finally {
            chain.doFilter( request,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private class AutoResetHttpServletResponse extends HttpServletResponseWrapper {

        private ServletOutputStream outputStream;
        private PrintWriter printWriter;

        public AutoResetHttpServletResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            try{
                if( null == this.outputStream )
                    this.outputStream = super.getOutputStream();
            }catch (IOException e){
                this.flushBuffer();
                this.reset();
                this.outputStream = super.getOutputStream();
            }
            return this.outputStream;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            try{
                if( null == this.printWriter )
                    this.printWriter = super.getWriter();
            }catch (IOException e){
                this.flushBuffer();
                this.reset();
                this.printWriter = super.getWriter();
            }
            return this.printWriter;
        }
    }
}
