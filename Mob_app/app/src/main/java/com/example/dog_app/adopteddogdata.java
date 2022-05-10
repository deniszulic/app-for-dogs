package com.example.dog_app;

public class adopteddogdata {
    private String ime, prezime, adresa, grad, boja, dlaka, vet_lokacija, ime_psa, spol,
            napomena, telefonskibr, pasmina, url_slike, kastrat, opasnost;
    private boolean aktivan, oglas_azila;
    private int starost, Korisnik_id, kg;
    private Integer postanski_broj;
    private long postavljeno;
    //ime, prezime, adresa, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol,
    // napomena, postavljeno, Korisnik_id, telefonskibr, pasmina, kg, kastrat, opasnost, aktivan, url_slike, oglas_azila
    public adopteddogdata(String ime, String prezime, String adresa, String grad,
                           Integer postanski_broj, String boja, int starost, String dlaka,
                           String vet_lokacija, String ime_psa, String spol, String napomena,
                          long postavljeno, int Korisnik_id, String telefonskibr, String pasmina, int kg,
                          String kastrat, String opasnost, boolean aktivan, String url_slike,
                          boolean oglas_azila){
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
        this.napomena=napomena;
        this.postavljeno=postavljeno;
        this.Korisnik_id=Korisnik_id;
        this.telefonskibr=telefonskibr;
        this.pasmina=pasmina;
        this.aktivan=aktivan;
        this.oglas_azila=oglas_azila;
        this.url_slike=url_slike;
        this.kg=kg;
        this.opasnost=opasnost;
        this.kastrat=kastrat;
    }
}
