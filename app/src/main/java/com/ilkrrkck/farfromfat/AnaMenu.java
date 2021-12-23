package com.ilkrrkck.farfromfat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AnaMenu extends AppCompatActivity {

    private final String admin_Mail = "ilker.kck@outlook.com";
    private final String admin_Sifre = "159753";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    Programlar program1=new Programlar();
    Programlar program2=new Programlar();

   // private FirebaseFirestore mFireStore;
  //  private DocumentReference docRef;


    private DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        //mFireStore=FirebaseFirestore.getInstance();
        mReference=FirebaseDatabase.getInstance().getReference();
        mUser=mAuth.getCurrentUser(); //şuan kullanılmıyor
        HashMap<String,HashMap<String,Integer>> bb=new HashMap<String,HashMap<String,Integer>>();



        GirisYap();

        VerileriGetir2(bb);


    }


    private void VerileriGetir2(HashMap<String, HashMap<String, Integer>> bb){
        mReference.child("Program1").get() //ÇALIŞIYOR REALTİMEDAN DEVAM EDİLECEK
                .addOnCompleteListener(this, new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(!task.isSuccessful())
                            Log.e("firebase","Veri Alırken Hata",task.getException());
                        else{
                            //Log.d("firebase",String.valueOf(task.getResult().getKey())); // programAdı
                            //task.getResult().child("Mekik").getValue();
                            //System.out.println(task.getResult().child("Mekik").child("Tekrar"));
                            //String prog_No=task.getResult().getKey();
                            //System.out.println(task.getResult().getValue());
                            //HashMap<String, Integer> aa=task.getResult().getValue(); //HASHMAP
                            Object aa= task.getResult().getValue();
                            HashMap<String,HashMap<String,Integer>> bb=new HashMap<String,HashMap<String,Integer>>();
                            bb.putAll((HashMap)aa);
                            VeriYerlestir(bb);


                        }
                    }
                });

    }

    private void VeriYerlestir(HashMap<String,HashMap<String,Integer>> veri){
        for (String ex_Adi:veri.keySet())
        {
            //System.out.println(ex_Adi+veri.get(ex_Adi).get("Tekrar"));
            //System.out.println(ex_Adi+veri.get(ex_Adi).get("Set"));
            int set=veri.get(ex_Adi).get("Set");
            int tekrar=veri.get(ex_Adi).get("Tekrar");
            program1.exercises.add(new Egzersizler(set,tekrar,ex_Adi));
        }
        System.out.println(program1.toString());
    }
   /* private void VerileriGetir(){
        //colRef=mFireStore.collection("Program1_Gun1").document("BenchPress").collection("Tekrar"); //COL İÇİ VERİLER
        docRef=mFireStore.collection("Program1_Gun1").document("BenchPress"); // ŞİMDİLİK SADECE BENCHPRESS VERİLERİ collları GETİRDİ
        docRef.get()
                .addOnSuccessListener(this, new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            System.out.println("VERİLER  "+documentSnapshot.getData());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AnaMenu.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }*/

    private void GirisYap(){

        mAuth.signInWithEmailAndPassword(admin_Mail,admin_Sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            Toast.makeText(AnaMenu.this,"Giriş Başarılı",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AnaMenu.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    }
                });



    }
}