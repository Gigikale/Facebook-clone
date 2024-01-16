package com.example.facebook_clone_3.servlet;

import com.example.facebook_clone_3.dao.PostDAO;
import com.example.facebook_clone_3.dao.WallPageDAO;
import com.example.facebook_clone_3.models.Post;
import com.example.facebook_clone_3.models.Post;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
@WebServlet("/edit")
public class EditPostServlet extends HttpServlet {
        @Override
        public void init(){
            WallPageDAO wallPageDAO= new WallPageDAO();
        }


        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            WallPageDAO wallPageDAO= new WallPageDAO();
            PostDAO postDAO= new PostDAO();
            resp.setContentType("text/html");
            HttpSession session= req.getSession();
            List<Post> postsList= (List<Post>) session.getAttribute("usersPost");
//            String content= req.getParameter("value");
            int postId=  Integer.valueOf(req.getParameter("id"));
//        User user=(User) session.getAttribute("user");
//        int userId= user.getUserId();
            Post post = postDAO.getPost(postId);
            System.out.println(post);
            req.setAttribute("updatePost",post);
            RequestDispatcher dispatcher= req.getRequestDispatcher("editedPosts.jsp");
            dispatcher.forward(req,resp);


        }
    }


