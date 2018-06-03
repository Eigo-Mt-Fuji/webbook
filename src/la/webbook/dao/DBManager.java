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
			final String DB_HOST = System.getProperty("db_host");
			final String DB_PORT = System.getProperty("db_port");
			final String DB_NAME = System.getProperty("db_name");
			final String DB_USER = System.getProperty("db_user");
			final String DB_PASSWORD = System.getProperty("db_password");

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

			c = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);

			return c;
		} catch (Exception e) {
			e.printStackTrace();

			throw new DataAccessException("データベース接続異常", e);
		}
	}

}
