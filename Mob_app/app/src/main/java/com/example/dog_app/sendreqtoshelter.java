package com.example.dog_app;

public class sendreqtoshelter {
    private int nestanak_id, korisnik_id;
    private long postavljeno;
    private String prihvaceno;
    public sendreqtoshelter(String prihvaceno, int nestanak_id, int korisnik_id, long postavljeno){
        this.nestanak_id = nestanak_id;
        this.korisnik_id = korisnik_id;
        this.postavljeno = postavljeno;
        this.prihvaceno = prihvaceno;
    }

    public int getNestanak_id() {
        return nestanak_id;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }
}
