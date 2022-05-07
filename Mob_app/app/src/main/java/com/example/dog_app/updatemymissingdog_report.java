package com.example.dog_app;

public class updatemymissingdog_report {
    private String ime, prezime, adresa_pronalaska, adresa_za_preuzimanje_psa, kontakt, napomena;
    public updatemymissingdog_report(String ime, String prezime, String adresa_pronalaska, String adresa_za_preuzimanje_psa, String kontakt, String napomena){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa_pronalaska = adresa_pronalaska;
        this.adresa_za_preuzimanje_psa = adresa_za_preuzimanje_psa;
        this.kontakt = kontakt;
        this.napomena = napomena;
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
}
