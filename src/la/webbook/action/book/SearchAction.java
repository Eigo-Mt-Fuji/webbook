package la.webbook.action.book;

import la.webbook.action.AbstractAction;
import la.webbook.dao.BookDao;
import la.webbook.entity.BookInfoBean;
import la.webbook.exception.DataAccessException;
import la.webbook.util.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

public class SearchAction extends AbstractAction {

    protected String run(HttpServletRequest request, HttpServletResponse response, Connection connection) throws DataAccessException {

        List<BookInfoBean> list = null;

        BookDao bookDao = new BookDao(connection);
        list = bookDao.findAll();

        // 検索結果が１件以上ある場合
        if (!list.isEmpty()) {

            request.setAttribute("action", "search");
            request.setAttribute("content_title", Constant.CONTENT_TITLE_BOOK_SEARCH_RESULT);
            request.setAttribute("list", list);
        } else {

            request.setAttribute("action", "index");
            request.setAttribute("content_title", Constant.CONTENT_TITLE_BOOK_SEARCH);
            request.setAttribute("message", Constant.ERROR_MESSAGE_NODATA);
        }

        return "/book/search.jsp";
    }
}
