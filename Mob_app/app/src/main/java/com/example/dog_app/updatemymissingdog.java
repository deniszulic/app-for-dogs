package com.example.dog_app;

import java.util.Date;

public class updatemymissingdog {
    private boolean aktivan;
    private String ime, prezime, adresa, grad, boja, dlaka, vet_lokacija, ime_psa, spol, pasmina,
            napomena;
    private int telefonskibr, postanski_broj, starost;
    private Date datum_izgubljen;
    //ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, napomena, datum_izgubljen, aktivan
    public updatemymissingdog(String ime, String prezime, String adresa, int telefonskibr,
                              String grad, int postanski_broj, String boja, int starost, String dlaka,
                              String vet_lokacija, String ime_psa, String spol, String pasmina,
                              String napomena, Date datum_izgubljen, boolean aktivan) {
        this.aktivan = aktivan;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.boja = boja;
        this.dlaka = dlaka;
        this.vet_lokacija = vet_lokacija;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.pasmina = pasmina;
        this.napomena = napomena;
        this.datum_izgubljen = datum_izgubljen;
        this.telefonskibr = telefonskibr;
        this.postanski_broj = postanski_broj;
        this.starost = starost;
    }
}
