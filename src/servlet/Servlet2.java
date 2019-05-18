package servlet;

import manage.account.AdminOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        test2(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //添加，修改用户信息，重置密码
    private void test2(HttpServletRequest request, HttpServletResponse response) throws IOException{
        AdminOperation adminOperation = new AdminOperation();
        String id = request.getParameter("reader_id");
        String name = request.getParameter("reader_name");
        String sex = request.getParameter("reader_sex");
        String canBorrowNumber = request.getParameter("reader_can_borrow");
        String borrowedNumber = request.getParameter("reader_borrows");
        String action = request.getParameter("action");
        String page = request.getParameter("page");
        String m;

        if (page != null){
            m = adminOperation.getAllUserInfo();
        }else{
            if (action.equals("addUser")){
                m = adminOperation.addUser(id,name,sex,canBorrowNumber,borrowedNumber);
            }else if (action.equals("modifyUser")){
                m = adminOperation.modifyUser(id,name,sex,canBorrowNumber,borrowedNumber);
            }else if (action.equals("cleanPassword")){
                m = adminOperation.cleanUserPassword(id);
            }else {
                m = "pp";
            }
        }

        response.getWriter().write(m);
    }
}
