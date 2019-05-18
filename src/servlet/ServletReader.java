package servlet;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import manage.account.JSONUnit;
import manage.account.UserOperation;
import manage.search.Borrowing;
import unit.JSONDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ServletReader")
public class ServletReader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        String action = request.getParameter("action");
        String page = request.getParameter("page");

        if (page != null){ //获取借阅信息
            borrowInfo(request, response);
        }
        if (action != null && action.equals("borrow")){
            borrow(request, response);
        }else if (action != null && action.equals("returnBook")){
            returnBook(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    //借书操作
    private void borrow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookId = request.getParameter("book_id");
        String readerId = request.getParameter("reader_id");
        UserOperation userOperation = new UserOperation();
        String m = userOperation.borrowBook(readerId, bookId);
        response.getWriter().write(m);
    }
    //获取借阅书籍列表
    private void borrowInfo(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String readerId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("reader_id")) readerId = cookie.getValue();
        }
        if (readerId == null) {
            JSONDate jsonDate = new JSONDate();
            jsonDate.put("success", false);
            jsonDate.put("msg","页面错误，请重新登录");
            response.getWriter().write(jsonDate.toString());
            return;
        }
        String msg = new UserOperation().getBorrowInfo(readerId);
        response.getWriter().write(msg);
    }
    //归还书
    private void returnBook(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String bookId = request.getParameter("book_id");
        String readerId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("reader_id")) readerId = cookie.getValue();}
        try {
            if (new Borrowing().removeInto(readerId,bookId)){
                JSONDate jsonDate = new JSONDate();
                jsonDate.put("success",true);
                jsonDate.put("msg","还书成功");
                response.getWriter().write(jsonDate.toString());
            }
        } catch (SQLException e) {
            JSONDate jsonDate = new JSONDate();
            jsonDate.put("success", false);
            jsonDate.put("msg","页面错误，请重新登录");
            response.getWriter().write(jsonDate.toString());
            return;
        }
    }
}
