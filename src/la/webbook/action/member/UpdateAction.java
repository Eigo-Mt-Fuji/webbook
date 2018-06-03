package la.webbook.action.member;

import la.webbook.action.AbstractAction;
import la.webbook.dao.MemberDao;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UpdateAction extends AbstractAction {
    @Override
    protected String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException, SQLException {


        try {
            MemberDao memberDao = new MemberDao(connection);

            String userId = request.getParameter("user_id");

            String userFamilyName = request.getParameter("user_family_name");
            String userName = request.getParameter("user_name");
            String userPostal = request.getParameter("user_postal");
            String userAddress = request.getParameter("user_address");
            String userEmail = request.getParameter("user_email");
            String userTel = request.getParameter("user_tel");
            String userBirthday = request.getParameter("user_birthday");
            String userPassword = request.getParameter("user_password");
            String userRole = request.getParameter("user_role");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedUserBirthday = null;

            parsedUserBirthday = format.parse(userBirthday);
            MemberBean bean = new MemberBean(
                Integer.parseInt(userId),
                userFamilyName,
                userName,
                userPostal,
                userAddress,
                userEmail,
                userTel,
                new Date(parsedUserBirthday.getTime()),
                userRole,
                userPassword
            );
            memberDao.update(bean);

            request.setAttribute("action", "search");
            request.setAttribute("content_title", Constant.CONTENT_TITLE_MEMBER_SEARCH_RESULT);
            request.setAttribute("message", "ユーザ" + userFamilyName + " " + userName + "を更新しました");

            connection.commit();

            return "/member/search.jsp";
        } catch (ParseException e) {
            request.setAttribute("message", "パラメータエラー");
            return "/error";
        }
    }
}
