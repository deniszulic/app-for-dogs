package com.example.dog_app;

import java.util.Date;

public class reportsonmymissingdogsdata {
    private String ime, prezime, adresa_pronalaska, adresa_za_preuzimanje_psa, kontakt, napomena,
            nestanak_ime, nestanak_prezime, adresa, telefonskibr, grad, postanski_broj,
    boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, nestanak_napomena, url_slike;
    private int id;
    private long postavljeno;
    private Date datum_izgubljen;
    public reportsonmymissingdogsdata(String ime, String prezime, String adresa_pronalaska, String adresa_za_preuzimanje_psa,
                                      String kontakt, String napomena, long postavljeno, int id, String nestanak_ime,
                                      String nestanak_prezime, String adresa, String telefonskibr, String grad, String postanski_broj,
                                      String boja, String starost, String dlaka, String vet_lokacija, String ime_psa, String spol,
                                      String pasmina, Date datum_izgubljen, String nestanak_napomena, String url_slike){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa_pronalaska = adresa_pronalaska;
        this.adresa_za_preuzimanje_psa = adresa_za_preuzimanje_psa;
        this.kontakt = kontakt;
        this.napomena = napomena;
        this.nestanak_ime = nestanak_ime;
        this.nestanak_prezime = nestanak_prezime;
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
        this.id = id;
        this.postavljeno = postavljeno;
        this.datum_izgubljen = datum_izgubljen;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa_pronalaska() {
        return adresa_pronalaska;
    }

    public String getAdresa_za_preuzimanje_psa() {
        return adresa_za_preuzimanje_psa;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getNapomena() {
        return napomena;
    }

    public String getNestanak_ime() {
        return nestanak_ime;
    }

    public String getNestanak_prezime() {
        return nestanak_prezime;
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

    public int getId() {
        return id;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public Date getDatum_izgubljen() {
        return datum_izgubljen;
    }
}
