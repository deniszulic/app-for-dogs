package com.example.dog_app;

public class myreportsadopteddogs_data {
    private String ime, prezime, adresa, grad, postanski_broj, kontakt, razlog_prijave, prihvaceno, napomena, naziv_azila, grad_azila, ime_udomljavanje, prezime_udomljavanje, adresa_udomljavanje, telefonskibr, grad_udomljavanje, postanski_broj_udomljavanje, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, kilaza, kastrat, opasnost, napomena_udomljavanje, url_slike;
    private boolean oglas_azila;
    private long postavljeno;
    private int id;
    public myreportsadopteddogs_data(String ime, String prezime, String adresa, String grad, String postanski_broj,
                                     String kontakt, String razlog_prijave, String prihvaceno, long postavljeno, String napomena,
                                     int id, String naziv_azila, String grad_azila, String ime_udomljavanje,
                                     String prezime_udomljavanje, String adresa_udomljavanje, String telefonskibr,
                                     String grad_udomljavanje, String postanski_broj_udomljavanje, String boja, String starost,
                                     String dlaka, String vet_lokacija, String ime_psa, String spol, String pasmina,
                                     String kilaza, String kastrat, String opasnost, String napomena_udomljavanje,
                                     String url_slike, boolean oglas_azila){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.kontakt = kontakt;
        this.razlog_prijave = razlog_prijave;
        this.prihvaceno = prihvaceno;
        this.napomena = napomena;
        this.naziv_azila = naziv_azila;
        this.grad_azila = grad_azila;
        this.ime_udomljavanje = ime_udomljavanje;
        this.prezime_udomljavanje = prezime_udomljavanje;
        this.adresa_udomljavanje = adresa_udomljavanje;
        this.telefonskibr = telefonskibr;
        this.grad_udomljavanje = grad_udomljavanje;
        this.postanski_broj_udomljavanje = postanski_broj_udomljavanje;
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
        this.napomena_udomljavanje = napomena_udomljavanje;
        this.url_slike = url_slike;
        this.oglas_azila = oglas_azila;
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

    public String getNaziv_azila() {
        return naziv_azila;
    }

    public String getGrad_azila() {
        return grad_azila;
    }

    public String getIme_udomljavanje() {
        return ime_udomljavanje;
    }

    public String getPrezime_udomljavanje() {
        return prezime_udomljavanje;
    }

    public String getAdresa_udomljavanje() {
        return adresa_udomljavanje;
    }

    public String getTelefonskibr() {
        return telefonskibr;
    }

    public String getGrad_udomljavanje() {
        return grad_udomljavanje;
    }

    public String getPostanski_broj_udomljavanje() {
        return postanski_broj_udomljavanje;
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

    public String getNapomena_udomljavanje() {
        return napomena_udomljavanje;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public boolean isOglas_azila() {
        return oglas_azila;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public int getId() {
        return id;
    }
}
