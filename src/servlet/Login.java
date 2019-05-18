package servlet;

import manage.account.Management;
import unit.JSONDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getParameter("from").equals("login")){
            checkLogin(request, response);
        }else {
            checkRegister(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Management management = new Management();
        String m = management.logins(username, password);
        response.getWriter().write(m); //返回值
        //返回的json数据中包含两个，才会被识别成json格式数据
    }

    private void checkRegister(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json; charset=utf-8");
        Management management = new Management();
        String userId = request.getParameter("userid");
        String userName = request.getParameter("username");
        String userSex = request.getParameter("usersex");
        String userPsd = request.getParameter("password");
        String m = management.registered(userId,userName,userSex,userPsd);

        response.getWriter().write(m);
    }
}
