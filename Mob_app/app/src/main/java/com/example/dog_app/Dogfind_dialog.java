package com.example.dog_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
//                commentid id=new commentid();
//                System.out.println("aaaaa "+id.getId());
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
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
//                            FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
//                            Dogfind_dialog dialog= new Dogfind_dialog();
//                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t);
                    }
            });
            }
        });
        return view;
    }
}
