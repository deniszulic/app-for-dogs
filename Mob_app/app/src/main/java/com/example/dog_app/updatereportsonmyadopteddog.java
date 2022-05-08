package com.example.dog_app;

public class updatereportsonmyadopteddog {
    private String napomena, prihvaceno;

    public updatereportsonmyadopteddog(String napomena, String prihvaceno) {
        this.napomena = napomena;
        this.prihvaceno = prihvaceno;
    }

    public String getNapomena() {
        return napomena;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }
}
