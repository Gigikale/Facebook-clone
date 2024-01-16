package com.example.facebook_clone_3.dao;

import com.example.facebook_clone_3.models.Post;
import com.example.facebook_clone_3.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/Facebook_clone";
    private String jdbcUsername = "root";
    private String jdbcPassword = "kalenebari@93";

    private static final String CREATE_NEW_POST= "INSERT INTO Post" + "(content,userId)VALUES" +"(?,?)";
    private static final String SELECT_ALL_POST= "select * from Post";
    private static final String SELECT_SPECIFIC_POST= "select * from Post where postId=?";


    private Connection getConnection(){
        Connection dataConnection= null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataConnection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return dataConnection;
    };

    public void createNewPost(String post, int userId) throws SQLException{
        try (  Connection connection= getConnection();
               PreparedStatement preparedStatement= connection.prepareStatement(CREATE_NEW_POST)){
            preparedStatement.setString(1, post);
            preparedStatement.setInt(2,userId);
            //preparedStatement.setDate(2, Date.valueOf(post.getDateCreated().toLocalDate()));
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Post> viewAllPosts() throws SQLException{
        List<Post>posts = new ArrayList<>();
        try(Connection connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_POST)) {

            ResultSet result= preparedStatement.executeQuery();

            while(result.next()){
                int id= result.getInt("postId");
                String content= result.getString("content");
//                Date dateCreated= result.getDate("dateCreated");
                Post post = new Post();
                post.setPostId(id);
                post.setContent(content);
                posts.add(post);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return posts;
    }

    public Post getPost(int postId){
        Post post = new Post();
        try(Connection connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_SPECIFIC_POST)){
            preparedStatement.setInt(Integer.parseInt("1"),postId);
            ResultSet result= preparedStatement.executeQuery();
            while(result.next()){
                int userId= result.getInt("userId");
                String content= result.getString("content");
                post.setContent(content);
                post.setPostId(postId);
                post.setUserId(userId);


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return post;
    }


}



