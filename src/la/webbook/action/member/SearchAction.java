package la.webbook.action.member;

import la.webbook.action.AbstractAction;
import la.webbook.dao.DBManager;
import la.webbook.dao.MemberDao;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchAction extends AbstractAction {

    /**
     * run
     * @param request
     * @param response
     * @return
     */
    protected String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException {

        List<MemberBean> list = null;

        //	3.	アクターは会員を「ID」「名前」「住所」「電話番号」「メールアドレス」で絞り込むためのキーワード
        String keyword = request.getParameter("keyword");
        // 要注意会員で絞り込むかどうかを選択
        String onlyNeedAttention = request.getParameter("onlyNeedAttention");

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

        return "/member/search.jsp";
    }
}
