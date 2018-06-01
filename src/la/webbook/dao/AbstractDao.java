package la.webbook.dao;

import java.sql.Connection;

import la.webbook.exception.DataAccessException;

/**
 * @author user
 */
public abstract class AbstractDao {

	private Connection connection;

	public AbstractDao(Connection connection) throws DataAccessException {

		this.setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
