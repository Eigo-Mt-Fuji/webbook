package la.webbook.action.session;

import la.webbook.action.AbstractAction;
import la.webbook.dao.DBManager;
import la.webbook.dao.MemberDao;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class LoginAction extends AbstractAction {

    @Override
    protected String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException {

        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");

        MemberDao memberDao = new MemberDao(connection);

        MemberBean memberBean = memberDao.login(email, password);

        if (memberBean == null) {

            request.setAttribute("content_title", Constant.CONTENT_TITLE_LOGIN);
            request.setAttribute("message", "ログイン失敗");

            return "/login.jsp";
        }

        // セッションにログイン情報を設定
        HttpSession session = request.getSession();
        session.setAttribute(Constant.SESSION_ATTRIBUTE_MEMBER, memberBean);

        return "/index";
    }
}
