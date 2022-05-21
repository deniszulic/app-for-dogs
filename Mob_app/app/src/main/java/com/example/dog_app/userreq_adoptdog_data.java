package com.example.dog_app;

public class userreq_adoptdog_data {
    //udomljavanje.ime, udomljavanje.prezime, udomljavanje.adresa, udomljavanje.telefonskibr, udomljavanje.grad, udomljavanje.postanski_broj, udomljavanje.boja,
    // udomljavanje.starost, udomljavanje.dlaka, udomljavanje.vet_lokacija, udomljavanje.ime_psa, udomljavanje.spol, udomljavanje.pasmina, udomljavanje.kilaza,
    // udomljavanje.kastrat, udomljavanje.opasnost, udomljavanje.napomena, udomljavanje.url_slike, azil_udomljavanje.napomena as azil_udomljavanje_napomena,
    // azil_udomljavanje.prihvaceno, azil_udomljavanje.postavljeno as azil_udomljavanje_postavljeno, azil_udomljavanje.id, udomljavanje.id as udomljavanje_id
    private String ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, kilaza, kastrat, opasnost, napomena, url_slike, azil_udomljavanje_napomena, prihvaceno;
    private Integer id, udomljavanje_id;
    private long azil_udomljavanje_postavljeno;
    public userreq_adoptdog_data(String ime, String prezime, String adresa, String telefonskibr, String grad, String postanski_broj,
                                 String boja, String starost, String dlaka, String vet_lokacija, String ime_psa, String spol,
                                 String pasmina, String kilaza, String kastrat, String opasnost, String napomena, String url_slike,
                                 String azil_udomljavanje_napomena, String prihvaceno, long azil_udomljavanje_postavljeno, Integer id,
                                 Integer udomljavanje_id){
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
        this.pasmina = pasmina;
        this.kilaza = kilaza;
        this.kastrat = kastrat;
        this.opasnost = opasnost;
        this.napomena = napomena;
        this.url_slike = url_slike;
        this.azil_udomljavanje_napomena = azil_udomljavanje_napomena;
        this.prihvaceno = prihvaceno;
        this.id = id;
        this.udomljavanje_id = udomljavanje_id;
        this.azil_udomljavanje_postavljeno = azil_udomljavanje_postavljeno;
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

    public String getNapomena() {
        return napomena;
    }

    public String getUrl_slike() {
        return url_slike;
    }

    public String getAzil_udomljavanje_napomena() {
        return azil_udomljavanje_napomena;
    }

    public String getPrihvaceno() {
        return prihvaceno;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUdomljavanje_id() {
        return udomljavanje_id;
    }

    public long getPostavljeno() {
        return azil_udomljavanje_postavljeno;
    }
}
