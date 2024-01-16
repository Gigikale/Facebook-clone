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
import java.util.List;
@WebServlet("/wall")
public class UserWalletServlet extends HttpServlet {
        WallPageDAO wallPageDAO;

        public void init(){
            wallPageDAO= new WallPageDAO();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                RequestDispatcher dispatcher = req.getRequestDispatcher("userWall.jsp");
                HttpSession session= req.getSession();
                User user= (User)session.getAttribute("user");
                List<Post> userPost=wallPageDAO.viewAllUsersPost(user);
                if(userPost.size()==0){
//               req.setAttribute("emptypost","No posts yet")
                    resp.sendRedirect("emptyPost.jsp");
                }else{
                    session.setAttribute("usersPosts",userPost);
                    resp.setContentType("text/html");
                    dispatcher.forward(req,resp);}
            }catch (IndexOutOfBoundsException e){

                e.printStackTrace();

                e.printStackTrace();
            }

        }
    }


