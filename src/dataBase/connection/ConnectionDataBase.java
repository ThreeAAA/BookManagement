package dataBase.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionDataBase {
    /**
     * 实现数据库的连接和关闭
     */
    private String url = null;
    private String user = null;
    private String password = null;
    private String jdbc_driver = "com.mysql.cj.jdbc.Driver";  //不晓得为什么，使用tomcat要手动加载驱动
    protected static Connection connection = null;
    /**
     * 加载配置文件
     */
    private void getConfig() throws IOException {

        Properties properties = new Properties();
        InputStream confStream = ConnectionDataBase.class.getResource("dataBase.properties").openStream();
        properties.load(confStream);
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }
    /**
     * 创建数据库连接
     */
    public Connection connect() throws SQLException, IOException {

        if (ConnectionDataBase.connection == null){
            if (url == null || user == null || password == null){
                this.getConfig();
            }
            try {
                Class.forName(jdbc_driver);
            } catch (ClassNotFoundException e) {
                System.out.println("驱动加载错误");
                e.printStackTrace();
            }
            ConnectionDataBase.connection = DriverManager.getConnection(url, user, password);
        }
        return ConnectionDataBase.connection;
    }

    /**
     *  关闭数据库连接
     */
    public void close() throws SQLException{

        if (connection != null){
            connection.close();
        }

    }

//    public void close(Statement statement) throws SQLException{
//        if (statement != null){
//            statement.close();
//        }
//    }

    /**
     * 关闭所有连接
     * 关闭connect和statement连接
     * @param statement 相当于查询语句的连接
     * @throws SQLException
     */
    public void closeAll(Statement statement) throws SQLException{

        if (statement != null){
            statement.close();
        }
        this.close();
    }

    public boolean excuteSQL(String SQL, String... values){
        /**
         * 执行sql语句并传入参数
         * 查询不能用这个
         */
        try{
            PreparedStatement pr = connection.prepareStatement(SQL);
            for (int i=0; i<values.length; i++){
                pr.setString(i+1, values[i]);
            }

            long temp =  pr.executeUpdate();
            pr.close();
            return temp>0;
        }catch (SQLException e){
            return false;  //插入数据格式或数据类型或sql语句不对
        }
    }

    public boolean excuteSQL(String SQL){
        try{
            PreparedStatement pr = connection.prepareStatement(SQL);
            long temp =  pr.executeUpdate();
            pr.close();
            return temp>0;
        }catch (SQLException e){
            return false;  //插入数据格式或数据类型或sql语句不对
        }
    }
}
