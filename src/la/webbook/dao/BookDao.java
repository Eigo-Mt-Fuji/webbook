package la.webbook.dao;

import la.webbook.entity.BookInfoBean;
import la.webbook.entity.MemberBean;
import la.webbook.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by e_fujikawa on 2018/06/03.
 */
public class BookDao extends AbstractDao {

    public BookDao(Connection connection) throws DataAccessException {
        super(connection);
    }

    public List<BookInfoBean> findAll() throws DataAccessException {
        List<BookInfoBean> list = new ArrayList<BookInfoBean>();

        Connection conn = this.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String sql = "select * from bookinfo order by bookinfo_isbn asc";
        try {

            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                BookInfoBean bean = this.createBean(resultSet);
                list.add(bean);
            }

            return list;
        } catch (SQLException e) {

            throw new DataAccessException("書籍情報検索エラー", e);
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

    private BookInfoBean createBean(ResultSet resultSet) throws SQLException {

        String bookinfoIsbn = resultSet.getString("bookinfo_isbn");
        String categoryCode = resultSet.getString("category_code");
        String publisherCode = resultSet.getString("publisher_code");
        String bookinfoName = resultSet.getString("bookinfo_name");
        String bookinfoAuthor = resultSet.getString("bookinfo_author");

        return new BookInfoBean(
            bookinfoIsbn,
            categoryCode,
            publisherCode,
            bookinfoName,
            bookinfoAuthor
        );
    }
}
