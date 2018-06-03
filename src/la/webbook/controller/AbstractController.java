package la.webbook.controller;

import la.webbook.action.AbstractAction;
import la.webbook.util.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/index", "/member", "/book"})
public class AbstractController extends HttpServlet implements Filter {

    /**
     * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse resp)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.processAction(req, resp);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse resp)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processAction(req, resp);
    }

    protected void processAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = null;
        try {

            action = req.getParameter("action");
            req.setAttribute("system_title", Constant.SYSTEM_TITLE);
            req.setAttribute("action", action);

            String controllerPackageName = this.getClass().getPackage().getName();

            String actionName = String.format(
                    "%s.action.%s.%s%sAction",
                    controllerPackageName.substring(0, controllerPackageName.lastIndexOf(".")),
                    req.getServletPath().substring(1).toLowerCase(),
                    action.toUpperCase().substring(0, 1),
                    action.toLowerCase().substring(1)
            );
            Class<? extends AbstractAction> c
                    = (Class<? extends AbstractAction>) Class.forName(actionName);
            AbstractAction actionInstance = c.newInstance();
            String page = actionInstance.run(req, resp);

            this.render(req, resp, page);
        } catch (Exception e) {

            req.setAttribute("message", action + "は不正なactionです");
            render(req, resp, "/common/error.jsp");
        }

    }
    /**
     * render
     * @param request
     * @param res
     * @param page
     * @throws ServletException
     * @throws IOException
     */
    protected void render(ServletRequest request, ServletResponse res, String page) throws ServletException, IOException {

        request.getRequestDispatcher(page.endsWith(".jsp") ? ("/pages" + page) : page).forward(request, res);
    }

    @Override
    public void doFilter(
            ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        if (!(req instanceof HttpServletRequest)) {
            chain.doFilter(req, resp);
            return ;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        String contextPath = httpRequest.getContextPath();
        String url = httpRequest.getRequestURI().toString();

        if (!url.startsWith(contextPath + "/session") && !url.startsWith(contextPath + "/assets")) {
            HttpSession session = httpRequest.getSession();

            if (session.getAttribute(Constant.SESSION_ATTRIBUTE_MEMBER) == null) {

                httpResponse.sendRedirect(contextPath + "/session");

                return;
            }
        }

        chain.doFilter(req, resp);
    }
    @Override public void init(FilterConfig c) throws ServletException {}
    @Override public void destroy() {}
}

