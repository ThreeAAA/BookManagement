package dataBase.operation;

import unit.MD5;
import dataBase.connection.ConnectionDataBase;

import java.io.IOException;
import java.sql.SQLException;

public class Insert extends ConnectionDataBase {
    /**
     * 向数据库中插入数据
     */

    public Insert() throws IOException, SQLException{
            this.connect();

    }

    /**
     *  向书籍信息表中插入数据
     *  插入书籍信息列表，依次是：图书编号，书名，作者，出版社，分类，总书量，现存书量
     */
    public boolean setBookInfo(String bookId, String title, String athor, String press, String category, String total, String surplus) throws SQLException {
        String time = DateUtil.getNowDay();
        String SQL = "INSERT INTO `books` (`book_id`, `title`, `author`, `publisher`, `category`, `put_in_time`, `total`, `surplus`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        return insert(SQL, bookId, title, athor,press,category,time,total,surplus);
    }
    public boolean setBookInfo(String bookId, String title, String athor, String press, String category, String putTime, String total, String surplus) throws SQLException {

        String SQL = "INSERT INTO `books` (`book_id`, `title`, `author`, `publisher`, `category`, `put_in_time`, `total`, `surplus`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        return insert(SQL, bookId, title, athor,press,category,putTime,total,surplus);
    }

    /**
     * 插入借阅信息
     * 输入参数分别是：读者证号，图书号，返还时间，借出时间
     */
    public boolean setBorrowingInfo(String readId, String bookId, String returnTime, String lendTime){

        String SQL = "INSERT INTO `borrow` (`reader_id`, `book_id`, `return_time`, `lend_time`) VALUES (?, ?, ?, ?);";
        return insert(SQL,readId, bookId, returnTime, lendTime);
    }

    /**
     * 插入账号和密码
     */
    public boolean setUserPsd(String userId, String password){

        String SQL = "INSERT INTO users (user_id, password) VALUES (?, ?);";
        password = MD5.md5(password);
        return insert(SQL, userId, password);
    }

    /**
     * 插入用户信息
     * id，姓名，性别
     * @param userId
     * @param name
     * @param sex
     * @return
     */
    public boolean setUserInfo(String userId, String name, String sex){
        String SQL = "INSERT INTO `readers` (`reader_id`, `name`, `sex`) VALUES (?, ?, ?)";
        return insert(SQL, userId,name,sex);
    }

    /**
     * 插入用户信息，包含借阅书数
     * @param userId
     * @param name
     * @param sex
     * @param number1
     * @param number2
     * @return
     */
    public boolean setUserInfo(String userId,String name,String sex,String number1, String number2){
        String SQL = "INSERT INTO `readers` (`reader_id`, `name`, `sex`, `surplus_book`, `loan_book`) VALUES (?, ?, ?, ?, ?)";
        return insert(SQL, userId,name,sex,number1,number2);
    }
    /**
     * 添加还书信息
     * @param readerId 书籍id
     * @param bookId 读者id
     * @param date 还书时间
     * @return
     */
    public boolean setReturnInfo(String readerId, String bookId, String date){
        String SQL = "INSERT INTO `return` (`reader_id`, `book_id`, `return_time`) VALUES (?, ?, ?)";
        return insert(SQL, readerId, bookId, date);
    }


    /**
     * 传入SQL语句和参数值，插入数据库
     */
    protected boolean insert(String SQL, String... value){

        return super.excuteSQL(SQL,value);
    }



}
