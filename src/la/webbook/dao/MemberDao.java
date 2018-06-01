/**
 *
 */
package la.webbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;

/**
 * @author user
 *
 */
public class MemberDao extends AbstractDao {

	/**
	 * @throws DataAccessException
	 */
	public MemberDao(Connection connection) throws DataAccessException {
		super(connection);
	}

	private MemberBean createBean(ResultSet resultSet) throws SQLException {

		MemberBean bean = new MemberBean(
			resultSet.getInt("user_id"),
			resultSet.getString("user_family_name"),
			resultSet.getString("user_name"),
			resultSet.getString("user_postal"),
			resultSet.getString("user_address"),
			resultSet.getString("user_tel"),
			resultSet.getString("user_email"),
			resultSet.getDate("user_birthday"),
			resultSet.getString("user_role")
		);

		return bean;
	}

	/**
	 * findAll
	 * @return
	 * @throws DataAccessException
	 */
	public MemberBean login(String email, String password) throws DataAccessException {

		Connection conn = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String sql = "select * from member where user_email = ? AND user_password = ?";
		try {
			MemberBean bean = null;

			statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				bean = this.createBean(resultSet);
				break;
			}

			return bean;
		} catch (SQLException e) {

			throw new DataAccessException("ログインエラー", e);
		}finally {

			try {
				if(resultSet != null) {

					resultSet.close();
				}
				if (statement != null) {

					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * findAll
	 * @return
	 * @throws DataAccessException
	 */
	public List<MemberBean> findAll() throws DataAccessException {

		List<MemberBean> list = new ArrayList<MemberBean>();

		Connection conn = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String sql = "select * from member order by user_id asc";
		try {

			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while(resultSet.next()) {
				MemberBean bean = this.createBean(resultSet);
				list.add(bean);
			}

			return list;
		} catch (SQLException e) {

			throw new DataAccessException("会員情報検索エラー", e);
		}finally {

			try {
				if(resultSet != null) {

					resultSet.close();
				}
				if (statement != null) {

					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *findBy
	 * @param keyword
	 * @param bOnlyNeedAttention
	 * @return
	 * @throws DataAccessException
	 */
	public List<MemberBean> findBy(String keyword, boolean bOnlyNeedAttention) throws DataAccessException {
		List<MemberBean> list = new ArrayList<MemberBean>();

		Connection conn = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			String whereClause = "";

			if (keyword != null && !keyword.isEmpty()) {

				whereClause = " (user_name LIKE ? OR user_family_name LIKE ? OR user_address LIKE ? OR user_email LIKE ? OR user_tel LIKE ? user_role LIKE ? OR user_birthday LIKE ? OR user_postal LIKE ?)";

			}

			// 要注意ユーザのみ表示のチェックが入っている場合
			if(bOnlyNeedAttention) {

				if(!whereClause.isEmpty()) {

					whereClause =  whereClause + " AND ";
				}
				whereClause
					= whereClause + " EXISTS (SELECT * FROM rental r WHERE r.user_id = m.user_id AND r.rental_return IS NULL AND r.rental_rent < now() - interval '10 days')";
			}

			String sql = String.format("SELECT * FROM member m WHERE %s ORDER BY user_id ASC", whereClause);

			statement = conn.prepareStatement(sql);
			if (keyword != null && !keyword.isEmpty()) {

				for(int i = 0; i < 8; i++) {
					statement.setString(i + 1,  "%" + keyword + "%");
				}
			}

			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				MemberBean bean = this.createBean(resultSet);
				list.add(bean);
			}

			return list;
		} catch (SQLException e) {

			throw new DataAccessException("会員情報検索エラー", e);
		}finally {

			try {

				if(resultSet != null) {

					resultSet.close();
				}
				if (statement != null) {

					statement.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
