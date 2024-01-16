package com.example.facebook_clone_3.dao;

import com.example.facebook_clone_3.models.Post;
import com.example.facebook_clone_3.models.Post;
import com.example.facebook_clone_3.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {
//    private static final String SELECT_ALL_POST_EXCEPT_USER = " SELECT * FROM Users WHERE userid ? ";
    private String jdbcURL = "jdbc:mysql://localhost:3306/Facebook_clone";
    private String jdbcUsername = "root";
    private String jdbcPassword = "kalenebari@93";

    private static final String SELECT_ALL_POST_EXCEPT_USER = "select * from Post where userId";

    private Connection getConnection(){
        Connection dataConnection= null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            dataConnection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return dataConnection;
    };


    public  List<Post> displayAllOtherUsersPosts(User user){
        List<Post>listOfPosts = new ArrayList<>();
        try (Connection connection= getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_POST_EXCEPT_USER)){
            preparedStatement.setInt(1,user.getUserId());
            ResultSet result= preparedStatement.executeQuery();
            while(result.next()){
                String content= result.getString("content");
                int userId= result.getInt("userId");
                int postId= result.getInt("postId");
                Post post = new Post();
                post.setContent(content);
                post.setPostId(postId);
                post.setUserId(userId);
                listOfPosts.add(post);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listOfPosts;
    }
}
