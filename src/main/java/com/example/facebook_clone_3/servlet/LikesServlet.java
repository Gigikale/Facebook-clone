package com.example.facebook_clone_3.servlet;

import com.example.facebook_clone_3.dao.LikesDAO;
import com.example.facebook_clone_3.models.Likes;
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
@WebServlet("/likes")
public class LikesServlet extends HttpServlet {
    LikesDAO likesDAO= new LikesDAO();


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                resp.setContentType("text/html");
                int postId= Integer.parseInt(req.getParameter("id"));
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");
                Likes like= likesDAO.checkLike(postId,user.getUserId());
                if(like.getLikesId()<1){
                    likesDAO.LikeAPost(user.getUserId(),postId);
                    List<Likes> likesList= likesDAO.displayAllLikesCount(postId);
                    req.setAttribute("likes",likesList);
                    RequestDispatcher dispatcher= req.getRequestDispatcher("/userWall.jsp");
                    dispatcher.forward(req,resp);
                }
                else {
                    likesDAO.unlikeAPost(like.getLikesId());
                    List<Likes> likesList= likesDAO.displayAllLikesCount(postId);
                    req.setAttribute("likes",likesList);
                    RequestDispatcher dispatcher= req.getRequestDispatcher("/userWall.jsp");
                    dispatcher.forward(req,resp);
                }


            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



