package com.ilkrrkck.farfromfat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

public class EgzersizMenu extends AppCompatActivity implements Serializable {

    private RecyclerView mRecyclerView;
    private EgzersizlerAdapter adapter2;
    private Intent exMenuIntent;
    private Context context;
    private ArrayList<Egzersizler> exArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=(RecyclerView)findViewById(R.id.AnaMenu_recyclerView);

        adapter2=new EgzersizlerAdapter(exArrayList);

        Intent gelenintent = getIntent(); //KENDİNDEN BİR ÖNCEKİ İNTENTİ YAKALAR VE DEĞİŞKENE ATAR

        //exArrayList = (ArrayList<Egzersizler>) gelenintent.getSerializableExtra("array");

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setAdapter(adapter2);
    }
    public void VeriCek(ArrayList<Egzersizler> exArrayList){
        this.exArrayList=exArrayList;
    }

}