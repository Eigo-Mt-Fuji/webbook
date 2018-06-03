package la.webbook.controller;

import java.io.IOException;

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
public class IndexController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberBean memberBean = (MemberBean)request.getSession().getAttribute(Constant.SESSION_ATTRIBUTE_MEMBER);
		if (memberBean == null) {

			response.sendRedirect(request.getContextPath() + "/session");
			return ;
		}

		if ("1".equals(memberBean.getUserRole()) ) {

			response.sendRedirect(request.getContextPath() + "/book?action=search");
		}else {

			response.sendRedirect(request.getContextPath() + "/member?action=search");
		}
	}

}
