package la.webbook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.webbook.entity.MemberBean;
import la.webbook.util.Constant;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/index")
public class IndexController extends ApplicationController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberBean memberBean = (MemberBean)request.getSession().getAttribute(Constant.SESSION_ATTRIBUTE_MEMBER);
		if ( memberBean != null && "1".equals(memberBean.getUserRole()) ) {

			response.sendRedirect(request.getContextPath() + "/member?action=search");
		}else {

			request.setAttribute("system_title", Constant.SYSTEM_TITLE);
			request.setAttribute("action", "index");

			RequestDispatcher dp = request.getRequestDispatcher("/index.jsp");
			dp.forward(request, response);
		}

	}

}
