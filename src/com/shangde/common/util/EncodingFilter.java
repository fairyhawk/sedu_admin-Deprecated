package com.shangde.common.util;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: guoqiang.liu
 * Date: 2009-3-14
 * Time: 10:47:15
 * To change this template use File | Settings | File Templates.
 */
public class EncodingFilter implements Filter {
    public FilterConfig filterConfig = null;

    public String encoding = "utf-8";

    public void destroy() {
        filterConfig = null;
        this.encoding = null;
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        arg0.setCharacterEncoding(this.encoding);
        arg2.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig = arg0;
        if (null != arg0 && null != arg0.getInitParameter("encoding")) {
            this.encoding = arg0.getInitParameter("encoding");
        }

    }
}
