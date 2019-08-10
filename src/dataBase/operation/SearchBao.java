package dataBase.operation;

import dataBase.connection.ConnectionDataBase;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 实现页面上的搜索功能
 */
public class SearchBao extends ConnectionDataBase {
    private Statement statement = null;
    private ResultSet resultSet = null;

    public SearchBao(){
        try {
            this.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查找作者
     * @param author
     * @return
     */
    public ResultSet searchAuthor(String author){
        String SQL = "select * from books where author like ?;";
        return search(SQL, "%"+author+"%");
    }

    /**
     * 查书名
     * @param title
     * @return
     */
    public ResultSet searchTitle(String title){
        String SQL = "select * from books where title like ?;";
        return search(SQL, "%"+title+"%");
    }

    /**
     * 搜索出版社
     * @param publisher
     * @return
     */
    public ResultSet searchPublisher(String publisher){
        String SQL = "select * from books where Publisher like ?;";
        return search(SQL, "%"+publisher+"%");
    }

    /**
     * 搜索读者
     * @param name
     * @return
     */
    public ResultSet searchName(String name){
        String SQL = "select * from readers where name like ?;";
        return search(SQL, "%"+name+"%");
    }

    /**
     * 搜索读者id
     * @param id
     * @return
     */
    public ResultSet searchReaderId(String id){
        String SQL = "select * from readers where reader_id like ?;";
        return search(SQL, "%"+id+"%");
    }

    private ResultSet search(String SQL, String... values){
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
}
