package com.example.dog_app;

public class updatemyreports_adopteddogs {
    private String ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave;
    public updatemyreports_adopteddogs(String ime, String prezime, String adresa, String grad, String kontakt, String postanski_broj,String razlog_prijave){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.kontakt = kontakt;
        this.razlog_prijave = razlog_prijave;
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

    public String getGrad() {
        return grad;
    }

    public String getPostanski_broj() {
        return postanski_broj;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getRazlog_prijave() {
        return razlog_prijave;
    }
}
