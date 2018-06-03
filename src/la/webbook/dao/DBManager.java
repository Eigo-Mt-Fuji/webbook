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

	private static final int DB_PORT = 5432;

	//private static final String DB_HOST = "localhost";
	//private static final String DB_NAME = "webbook";
	//private static final String DB_USER = "postgres";
	//private static final String DB_PASSWORD = "postgres";
	private static final String DB_HOST = "ec2-50-16-196-238.compute-1.amazonaws.com";
	private static final String DB_NAME = "d785h5ni5a4oup";
	private static final String DB_USER = "cbqdrbxajofcwz";
	private static final String DB_PASSWORD = "1c98304fc5bfe93a360f4a74b127684e5dcf3ffcda2c4d556a8efd59809fca6e";

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
			String url = "jdbc:postgresql://" + DBManager.DB_HOST + ":" + DBManager.DB_PORT + "/" + DBManager.DB_NAME;

			c = DriverManager.getConnection(url, DBManager.DB_USER, DBManager.DB_PASSWORD);

			return c;
		} catch (Exception e) {
			e.printStackTrace();

			throw new DataAccessException("データベース接続異常", e);
		}
	}

}
