package la.webbook.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.webbook.dao.DBManager;
import la.webbook.dao.MemberDao;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member")
public class MemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		request.setAttribute("system_title", Constant.SYSTEM_TITLE);
		request.setAttribute("action", action);

		// 検索条件入力画面を初期表示
		if (action.equals("index")) {

			request.setAttribute("title", Constant.CONTENT_TITLE_MEMBER_SEARCH);
			RequestDispatcher dp = request.getRequestDispatcher("/member/search.jsp");
			dp.forward(request, response);
		// 検索ボタン押下
		}else if(action.equals("search")) {

			search(request, response);
		}
	}

	/**
	 * 会員検索
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<MemberBean> list = null;

		Connection connection = null;
		try {

			//	3.	アクターは会員を「ID」「名前」「住所」「電話番号」「メールアドレス」で絞り込むためのキーワード
			String keyword = request.getParameter("keyword");
			// 要注意会員で絞り込むかどうかを選択
			String onlyNeedAttention = request.getParameter("onlyNeedAttention");

			connection = DBManager.getConnection();
			MemberDao memberDao = new MemberDao(connection);

			if ((keyword != null && !keyword.isEmpty()) || (onlyNeedAttention != null && !onlyNeedAttention.isEmpty())) {
				boolean bOnlyNeedAttention = "true".equals(onlyNeedAttention) ? true : false;

				list = memberDao.findBy(keyword, bOnlyNeedAttention);
			}else {

				list = memberDao.findAll();
			}

			// 検索結果が１件以上ある場合
			if (!list.isEmpty()) {

				request.setAttribute("action", "search");
				request.setAttribute("content_title", Constant.CONTENT_TITLE_MEMBER_SEARCH_RESULT);
				request.setAttribute("list", list);
			// 入力条件に該当する会員がひとりもいなかった
			}else {

				// システムは該当するユーザが存在しない旨を伝えるメッセージとともに、検索条件入力画面を再表示する
				request.setAttribute("action", "index");
				request.setAttribute("content_title", Constant.CONTENT_TITLE_MEMBER_SEARCH);
				request.setAttribute("message", "該当するユーザが存在しませんでした。");
			}

			RequestDispatcher dp = request.getRequestDispatcher("/member/search.jsp");
			dp.forward(request, response);
		} catch (DataAccessException e) {

			e.printStackTrace();
			request.setAttribute("message", e.getMessage());

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		基本系列
//		1.	メニューから「会員の登録」を選択すると、このユースケースが開始される（E-1, E-2)
//		2.	システムは会員情報を入力する画面を表示する
//		3.	アクターは登録する会員の、名前、住所、電話番号、メールアドレス、生年月日を入力し、「確認画面へ」ボタンを押す（E-3）
//		4.	システムは登録情報の確認画面を表示する
//		5.	アクターは「登録する」ボタンを押す
//		6.	システムは会員を登録してランダムなパスワードを発行し、会員登録完了画面を表示する
//
//		例外系列
//		E-1：　アクターがログインしていなかった
//		1.	システムは、「ログインする」ユースケースを起動する
//
//		E-2：　アクターが図書館職員ではなく一般利用者としてログインしていた
//		1.	システムは、その機能を利用する権限がない旨を伝える画面を表示する
//
//		E-3：　アクターが次の入力チェック条件を満たさずに「確認画面へ」ボタンを押した
//			「名前」は必須、50文字以下
//			「住所」は必須、200文字以下
//			「電話番号」は必須、20文字以下
//			「メールアドレス」は、50文字以下、メールアドレスとして正しいフォーマットであること、同じメールアドレスが登録されていないこと
//		1.	システムは、入力が正しくない旨を伝えるメッセージとともに、会員情報入力画面を再表示する


	}

}
