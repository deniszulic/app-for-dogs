package com.example.dog_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class obrazac_nestalipsi_korisnik extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextInputLayout ime_nestalipsi_korisnik, prezime_nestalipsi_korisnik,
            adresa_nestalipsi_korisnik, telefonskibr_nestalipsi_korisnik, grad_nestalipsi_korisnik,
            postnum_nestalipsi_korisnik, boja_nestalipsi_korisnik, starost_nestalipsi_korisnik,
            vet_lokacija_nestalipsi_korisnik, imepsa_nestalipsi_korisnik,
            pasmina_nestalipsi_korisnik, napomena_nestalipsi_korisnik;
    private AutoCompleteTextView dlaka_nestalipsi_korisnik, spol_nestalipsi_korisnik;
    private AppCompatButton datumizgubljen_nestalipsi_korisnik, posalji_obrazac_nestalipsi_korisnik;
    private String datum;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    private String mParam1;
    private String mParam2;

    public obrazac_nestalipsi_korisnik() {
        // Required empty public constructor
    }

    public static obrazac_nestalipsi_korisnik newInstance(String param1, String param2) {
        obrazac_nestalipsi_korisnik fragment = new obrazac_nestalipsi_korisnik();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_obrazac_nestalipsi_korisnik, container, false);
        View v=inflater.inflate(R.layout.fragment_obrazac_nestalipsi_korisnik, container, false);
        ime_nestalipsi_korisnik=v.findViewById(R.id.ime_nestalipsi_korisnik);
        prezime_nestalipsi_korisnik=v.findViewById(R.id.prezime_nestalipsi_korisnik);
        adresa_nestalipsi_korisnik=v.findViewById(R.id.adresa_nestalipsi_korisnik);
        telefonskibr_nestalipsi_korisnik=v.findViewById(R.id.telefonskibr_nestalipsi_korisnik);
        grad_nestalipsi_korisnik=v.findViewById(R.id.grad_nestalipsi_korisnik);
        postnum_nestalipsi_korisnik=v.findViewById(R.id.postnum_nestalipsi_korisnik);
        boja_nestalipsi_korisnik=v.findViewById(R.id.boja_nestalipsi_korisnik);
        starost_nestalipsi_korisnik=v.findViewById(R.id.starost_nestalipsi_korisnik);
        vet_lokacija_nestalipsi_korisnik=v.findViewById(R.id.vet_lokacija_nestalipsi_korisnik);
        imepsa_nestalipsi_korisnik=v.findViewById(R.id.imepsa_nestalipsi_korisnik);
        pasmina_nestalipsi_korisnik=v.findViewById(R.id.pasmina_nestalipsi_korisnik);
        napomena_nestalipsi_korisnik=v.findViewById(R.id.napomena_nestalipsi_korisnik);
        dlaka_nestalipsi_korisnik=v.findViewById(R.id.dlaka_nestalipsi_korisnik);
        spol_nestalipsi_korisnik=v.findViewById(R.id.spol_nestalipsi_korisnik);
        datumizgubljen_nestalipsi_korisnik=v.findViewById(R.id.datumizgubljen_nestalipsi_korisnik);
        posalji_obrazac_nestalipsi_korisnik=v.findViewById(R.id.posalji_obrazac_nestalipsi_korisnik);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_nestalipsi_korisnik.setText(arrayAdapter.getItem(0).toString(), false);
        dlaka_nestalipsi_korisnik.setAdapter(arrayAdapter);

        String[] spol = {"M", "Ž"};
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_nestalipsi_korisnik.setText(arrayAdapter1.getItem(0).toString(), false);
        spol_nestalipsi_korisnik.setAdapter(arrayAdapter1);

        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        //int getid=sp1.getInt("id", 0);
//        String getid = sp1.getString("id", null);
//        System.out.println("iiddddddd:"+getid);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        datumizgubljen_nestalipsi_korisnik.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        datum=materialDatePicker.getHeaderText();
                        System.out.println("datum:"+datum);
                    }
                });
        posalji_obrazac_nestalipsi_korisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                long timestamp = date.getTime();
                missingdogsdata data= new missingdogsdata(ime_nestalipsi_korisnik.getEditText().getText().toString(), prezime_nestalipsi_korisnik.getEditText().getText().toString(), adresa_nestalipsi_korisnik.getEditText().getText().toString(), grad_nestalipsi_korisnik.getEditText().getText().toString(), Integer.parseInt(postnum_nestalipsi_korisnik.getEditText().getText().toString()), boja_nestalipsi_korisnik.getEditText().getText().toString(), Integer.parseInt(starost_nestalipsi_korisnik.getEditText().getText().toString()), dlaka_nestalipsi_korisnik.getText().toString(), vet_lokacija_nestalipsi_korisnik.getEditText().getText().toString(), imepsa_nestalipsi_korisnik.getEditText().getText().toString(), spol_nestalipsi_korisnik.getText().toString(), datum, napomena_nestalipsi_korisnik.getEditText().getText().toString(), timestamp, sp1.getInt("id", 0), telefonskibr_nestalipsi_korisnik.getEditText().getText().toString(), pasmina_nestalipsi_korisnik.getEditText().getText().toString(), true, false, null);
                Call<Void> call=retrofitInterface.missingdog(data);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        return v;
    }
}