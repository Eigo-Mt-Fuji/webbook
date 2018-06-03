package la.webbook.action.member;

import la.webbook.action.AbstractAction;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class NewAction extends AbstractAction{

    @Override
    protected String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException, SQLException {
        MemberBean memberBean = new MemberBean();
        memberBean.setUserId(-1);
        memberBean.setUserPassword("himitu");
        memberBean.setUserRole("1");
        request.setAttribute("content", memberBean);
        request.setAttribute("content_title", Constant.CONTENT_TITLE_MEMBER_NEW);
        return "/member/new.jsp";
    }
}
