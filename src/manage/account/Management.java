package manage.account;

import dataBase.operation.Insert;
import dataBase.operation.Query;
import unit.JSONDate;
import unit.MD5;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 控制账户的登陆注册
 */
public class Management implements IManagement{
    /**
     * 登录操作
     * @param usernumber
     * @param password
     * @return
     */
    @Override
    public String logins(String usernumber, String password){
        JSONDate jsonDate = new JSONDate();
        String msg = "-1";  //标记跳转页面
        boolean success = false;  //标记验证情况
        //1，数据传入数据库验证
        Query q;
        try {
            q = new Query(); //主要是连接数据库时错误
        } catch (IOException | SQLException e) {
            //此处应在日志里记录下错误
            jsonDate.put("success", false);
            jsonDate.put("msg",msg);
            return jsonDate.toString();
        }
        ResultSet resultSet = q.querUsePassInfo(usernumber, MD5.md5(password));
        //2,判断结果
        try {
            if (resultSet.next()){
                success = true;
               if(resultSet.getInt(3) == 1){
                    msg = "2"; //管理员端
               }else {
                   msg = "1";  //读者端
               }
            }
        } catch (SQLException ignored) { //判断管理员标志时候可能出错
            success = false;
        }
        //3，返回结果填入json中
        jsonDate.put("success",success);
        jsonDate.put("msg",msg);
        return jsonDate.toString();
    }

    /**
     * 注册操作
     * @param id
     * @param name
     * @param sex
     * @param password
     * @return
     */
    @Override
    public String registered(String id, String name, String sex, String password) {
        String msg = "";
        boolean success = true;
        JSONDate jsonDate = new JSONDate();
        Insert insert;
        try {
            insert = new Insert();
        } catch (IOException | SQLException e) {
            msg = "服务器异常！";
            jsonDate.put("msg",msg);
            jsonDate.put("success", false);
            return jsonDate.toString();
        }
        //1，验证信息正确性
        String regex = "\\d{5,10}";
        if (!id.matches(regex)){
            success = false;
            msg = "账号格式错误";
        }
        if (password.length()>20 || password.length()<6){
            success = false;
            if (msg.length()>0){
                msg += "，密码格式错误";
            }else{
                msg = "密码格式有误";
            }

        }
        //2，写入数据库:检测用户存在否-》写入
        if (success && !insert.setUserPsd(id,password)){
            success = false;
            msg = "服务器异常，请联系管理员";
        }
        if (success && !insert.setUserInfo(id,name,sex)){
            // 此处应清除插入的注册密码信息
            success = false;
            msg = "服务器异常，请联系管理员";
        }
        //3，返回成功信息/失败信息
        jsonDate.put("msg",msg);
        jsonDate.put("success",success);
        return jsonDate.toString();
    }

}
