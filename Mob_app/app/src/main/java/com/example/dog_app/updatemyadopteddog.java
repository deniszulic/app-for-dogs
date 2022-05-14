package com.example.dog_app;

public class updatemyadopteddog {
    //ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija,
    // ime_psa, spol, pasmina, kilaza, kastrat, opasnost, napomena, aktivan
    private String ime, prezime, adresa, grad, boja, dlaka, vet_lokacija, ime_psa, spol, pasmina, kastrat, opasnost, napomena;
    private int telefonskibr, starost, kilaza;
    private Integer postanski_broj;
    private boolean aktivan;
    public updatemyadopteddog(String ime, String prezime, String adresa, int telefonskibr, String grad,
                              Integer postanski_broj, String boja, int starost,
                              String dlaka, String vet_lokacija, String ime_psa, String spol,
                              String pasmina, int kilaza, String kastrat, String opasnost, String napomena, boolean aktivan){
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
        this.kastrat = kastrat;
        this.opasnost = opasnost;
        this.napomena = napomena;
        this.telefonskibr = telefonskibr;
        this.postanski_broj = postanski_broj;
        this.starost = starost;
        this.kilaza = kilaza;
        this.aktivan = aktivan;
    }
}
