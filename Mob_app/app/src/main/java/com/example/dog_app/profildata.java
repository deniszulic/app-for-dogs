package com.example.dog_app;

public class profildata {
    private String ime, prezime;
    private int id;
    private long datumreg;
    public profildata(String ime, String prezime, int id, long datumreg){
        this.ime=ime;
        this.prezime=prezime;
        this.id=id;
        this.datumreg=datumreg;
    }
    public profildata(String ime, String prezime){
        this.ime=ime;
        this.prezime=prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getId() {
        return id;
    }

    public long getDatumreg() {
        return datumreg;
    }
}
