package la.webbook.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.webbook.dao.DBManager;
import la.webbook.dao.MemberDao;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

/**
 * Servlet implementation class SessionController
 */
@WebServlet("/session")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if( action.equals("login")) {

			login(request, response);
		}else if(action.equals("logout")) {

			logout(request, response);
		}else {

			RequestDispatcher dp = request.getRequestDispatcher("/login.jsp");
			dp.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションにログイン情報を設定
		HttpSession session = request.getSession();

		session.setAttribute("member", null);
		request.setAttribute("system_title", la.webbook.util.Constant.SYSTEM_TITLE);
		request.setAttribute("content_title", la.webbook.util.Constant.CONTENT_TITLE_LOGIN);

		RequestDispatcher dp = request.getRequestDispatcher("/login.jsp");
		dp.forward(request, response);
	}

	/**
	 * ログイン
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection connection = null;
		try {

			String email =  request.getParameter("user_email");
			String password =  request.getParameter("user_password");

			connection = DBManager.getConnection();
			MemberDao memberDao = new MemberDao(connection);

			MemberBean memberBean = memberDao.login(email, password);

			if (memberBean != null) {

				// セッションにログイン情報を設定
				HttpSession session = request.getSession();
				session.setAttribute("member", memberBean);

				response.sendRedirect(this.getServletContext().getContextPath() + "/index");
			}else {

				// システムは該当するユーザが存在しない旨を伝えるメッセージとともに、検索条件入力画面を再表示する
				request.setAttribute("content_title", Constant.CONTENT_TITLE_LOGIN);
				request.setAttribute("message", "ログイン失敗");

				RequestDispatcher dp = request.getRequestDispatcher("/login.jsp");
				dp.forward(request, response);
			}
		} catch (DataAccessException e) {

			e.printStackTrace();
			request.setAttribute("message", e.getMessage());

			RequestDispatcher dp = request.getRequestDispatcher("/common/error.jsp");
			dp.forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("message", "エラー");

			RequestDispatcher dp = request.getRequestDispatcher("/common/error.jsp");
			dp.forward(request, response);
		}finally {

			if (connection != null) {

				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ログアウト
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// セッション初期化
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect(this.getServletContext().getContextPath() + "/session");
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("message", "エラー");

			RequestDispatcher dp = request.getRequestDispatcher("/common/error.jsp");
			dp.forward(request, response);
		}
	}

}
