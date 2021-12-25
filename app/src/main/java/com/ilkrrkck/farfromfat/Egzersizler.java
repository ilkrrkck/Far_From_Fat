package com.ilkrrkck.farfromfat;

public class Egzersizler{
    public String getExAd() {
        return exAd;
    }

    public long getExSet() {
        return exSet;
    }

    public long getExTekrar() {
        return exTekrar;
    }

    public long getExDinlenme() {
        return exDinlenme;
    }

    //set sayı
    //tekrar sayı
    private String exAd;
    private long exSet;
    private long exTekrar;
    private long exDinlenme;

    public Egzersizler(long exSet, long exTekrar,String exAd){
        this.exSet=exSet;
        this.exTekrar=exTekrar;
        this.exAd=exAd;
    }

}
