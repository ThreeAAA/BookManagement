package manage.account;

import dataBase.operation.Query;
import manage.search.Borrowing;
import unit.JSONDate;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserOperation {

    /**
     * 借书
     */
    public String borrowBook(String readerid, String bookId){
        Borrowing borrowing = new Borrowing();
        JSONDate jsonDate = new JSONDate();

        try {
            if (!borrowing.verfyBorrowInfo(readerid,bookId)) {
                jsonDate.put("success",false);
                jsonDate.put("msg","已经借阅,请选择其他书籍");
            }
            if(!borrowing.addInto(readerid, bookId)) throw new SQLException();
        } catch (SQLException | IOException e) {
            jsonDate.put("success",false);
            jsonDate.put("msg","借阅失败");
            return jsonDate.toString();
        }
        jsonDate.put("success",true);
        jsonDate.put("msg","借阅成功");
        return jsonDate.toString();
    }

    /**
     *  获取借书信息
     */
    public String getBorrowInfo(String readId){
        JSONDate jsonDate = new JSONDate();  //返回信息
        ResultSet borrowResult;  //借阅数据库返回信息
        ResultSet bookResult;
        Query query;
        int rows = 0;
        try {
            query = new Query();
            borrowResult = query.queryAllBorrowInfo(readId);  //获得借书书籍id
            if (!borrowResult.next()){
                //没有借书，返回提示
                jsonDate.put("success",false);
                jsonDate.put("msg","没有借阅信息");
                return jsonDate.toString();
            }else{
                do {
                    String id = borrowResult.getString(3);
                    String time1 = borrowResult.getString(4);
                    String time2 = borrowResult.getString(5);

                    bookResult = query.queryBookId(id);
                    if (!bookResult.next()) throw new SQLException();
                    JSONDate bookInfo = JSONUnit.getBorrowBookJson(bookResult, time1, time2);
                    jsonDate.putList("rows",bookInfo);

                    rows++;
                }while (borrowResult.next());
            }
        } catch (IOException | SQLException e) {
            jsonDate.put("success",false);
            jsonDate.put("msg","获取信息失败");
            return jsonDate.toString();
        }
        //获得借书书籍所有信息

        jsonDate.put("success",true);
        jsonDate.put("msg","获取成功");
        jsonDate.put("total",rows);
        return jsonDate.toString();
    }


}
