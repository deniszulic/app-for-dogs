package com.example.dog_app;

public class reportsonmyadopteddog_data {

    private String ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave, prihvaceno,
            napomena, udomljavanje_ime, udomljavanje_prezime, udomljavanje_adresa, telefonskibr,
    udomljavanje_grad, udomljavanje_postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa,
            spol, pasmina, kilaza, kastrat, opasnost, udomljavanje_napomena, url_slike;
    private long postavljeno;
    private int id;

    public reportsonmyadopteddog_data(String ime, String prezime, String adresa, String grad, String postanski_broj,
                                     String kontakt, String razlog_prijave, String prihvaceno, long postavljeno, String napomena,
                                     int id, String udomljavanje_ime,
                                     String udomljavanje_prezime, String udomljavanje_adresa, String telefonskibr,
                                     String udomljavanje_grad, String udomljavanje_postanski_broj, String boja, String starost,
                                     String dlaka, String vet_lokacija, String ime_psa, String spol, String pasmina,
                                     String kilaza, String kastrat, String opasnost, String udomljavanje_napomena,
                                     String url_slike){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.kontakt = kontakt;
        this.razlog_prijave = razlog_prijave;
        this.prihvaceno = prihvaceno;
        this.napomena = napomena;
        this.udomljavanje_ime = udomljavanje_ime;
        this.udomljavanje_prezime = udomljavanje_prezime;
        this.udomljavanje_adresa = udomljavanje_adresa;
        this.telefonskibr = telefonskibr;
        this.udomljavanje_grad = udomljavanje_grad;
        this.udomljavanje_postanski_broj = udomljavanje_postanski_broj;
        this.boja = boja;
        this.starost = starost;
        this.dlaka = dlaka;
        this.vet_lokacija = vet_lokacija;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.pasmina = pasmina;
        this.kilaza = kilaza;
        this.kastrat = kastrat;
        this.opasnost = opasnost;
        this.udomljavanje_napomena = udomljavanje_napomena;
        this.url_slike = url_slike;
        this.postavljeno = postavljeno;
        this.id = id;
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

    public String getPrihvaceno() {
        return prihvaceno;
    }

    public String getNapomena() {
        return napomena;
    }

    public String getUdomljavanje_ime() {
        return udomljavanje_ime;
    }

    public String getUdomljavanje_prezime() {
        return udomljavanje_prezime;
    }

    public String getUdomljavanje_adresa() {
        return udomljavanje_adresa;
    }

    public String getTelefonskibr() {
        return telefonskibr;
    }

    public String getUdomljavanje_grad() {
        return udomljavanje_grad;
    }

    public String getUdomljavanje_postanski_broj() {
        return udomljavanje_postanski_broj;
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

    public String getKilaza() {
        return kilaza;
    }

    public String getKastrat() {
        return kastrat;
    }

    public String getOpasnost() {
        return opasnost;
    }

    public String getUdomljavanje_napomena() {
        return udomljavanje_napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public int getId() {
        return id;
    }
}
