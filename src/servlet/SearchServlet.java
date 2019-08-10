package servlet;

import manage.account.JSONUnit;
import manage.book.Book;
import manage.circulation.Search;
import unit.JSONDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 进行图书信息的查询
 */
@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        //类别
        String info = request.getParameter("info");
        //搜索内容
        String content = request.getParameter("content");
        //将信息传入后台验证
        Search search = new Search();
        String tableDate = null;  //返回网页的json数据
        ResultSet resultSet = null;  //查询的数据库数据
        int t = 1; //标志：1=书，其他=读者
        System.out.println(info+"=="+content);
        if ("title".equals(info)){
            resultSet = search.searchBook(content);
        }else if ("author".equals(info)){
            resultSet = search.searchAuthor(content);
        }else if ("publisher".equals(info)){
            resultSet = search.searchPublisher(content);
        }else if ("id".equals(info)){
            resultSet = search.searchReaderId(content);
            t = 2;
        }else if ("name".equals(info)){
            resultSet = search.searchName(content);
            t = 2;
        }else {
            response.getWriter().write(tableDate);
            return;
        }

        tableDate = somedata(resultSet,t);
        System.out.println(tableDate);
        response.getWriter().write(tableDate);
    }

    /**
     * 将数据库信息转化成json形式
     * @param resultSet
     * @return
     */
    private String somedata(ResultSet resultSet, int t){
        JSONDate jsonDate = new JSONDate();
        ArrayList<JSONDate> bookArray = new ArrayList<>();
        int rows = 0;
        try {
            if (!resultSet.next()){
                throw new SQLException();
            }else{ //取出每本书信息，保存到bookArray
                do {
                    JSONDate info;
                    if (t == 1){
                        info = JSONUnit.getBookJson(resultSet);
                    }else {
                        info = JSONUnit.getReaderJson(resultSet);
                    }

                    bookArray.add(info);
                    rows++;
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            return "{}";
        }

        jsonDate.put("total",rows);
        for (int i=0;i<rows;i++){
            jsonDate.putList("rows", bookArray.get(i));
        }
        return jsonDate.toString();
    }
}
