package dataBase.operation;

import dataBase.connection.ConnectionDataBase;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateUtil extends ConnectionDataBase {

    public UpdateUtil() throws SQLException, IOException {
        this.connect();
    }

    /**
     * 修改图书
     * @return
     */
    public boolean updateBookInfo(String bookId, String title, String author, String press, String category, String time,String total, String surplus){
        String SQL = "UPDATE `books` SET `title`= ?, `author`=?, `publisher`=?, `category`=?, `put_in_time`=?,`total`=?, `surplus`=? WHERE (`book_id`=?);";
        return update(SQL, title, author,press,category,time,total,surplus, bookId);
    }

    /**
     * 修改用户
     */
    public boolean updateUserInfo(String id, String name, String sex, String canBorrowNumber, String borrowedNumber){
        String SQL = "UPDATE `readers` SET `name`=?, `sex`=?, `surplus_book`=?, `loan_book`=? WHERE (`reader_id`=?);";
        return update(SQL, name, sex,canBorrowNumber,borrowedNumber,id);
    }

    /**
     * 修改密码
     * @return
     */
    public boolean updateUserPsd(String id, String password){
        String SQL = "UPDATE `users` SET `password`=? WHERE (`user_id`=?);";
        return update(SQL, password, id);
    }

    private boolean update(String SQL, String... values){
        return super.excuteSQL(SQL, values);
    }
}
