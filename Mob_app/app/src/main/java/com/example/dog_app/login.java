package com.example.dog_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private FirebaseAuth mAuth;
    private TextInputLayout email, password;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    private String mParam1;
    private String mParam2;
    private AppCompatButton gotoregistertransition, login;

    public login() {
        // Required empty public constructor
    }
    public static login newInstance(String param1, String param2) {
        login fragment = new login();
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
        //return inflater.inflate(R.layout.login, container, false);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        email=(TextInputLayout) view.findViewById(R.id.email_log);
        password=(TextInputLayout) view.findViewById(R.id.pass_log);
        login=(AppCompatButton) view.findViewById(R.id.login_btn);
        gotoregistertransition=(AppCompatButton) view.findViewById(R.id.gotoregistertransition);
        //mAuth = FirebaseAuth.getInstance();
        gotoregistertransition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), transition_register.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
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
                                SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
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
                        Toast.makeText(getActivity(), t.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return view;
    }
}