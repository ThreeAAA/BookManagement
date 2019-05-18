package servlet;

import manage.account.AdminOperation;
import unit.JSONDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        test1(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //添加和修改书信息
    private void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminOperation adminOperation = new AdminOperation();
        String bookId = request.getParameter("book_id");
        String bookTitle = request.getParameter("book_name");
        String bookAuthor = request.getParameter("book_author");
        String bookPublish = request.getParameter("book_publish");
        String bookCategoty = request.getParameter("book_categoty");
        String bookTime = request.getParameter("book_time");
        String bookAllAmount = request.getParameter("book_all_amount");
        String bookBrwAmount = request.getParameter("book_brw_amount");
        String action = request.getParameter("action");
        String page = request.getParameter("page");
        String m;
        if (page != null) { //此处要优化
            m= adminOperation.getAllBookInfo();
        } else {
            if (action.equals("add")){
                m = adminOperation.addBook(bookId,bookTitle,bookAuthor,bookPublish,bookCategoty,bookTime,bookAllAmount,bookBrwAmount);
            }else if (action.equals("modify")){
                m = adminOperation.modfiyBook(bookId,bookTitle,bookAuthor,bookPublish,bookCategoty,bookTime,bookAllAmount,bookBrwAmount);
            }else if (action.equals("bookInfo")){
                m= adminOperation.getAllBookInfo();
            }else {
                m = "aa";
            }
        }

        response.getWriter().write(m);
    }

}
