package manage.book;

import dataBase.operation.Delete;
import dataBase.operation.Insert;
import dataBase.operation.Query;
import dataBase.operation.UpdateUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * 实现书籍添加，删除，修改
 */
public class Book {

    /**
     * 添加书籍，只是传不传入时间区别
     */
    public boolean add(String bookId, String title, String athor, String press, String category, String time, String total, String surplus) throws SQLException, IOException {
        if (time.equals("")){  //验证时间格式
            return new Insert().setBookInfo(bookId,title,athor,press,category,total,surplus);
        }
        return new Insert().setBookInfo(bookId,title,athor,press,category,time,total,surplus);
    }

    /**
     * 输入书籍book_id删除该书
     * @param id
     * @return
     */
    public boolean remove(String id) throws IOException,SQLException{
        return new Delete().deleteBook(id);
    }

    /**
     * 修改书籍信息
     * @param bookId
     * @param bookInfo
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public boolean update(String bookId, Map<String, String> bookInfo) throws IOException,SQLException{
        //author，press，category可以为空
        String title = bookInfo.get("title");
        String author = bookInfo.get("author");
        String press = bookInfo.get("press");
        String category = bookInfo.get("category");
        String total = bookInfo.get("total");
        String surplus = bookInfo.get("surplus");
        String time = bookInfo.get("time");
        return new UpdateUtil().updateBookInfo(bookId, title, author,press,category,time,total,surplus);
    }

    /**
     * 获取书籍信息
     * @return
     */
    public ResultSet getInfo() throws IOException, SQLException {
        Query query = new Query();
        return query.queryAllBook();
    }
}
