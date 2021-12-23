package com.ilkrrkck.farfromfat;

import java.util.ArrayList;

public class Programlar{

    private static ArrayList<Programlar> programlar=new ArrayList<Programlar>();
    private String prog_Ad;
    ArrayList<Egzersizler> exercises=new ArrayList<Egzersizler>();
    public Programlar(String prog_Ad){
        this.prog_Ad=prog_Ad;
    }
    public Programlar(){

    }
    public void Prog_ListeyeEkle(Programlar program){
        programlar.add(program);
    }
    //egzersizler list  egzersiz nesnelerini tut
    //
    public String getProg_Ad() {
        return prog_Ad;
    }

    static public ArrayList<Programlar> veriyiDondur(){
        return programlar;
    }
}
