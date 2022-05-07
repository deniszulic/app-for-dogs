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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class myreports_missingdogs extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static List<myreportsdisapp_data> listItems;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public myreports_missingdogs() {
        // Required empty public constructor
    }

    public static myreports_missingdogs newInstance(String param1, String param2) {
        myreports_missingdogs fragment = new myreports_missingdogs();
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
//        return inflater.inflate(R.layout.fragment_myreports_missingdogs, container, false);
        View v = inflater.inflate(R.layout.fragment_myreports_missingdogs, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.nestalipsi_mojeprijave);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp.getString("email", null);
        Call<myreportsdisapp_data[]> data = retrofitInterface.myreportsdisapp(getemail);

        listItems=new ArrayList<>();
        data.enqueue(new Callback<myreportsdisapp_data[]>() {
            @Override
            public void onResponse(Call<myreportsdisapp_data[]> call, Response<myreportsdisapp_data[]> response) {
                if(response.code()==200) {
                    myreportsdisapp_data[] data = response.body();
                    listItems.addAll(Arrays.asList(data));
                    System.out.println("length:"+listItems.size());
//                    adapter = new myadoptdog_user_adapter(listItems, getContext());
                    adapter= new myreports_missingdogs_adapter(listItems, getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<myreportsdisapp_data[]> call, Throwable t) {

            }
        });
        return v;
    }
    public static void setListItems(int change, myreportsdisapp_data a) {
        listItems.set(change, a);
        adapter.notifyItemChanged(change);
    }
}