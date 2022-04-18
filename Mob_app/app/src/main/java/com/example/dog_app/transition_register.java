package com.example.dog_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class transition_register extends AppCompatActivity {
    private TextInputLayout email,password, ime_reg, prezime_reg, oib, naziv, ulica_reg, kucnibr_reg, grad_reg, postnum_reg;
    private AppCompatButton register_btn;
    private TextView textView2;
    private AutoCompleteTextView autoCompleteTextView;
    private boolean isAllFieldsChecked = false;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    private AppCompatButton gotologin_transition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_register);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        gotologin_transition = findViewById(R.id.gotologin_transition);
        email= (TextInputLayout) findViewById(R.id.email_reg_tr);
        password=(TextInputLayout)findViewById(R.id.pass_reg_tr);
        register_btn=(AppCompatButton) findViewById(R.id.register_btn_tr);
        textView2=(TextView) findViewById(R.id.textView2_tr);
        ime_reg=(TextInputLayout) findViewById(R.id.ime_reg_tr);
        prezime_reg=(TextInputLayout) findViewById(R.id.prezime_reg_tr);
        autoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.autoComplete_reg_tr);
        oib = (TextInputLayout) findViewById(R.id.oib_reg_tr);
        naziv = (TextInputLayout) findViewById(R.id.naziv_reg_tr);
        ulica_reg= (TextInputLayout) findViewById(R.id.ulica_reg_tr);
        kucnibr_reg=(TextInputLayout) findViewById(R.id.kucnibr_reg_tr);
        grad_reg=(TextInputLayout) findViewById(R.id.grad_reg_tr);
        postnum_reg=(TextInputLayout) findViewById(R.id.postnum_reg_tr);

        String[] usertype = {"Korisnik", "Azil"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(transition_register.this, R.layout.dropdown_item, usertype);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);
        if (autoCompleteTextView.getText().toString().equals("Korisnik")) {
            oib.setVisibility(View.GONE);
            naziv.setVisibility(View.GONE);
            ulica_reg.setVisibility(View.GONE);
            kucnibr_reg.setVisibility(View.GONE);
            grad_reg.setVisibility(View.GONE);
            postnum_reg.setVisibility(View.GONE);
        }
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (autoCompleteTextView.getText().toString().equals("Korisnik")) {
                    oib.setVisibility(View.GONE);
                    naziv.setVisibility(View.GONE);
                    ulica_reg.setVisibility(View.GONE);
                    kucnibr_reg.setVisibility(View.GONE);
                    grad_reg.setVisibility(View.GONE);
                    postnum_reg.setVisibility(View.GONE);
                }
                if (autoCompleteTextView.getText().toString().equalsIgnoreCase("Azil")) {
                    oib.setVisibility(View.VISIBLE);
                    naziv.setVisibility(View.VISIBLE);
                    ulica_reg.setVisibility(View.VISIBLE);
                    kucnibr_reg.setVisibility(View.VISIBLE);
                    grad_reg.setVisibility(View.VISIBLE);
                    postnum_reg.setVisibility(View.VISIBLE);
                }
            }
        });
        gotologin_transition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoCompleteTextView.getText().toString().equals("Korisnik")) {
                    isAllFieldsChecked = checkfields();
                    if(isAllFieldsChecked){
                        Date date = new Date();
                        long timestamp = date.getTime();
                        registerdata data=new registerdata(ime_reg.getEditText().getText().toString(), email.getEditText().getText().toString(), password.getEditText().getText().toString(), prezime_reg.getEditText().getText().toString(), "korisnik", timestamp);
                        Call<Void> call=retrofitInterface.postJson(data);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                //System.out.println(t);
                                Toast.makeText(transition_register.this, t.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                if (autoCompleteTextView.getText().toString().equals("Azil")) {
                    isAllFieldsChecked = checkfields_azil();
                    if(isAllFieldsChecked){
                        Date date = new Date();
                        long timestamp = date.getTime();
                        registerdata data=new registerdata(ime_reg.getEditText().getText().toString(), email.getEditText().getText().toString(), password.getEditText().getText().toString(), prezime_reg.getEditText().getText().toString(), "azil", timestamp);

                        Call<Integer> call = retrofitInterface.register(data);
                        call.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.code() == 201) {
                                    int userid=response.body();
                                    if(response.body()>=0){
                                        registershelter shelter= new registershelter(oib.getEditText().getText().toString(), ulica_reg.getEditText().getText().toString(), kucnibr_reg.getEditText().getText().toString(), grad_reg.getEditText().getText().toString(), postnum_reg.getEditText().getText().toString(), naziv.getEditText().getText().toString(), response.body());
                                        Call<Integer> regshelter =retrofitInterface.shelterregister(shelter);
                                        regshelter.enqueue(new Callback<Integer>() {
                                            @Override
                                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                            }

                                            @Override
                                            public void onFailure(Call<Integer> call, Throwable t) {
                                                Call<Void> delete=retrofitInterface.deleteuser(userid);
                                                Toast.makeText(transition_register.this, t.toString(),
                                                        Toast.LENGTH_LONG).show();
                                                delete.enqueue(new Callback<Void>() {
                                                    @Override
                                                    public void onResponse(Call<Void> call, Response<Void> response) {

                                                    }

                                                    @Override
                                                    public void onFailure(Call<Void> call, Throwable t) {
                                                        Toast.makeText(transition_register.this, t.toString(),
                                                                Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                //System.out.println(t);
                                Toast.makeText(transition_register.this, t.toString(),
                                        Toast.LENGTH_LONG).show();
                                System.out.println(t);
                            }
                        });
                    }
                }
            }
        });
    }

    private boolean checkfields() {
        if (TextUtils.isEmpty(ime_reg.getEditText().getText().toString())) {
            ime_reg.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_reg.getEditText().getText().toString())) {
            prezime_reg.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(email.getEditText().getText().toString())) {
            email.setError("Potreban je email!");
            return false;
        } else if (TextUtils.isEmpty(password.getEditText().getText().toString())) {
            password.setError("Potrebna je lozinka!");
            return false;
        }
        return true;
    }

    private boolean checkfields_azil() {
        if (TextUtils.isEmpty(ime_reg.getEditText().getText().toString())) {
            ime_reg.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_reg.getEditText().getText().toString())) {
            prezime_reg.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(email.getEditText().getText().toString())) {
            email.setError("Potreban je email!");
            return false;
        }
        if (TextUtils.isEmpty(password.getEditText().getText().toString())) {
            password.setError("Potrebna je lozinka!");
            return false;
        }
        if (TextUtils.isEmpty(oib.getEditText().getText().toString())) {
            oib.setError("Potreban je oib!");
            return false;
        }
        if (TextUtils.isEmpty(naziv.getEditText().getText().toString())) {
            naziv.setError("Potreban je naziv azila!");
            return false;
        }
        if (TextUtils.isEmpty(ulica_reg.getEditText().getText().toString())) {
            ulica_reg.setError("Potrebna je ulica!");
            return false;
        }
        if (TextUtils.isEmpty(kucnibr_reg.getEditText().getText().toString())) {
            kucnibr_reg.setError("Potreban je kućni broj!");
            return false;
        }
        if (TextUtils.isEmpty(grad_reg.getEditText().getText().toString())) {
            grad_reg.setError("Potreban je grad!");
            return false;
        }
        else if (TextUtils.isEmpty(postnum_reg.getEditText().getText().toString())) {
            postnum_reg.setError("Potreban je poštanski broj!");
            return false;
        }
        return true;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}