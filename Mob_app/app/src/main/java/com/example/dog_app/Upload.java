package com.example.dog_app;

public class Upload {
    private String name;
    private String imageurl;
    public Upload(){

    }
    public Upload(String imageurl){
        this.imageurl=imageurl;
    }
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}