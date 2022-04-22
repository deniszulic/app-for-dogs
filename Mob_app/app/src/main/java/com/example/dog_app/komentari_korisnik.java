package com.example.dog_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class komentari_korisnik extends AppCompatActivity {


    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    public TextView id;
    public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad, postnum, vet_lok, spol, starost, napomena;
    public LinearLayout podaci;
    public ImageView slika;
    private Chip chip;
    private List<Comments> commentsList;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentari_korisnik);

        ime=(TextView) findViewById(R.id.ime_nestalipsi_korisnik_item_komentari);
        imepsa=(TextView) findViewById(R.id.imepsa_nestalipsi_korisnik_item_komentari);
        postavljeno=(TextView) findViewById(R.id.postavljeno_nestalipsi_korisnik_komentari);
        podaci = (LinearLayout) findViewById(R.id.podaci_nestalipsi_korisnik_komentari);
        slika=(ImageView) findViewById(R.id.slika_nestalipsi_korisnik_komentari);
        adresa=(TextView) findViewById(R.id.adresa_nestalipsi_korisnik_item_komentari);
        telbr=(TextView) findViewById(R.id.telbr_nestalipsi_korisnik_item_komentari);
        boja=(TextView) findViewById(R.id.boja_nestalipsi_korisnik_item_komentari);
        dlaka=(TextView) findViewById(R.id.dlaka_nestalipsi_korisnik_item_komentari);
        pasmina=(TextView) findViewById(R.id.pasmina_nestalipsi_korisnik_item_komentari);
        prezime=(TextView) findViewById(R.id.prezime_nestalipsi_korisnik_item_komentari);
        chip=(Chip) findViewById(R.id.chip_nestalipsi_korisnik_item_komentari);
        postnum=(TextView) findViewById(R.id.postnum_nestalipsi_korisnik_item_komentari);
        vet_lok=(TextView) findViewById(R.id.vet_lokacija_nestalipsi_korisnik_item_komentari);
        spol=(TextView) findViewById(R.id.spol_nestalipsi_korisnik_item_komentari);
        starost=(TextView) findViewById(R.id.starost_nestalipsi_korisnik_item_komentari);
        grad=(TextView) findViewById(R.id.grad_nestalipsi_korisnik_item_komentari);
        napomena=(TextView) findViewById(R.id.napomena_nestalipsi_korisnik_item_komentari);
        recyclerView = (RecyclerView) findViewById(R.id.comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        int id=getIntent().getIntExtra("id",0);
        Call<ListItem[]> data= retrofitInterface.getcommentsuser(id);
        Call<Comments[]> datacomments= retrofitInterface.getcomments(id);

        data.enqueue(new Callback<ListItem[]>() {
            @Override
            public void onResponse(Call<ListItem[]> call, Response<ListItem[]> response) {
                ListItem[] data=response.body();
                ime.setText(data[0].getIme());
                imepsa.setText(data[0].getIme_psa());
                Date date = new Date(data[0].getPostavljeno());
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                postavljeno.setText(format.format(date));
                adresa.setText(data[0].getAdresa());
                telbr.setText(data[0].getTelefonskibr());
                boja.setText(data[0].getBoja());
                dlaka.setText(data[0].getDlaka());
                pasmina.setText(data[0].getPasmina());
                prezime.setText(data[0].getPrezime());
                chip.setText(data[0].getDatum_izgubljen());
                postnum.setText(String.valueOf(data[0].getPostanski_broj()));
                vet_lok.setText(data[0].getVet_lokacija());
                spol.setText(data[0].getSpol());
                starost.setText(String.valueOf(data[0].getStarost()));
                grad.setText(data[0].getGrad());
                napomena.setText(data[0].getNapomena());

                if(data[0].getUrl_slike()!=null) {
                    slika.setVisibility(View.VISIBLE);
                    Picasso.get().load(data[0].getUrl_slike()).into(slika);
                }
                else{
                    slika.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ListItem[]> call, Throwable t) {

            }
        });

        commentsList=new ArrayList<>();
        datacomments.enqueue(new Callback<Comments[]>() {
            @Override
            public void onResponse(Call<Comments[]> call, Response<Comments[]> response) {
                Comments[] data=response.body();
//                System.out.println("sheesh:"+data[0].getKomentar());
                commentsList.addAll(Arrays.asList(data));
                adapter = new CommentsAdapter(commentsList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Comments[]> call, Throwable t) {

            }
        });
    }
}