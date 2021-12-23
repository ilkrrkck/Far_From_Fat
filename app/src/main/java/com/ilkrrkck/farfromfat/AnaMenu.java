package com.ilkrrkck.farfromfat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.time.format.TextStyle;
import java.util.HashMap;

public class AnaMenu extends AppCompatActivity {
    //region Firebase_Var
    private final String admin_Mail = "ilker.kck@outlook.com";
    private final String admin_Sifre = "159753";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mReference;
    //endregion

    private LinearLayout dis_linearLayout;
    private RecyclerView mRecyclerView;
    private ProgramlarAdapter adapter;
    //endregion

    Programlar program1=new Programlar("Program1");
    Programlar program2=new Programlar("Program2");







    private void anaSayfaTasarim(){

        /*
        dis_linearLayout = new LinearLayout(this);
        dis_linearLayout.setOrientation(LinearLayout.VERTICAL);


        LinearLayout ic_linearLayout = new LinearLayout(this);
        ic_linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams ic_linearLayout_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        ic_linearLayout_params.leftMargin=5;
        ic_linearLayout_params.rightMargin=5;
        ic_linearLayout.setLayoutParams(ic_linearLayout_params);

        for(int i=0;i<2;i++)
        {

            //region Tasarım_Var
            ImageView program_imageView = new ImageView(this);
            LinearLayout.LayoutParams imageView_params = new LinearLayout.LayoutParams(96, 96);
            program_imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            program_imageView.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal);
            program_imageView.setLayoutParams(imageView_params);


            LinearLayout.LayoutParams programAdi_textView_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView programAdi_textView = new TextView(this);
            programAdi_textView.setTextSize(19);
            programAdi_textView.setText("PROGRAM");
            programAdi_textView.setTypeface(Typeface.DEFAULT_BOLD);
            programAdi_textView.setLayoutParams(programAdi_textView_params);
            ic_linearLayout.addView(programAdi_textView);
            dis_linearLayout.addView(program_imageView);
            dis_linearLayout.addView(ic_linearLayout);

        }

         */
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        program1.Prog_ListeyeEkle(program1);
        program2.Prog_ListeyeEkle(program2);
        //anaSayfaTasarim();
        //setContentView(R.layout.programlar);
        mAuth = FirebaseAuth.getInstance();
        //mFireStore=FirebaseFirestore.getInstance();
        mReference=FirebaseDatabase.getInstance().getReference();
        mUser=mAuth.getCurrentUser(); //şuan kullanılmıyor
        HashMap<String,HashMap<String,Integer>> bb=new HashMap<String,HashMap<String,Integer>>();

        mRecyclerView=(RecyclerView)findViewById(R.id.AnaMenu_recyclerView);
        adapter=new ProgramlarAdapter(Programlar.veriyiDondur(),this);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);


        GirisYap();

        VerileriGetir2(program1,bb);


        adapter.setOnItemClickListener(new ProgramlarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programlar program, int position) {
                Toast.makeText(getApplicationContext(),program.getProg_Ad(),Toast.LENGTH_LONG).show();
            }
        });
    }


    private void VerileriGetir2(Programlar program,HashMap<String, HashMap<String, Integer>> bb){
        mReference.child(program.getProg_Ad()).get() //ÇALIŞIYOR REALTİMEDAN DEVAM EDİLECEK
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
                            HashMap<String,HashMap<String, Long>> bb= new HashMap<String , HashMap<String, Long>>();
                            bb.putAll((HashMap)aa);
                            VeriYerlestir(program,bb);


                        }
                    }
                });

    }

    private void VeriYerlestir(Programlar program,HashMap<String,HashMap<String,Long>> veri){
        for (String ex_Adi:veri.keySet())
        {
            //System.out.println(ex_Adi+veri.get(ex_Adi).get("Tekrar"));
            //System.out.println(ex_Adi+veri.get(ex_Adi).get("Set"));
            long set= veri.get(ex_Adi).get("Set");
            long tekrar=veri.get(ex_Adi).get("Tekrar");
            program.exercises.add(new Egzersizler(set,tekrar,ex_Adi)); // ASIL OLAY
        }

        /**
         *         for (Egzersizler i : program1.exercises) {
         *             System.out.println(i.getExAd()+i.getExSet()+i.getExTekrar());
         *         }
         *
         *         VERİLER ÇEKİLEBİLİYOR VE NESNELER OLUŞTURULDU
         */
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

    private void CikisYap(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        //UYGULAMADAN ÇIKMAK
    }


    @Override
    public void onBackPressed() {
        CikisYap();
    }
}