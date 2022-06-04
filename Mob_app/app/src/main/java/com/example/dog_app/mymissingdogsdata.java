package com.example.dog_app;

import java.util.Date;

public class mymissingdogsdata {
    private String ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka,
            vet_lokacija, ime_psa, spol, nestanak_napomena, url_slike, pasmina,
    prihvaceno, napomena;
    private boolean aktivan;
    private int id;
    private long postavljeno;
    private Date datum_izgubljen;
    public mymissingdogsdata(int id, String ime, String prezime, String adresa, String telefonskibr,
                    String grad, String postanski_broj, String boja, String starost, String dlaka,
                    String vet_lokacija, String ime_psa, String spol, Date datum_izgubljen,
                    String nestanak_napomena, String url_slike, long postavljeno, String pasmina,
                             boolean aktivan, String prihvaceno, String napomena) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefonskibr = telefonskibr;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.boja = boja;
        this.starost = starost;
        this.dlaka = dlaka;
        this.vet_lokacija = vet_lokacija;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.datum_izgubljen = datum_izgubljen;
        this.nestanak_napomena = nestanak_napomena;
        this.url_slike = url_slike;
        this.pasmina = pasmina;
        this.prihvaceno = prihvaceno;
        this.napomena = napomena;
        this.aktivan = aktivan;
        this.id = id;
        this.postavljeno = postavljeno;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTelefonskibr() {
        return telefonskibr;
    }

    public String getGrad() {
        return grad;
    }

    public String getPostanski_broj() {
        return postanski_broj;
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

    public String getVet_lokacija() {
        return vet_lokacija;
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

    public String getNestanak_napomena() {
        return nestanak_napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getPasmina() {
        return pasmina;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }

    public String getNapomena() {
        return napomena;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public int getId() {
        return id;
    }

    public long getPostavljeno() {
        return postavljeno;
    }
}
