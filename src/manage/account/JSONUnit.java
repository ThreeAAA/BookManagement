package manage.account;

import unit.JSONDate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JSONUnit {
    /**
     * 把书信息转换成JSON
     * @param bookResultSet
     * @return
     * @throws SQLException
     */
    public static JSONDate  getBookJson(ResultSet bookResultSet) throws SQLException {
        JSONDate bookInfo = new JSONDate();
        jjkk(bookInfo,bookResultSet);
        bookInfo.put("setTime", bookResultSet.getString(6));
        bookInfo.put("totalBook", bookResultSet.getString(7));
        bookInfo.put("surplus", bookResultSet.getString(8));
        return bookInfo;
    }

    public static JSONDate getBorrowBookJson(ResultSet bookResultSet, String setTime,String getTime) throws SQLException {
        JSONDate bookInfo = new JSONDate();
        jjkk(bookInfo,bookResultSet);
        bookInfo.put("getTime",getTime);
        bookInfo.put("returnTime",setTime);
        return bookInfo;
    }

    //把重复代码提出来而已
    private static void jjkk(JSONDate bookInfo, ResultSet bookResultSet) throws SQLException {
        bookInfo.put("id", bookResultSet.getString(1));
        bookInfo.put("title", bookResultSet.getString(2));
        bookInfo.put("author", bookResultSet.getString(3));
        bookInfo.put("publisher", bookResultSet.getString(4));
        bookInfo.put("category", bookResultSet.getString(5));
    }

    public static JSONDate getReaderJson(ResultSet readerResultSet) throws SQLException{
        JSONDate readerInfo = new JSONDate();
        readerInfo.put("id",readerResultSet.getString(1));
        readerInfo.put("name",readerResultSet.getString(2));
        readerInfo.put("sex",readerResultSet.getString(3));
        readerInfo.put("surplus_book",readerResultSet.getString(4));
        readerInfo.put("loan_book",readerResultSet.getString(5));
        return readerInfo;
    }
}
