package dataBase;

import dataBase.connection.ConnectionDataBase;
import dataBase.operation.Delete;
import dataBase.operation.Insert;
import dataBase.operation.Query;
import dataBase.operation.Unit;
import unit.MD5;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    //数据库连接测试
    @org.junit.Test
    public void test() throws Exception{
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        connectionDataBase.connect();
        connectionDataBase.close();
    }

    //md5测试
    @org.junit.Test
    public void md5(){
        System.out.println(MD5.md5("啊啊不不不"));
    }

    @org.junit.Test
    public void insert() throws IOException, SQLException {
        Insert i = new Insert();
        i.setBookInfo(null,"数值分析","Tom","新华社","理科","5","6");
        i.close();
    }

    @org.junit.Test
    public void delete() throws Exception{
        Delete d = new Delete();
        d.deleteBook("");
    }

    @org.junit.Test
    public void query() throws Exception{
        Query q  = new Query();
//        ResultSet r = q.queryBookId("11");
        ResultSet r = q.queryAllBook();

        while (r.next()){
            System.out.println(r.getInt(1)+"---"+r.getString(2));
        }

    }

    @org.junit.Test
    public void quchong() throws Exception{
//        Unit.quchong();
        Unit.inOO();
    }

    @org.junit.Test
    public void insertUser() throws Exception{
        String password = MD5.md5("00000000");
        String user_id = "Admin";
        String admin_mark = "1";

    }
}
