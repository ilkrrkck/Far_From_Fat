package com.ilkrrkck.farfromfat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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

import java.io.Serializable;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;

public class AnaMenu extends AppCompatActivity implements Serializable {
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

    private Context context;
    private ArrayList<Egzersizler> egzersizlers;

    Programlar program1 = new Programlar("Program1");
    Programlar program2 = new Programlar("Program2");


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
        mReference = FirebaseDatabase.getInstance().getReference();
        mUser = mAuth.getCurrentUser(); //??uan kullan??lm??yor
        HashMap<String, HashMap<String, Long>> bb = new HashMap<String, HashMap<String, Long>>();

        mRecyclerView = (RecyclerView) findViewById(R.id.AnaMenu_recyclerView);
        adapter = new ProgramlarAdapter(Programlar.veriyiDondur(), this);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);


        GirisYap();

        VerileriGetir2(program1, bb);


        adapter.setOnItemClickListener(new ProgramlarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programlar program, int position) {

                Toast.makeText(getApplicationContext(), program.getProg_Ad(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AnaMenu.this, EgzersizMenu.class); //nereden nereye gitti??imizi belirttik
                //SAYFA GE???????? SA??LANDI

                //intent.putExtra("array", egzersizlers); //KEY VALUE ??LE ??ALI??IYOR

                EgzersizMenu exMenu=new EgzersizMenu();
                exMenu.VeriCek(egzersizlers);

                finish(); // ge??erli aktiviteyi kapat??r. Loginli i??lemler i??in

                startActivity(intent); // intenti ba??lat

            }
        });


    }


    private void VerileriGetir2(Programlar program, HashMap<String, HashMap<String, Long>> bb) {
        mReference.child(program.getProg_Ad()).get() //??ALI??IYOR REALT??MEDAN DEVAM ED??LECEK
                .addOnCompleteListener(this, new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful())
                            Log.e("firebase", "Veri Al??rken Hata", task.getException());
                        else {
                            Object aa = task.getResult().getValue();
                            HashMap<String, HashMap<String, Long>> bb = new HashMap<String, HashMap<String, Long>>();
                            bb.putAll((HashMap) aa);
                            VeriYerlestir(program, bb);
                            //EgzersizlerAdapter.VeriGetir(); //EGZERS??Z ADAPTER'A VER?? G??NDER??LD??
                        }
                    }
                });
    }


    public ArrayList<Egzersizler> VeriYerlestir(Programlar program, HashMap<String, HashMap<String, Long>> veri) {
        for (String ex_Adi : veri.keySet()) {
            //System.out.println(ex_Adi+veri.get(ex_Adi).get("Tekrar"));
            //System.out.println(ex_Adi+veri.get(ex_Adi).get("Set"));
            long set = veri.get(ex_Adi).get("Set");
            long tekrar = veri.get(ex_Adi).get("Tekrar");
            program.exercises.add(new Egzersizler(set, tekrar, ex_Adi)); // ASIL OLAY
        }
        egzersizlers = program.exercises;
        return program.exercises;
    }


    private void GirisYap() {

        mAuth.signInWithEmailAndPassword(admin_Mail, admin_Sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            Toast.makeText(AnaMenu.this, "Giri?? Ba??ar??l??", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AnaMenu.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void CikisYap() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        //UYGULAMADAN ??IKMAK
    }


    @Override
    public void onBackPressed() {
        CikisYap();
    }
}