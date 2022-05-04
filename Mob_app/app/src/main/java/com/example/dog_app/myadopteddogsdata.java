package com.example.dog_app;

public class myadopteddogsdata {
    private int id;
    private String ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka,
            vet_lokacija, ime_psa, spol, napomena, url_slike, pasmina, kilaza, kastrat, opasnost,
            azil_udomljavanje_napomena, prihvaceno;
    private long postavljeno;
    private boolean aktivan;
    public myadopteddogsdata(int id, String ime, String prezime, String adresa, String telefonskibr,
                             String grad, String postanski_broj, String boja, String starost,
                             String dlaka, String vet_lokacija, String ime_psa, String spol,
                             String napomena, String url_slike, long postavljeno, String pasmina,
                             String kilaza, String kastrat, String opasnost, boolean aktivan,
                             String azil_udomljavanje_napomena, String prihvaceno){
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefonskibr = telefonskibr;
        this.grad = grad;
        this.postanski_broj = postanski_broj;
        this.boja = boja;
        this.starost = starost;
        this.dlaka = dlaka;
        this.vet_lokacija = vet_lokacija;
        this.ime_psa = ime_psa;
        this.spol = spol;
        this.napomena = napomena;
        this.url_slike = url_slike;
        this.pasmina = pasmina;
        this.kilaza = kilaza;
        this.kastrat = kastrat;
        this.opasnost = opasnost;
        this.azil_udomljavanje_napomena = azil_udomljavanje_napomena;
        this.prihvaceno = prihvaceno;
        this.postavljeno = postavljeno;
        this.aktivan = aktivan;
    }

    public int getId() {
        return id;
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

    public String getPostanski_broj() {
        return postanski_broj;
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

    public String getNapomena() {
        return napomena;
    }

    public String getUrl_slike() {
        return url_slike;
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

    public String getAzil_udomljavanje_napomena() {
        return azil_udomljavanje_napomena;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }

    public long getPostavljeno() {
        return postavljeno;
    }

    public boolean isAktivan() {
        return aktivan;
    }
}
