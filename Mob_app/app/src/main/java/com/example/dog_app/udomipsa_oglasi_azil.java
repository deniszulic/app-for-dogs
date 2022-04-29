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

public class udomipsa_oglasi_azil extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private List<shelteradopteddogdata> listItems;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public udomipsa_oglasi_azil() {
        // Required empty public constructor
    }
    public static udomipsa_oglasi_azil newInstance(String param1, String param2) {
        udomipsa_oglasi_azil fragment = new udomipsa_oglasi_azil();
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
//        return inflater.inflate(R.layout.fragment_udomipsa_oglasi_azil, container, false);
        View v= inflater.inflate(R.layout.fragment_udomipsa_oglasi_azil, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.udomipsa_oglasi_azil);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Call<shelteradopteddogdata[]> data = retrofitInterface.getshelteradopteddogs();

        listItems=new ArrayList<>();
        data.enqueue(new Callback<shelteradopteddogdata[]>() {
            @Override
            public void onResponse(Call<shelteradopteddogdata[]> call, Response<shelteradopteddogdata[]> response) {
                if(response.code()==200) {
                    shelteradopteddogdata[] data = response.body();
                    listItems.addAll(Arrays.asList(data));
                    adapter = new adopt_shelter_adapter(listItems, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<shelteradopteddogdata[]> call, Throwable t) {

            }
        });
        return v;
    }
}