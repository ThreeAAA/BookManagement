package dataBase.operation;

import dataBase.connection.ConnectionDataBase;
import java.io.IOException;
import java.sql.SQLException;

public class Delete extends ConnectionDataBase {

    public Delete() throws SQLException, IOException {
        this.connect();
    }

    /**
     * 删除指定书籍
     */
    public boolean deleteBook(String id) throws SQLException{

        String SQL = "DELETE FROM `books` WHERE (`book_id`= ? );";  //删书
        return delete(SQL, id);
    }

    public boolean deleteReturnInfo(String readerId, String bookId){
        String SQL = "DELETE FROM `return` WHERE (reader_id=? and book_id= ?);";
        return delete(SQL, readerId, bookId);
    }

    public boolean deleteBorrowInfo(String readerId, String bookId){
        String SQL = "DELETE FROM `borrow` WHERE (reader_id=? and book_id = ?);";
        return delete(SQL, readerId, bookId);
    }

    protected boolean delete(String SQL){
        return super.excuteSQL(SQL);
    }

    protected boolean delete(String SQL, String... values){
       return super.excuteSQL(SQL, values);
    }

}
