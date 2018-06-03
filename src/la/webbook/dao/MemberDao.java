package la.webbook.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;

public class MemberDao extends AbstractDao {

	public MemberDao(Connection connection) throws DataAccessException {
		super(connection);
	}

	private MemberBean createBean(ResultSet resultSet) throws SQLException {

		return new MemberBean(
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
	}

	public MemberBean login(String email, String password) throws DataAccessException {

		Connection conn = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String sql = "select * from member where user_email = ? AND user_password = ?";

			statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			resultSet.next();

			return this.createBean(resultSet);
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


	public List<MemberBean> findAll() throws DataAccessException {

		List<MemberBean> list = new ArrayList<>();

		Connection conn = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			statement = conn.prepareStatement("select * from member order by user_id asc");
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

	public List<MemberBean> findBy(String keyword, boolean bOnlyNeedAttention) throws DataAccessException {
		List<MemberBean> list = new ArrayList<>();

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

			throw new DataAccessException("DBエラー", e);
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

	public MemberBean findById(int userId) throws DataAccessException {
		Connection conn = this.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			String sql = "select * from member where user_id=?";

			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			resultSet.next();
			return this.createBean(resultSet);
		} catch (SQLException e) {

			throw new DataAccessException("DBエラー", e);
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
	public int update(MemberBean bean) throws DataAccessException {
		Connection conn = this.getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "UPDATE member SET user_family_name=?, user_name=?,user_postal=?,user_address=?,user_email=?,user_tel=?,user_birthday=?,user_role=?,user_password=? WHERE user_id=?";

			statement = conn.prepareStatement(sql);
			statement.setString(1, bean.getUserFamilyName());
			statement.setString(2, bean.getUserName());
			statement.setString(3, bean.getUserPostal());
			statement.setString(4, bean.getUserAddress());
			statement.setString(5, bean.getUserEmail());
			statement.setString(6, bean.getUserTel());
			statement.setDate(7, bean.getUserBirthday());
			statement.setString(8, bean.getUserRole());
			statement.setString(9, bean.getUserPassword());
			statement.setInt(10, bean.getUserId());

			return statement.executeUpdate();
		} catch (SQLException e) {

			throw new DataAccessException("DBエラー", e);
		}finally {

			try {

				if (statement != null) {

					statement.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public int insert(MemberBean bean) throws DataAccessException {

		Connection conn = this.getConnection();
		PreparedStatement statement = null;

		try {

			String sql = "INSERT INTO member(user_family_name,user_name,user_postal,user_address,user_email,user_tel,user_birthday,user_role,user_password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

			statement = conn.prepareStatement(sql);
			statement.setString(1, bean.getUserFamilyName());
			statement.setString(2, bean.getUserName());
			statement.setString(3, bean.getUserPostal());
			statement.setString(4, bean.getUserAddress());
			statement.setString(5, bean.getUserEmail());
			statement.setString(6, bean.getUserTel());
			statement.setDate(7, bean.getUserBirthday());
			statement.setString(8, bean.getUserRole());
			statement.setString(9, bean.getUserPassword());

			return statement.executeUpdate();
		} catch (SQLException e) {

			throw new DataAccessException("DBエラー", e);
		}finally {

			try {

				if (statement != null) {

					statement.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public int delete(MemberBean memberBean) throws DataAccessException {

		Connection conn = this.getConnection();
		PreparedStatement statement = null;

		try {

			String sql = "DELETE FROM member WHERE user_id=?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, memberBean.getUserId());
			return statement.executeUpdate();
		} catch (SQLException e) {

			throw new DataAccessException("DBエラー", e);
		}finally {

			try {

				if (statement != null) {

					statement.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
