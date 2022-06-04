package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class profil extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextInputLayout ime_profil, prezime_profil, datum_profil, lozinka_profil;
    private Button update_profile, updatepass_profile;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    public profil() {
        // Required empty public constructor
    }
    public static profil newInstance(String param1, String param2) {
        profil fragment = new profil();
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
        View v= inflater.inflate(R.layout.fragment_profil, container, false);
        ime_profil=(TextInputLayout) v.findViewById(R.id.ime_profil);
        prezime_profil=(TextInputLayout) v.findViewById(R.id.prezime_profil);
        datum_profil=(TextInputLayout) v.findViewById(R.id.datum_profil);
        update_profile=(Button) v.findViewById(R.id.update_profile);
        lozinka_profil=(TextInputLayout) v.findViewById(R.id.lozinka_profil);
        updatepass_profile=(Button) v.findViewById(R.id.updatepass_profile);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp.getString("email", null);
        int id=sp.getInt("id", 0);
        Call<profildata[]> data = retrofitInterface.getdataprofile(getemail);

        data.enqueue(new Callback<profildata[]>() {
            @Override
            public void onResponse(Call<profildata[]> call, Response<profildata[]> response) {
                if(response.code()==200){
                    profildata[] data=response.body();
                    ime_profil.getEditText().setText(data[0].getIme());
                    prezime_profil.getEditText().setText(data[0].getPrezime());
                    Date date = new Date(data[0].getDatumreg());
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                    datum_profil.getEditText().setText(format.format(date));
                }
            }

            @Override
            public void onFailure(Call<profildata[]> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profildata dataprofil=new profildata(String.valueOf(ime_profil.getEditText().getText()), String.valueOf(prezime_profil.getEditText().getText()));
                Call<profildata[]> call=retrofitInterface.updatedata(id, dataprofil);
                call.enqueue(new Callback<profildata[]>() {
                    @Override
                    public void onResponse(Call<profildata[]> call, Response<profildata[]> response) {
                        if(response.code()==200){
                            Toast.makeText(getActivity(),"Podaci uspješno ažurirani!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<profildata[]> call, Throwable t) {
                        Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        updatepass_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passworddata passdata=new passworddata(String.valueOf(lozinka_profil.getEditText().getText()));
                Call<passworddata[]> call=retrofitInterface.updatepassword(id, passdata);
                call.enqueue(new Callback<passworddata[]>() {
                    @Override
                    public void onResponse(Call<passworddata[]> call, Response<passworddata[]> response) {
                        if(response.code()==200){
                            Toast.makeText(getActivity(),"Lozinka je uspješno ažurirana!",Toast.LENGTH_SHORT).show();
                            lozinka_profil.getEditText().getText().clear();
                        }
                    }

                    @Override
                    public void onFailure(Call<passworddata[]> call, Throwable t) {
                        Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v;
    }
}