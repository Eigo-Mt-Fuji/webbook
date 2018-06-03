package la.webbook.action;

import la.webbook.dao.DBManager;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractAction {

    public String run(HttpServletRequest req, HttpServletResponse resp) {

        Connection connection = null;

        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            return this.run(req, resp, connection);
        } catch (DataAccessException e) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


            e.printStackTrace();
            req.setAttribute("message", e.getMessage());

            return "/error";
        } catch (Exception e) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

            e.printStackTrace();
            req.setAttribute("message", Constant.ERROR_MESSAGE);

            return "/error";
        } finally {

            if (connection != null) {

                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected abstract String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException, SQLException;
}
