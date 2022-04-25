package com.example.dog_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class nestalipsi_oglasi_korisnik extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<ListItem> listItems;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    private String mParam1;
    private String mParam2;

    public nestalipsi_oglasi_korisnik() {
        // Required empty public constructor
    }

    public static nestalipsi_oglasi_korisnik newInstance(String param1, String param2) {
        nestalipsi_oglasi_korisnik fragment = new nestalipsi_oglasi_korisnik();
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
        //return inflater.inflate(R.layout.fragment_nestalipsi_oglasi_korisnik, container, false);
        View v=inflater.inflate(R.layout.fragment_nestalipsi_oglasi_korisnik, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.nestalipsi_oglasi_korisnik);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Call<ListItem[]> data = retrofitInterface.missingdogs();

        listItems=new ArrayList<>();
        data.enqueue(new Callback<ListItem[]>() {
            @Override
            public void onResponse(Call<ListItem[]> call, Response<ListItem[]> response) {
                ListItem[] data = response.body();
                listItems.addAll(Arrays.asList(data));

//                System.out.println("listitem:"+listItems.get(0).getEmail());
                adapter = new MyAdapter(listItems, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListItem[]> call, Throwable t) {

            }
        });
        return v;
    }
}