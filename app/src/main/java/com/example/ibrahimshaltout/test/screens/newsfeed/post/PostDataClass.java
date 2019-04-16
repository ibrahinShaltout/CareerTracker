package com.example.ibrahimshaltout.test.screens.newsfeed.post;

public class PostDataClass {

    private String postData;
    private String postName;
    private String timeAndDate;
    public String imageURL;
    public String imageName;
    private String user_ID;
    private String post_ID;
    private int number_of_likes;

    private String comment;
    private String writerID;
    private String writerName;

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriterID() {
        return writerID;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public String getPost_ID() {
        return post_ID;
    }

    public void setPost_ID(String post_ID) {
        this.post_ID = post_ID;
    }

    public int getNumber_of_likes() {
        return number_of_likes;
    }

    public void setNumber_of_likes(int number_of_likes) {
        this.number_of_likes = number_of_likes;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public PostDataClass(){

    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }



    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


}
