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


public class userreq_mis_adopt extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static List<userreq_missingdog_data> listItems;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userreq_mis_adopt() {
        // Required empty public constructor
    }

    public static userreq_mis_adopt newInstance(String param1, String param2) {
        userreq_mis_adopt fragment = new userreq_mis_adopt();
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
        View v = inflater.inflate(R.layout.fragment_userreq_mis_adopt, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = (RecyclerView) v.findViewById(R.id.userrequest_missingdog_adopt);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp.getString("email", null);
        Call<userreq_missingdog_data[]> data = retrofitInterface.userreqmissingdogaccepted(getemail);

        listItems=new ArrayList<>();
        data.enqueue(new Callback<userreq_missingdog_data[]>() {
            @Override
            public void onResponse(Call<userreq_missingdog_data[]> call, Response<userreq_missingdog_data[]> response) {
                if(response.code()==200) {
                    try{
                        userreq_missingdog_data[] data = response.body();
                        listItems.addAll(Arrays.asList(data));
                        adapter= new user_req_missingdog_adopadapter(listItems, getContext());
                        recyclerView.setAdapter(adapter);
                    }catch(Exception e){System.out.println(e);}
                }
            }

            @Override
            public void onFailure(Call<userreq_missingdog_data[]> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    public static void setListItems(int index) {
        listItems.remove(index);
        adapter.notifyItemRemoved(index);
    }
    public static void setListItemschange(int change, userreq_missingdog_data a) {
        listItems.set(change, a);
        adapter.notifyItemChanged(change);
    }
}