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

public class nestalipsi_oglasi_korisnik_azil extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private List<Missingdogs_user_shelter_data> listItems;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nestalipsi_oglasi_korisnik_azil() {
        // Required empty public constructor
    }
    public static nestalipsi_oglasi_korisnik_azil newInstance(String param1, String param2) {
        nestalipsi_oglasi_korisnik_azil fragment = new nestalipsi_oglasi_korisnik_azil();
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
//        return inflater.inflate(R.layout.fragment_nestalipsi_oglasi_korisnik_azil, container, false);
        View v = inflater.inflate(R.layout.fragment_nestalipsi_oglasi_korisnik_azil, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.nestalipsi_oglasi_korisnik_azil);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Call<Missingdogs_user_shelter_data[]> data = retrofitInterface.missingdogs_usershelter();

        listItems=new ArrayList<>();
        data.enqueue(new Callback<Missingdogs_user_shelter_data[]>() {
            @Override
            public void onResponse(Call<Missingdogs_user_shelter_data[]> call, Response<Missingdogs_user_shelter_data[]> response) {
                Missingdogs_user_shelter_data[] data = response.body();
                listItems.addAll(Arrays.asList(data));

                adapter = new Missingdogs_user_shelter_adapter(listItems, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Missingdogs_user_shelter_data[]> call, Throwable t) {

            }
        });
        return v;
    }
}