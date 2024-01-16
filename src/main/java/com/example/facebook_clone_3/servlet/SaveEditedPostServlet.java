package com.example.facebook_clone_3.servlet;

import com.example.facebook_clone_3.dao.WallPageDAO;
import com.example.facebook_clone_3.models.Post;
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
@WebServlet("/saveEdited")
public class SaveEditedPostServlet extends HttpServlet {

        WallPageDAO wallPageDAO= new WallPageDAO();

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                String content= req.getParameter("edited-post");
                int postId= Integer.parseInt(req.getParameter("id"));
                HttpSession session = req.getSession();
                User user= (User) session.getAttribute("user");
                Post post = new Post();
                post.setPostId(postId);
                post.setContent(content);
                post.setUserId(user.getUserId());
                Boolean status= wallPageDAO.updateUserPost(post);
                System.out.println(status);
                if(status){
                    RequestDispatcher dispatcher= req.getRequestDispatcher("wall");
                    dispatcher.forward(req,resp);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }


