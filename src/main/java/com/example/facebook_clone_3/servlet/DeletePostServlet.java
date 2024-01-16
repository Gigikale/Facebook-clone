package com.example.facebook_clone_3.servlet;

import java.io.IOException;
import java.sql.SQLException;


import com.example.facebook_clone_3.dao.WallPageDAO;
//import com.example.facebook_clone_3.dao.models.WallPageDAOdao;
import com.example.facebook_clone_3.models.Post;
//import com.example.facebookclone_3.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/deleted")
    public class DeletePostServlet extends HttpServlet {
        WallPageDAO wallPageDAO;
        public void init(){
            wallPageDAO= new WallPageDAO();
        }
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            try {
                response.setContentType("text/html");
                HttpSession session = request.getSession();
                int postId = Integer.valueOf(request.getParameter("id"));
                System.out.println(postId);
//           User user= (User) session.getAttribute("user");
                boolean status = wallPageDAO.deletePost(postId);
//           List<Posts> postsList = wallPageDAO.viewAllUsersPost(user);
                System.out.println(status);
                if (status) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/wall");
                    dispatcher.forward(request, response);
//                response.sendRedirect("userWall.jsp");
                }

            } catch (SQLException | ServletException e) {
                e.printStackTrace();
            }
        }


    }


