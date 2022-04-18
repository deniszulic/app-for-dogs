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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class transition_login extends AppCompatActivity {

    private TextInputLayout email,password;
    private AppCompatButton login_btn,login_transition;
    private ImageView imageView;
    private TextView headline_login_transition;
    private LinearLayout container;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

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
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        login_transition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", email.getEditText().getText().toString());
                map.put("lozinka", password.getEditText().getText().toString());

                Call<getlogindata[]> call = retrofitInterface.login(map);

//                logindata data=new logindata(email.getEditText().getText().toString(), password.getEditText().getText().toString());
//                Call<Void> call=retrofitInterface.login(data);
                call.enqueue(new Callback<getlogindata[]>() {
                    @Override
                    public void onResponse(Call<getlogindata[]> call, Response<getlogindata[]> response) {
                        if (response.code() == 200) {
                            getlogindata[] result= response.body();
                            System.out.println("ispis:"+result[0].getId()+" tipkorisnika:"+result[0].getTipkorisnika()+" email:"+result[0].getEmail()+" ime:"+result[0].getIme()+" prezime:"+result[0].getPrezime());
                            SharedPreferences sp=transition_login.this.getSharedPreferences("userdata", Context.MODE_PRIVATE);
                            SharedPreferences.Editor data=sp.edit();
                            data.putString("email", result[0].getEmail());
                            data.putString("tipkorisnika", result[0].getTipkorisnika());
                            data.putInt("id", result[0].getId());
                            data.putString("ime", result[0].getIme());
                            data.putString("prezime", result[0].getPrezime());
                            data.commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<getlogindata[]> call, Throwable t) {
                        //System.out.println(t);
                        Toast.makeText(transition_login.this, t.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
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