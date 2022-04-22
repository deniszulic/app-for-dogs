package com.example.dog_app;

public class Sendcomment {
    private String komentar;
    private long postavljeno;
    private int Nestanak_id;
    private int Korisnik_id;

    public Sendcomment(String komentar, long postavljeno, int Nestanak_id, int Korisnik_id) {
        this.komentar = komentar;
        this.postavljeno = postavljeno;
        this.Nestanak_id = Nestanak_id;
        this.Korisnik_id = Korisnik_id;
    }

    public String getKomentar() {
        return komentar;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public int getNestanak_id() {
        return Nestanak_id;
    }

    public int getKorisnik_id() {
        return Korisnik_id;
    }
}
