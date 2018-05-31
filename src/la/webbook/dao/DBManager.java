/**
 *
 */
package la.webbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import la.webbook.exception.DataAccessException;

/**
 * @author user
 *
 */
public class DBManager {

	private static final String DB_HOST = "localhost";
	private static final int DB_PORT = 3306;
	private static final String DB_NAME = "webbook";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "postgres";


	public DBManager() {


	}

	/**
	 * データベースに接続
	 * @return
	 * @throws DataAccessException
	 */
	public static Connection getConnection() throws DataAccessException {
		Connection c = null;

		try {

			Class.forName("org.postgresql.Driver");

			String url
			  = "jdbc:postgresql:" + DBManager.DB_NAME;

			c = DriverManager.getConnection(url, DBManager.DB_USER, DBManager.DB_PASSWORD);

			return c;
		} catch (Exception e) {
			e.printStackTrace();

			throw new DataAccessException("データベース接続異常", e);
		}
	}

}
