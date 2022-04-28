package com.example.dog_app;

import java.util.Date;

public class missingdogsdata {
    private String ime, prezime, adresa, grad, boja, dlaka, vet_lokacija, ime_psa, spol,
            napomena, telefonskibr, pasmina, url_slike, datum_izgubljen;
    private boolean aktivan, oglas_azila;
    private int postanski_broj, starost, Korisnik_id;
    private long postavljeno;
    //private Date datum_izgubljen;
    public missingdogsdata(String ime, String prezime, String adresa, String grad,
                           int postanski_broj, String boja, int starost, String dlaka,
                           String vet_lokacija, String ime_psa, String spol, String datum_izgubljen,
                           String napomena, long postavljeno, int Korisnik_id, String telefonskibr,
                           String pasmina, boolean aktivan, boolean oglas_azila, String url_slike){
        this.ime=ime;
        this.prezime=prezime;
        this.adresa=adresa;
        this.grad=grad;
        this.postanski_broj=postanski_broj;
        this.boja=boja;
        this.starost=starost;
        this.dlaka=dlaka;
        this.vet_lokacija=vet_lokacija;
        this.ime_psa=ime_psa;
        this.spol=spol;
        this.datum_izgubljen=datum_izgubljen;
        this.napomena=napomena;
        this.postavljeno=postavljeno;
        this.Korisnik_id=Korisnik_id;
        this.telefonskibr=telefonskibr;
        this.pasmina=pasmina;
        this.aktivan=aktivan;
        this.oglas_azila=oglas_azila;
        this.url_slike=url_slike;
    }
}
