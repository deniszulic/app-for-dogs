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


public class mojioglasi_udomipsa_azil extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static List<myadopteddogsdata> listItems;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public mojioglasi_udomipsa_azil() {
        // Required empty public constructor
    }

    public static mojioglasi_udomipsa_azil newInstance(String param1, String param2) {
        mojioglasi_udomipsa_azil fragment = new mojioglasi_udomipsa_azil();
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
        View v = inflater.inflate(R.layout.fragment_mojioglasi_udomipsa_azil, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.mojioglasi_udomipsa_azil);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp.getString("email", null);
        Call<myadopteddogsdata[]> data = retrofitInterface.getmyadopteddogs(getemail);

        listItems=new ArrayList<>();
        data.enqueue(new Callback<myadopteddogsdata[]>() {
            @Override
            public void onResponse(Call<myadopteddogsdata[]> call, Response<myadopteddogsdata[]> response) {
                if(response.code()==200) {
                    myadopteddogsdata[] data = response.body();
                    listItems.addAll(Arrays.asList(data));
                    adapter = new myadoptdog_shelter_adapter(listItems, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<myadopteddogsdata[]> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
    public static void setListItems(int change, myadopteddogsdata a) {
        listItems.set(change, a);
        adapter.notifyItemChanged(change);
    }
}