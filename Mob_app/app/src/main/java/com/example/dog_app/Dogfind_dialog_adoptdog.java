package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dogfind_dialog_adoptdog extends BottomSheetDialogFragment {
    private TextInputLayout ime_adoptdog_form, prezime_adoptdog_form, adresa_adoptdog_form,
            kontakt_adoptdog_form, grad_adoptdog_form, postnum_adoptdog_form,
            razlogprijave_adoptdog_form;
    private Button send_adoptdog_form;
    private TextView ime_adoptdog_form_data, prezime_adoptdog_form_data, adresa_adoptdog_form_data,
            grad_adoptdog_form_data, postnum_adoptdog_form_data, imepsa_adoptdog_form_data, kilaza_adoptdog_form_data,
            pasmina_adoptdog_form_data;
    private int commentid;
    private String naziv_azila, grad_azila;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private boolean isAllFieldsChecked = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dogfind_form_adoptdog, container, false);
        ime_adoptdog_form =(TextInputLayout) view.findViewById(R.id.ime_adoptdog_form);
        prezime_adoptdog_form=(TextInputLayout) view.findViewById(R.id.prezime_adoptdog_form);
        adresa_adoptdog_form=(TextInputLayout) view.findViewById(R.id.adresa_adoptdog_form);
        kontakt_adoptdog_form=(TextInputLayout) view.findViewById(R.id.kontakt_adoptdog_form);
        grad_adoptdog_form=(TextInputLayout) view.findViewById(R.id.grad_adoptdog_form);
        postnum_adoptdog_form=(TextInputLayout) view.findViewById(R.id.postnum_adoptdog_form);
        razlogprijave_adoptdog_form=(TextInputLayout) view.findViewById(R.id.razlogprijave_adoptdog_form);
        ime_adoptdog_form_data=(TextView) view.findViewById(R.id.ime_adoptdog_form_data);
        prezime_adoptdog_form_data=(TextView) view.findViewById(R.id.prezime_adoptdog_form_data);
        adresa_adoptdog_form_data=(TextView) view.findViewById(R.id.adresa_adoptdog_form_data);
        grad_adoptdog_form_data=(TextView) view.findViewById(R.id.grad_adoptdog_form_data);
        postnum_adoptdog_form_data=(TextView) view.findViewById(R.id.postnum_adoptdog_form_data);
        imepsa_adoptdog_form_data=(TextView) view.findViewById(R.id.imepsa_adoptdog_form_data);
        kilaza_adoptdog_form_data=(TextView) view.findViewById(R.id.kilaza_adoptdog_form_data);
        pasmina_adoptdog_form_data=(TextView) view.findViewById(R.id.pasmina_adoptdog_form_data);
        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        send_adoptdog_form=view.findViewById(R.id.send_adoptdog_form);

        imepsa_adoptdog_form_data.setText(adoptdog_user_adapter.getIme_psa());
        ime_adoptdog_form_data.setText(adoptdog_user_adapter.getIme_vlasnika());
        prezime_adoptdog_form_data.setText(adoptdog_user_adapter.getPrezime_vlasnika());
        adresa_adoptdog_form_data.setText(adoptdog_user_adapter.getAdresa());
        grad_adoptdog_form_data.setText(adoptdog_user_adapter.getGrad());
        postnum_adoptdog_form_data.setText(String.valueOf(adoptdog_user_adapter.getPostanski_broj()));
        kilaza_adoptdog_form_data.setText(String.valueOf(adoptdog_user_adapter.getKilaza()));
        pasmina_adoptdog_form_data.setText(adoptdog_user_adapter.getPasmina());
        send_adoptdog_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                ime_adoptdog_form.setError(null);
                prezime_adoptdog_form.setError(null);
                adresa_adoptdog_form.setError(null);
                kontakt_adoptdog_form.setError(null);
                grad_adoptdog_form.setError(null);
                postnum_adoptdog_form.setError(null);
                razlogprijave_adoptdog_form.setError(null);
                isAllFieldsChecked = checkfields();
                if(isAllFieldsChecked) {
                    commentid=Missingdogs_user_shelter_adapter.getid();
                    naziv_azila=Missingdogs_user_shelter_adapter.getNaziv_azila();
                    grad_azila=Missingdogs_user_shelter_adapter.getGrad_azila();
                    Date date = new Date();
                    long timestamp = date.getTime();
                    int id=sp1.getInt("id",0);
                    adopt_dog_application data= new adopt_dog_application(ime_adoptdog_form.getEditText().getText().toString(), prezime_adoptdog_form.getEditText().getText().toString(), adresa_adoptdog_form.getEditText().getText().toString(), grad_adoptdog_form.getEditText().getText().toString(), postnum_adoptdog_form.getEditText().getText().toString(), kontakt_adoptdog_form.getEditText().getText().toString(), razlogprijave_adoptdog_form.getEditText().getText().toString(), "Obrada", timestamp, adoptdog_user_adapter.getid(), id, null, null);
                    Call<Void> call = retrofitInterface.reportadopdog(data);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201) {
                                ime_adoptdog_form.getEditText().getText().clear();
                                prezime_adoptdog_form.getEditText().getText().clear();
                                adresa_adoptdog_form.getEditText().getText().clear();
                                kontakt_adoptdog_form.getEditText().getText().clear();
                                grad_adoptdog_form.getEditText().getText().clear();
                                postnum_adoptdog_form.getEditText().getText().clear();
                                razlogprijave_adoptdog_form.getEditText().getText().clear();
                                Toast.makeText(getActivity(),"Poslano!",Toast.LENGTH_SHORT).show();
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
        if (TextUtils.isEmpty(ime_adoptdog_form.getEditText().getText().toString())) {
            ime_adoptdog_form.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_adoptdog_form.getEditText().getText().toString())) {
            prezime_adoptdog_form.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(adresa_adoptdog_form.getEditText().getText().toString())) {
            adresa_adoptdog_form.setError("Potrebna je adresa!");
            return false;
        }
        if (TextUtils.isEmpty(grad_adoptdog_form.getEditText().getText().toString())) {
            grad_adoptdog_form.setError("Potreban je grad!");
            return false;
        }
        if (TextUtils.isEmpty(postnum_adoptdog_form.getEditText().getText().toString())) {
            postnum_adoptdog_form.setError("Potreban je poštanski broj!");
            return false;
        }
        if (TextUtils.isEmpty(kontakt_adoptdog_form.getEditText().getText().toString())) {
            kontakt_adoptdog_form.setError("Potreban je kontakt!");
            return false;
        }
        else if (TextUtils.isEmpty(razlogprijave_adoptdog_form.getEditText().getText().toString())) {
            razlogprijave_adoptdog_form.setError("Potreban je razlog!");
            return false;
        }
        return true;
    }
}
