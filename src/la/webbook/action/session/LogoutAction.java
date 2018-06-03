package la.webbook.action.session;

import la.webbook.action.AbstractAction;
import la.webbook.exception.DataAccessException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * Created by e_fujikawa on 2018/06/03.
 */
public class LogoutAction extends AbstractAction {

    @Override
    protected String run(HttpServletRequest req, HttpServletResponse resp, Connection connection) throws DataAccessException {
        // セッション初期化
        HttpSession session = req.getSession();
        session.invalidate();

        return "/index";
    }
}

