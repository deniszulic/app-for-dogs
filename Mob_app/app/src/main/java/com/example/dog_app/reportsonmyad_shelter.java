package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class reportsonmyad_shelter extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static List<reportsonmymissingdogsdata> listItems;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public reportsonmyad_shelter() {
        // Required empty public constructor
    }

    public static reportsonmyad_shelter newInstance(String param1, String param2) {
        reportsonmyad_shelter fragment = new reportsonmyad_shelter();
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
        View v = inflater.inflate(R.layout.fragment_reportsonmyad_shelter, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.reportonmymisingdogs_shelter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp.getString("email", null);
        Call<reportsonmymissingdogsdata[]> data = retrofitInterface.reportsonmydog(getemail);

        listItems=new ArrayList<>();
        data.enqueue(new Callback<reportsonmymissingdogsdata[]>() {
            @Override
            public void onResponse(Call<reportsonmymissingdogsdata[]> call, Response<reportsonmymissingdogsdata[]> response) {
                if(response.code()==200) {
                    try{
                        reportsonmymissingdogsdata[] data = response.body();
                        listItems.addAll(Arrays.asList(data));
                        adapter= new reportsonmymissingdogs_adapter_shelter(listItems, getContext());
                        recyclerView.setAdapter(adapter);
                    }catch(Exception e){System.out.println(e);}
                }
            }

            @Override
            public void onFailure(Call<reportsonmymissingdogsdata[]> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}