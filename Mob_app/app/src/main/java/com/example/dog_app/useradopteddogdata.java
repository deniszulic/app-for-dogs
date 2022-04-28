package com.example.dog_app;

public class useradopteddogdata {
    private String ime,  prezime,  adresa,  telefonskibr, grad, boja, dlaka, vet_lokacija,  ime_psa,
            spol,  napomena, url_slike,  pasmina, kastrat,  opasnost,  email;
    private int id, starost, postanski_broj, kilaza;
    private long postavljeno;

    public useradopteddogdata(int id, String ime, String prezime, String adresa, String telefonskibr,
                    String grad, int postanski_broj, String boja, int starost, String dlaka,
                    String vet_lokacija, String ime_psa, String spol, String napomena,
                    String url_slike, long postavljeno, String pasmina, int kilaza,
                    String kastrat, String opasnost, String email){
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefonskibr = telefonskibr;
        this.grad = grad;
        this.boja = boja;
        this.dlaka = dlaka;
        this.vet_lokacija = vet_lokacija;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.napomena = napomena;
        this.url_slike = url_slike;
        this.pasmina = pasmina;
        this.kastrat = kastrat;
        this.opasnost = opasnost;
        this.email = email;
        this.id = id;
        this.starost = starost;
        this.postanski_broj = postanski_broj;
        this.kilaza = kilaza;
        this.postavljeno=postavljeno;
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

    public String getNapomena() {
        return napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getPasmina() {
        return pasmina;
    }

    public String getKastrat() {
        return kastrat;
    }

    public String getOpasnost() {
        return opasnost;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getStarost() {
        return starost;
    }

    public int getPostanski_broj() {
        return postanski_broj;
    }

    public int getKilaza() {
        return kilaza;
    }

    public long getPostavljeno() {
        return postavljeno;
    }
}
