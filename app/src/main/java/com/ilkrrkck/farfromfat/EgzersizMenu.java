package com.ilkrrkck.farfromfat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;


public class EgzersizMenu extends AppCompatActivity {




    ArrayList<String> exIsimler;
    ArrayList<Long> tekrarList;
    ArrayList<Long> setList;
    HashMap<String,Long> setArraylist;
    HashMap<String,Long> tekrarArraylist;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;


    Programlar program=new Programlar();

    private String secim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egzersiz_menu);

        expandableListView=findViewById(R.id.ex_exListView);

        MainAdapter adapter;
        Intent gelenintent = getIntent(); //KENDİNDEN BİR ÖNCEKİ İNTENTİ YAKALAR VE DEĞİŞKENE ATAR
        String secim=gelenintent.getStringExtra("secim"); //KEY VALUE İLE ÇALIŞIYOR

    }

    public void VeriYerlestir(Programlar program,HashMap<String, HashMap<String, Long>> veri){
        this.program=program;
        for (String ex_Adi:veri.keySet())
        {
            System.out.println(ex_Adi+veri.get(ex_Adi).get("Tekrar"));
            System.out.println(ex_Adi+veri.get(ex_Adi).get("Set"));
            long set= veri.get(ex_Adi).get("Set");
            long tekrar=veri.get(ex_Adi).get("Tekrar");
            exIsimler.add(ex_Adi);
            setList.add(set);
            tekrarList.add(tekrar);
            program.exercises.add(new Egzersizler(set,tekrar,ex_Adi)); // ASIL OLAY
        }
    }
}