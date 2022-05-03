package com.example.dog_app;

public class shelters {
    private int id;
    private String grad, naziv;
    public shelters(String grad, String naziv, int id){
        this.grad=grad;
        this.naziv=naziv;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getGrad() {
        return grad;
    }

    public String getNaziv() {
        return naziv;
    }
}
