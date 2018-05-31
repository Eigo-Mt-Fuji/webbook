package la.webbook.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.webbook.entity.MemberBean;

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


		// 検索条件入力画面を初期表示
		if (action.equals("index")) {
			//		1.	メニューから「会員の検索」を選択すると、このユースケースが開始される（E-1, E-2)
			//		2.	システムは会員の検索条件を入力する画面を表示する
			request.setAttribute("title", "会員検索");
			RequestDispatcher dp = request.getRequestDispatcher("/member/search.jsp");
			dp.forward(request, response);
		// 検索ボタン押下
		}else if(action.equals("search")) {

			//	3.	アクターは会員を「ID」「名前」「住所」「電話番号」「メールアドレス」で絞り込むためのキーワード
			String keyword = request.getParameter("keyword");
			// 要注意会員で絞り込むかどうかを選択
			boolean onlyNeedAttention = Boolean.parseBoolean(request.getParameter("onlyNeedAttention"));

			ArrayList<MemberBean> list = new ArrayList<MemberBean>();
			MemberBean bean  = new MemberBean() ;
			bean.setUserId(1);
			bean.setUserPostal("999-9999");
			bean.setUserRole("People");
			bean.setUserTel("0120-117-117");
			bean.setUserAddress("Miyamasuzaka 1-1-1, Shibuya, Tokyo");
			bean.setUserBirthday(new Date(System.currentTimeMillis()));
			bean.setUserName("Eigo");
			bean.setUserFamilyName("Fujikawa");
			bean.setUserPassword("himitu");
			list.add(bean);
//			4.	システムは入力条件に該当する会員の一覧を表示する（S-1, E-3）
			request.setAttribute("title", "会員検索結果");
			request.setAttribute("list", list);

		}




//
//		代替系列
//
//		S-1：　入力条件がひとつも入力されていなかった
//		1.	システムは、会員を全件検索し、一覧を表示する
//
//		例外系列
//		E-1：　アクターがログインしていなかった
//		2.	システムは、「ログインする」ユースケースを起動する
//
//		E-2：　アクターが図書館職員ではなく一般利用者としてログインしていた
//		1.	システムは、その機能を利用する権限がない旨を伝える画面を表示する
//
//		E-3：　入力条件に該当する会員がひとりもいなかった
//		1.	システムは該当するユーザが存在しない旨を伝えるメッセージとともに、検索条件入力画面を再表示する
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
