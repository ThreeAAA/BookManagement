package dataBase.operation;

import dataBase.connection.ConnectionDataBase;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class Query extends ConnectionDataBase {
    /**
     * 对数据库的查询功能
     */
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Query() throws IOException, SQLException {
        this.connect();
    }

    public ResultSet queryBookId(String id){
        String SQL = "select * from books where book_id = ?;";
        return this.query(SQL, id);
    }

    public ResultSet queryReaderId(String id){
        String SQL = "select * from readers where reader_id = ?;";
        return this.query(SQL, id);
    }

    public ResultSet queryReturnInfo(String readId, String bookId){
        String SQL = "select * from `return` where (reader_id=? and  book_id= ?);";
        return this.query(SQL, readId, bookId);
    }

    public ResultSet queryBorrowInfo(String readId, String bookId){
        String SQL = "select * from `borrow` where (reader_id=? and book_id = ?);";
        return this.query(SQL, readId, bookId);
    }
    //查询所有借阅书籍
    public ResultSet queryAllBorrowInfo(String readId){
        String SQL = "select * from `borrow` where (reader_id=?);";
        return this.query(SQL, readId);
    }

    public ResultSet queryAllBook() throws SQLException{
        String SQL = "select * from books;";
        return this.query(SQL);
    }

    public ResultSet queryAllUser() throws SQLException{
        String SQL = "select * from readers;";
        return this.query(SQL);
    }

    //验证账户密码信息
    public ResultSet querUsePassInfo(String useId, String passwordMD5) {
        String SQL = "select * from `users` where(user_id=? and password=?);";
        return this.query(SQL,useId, passwordMD5);
    }

    /**
     * 执行查询语句，且自动关闭
     * @param SQL SQL语句
     * @param values 参数
     * @return  结果集对象或空值
     */
    private ResultSet query(String SQL, String... values){
        try{
            PreparedStatement pr = connection.prepareStatement(SQL);
            for (int i=0; i<values.length; i++){
                pr.setString(i+1, values[i]);
            }

            ResultSet resultSet =  pr.executeQuery();
            setResultSet(resultSet);
            setStatement(pr);
            return resultSet;
        }catch (SQLException e){
            return null; //查找数据格式或数据类型或sql语句不对
        }
    }


    public void setStatement(Statement statement) {
        if (statement == null){
            this.statement = statement;
        }

    }

    public void setResultSet(ResultSet resultSet) {
        if (resultSet == null){
            this.resultSet = resultSet;
        }

    }

    /**
     * 关闭statement连接和之后的连接
     */
    public void closeStatement(){
        try{
            if(this.resultSet != null) {
                resultSet.close();
                resultSet = null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if (this.statement != null){
                statement.close();
                statement = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
