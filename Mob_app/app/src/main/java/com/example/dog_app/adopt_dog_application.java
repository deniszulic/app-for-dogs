package com.example.dog_app;

public class adopt_dog_application {
    private String ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave, prihvaceno, naziv_azila, grad_azila;
    private long postavljeno;
    private int udomljavanje_id, korisnik_id;
    public adopt_dog_application(String ime, String prezime, String adresa, String grad,
                                 String postanski_broj, String kontakt, String razlog_prijave,
                                 String prihvaceno, long postavljeno, int udomljavanje_id,
                                 int korisnik_id, String naziv_azila, String grad_azila){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.kontakt = kontakt;
        this.razlog_prijave = razlog_prijave;
        this.prihvaceno = prihvaceno;
        this.naziv_azila = naziv_azila;
        this.grad_azila = grad_azila;
        this.postavljeno = postavljeno;
        this.udomljavanje_id = udomljavanje_id;
        this.korisnik_id = korisnik_id;
    }
}
