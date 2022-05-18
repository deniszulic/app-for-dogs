package com.example.dog_app;

import java.util.Date;

public class userreq_missingdog_data {
    //nestanak.ime, nestanak.prezime, nestanak.adresa, nestanak.telefonskibr, nestanak.grad, nestanak.postanski_broj, nestanak.boja, nestanak.starost, nestanak.dlaka, nestanak.vet_lokacija, nestanak.ime_psa, nestanak.spol, nestanak.pasmina, nestanak.datum_izgubljen, nestanak.napomena as nestanak_napomena, nestanak.url_slike, azil_nestanak.napomena as azil_nestanak_napomena, azil_nestanak.prihvaceno, azil_nestanak.id, azil_nestanak.nestanak_id as azil_nestanak_nestanak_id, azil_nestanak.postavljeno, nestanak.id as nestanak_id
    private String ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, nestanak_napomena, url_slike, azil_nestanak_napomena, prihvaceno;
    private int id, azil_nestanak_nestanak_id, nestanak_id;
    private long postavljeno;
    private Date datum_izgubljen;
    public userreq_missingdog_data(String ime, String prezime, String adresa, String telefonskibr, String grad, String postanski_broj, String boja, String starost, String dlaka, String vet_lokacija, String ime_psa, String spol, String pasmina, Date datum_izgubljen, String nestanak_napomena, String url_slike, String azil_nestanak_napomena, String prihvaceno, int id, int azil_nestanak_nestanak_id, long postavljeno, int nestanak_id){
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
        this.pasmina = pasmina;
        this.nestanak_napomena = nestanak_napomena;
        this.url_slike = url_slike;
        this.azil_nestanak_napomena = azil_nestanak_napomena;
        this.prihvaceno = prihvaceno;
        this.id = id;
        this.azil_nestanak_nestanak_id = azil_nestanak_nestanak_id;
        this.nestanak_id = nestanak_id;
        this.postavljeno = postavljeno;
        this.datum_izgubljen = datum_izgubljen;
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

    public String getPasmina() {
        return pasmina;
    }

    public String getNestanak_napomena() {
        return nestanak_napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getAzil_nestanak_napomena() {
        return azil_nestanak_napomena;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }

    public int getId() {
        return id;
    }

    public int getAzil_nestanak_nestanak_id() {
        return azil_nestanak_nestanak_id;
    }

    public int getNestanak_id() {
        return nestanak_id;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public Date getDatum_izgubljen() {
        return datum_izgubljen;
    }
}
