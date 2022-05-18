package com.example.dog_app;

public class updateuserreq_missingdog {
    private String prihvaceno, napomena;

    public updateuserreq_missingdog(String prihvaceno, String napomena) {
        this.prihvaceno = prihvaceno;
        this.napomena = napomena;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }

    public String getNapomena() {
        return napomena;
    }
}
