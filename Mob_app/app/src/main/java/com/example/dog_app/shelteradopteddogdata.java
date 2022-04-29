package com.example.dog_app;

public class shelteradopteddogdata {
    private String telefonskibr, grad, boja, dlaka, ime_psa,
            spol,  napomena, url_slike,  pasmina, kastrat,  opasnost,  email, starost,
            postanski_broj, kilaza, naziv, ulica,kucnibr;
    private int id;
    private long postavljeno;

    public shelteradopteddogdata(int id, String telefonskibr, String boja, String starost, String dlaka, String ime_psa, String spol, String napomena, String url_slike, long postavljeno, String pasmina, String kilaza, String kastrat, String opasnost, String email, String naziv, String grad, String postanski_broj, String ulica, String kucnibr){
        this.telefonskibr = telefonskibr;
        this.grad = grad;
        this.boja = boja;
        this.dlaka = dlaka;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.napomena = napomena;
        this.url_slike = url_slike;
        this.pasmina = pasmina;
        this.kastrat = kastrat;
        this.opasnost = opasnost;
        this.email = email;
        this.starost = starost;
        this.postanski_broj = postanski_broj;
        this.kilaza = kilaza;
        this.id = id;
        this.postavljeno = postavljeno;
        this.naziv=naziv;
        this.ulica=ulica;
        this.kucnibr=kucnibr;
    }

    public String getTelefonskibr() {
        return telefonskibr;
    }

    public String getGrad() {
        return grad;
    }

    public String getBoja() {
        return boja;
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

    public String getNapomena() {
        return napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getPasmina() {
        return pasmina;
    }

    public String getKastrat() {
        return kastrat;
    }

    public String getOpasnost() {
        return opasnost;
    }

    public String getEmail() {
        return email;
    }

    public String getStarost() {
        return starost;
    }

    public String getPostanski_broj() {
        return postanski_broj;
    }

    public String getKilaza() {
        return kilaza;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getUlica() {
        return ulica;
    }

    public String getKucnibr() {
        return kucnibr;
    }

    public int getId() {
        return id;
    }

    public long getPostavljeno() {
        return postavljeno;
    }
}
