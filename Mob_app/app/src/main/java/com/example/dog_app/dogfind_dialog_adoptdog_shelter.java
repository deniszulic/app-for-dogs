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

public class dogfind_dialog_adoptdog_shelter extends BottomSheetDialogFragment {
    private TextInputLayout ime_adoptdog_form_shelter, prezime_adoptdog_form_shelter, adresa_adoptdog_form_shelter,
            kontakt_adoptdog_form_shelter, grad_adoptdog_form_shelter, postnum_adoptdog_form_shelter,
            razlogprijave_adoptdog_form_shelter;
    private Button send_adoptdog_form_shelter;
    private TextView grad_adoptdog_form_shelter_data, postnum_adoptdog_form_shelter_data,
            imepsa_adoptdog_form_shelter_data, kilaza_adoptdog_form_shelter_data,
            pasmina_adoptdog_form_shelter_data, adresa_adoptdog_form_data_shelter;
    private int commentid;
    private String naziv_azila, grad_azila;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private boolean isAllFieldsChecked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dogfind_form_adoptdog_shelter, container, false);
        ime_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.ime_adoptdog_form_shelter);
        prezime_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.prezime_adoptdog_form_shelter);
        adresa_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.adresa_adoptdog_form_shelter);
        kontakt_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.kontakt_adoptdog_form_shelter);
        grad_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.grad_adoptdog_form_shelter);
        postnum_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.postnum_adoptdog_form_shelter);
        razlogprijave_adoptdog_form_shelter = (TextInputLayout) view.findViewById(R.id.razlogprijave_adoptdog_form_shelter);
        grad_adoptdog_form_shelter_data = (TextView) view.findViewById(R.id.grad_adoptdog_form_data_shelter);
        postnum_adoptdog_form_shelter_data = (TextView) view.findViewById(R.id.postnum_adoptdog_form_data_shelter);
        imepsa_adoptdog_form_shelter_data = (TextView) view.findViewById(R.id.imepsa_adoptdog_form_data_shelter);
        kilaza_adoptdog_form_shelter_data = (TextView) view.findViewById(R.id.kilaza_adoptdog_form_data_shelter);
        pasmina_adoptdog_form_shelter_data = (TextView) view.findViewById(R.id.pasmina_adoptdog_form_data_shelter);
        adresa_adoptdog_form_data_shelter=(TextView) view.findViewById(R.id.adresa_adoptdog_form_data_shelter);
        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        send_adoptdog_form_shelter = view.findViewById(R.id.send_adoptdog_form_shelter);

        imepsa_adoptdog_form_shelter_data.setText(adopt_shelter_adapter.getIme_psa());
        grad_adoptdog_form_shelter_data.setText(adopt_shelter_adapter.getGrad());
        postnum_adoptdog_form_shelter_data.setText(adopt_shelter_adapter.getPostanski_broj());
        kilaza_adoptdog_form_shelter_data.setText(adopt_shelter_adapter.getKilaza());
        pasmina_adoptdog_form_shelter_data.setText(adopt_shelter_adapter.getPasmina());
        adresa_adoptdog_form_data_shelter.setText(adopt_shelter_adapter.getUlica());
        send_adoptdog_form_shelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                ime_adoptdog_form_shelter.setError(null);
                prezime_adoptdog_form_shelter.setError(null);
                adresa_adoptdog_form_shelter.setError(null);
                kontakt_adoptdog_form_shelter.setError(null);
                grad_adoptdog_form_shelter.setError(null);
                postnum_adoptdog_form_shelter.setError(null);
                razlogprijave_adoptdog_form_shelter.setError(null);
                isAllFieldsChecked = checkfields();
                if (isAllFieldsChecked) {
                    commentid = Missingdogs_user_shelter_adapter.getid();
                    naziv_azila = Missingdogs_user_shelter_adapter.getNaziv_azila();
                    grad_azila = Missingdogs_user_shelter_adapter.getGrad_azila();
                    Date date = new Date();
                    long timestamp = date.getTime();
                    int id = sp1.getInt("id", 0);
                    adopt_dog_application data = new adopt_dog_application(ime_adoptdog_form_shelter.getEditText().getText().toString(), prezime_adoptdog_form_shelter.getEditText().getText().toString(), adresa_adoptdog_form_shelter.getEditText().getText().toString(), grad_adoptdog_form_shelter.getEditText().getText().toString(), postnum_adoptdog_form_shelter.getEditText().getText().toString(), kontakt_adoptdog_form_shelter.getEditText().getText().toString(), razlogprijave_adoptdog_form_shelter.getEditText().getText().toString(), "Obrada", timestamp, adopt_shelter_adapter.getid(), id, adopt_shelter_adapter.getNaziv_azila(), adopt_shelter_adapter.getGrad_azila());
                    Call<Void> call = retrofitInterface.reportadopdog(data);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201) {
                                ime_adoptdog_form_shelter.getEditText().getText().clear();
                                prezime_adoptdog_form_shelter.getEditText().getText().clear();
                                adresa_adoptdog_form_shelter.getEditText().getText().clear();
                                kontakt_adoptdog_form_shelter.getEditText().getText().clear();
                                grad_adoptdog_form_shelter.getEditText().getText().clear();
                                postnum_adoptdog_form_shelter.getEditText().getText().clear();
                                razlogprijave_adoptdog_form_shelter.getEditText().getText().clear();
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
        if (TextUtils.isEmpty(ime_adoptdog_form_shelter.getEditText().getText().toString())) {
            ime_adoptdog_form_shelter.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_adoptdog_form_shelter.getEditText().getText().toString())) {
            prezime_adoptdog_form_shelter.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(adresa_adoptdog_form_shelter.getEditText().getText().toString())) {
            adresa_adoptdog_form_shelter.setError("Potrebna je adresa!");
            return false;
        }
        if (TextUtils.isEmpty(grad_adoptdog_form_shelter.getEditText().getText().toString())) {
            grad_adoptdog_form_shelter.setError("Potreban je grad!");
            return false;
        }
        if (TextUtils.isEmpty(postnum_adoptdog_form_shelter.getEditText().getText().toString())) {
            postnum_adoptdog_form_shelter.setError("Potreban je poštanski broj!");
            return false;
        }
        if (TextUtils.isEmpty(kontakt_adoptdog_form_shelter.getEditText().getText().toString())) {
            kontakt_adoptdog_form_shelter.setError("Potreban je kontakt!");
            return false;
        }
        else if (TextUtils.isEmpty(razlogprijave_adoptdog_form_shelter.getEditText().getText().toString())) {
            razlogprijave_adoptdog_form_shelter.setError("Potreban je razlog!");
            return false;
        }
        return true;
    }
}
