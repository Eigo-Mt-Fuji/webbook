package la.webbook.action.member;

import la.webbook.action.AbstractAction;
import la.webbook.dao.MemberDao;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteAction extends AbstractAction {
    @Override
    protected String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException, SQLException {
        String userId = request.getParameter("user_id");
        request.setAttribute("action", "search");
        MemberDao memberDao = new MemberDao(connection);
        MemberBean memberBean = memberDao.findById(Integer.parseInt(userId));
        request.setAttribute("content_title", Constant.CONTENT_TITLE_MEMBER_SEARCH_RESULT);
        request.setAttribute("message", "ユーザ" + memberBean.getUserFamilyName() + " " + memberBean.getUserName() + "削除しました");
        memberDao.delete(memberBean);
        return "/member/search.jsp";
    }
}
