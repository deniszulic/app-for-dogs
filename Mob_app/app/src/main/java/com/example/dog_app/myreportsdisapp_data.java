package com.example.dog_app;

import java.util.Date;

public class myreportsdisapp_data {
    private String ime, prezime, adresa_pronalaska, adresa_za_preuzimanje_psa, napomena, kontakt,
            ime_nestanak, prezime_nestanak, adresa, telefonskibr, grad, postanski_broj, boja, starost,
    dlaka, vet_lokacija, ime_psa, spol, pasmina, napomena_nestanak, url_slike, naziv_azila, grad_azila;
    private long postavljeno;
    private int id;
    private boolean oglas_azila;
    private Date datum_izgubljen;
    public myreportsdisapp_data(String ime, String prezime, String adresa_pronalaska, String adresa_za_preuzimanje_psa, String napomena,
                                long postavljeno, String kontakt, int id,
                                String ime_nestanak, String prezime_nestanak, String adresa,
                                String telefonskibr, String grad, String postanski_broj, String boja, String starost,
                                String dlaka, String vet_lokacija, String ime_psa, String spol,
                                String pasmina, Date datum_izgubljen, String napomena_nestanak, String url_slike,
                                boolean oglas_azila, String naziv_azila, String grad_azila){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa_pronalaska = adresa_pronalaska;
        this.adresa_za_preuzimanje_psa = adresa_za_preuzimanje_psa;
        this.napomena = napomena;
        this.kontakt = kontakt;
        this.ime_nestanak = ime_nestanak;
        this.prezime_nestanak = prezime_nestanak;
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
        this.napomena_nestanak = napomena_nestanak;
        this.url_slike = url_slike;
        this.naziv_azila = naziv_azila;
        this.grad_azila = grad_azila;
        this.postavljeno = postavljeno;
        this.id = id;
        this.oglas_azila = oglas_azila;
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

    public String getNapomena() {
        return napomena;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getIme_nestanak() {
        return ime_nestanak;
    }

    public String getPrezime_nestanak() {
        return prezime_nestanak;
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

    public String getNapomena_nestanak() {
        return napomena_nestanak;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getNaziv_azila() {
        return naziv_azila;
    }

    public String getGrad_azila() {
        return grad_azila;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public int getId() {
        return id;
    }

    public boolean isOglas_azila() {
        return oglas_azila;
    }

    public Date getDatum_izgubljen() {
        return datum_izgubljen;
    }
}
