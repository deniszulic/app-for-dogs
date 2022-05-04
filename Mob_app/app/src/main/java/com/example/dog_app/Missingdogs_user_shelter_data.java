package com.example.dog_app;

import java.util.Date;

public class Missingdogs_user_shelter_data {
    private int id;
    private String telefonskibr, boja, starost, dlaka, ime_psa, spol, napomena,
            url_slike, pasmina, email, naziv, grad, postanski_broj, ulica, kucnibr;
    private long postavljeno;
    private Date datum_izgubljen;

    public Missingdogs_user_shelter_data(int id, String telefonskibr, String boja, String starost, String dlaka, String ime_psa, String spol, Date datum_izgubljen, String napomena, String url_slike, long postavljeno, String pasmina, String email, String naziv, String grad, String postanski_broj, String ulica, String kucnibr){
        this.id = id;
        this.telefonskibr = telefonskibr;
        this.boja = boja;
        this.starost = starost;
        this.dlaka = dlaka;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.datum_izgubljen = datum_izgubljen;
        this.napomena = napomena;
        this.url_slike = url_slike;
        this.pasmina = pasmina;
        this.email = email;
        this.naziv = naziv;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.ulica = ulica;
        this.kucnibr = kucnibr;
        this.postavljeno = postavljeno;
    }

    public int getId() {
        return id;
    }

    public String getTelefonskibr() {
        return telefonskibr;
    }

    public String getBoja() {
        return boja;
    }

    public String getStarost() {
        return starost;
    }

    public String getDlaka() {
        return dlaka;
    }

    public String getIme_psa() {
        return ime_psa;
    }

    public String getSpol() {
        return spol;
    }

    public Date getDatum_izgubljen() {
        return datum_izgubljen;
    }

    public String getNapomena() {
        return napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getPasmina() {
        return pasmina;
    }

    public String getEmail() {
        return email;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getGrad() {
        return grad;
    }

    public String getPostanski_broj() {
        return postanski_broj;
    }

    public String getUlica() {
        return ulica;
    }

    public String getKucnibr() {
        return kucnibr;
    }

    public long getPostavljeno() {
        return postavljeno;
    }
}
