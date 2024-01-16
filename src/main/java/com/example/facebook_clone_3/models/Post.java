package com.example.facebook_clone_3.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
   public class Post {
        private int postId;
        private String content;
        private int userId;
        private User user;

//    public Post () {
//    }
//
//    public void setPostId(int postId) {
//    }
//
//    public void setContent(String content) {
//    }
//
//    public void setUserId(int userId) {
//    }
//
//    public int getPostId() {
//        return 0;
//    }
//
//    public String getContent() {
//        return null;
//    }
}




