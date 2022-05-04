package com.example.dog_app;

import java.util.Date;

public class ListItem {
    private String ime, prezime, adresa, telefonskibr, grad, boja, dlaka, vet_lokacija, ime_psa,
    spol, napomena, url_slike, pasmina, email;
    private long postavljeno;
    private int id, postanski_broj, starost;
    private Date datum_izgubljen;

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

    public String getBoja() {
        return boja;
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

    public String getNapomena() {
        return napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getPasmina() {
        return pasmina;
    }

    public String getEmail() {
        return email;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public int getId() {
        return id;
    }

    public int getPostanski_broj() {
        return postanski_broj;
    }

    public int getStarost() {
        return starost;
    }

    public ListItem(int id, String ime, String prezime, String adresa, String telefonskibr,
                    String grad, int postanski_broj, String boja, int starost, String dlaka,
                    String vet_lokacija, String ime_psa, String spol, Date datum_izgubljen,
                    String napomena, String url_slike, long postavljeno, String pasmina, String email){
        this.id=id;
        this.ime=ime;
        this.prezime=prezime;
        this.adresa=adresa;
        this.telefonskibr=telefonskibr;
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
        this.url_slike=url_slike;
        this.postavljeno=postavljeno;
        this.pasmina=pasmina;
        this.email=email;
    }
    public ListItem(){};
}
