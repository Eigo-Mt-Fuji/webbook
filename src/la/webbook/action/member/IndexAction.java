package la.webbook.action.member;

import la.webbook.action.AbstractAction;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class IndexAction extends AbstractAction {

    @Override
    public String run(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
        req.setAttribute("title", Constant.CONTENT_TITLE_MEMBER_SEARCH);
        return "/member/search.jsp";
    }
}
