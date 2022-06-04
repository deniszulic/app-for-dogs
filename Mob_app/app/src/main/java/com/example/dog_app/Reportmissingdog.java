package com.example.dog_app;

public class Reportmissingdog {
    private String ime, prezime, napomena, adresa_pronalaska, adresa_za_preuzimanje_psa, kontakt, naziv_azila, grad_azila;
    private long postavljeno;
    private int korisnik_id, nestanak_id;

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getNapomena() {
        return napomena;
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

    public String getNaziv_azila() {
        return naziv_azila;
    }

    public String getGrad_azila() {
        return grad_azila;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public int getNestanak_id() {
        return nestanak_id;
    }
    public Reportmissingdog(String ime, String prezime, String napomena, String adresa_pronalaska, String adresa_za_preuzimanje_psa, long postavljeno, int korisnik_id, int nestanak_id, String kontakt){
        this.ime = ime;
        this.prezime = prezime;
        this.napomena = napomena;
        this.adresa_pronalaska = adresa_pronalaska;
        this.adresa_za_preuzimanje_psa = adresa_za_preuzimanje_psa;
        this.kontakt = kontakt;
        this.naziv_azila = naziv_azila;
        this.grad_azila = grad_azila;
        this.postavljeno = postavljeno;
        this.korisnik_id = korisnik_id;
        this.nestanak_id = nestanak_id;
    }
    public Reportmissingdog(String ime, String prezime, String napomena, String adresa_pronalaska, String adresa_za_preuzimanje_psa, long postavljeno, int korisnik_id, int nestanak_id, String kontakt, String naziv_azila, String grad_azila){
        this.ime = ime;
        this.prezime = prezime;
        this.napomena = napomena;
        this.adresa_pronalaska = adresa_pronalaska;
        this.adresa_za_preuzimanje_psa = adresa_za_preuzimanje_psa;
        this.kontakt = kontakt;
        this.naziv_azila = naziv_azila;
        this.grad_azila = grad_azila;
        this.postavljeno = postavljeno;
        this.korisnik_id = korisnik_id;
        this.nestanak_id = nestanak_id;
    }
}
