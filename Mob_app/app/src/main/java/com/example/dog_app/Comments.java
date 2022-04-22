package com.example.dog_app;

public class Comments {

    private String email, komentar;
    private int id;
    private long postavljeno;

    public String getEmail() {
        return email;
    }

    public String getKomentar() {
        return komentar;
    }

    public int getId() {
        return id;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public Comments(String email, int id, String komentar, long postavljeno){
        this.email=email;
        this.id=id;
        this.komentar=komentar;
        this.postavljeno=postavljeno;
    }
}
