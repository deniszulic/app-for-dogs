package com.example.dog_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class transition_login extends AppCompatActivity {

    private TextInputLayout email,password;
    private AppCompatButton login_btn,login_transition;
    private ImageView imageView;
    private TextView headline_login_transition;
    private LinearLayout container;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_login);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        email=(TextInputLayout) findViewById(R.id.email_log_transition);
        password=(TextInputLayout) findViewById(R.id.pass_log_transition);
        login_btn=(AppCompatButton) findViewById(R.id.login_btn_transition);
        login_transition=(AppCompatButton) findViewById(R.id.gotoregistration_transition);
        imageView=(ImageView) findViewById(R.id.header_image_tr_log);
        headline_login_transition=(TextView) findViewById(R.id.headline_login_transition);
        //mAuth = FirebaseAuth.getInstance();

        login_transition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                mAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
//                        .addOnCompleteListener(transition_login.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    /*Toast.makeText(getContext(), "Uspjesna prijava",
//                                            Toast.LENGTH_SHORT).show();*/
//                                    //FirebaseUser user = mAuth.getCurrentUser();
//                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
//                                    System.out.println("email:"+user.getEmail());
//                                    if (user != null) {
//                                        DocumentReference docRef = db.collection("korisnici").document(user.getEmail());
//                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                                if (task.isSuccessful()) {
//                                                    DocumentSnapshot document = task.getResult();
//                                                    if (document.exists()) {
//                                                        String korisnik= (String) document.getData().get("tipKorisnika");
//                                                        if(korisnik.equals("Korisnik")){
//                                                            System.out.println("korisnik");
//                                                            SharedPreferences sp=transition_login.this.getSharedPreferences("Login", Context.MODE_PRIVATE);
//                                                            SharedPreferences.Editor data=sp.edit();
//                                                            data.putString("email", user.getEmail());
//                                                            data.putString("tipKorisnika", korisnik);
//                                                            data.commit();
//                                                            Intent i = new Intent(transition_login.this, Pocetni_zaslon.class);
//                                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                                            startActivity(i);
//                                                            transition_login.this.finish();
//                                                        }
//                                                        if(korisnik.equals("Poduzeće")){
//                                                            System.out.println("Poduzeće");
//                                                            SharedPreferences sp=transition_login.this.getSharedPreferences("Login", Context.MODE_PRIVATE);
//                                                            SharedPreferences.Editor data=sp.edit();
//                                                            data.putString("email", user.getEmail());
//                                                            data.putString("tipKorisnika", korisnik);
//                                                            data.commit();
//                                                            Intent i= new Intent(transition_login.this, pocetni_zaslon_poduzece.class);
//                                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                                            startActivity(i);
//                                                            transition_login.this.finish();
//                                                        }
//                                                    } else {
//                                                        Log.d("No document", "No such document");
//                                                    }
//                                                } else {
//                                                    Log.d("Fail", "get failed with ", task.getException());
//                                                }
//                                            }
//                                        });
//                                    }
//                                } else {
//                                    /*Toast.makeText(getContext(), "Neuspjesna prijava",
//                                            Toast.LENGTH_SHORT).show();*/
//                                }
//                            }
//                        });
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}