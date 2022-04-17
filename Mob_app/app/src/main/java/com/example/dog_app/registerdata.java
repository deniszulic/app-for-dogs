package com.example.dog_app;

public class registerdata{
    private String ime, email, lozinka, prezime, tipkorisnika;
    long datumreg;

    public registerdata(String ime, String email, String lozinka, String prezime, String tipkorisnika, long datumreg){
        this.ime=ime;
        this.email=email;
        this.lozinka=lozinka;
        this.prezime=prezime;
        this.tipkorisnika=tipkorisnika;
        this.datumreg=datumreg;
    }
}
