package com.example.dog_app;

public class sendreqtoshelter_adoptdog {
    private int udomljavanje_id, korisnik_id;
    private long postavljeno;
    private String prihvaceno;
    public sendreqtoshelter_adoptdog(int udomljavanje_id, int korisnik_id, long postavljeno, String prihvaceno){
        this.udomljavanje_id = udomljavanje_id;
        this.korisnik_id = korisnik_id;
        this.postavljeno = postavljeno;
        this.prihvaceno = prihvaceno;
    }

    public int getUdomljavanje_id() {
        return udomljavanje_id;
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
