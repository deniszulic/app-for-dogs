package com.example.dog_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//AppCompatDialogFragment
public class Dogfind_dialog extends BottomSheetDialogFragment {
    private TextInputLayout ime_dogfind_form, prezime_dogfind_form, adresapronalaska_dogfind_form, adresapreuzimanje_dogfind_form, kontakt_dogfind_form, napomena_dogfind_form;
    private Button send_dogfind_form;
    private int commentid;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private boolean isAllFieldsChecked = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dogfind_form, container, false);
        ime_dogfind_form =(TextInputLayout) view.findViewById(R.id.ime_dogfind_form);
        prezime_dogfind_form=(TextInputLayout) view.findViewById(R.id.prezime_dogfind_form);
        adresapronalaska_dogfind_form=(TextInputLayout) view.findViewById(R.id.adresapronalaska_dogfind_form);
        adresapreuzimanje_dogfind_form=(TextInputLayout) view.findViewById(R.id.adresapreuzimanje_dogfind_form);
        kontakt_dogfind_form=(TextInputLayout) view.findViewById(R.id.kontakt_dogfind_form);
        napomena_dogfind_form=(TextInputLayout) view.findViewById(R.id.napomena_dogfind_form);
        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        send_dogfind_form=view.findViewById(R.id.send_dogfind_form);
        send_dogfind_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentid=MyAdapter.getid();
                Date date = new Date();
                long timestamp = date.getTime();
                int id=sp1.getInt("id",0);
                Reportmissingdog data= new Reportmissingdog(ime_dogfind_form.getEditText().getText().toString(), prezime_dogfind_form.getEditText().getText().toString(), napomena_dogfind_form.getEditText().getText().toString(), adresapronalaska_dogfind_form.getEditText().getText().toString(), adresapreuzimanje_dogfind_form.getEditText().getText().toString(), timestamp, id, commentid, kontakt_dogfind_form.getEditText().getText().toString());
                retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    retrofitInterface = retrofit.create(RetrofitInterface.class);
                Call<Void> call = retrofitInterface.reportmissingdog(data);
                ime_dogfind_form.setError(null);
                prezime_dogfind_form.setError(null);
                adresapreuzimanje_dogfind_form.setError(null);
                kontakt_dogfind_form.setError(null);
                isAllFieldsChecked = checkfields();
                if(isAllFieldsChecked) {
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201) {
                                try{
                                    Toast.makeText(getActivity(),"Poslano!",Toast.LENGTH_SHORT).show();
                                    ime_dogfind_form.getEditText().getText().clear();
                                    prezime_dogfind_form.getEditText().getText().clear();
                                    adresapreuzimanje_dogfind_form.getEditText().getText().clear();
                                    adresapronalaska_dogfind_form.getEditText().getText().clear();
                                    kontakt_dogfind_form.getEditText().getText().clear();
                                    napomena_dogfind_form.getEditText().getText().clear();
                                }catch(Exception e){System.out.println(e);}
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
    private boolean checkfields() {
        if (TextUtils.isEmpty(ime_dogfind_form.getEditText().getText().toString())) {
            ime_dogfind_form.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_dogfind_form.getEditText().getText().toString())) {
            prezime_dogfind_form.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(adresapreuzimanje_dogfind_form.getEditText().getText().toString())) {
            adresapreuzimanje_dogfind_form.setError("Potrebna je adresa!");
            return false;
        }
        if (TextUtils.isEmpty(kontakt_dogfind_form.getEditText().getText().toString())) {
            kontakt_dogfind_form.setError("Potreban je kontakt!");
            return false;
        }
        return true;
    }
}
