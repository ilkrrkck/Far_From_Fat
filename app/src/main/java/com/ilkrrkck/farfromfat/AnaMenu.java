package com.ilkrrkck.farfromfat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AnaMenu extends AppCompatActivity {

    private final String admin_Mail = "ilker.kck@outlook.com";
    private final String admin_Sifre = "159753";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore mFireStore;
    private DocumentReference docRef;
    private CollectionReference colRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mFireStore=FirebaseFirestore.getInstance();
        mUser=mAuth.getCurrentUser();
        GirisYap();

        VerileriGetir();


    }

    private void VerileriGetir(){
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
    }

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