package com.example.facebook_clone_3.servlet;

import com.example.facebook_clone_3.dao.PostDAO;
import com.example.facebook_clone_3.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/savePost")
public class SavePostServlet extends HttpServlet {
    private PostDAO postDAO;

        public void init(){
            postDAO=new PostDAO();
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/wall");
            try {
                response.setContentType("text/html");
                String newContent= request.getParameter("user-new-post");
                HttpSession session= request.getSession();
                User user= (User)session.getAttribute("user");
                postDAO.createNewPost(newContent,user.getUserId());

                dispatcher.forward(request,response);
            } catch (ServletException | SQLException e) {
                e.printStackTrace();
            }
        }
    }



