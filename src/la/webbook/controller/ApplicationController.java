package la.webbook.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class ApplicationController extends HttpServlet implements Filter {

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String applicationContext = httpRequest.getContextPath();
            String url = httpRequest.getRequestURI().toString();

            if (!url.equals(applicationContext) && !url.startsWith(applicationContext + "/session")) {
                HttpSession session = httpRequest.getSession();

                if (session.getAttribute("member") == null) {

                    httpResponse.sendRedirect(applicationContext + "/session");

                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
    @Override public void init(FilterConfig c) throws ServletException {}
    @Override public void destroy() {}
}

