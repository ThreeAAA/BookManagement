package manage.account;

import com.mysql.cj.xdevapi.JsonString;
import dataBase.operation.Insert;
import dataBase.operation.Query;
import dataBase.operation.UpdateUtil;
import manage.book.Book;
import unit.JSONDate;
import unit.MD5;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminOperation {

    private String password = "00000000";

    public static String search(String type, String keywords) {
        return null;
    }

    /**
     * 添加书
     * @return
     */
    public String addBook(String bookId, String bookTitle, String bookAuthor, String bookPublish,
                                 String bookCategoty, String bookTime, String bookAllAmount, String bookBrwAmount) {
        Book book = new Book();
        System.out.println(bookId);
        JSONDate jsonDate = new JSONDate();
        if (bookId.equals("") || bookTitle.equals("") || bookAuthor.equals("") || bookPublish.equals("")
                || bookCategoty.equals("") || bookAllAmount.equals("") || bookBrwAmount.equals("")){
            jsonDate.put("success", false);
            jsonDate.put("msg","数据格式不正确");
            return jsonDate.toString();
        }
        try {  //数据库写入
            if (!book.add(bookId,bookTitle,bookAuthor,bookPublish,bookCategoty,bookTime,bookAllAmount,bookBrwAmount)) {
                throw new SQLException();
            }
        } catch (SQLException | IOException e) {
            jsonDate.put("msg","数据库写入失败，请联系维护人员");
            jsonDate.put("success", false);
            return jsonDate.toString();
        }
        jsonDate.put("msg","完成");
        jsonDate.put("success",true);
        return jsonDate.toString();
    }

    public String modfiyBook(String bookId, String bookTitle, String bookAuthor, String bookPublish,
                             String bookCategoty, String bookTime, String bookAllAmount, String bookBrwAmount){
        Book book = new Book();
        JSONDate jsonDate = new JSONDate();

        //验证数据
        if (bookId.equals("") || bookTitle.equals("") || bookAuthor.equals("") || bookPublish.equals("")
                || bookCategoty.equals("") || bookAllAmount.equals("") || bookBrwAmount.equals("")){
            jsonDate.put("msg","数据格式不正确");
            jsonDate.put("success", false);
            return jsonDate.toString();
        }

        Map<String,String> bookMap = new HashMap<String, String>();
        // title,author,press,category,time,total,surplus 这些key
        bookMap.put("title", bookTitle);
        bookMap.put("author", bookAuthor);
        bookMap.put("press", bookPublish);
        bookMap.put("category", bookCategoty);
        bookMap.put("time", bookTime);
        bookMap.put("total", bookAllAmount);
        bookMap.put("surplus", bookBrwAmount);


        //数据库写入
        try {
            if (!book.update(bookId, bookMap)) {
                throw new SQLException();
            }
        } catch (SQLException | IOException e) {
            jsonDate.put("msg","数据库写入失败，请联系维护人员");
            jsonDate.put("success", false);
            return jsonDate.toString();
        }
        jsonDate.put("msg","完成");
        jsonDate.put("success",true);
        return jsonDate.toString();
    }

    /**
     * 获得所有书籍信息
     * @return
     */
    public String getAllBookInfo(){ //这里应传入管理者信息
        JSONDate jsonDate = new JSONDate();
        ArrayList<JSONDate> bookArray = new ArrayList<>();
        Book book = new Book(); //用来进行书籍列表操作的类
        int rows = 0;
        try {
            ResultSet resultSet = book.getInfo();
            if (!resultSet.next()){
                throw new SQLException();
            }else{ //取出每本书信息，保存到bookArray
                do {
                    JSONDate bookInfo = JSONUnit.getBookJson(resultSet);
//                    bookInfo.put("id", resultSet.getString(1));
//                    bookInfo.put("title", resultSet.getString(2));
//                    bookInfo.put("author", resultSet.getString(3));
//                    bookInfo.put("publisher", resultSet.getString(4));
//                    bookInfo.put("category", resultSet.getString(5));
//                    bookInfo.put("setTime", resultSet.getString(6));
//                    bookInfo.put("totalBook", resultSet.getString(7));
//                    bookInfo.put("surplus", resultSet.getString(8));
                    bookArray.add(bookInfo);
                    rows++;
                }while (resultSet.next());
            }
        } catch (IOException | SQLException e) {
            jsonDate.put("msg","数据库出现问题，请联系维护人员");
            jsonDate.put("success", false);
            return jsonDate.toString();
        }

        jsonDate.put("success",true);
        jsonDate.put("msg","完成");
        jsonDate.put("total",rows);
        for (int i=0;i<rows;i++){
            jsonDate.putList("rows", bookArray.get(i));
        }
        return jsonDate.toString();
    }

    /**
     * 添加用户
     * @return
     */
    public String addUser(String id, String name, String sex, String canBorrowNumber, String borrowedNumber) {
        JSONDate jsonDate = new JSONDate();
        try {
            Insert insert = new Insert();
            if (!insert.setUserInfo(id, name, sex, canBorrowNumber, borrowedNumber)) throw new SQLException();
            if (!insert.setUserPsd(id, password));
        }catch (Exception e){
            jsonDate.put("success", false);
            jsonDate.put("msg","数据库异常");
            return jsonDate.toString();
        }
        jsonDate.put("success",true);
        jsonDate.put("msg","成功");
        return jsonDate.toString();
    }

    /**
     * 修改用户
     * @return
     */
    public String modifyUser(String id, String name, String sex, String canBorrowNumber, String borrowedNumber){
        JSONDate jsonDate = new JSONDate();
        //此处添加数据验证
        try {
            if (!new UpdateUtil().updateUserInfo(id, name, sex, canBorrowNumber, borrowedNumber)){
                throw new SQLException();
            }
        } catch (SQLException | IOException e) {
            jsonDate.put("success", false);
            jsonDate.put("msg","数据库异常");
            return jsonDate.toString();
        }
        jsonDate.put("success", true);
        jsonDate.put("msg","完成");
        return jsonDate.toString();
    }

    /**
     * 获取所有用户信息
     * @return
     */
    public String getAllUserInfo(){
        JSONDate jsonDate = new JSONDate();
        ArrayList<JSONDate> userArray = new ArrayList<>();
        Query query;
        int rows = 0; //存储长度
        try {
            query = new Query();
            ResultSet resultSet = query.queryAllUser();
            if (!resultSet.next()){
                throw new SQLException();
            }else{ //取出用户信息放入缓存中
                do {
                    JSONDate userInfo = new JSONDate();
                    userInfo.put("id", resultSet.getString(1));
                    userInfo.put("name", resultSet.getString(2));
                    userInfo.put("sex", resultSet.getString(3));
                    userInfo.put("surplus_book", resultSet.getString(4));
                    userInfo.put("loan_book", resultSet.getString(5));
                    userArray.add(userInfo);
                    rows++;
                }while (resultSet.next());
            }
        } catch (IOException | SQLException e) {
            jsonDate.put("msg","数据库出现问题，请联系维护人员");
            jsonDate.put("success", false);
            return jsonDate.toString();
        }
        jsonDate.put("msg","完成");
        jsonDate.put("success",true);

        jsonDate.put("total",rows);
        for (int i=0;i<rows;i++){
            jsonDate.putList("rows", userArray.get(i));
        }
        return jsonDate.toString();
    }
    /**
     * 重置密码
     */
    public String cleanUserPassword(String id){
        JSONDate jsonDate = new JSONDate();
        UpdateUtil updateUtil;
        try{
            updateUtil = new UpdateUtil();
            updateUtil.updateUserPsd(id, MD5.md5(password));
        }catch (Exception e){
            jsonDate.put("msg","数据库出现问题，请联系维护人员");
            jsonDate.put("success", false);
            return jsonDate.toString();
        }
        jsonDate.put("msg","重置密码完成");
        jsonDate.put("success",true);
        return jsonDate.toString();
    }

}
