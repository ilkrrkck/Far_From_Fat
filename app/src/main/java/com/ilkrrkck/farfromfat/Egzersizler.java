package com.ilkrrkck.farfromfat;

public class Egzersizler{
    //set sayı
    //tekrar sayı
    private String exAd;
    private int exSet;
    private int exTekrar;
    private int exDinlenme;

    public Egzersizler(int exSet, int exTekrar,String exAd){
        this.exSet=exSet;
        this.exTekrar=exTekrar;
        this.exAd=exAd;
    }

    //region Setters
    public void setExDinlenme(int exDinlenme) {
        this.exDinlenme = exDinlenme;
    }

    public void setExSet(int exSet) {
        this.exSet = exSet;
    }

    public void setExTekrar(int exTekrar) {
        this.exTekrar = exTekrar;
    }
    //endregion

    //region Getters
    public int getExTekrar() {
        return exTekrar;
    }
    public int getExSet() {
        return exSet;
    }
    public int getExDinlenme() {
        return exDinlenme;
    }

    public void setExAd(String exAd) {
        this.exAd = exAd;
    }

    public String getExAd() {
        return exAd;
    }
    //endregion
}
