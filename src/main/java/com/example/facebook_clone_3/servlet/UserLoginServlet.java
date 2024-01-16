package com.example.facebook_clone_3.servlet;

import com.example.facebook_clone_3.dao.DashboardDAO;
import com.example.facebook_clone_3.dao.UserDAO;
import com.example.facebook_clone_3.models.Post;
import com.example.facebook_clone_3.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/userLogin")
public class UserLoginServlet {
        private UserDAO userDAO;
        private DashboardDAO dashboardDAO;

        public void init () {
            //message = "Hello World!";
            userDAO= new UserDAO();
            dashboardDAO=new DashboardDAO();

        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html");
            String name= request.getParameter("login_username");
            String password= request.getParameter("login_password");


            try {
                User user1= userDAO.getUser(name,password);
                if(user1!=null){
                    HttpSession session= request.getSession();
                    List<Post> posts=dashboardDAO.displayAllOtherUsersPosts(user1);
                    session.setAttribute("user",user1);
                    session.setAttribute("posts",posts);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                    dispatcher.forward(request, response);
                }else{
                    String message= "Invalid username or password";
                    request.setAttribute("message",message);
                }


//
//            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
//            dispatcher.forward(request, response);
            }catch(ServletException ex){
                ex.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        public void destroy() {
        }
    }


