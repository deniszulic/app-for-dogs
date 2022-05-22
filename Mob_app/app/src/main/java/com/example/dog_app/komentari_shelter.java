package com.example.dog_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class komentari_shelter extends AppCompatActivity {
    private ImageView slika_nestalipsi_korisnik_azil;
    private TextView imeazila_user_shelter, grad_user_shelter, ulica_user_shelter,
            postnum_user_shelter, postavljeno_nestalipsi_korisnik_azil,
            imepsa_nestalipsi_korisnik_azil, telbr_nestalipsi_korisnik_azil,
            boja_nestalipsi_korisnik_azil, dlaka_nestalipsi_korisnik_azil,
            pasmina_nestalipsi_korisnik_azil, spol_nestalipsi_korisnik_azil,
            starost_nestalipsi_korisnik_azil, napomena_nestalipsi_korisnik_azil_komentar;
    private Chip chip_nestalipsi_korisnik_azil;
    private TextInputLayout insertcomment;
    private Button send_comment;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private List<Comments> commentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentari_korisnik_azil);
        slika_nestalipsi_korisnik_azil=(ImageView) findViewById(R.id.slika_nestalipsi_korisnik_azil_komentari);
        imeazila_user_shelter=(TextView) findViewById(R.id.imeazila_user_shelter_komentar);
        grad_user_shelter=(TextView) findViewById(R.id.grad_user_shelter_komentar);
        ulica_user_shelter=(TextView) findViewById(R.id.ulica_user_shelter_komentar);
        postnum_user_shelter=(TextView) findViewById(R.id.postnum_user_shelter_komentar);
        postavljeno_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.postavljeno_nestalipsi_korisnik_azil_komentar);
        imepsa_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.imepsa_nestalipsi_korisnik_azil_komentar);
        telbr_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.telbr_nestalipsi_korisnik_azil_komentar);
        boja_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.boja_nestalipsi_korisnik_azil_komentar);
        dlaka_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.dlaka_nestalipsi_korisnik_azil_komentar);
        pasmina_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.pasmina_nestalipsi_korisnik_azil_komentar);
        spol_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.spol_nestalipsi_korisnik_azil_komentar);
        starost_nestalipsi_korisnik_azil=(TextView) findViewById(R.id.starost_nestalipsi_korisnik_azil_komentar);
        napomena_nestalipsi_korisnik_azil_komentar=(TextView) findViewById(R.id.napomena_nestalipsi_korisnik_azil_komentar);
        chip_nestalipsi_korisnik_azil=(Chip) findViewById(R.id.chip_nestalipsi_korisnik_azil_komentar);

        insertcomment=(TextInputLayout) findViewById(R.id.insertcomment_user_shelter);
        send_comment=(Button) findViewById(R.id.send_comment_user_shelter);
        recyclerView = (RecyclerView) findViewById(R.id.comments_user_shelter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        int id=getIntent().getIntExtra("id",0);
        Call<Missingdogs_user_shelter_data[]> data= retrofitInterface.getdatashelter(id);
        Call<Comments[]> datacomments= retrofitInterface.getcomments(id);

        data.enqueue(new Callback<Missingdogs_user_shelter_data[]>() {
            @Override
            public void onResponse(Call<Missingdogs_user_shelter_data[]> call, Response<Missingdogs_user_shelter_data[]> response) {
                Missingdogs_user_shelter_data[] data=response.body();
                imeazila_user_shelter.setText(data[0].getNaziv());
                grad_user_shelter.setText(data[0].getGrad());
                ulica_user_shelter.setText(data[0].getUlica());
                postnum_user_shelter.setText(data[0].getPostanski_broj());
                Date date = new Date(data[0].getPostavljeno());
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                postavljeno_nestalipsi_korisnik_azil.setText(format.format(date));
                imepsa_nestalipsi_korisnik_azil.setText(data[0].getIme_psa());
                telbr_nestalipsi_korisnik_azil.setText(data[0].getTelefonskibr());
                boja_nestalipsi_korisnik_azil.setText(data[0].getBoja());
                dlaka_nestalipsi_korisnik_azil.setText(data[0].getDlaka());
                pasmina_nestalipsi_korisnik_azil.setText(data[0].getPasmina());
                spol_nestalipsi_korisnik_azil.setText(data[0].getSpol());
                starost_nestalipsi_korisnik_azil.setText(data[0].getStarost());
//                chip_nestalipsi_korisnik_azil.setText(getDate(data[0].getDatum_izgubljen()));
                Date date1 = new Date(String.valueOf(data[0].getDatum_izgubljen()));
                chip_nestalipsi_korisnik_azil.setText("Izgubljen: "+format.format(date1));
                napomena_nestalipsi_korisnik_azil_komentar.setText(data[0].getNapomena());
                if(data[0].getUrl_slike()!=null) {
                    slika_nestalipsi_korisnik_azil.setVisibility(View.VISIBLE);
                    Picasso.get().load(data[0].getUrl_slike()).into(slika_nestalipsi_korisnik_azil);
                }
                else{
                    slika_nestalipsi_korisnik_azil.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Missingdogs_user_shelter_data[]> call, Throwable t) {

            }
        });
        commentsList=new ArrayList<>();
        datacomments.enqueue(new Callback<Comments[]>() {
            @Override
            public void onResponse(Call<Comments[]> call, Response<Comments[]> response) {
                Comments[] data=response.body();
//                System.out.println("sheesh:"+data[0].getKomentar());
                commentsList.addAll(Arrays.asList(data));
                adapter = new CommentsAdaptershelter(commentsList, id);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Comments[]> call, Throwable t) {

            }
        });

        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                long timestamp = date.getTime();
                SharedPreferences sp1=getSharedPreferences("userdata", MODE_PRIVATE);
                int userid=sp1.getInt("id", 0);
                Sendcomment commentdata=new Sendcomment(insertcomment.getEditText().getText().toString(), timestamp, id, userid);
                Call<Void> senddata= retrofitInterface.sendcomment(commentdata);
                senddata.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        insertcomment.getEditText().getText().clear();

                        Call<Comments[]> datacomments= retrofitInterface.getcomments(id);
                        commentsList=new ArrayList<>();
                        datacomments.enqueue(new Callback<Comments[]>() {
                            @Override
                            public void onResponse(Call<Comments[]> call, Response<Comments[]> response) {
                                Comments[] data=response.body();
                                commentsList.addAll(Arrays.asList(data));
                                adapter = new CommentsAdaptershelter(commentsList, id);
                                recyclerView.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<Comments[]> call, Throwable t) {

                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
    public String getDate(String vlaue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
            Date date = dateFormat.parse(vlaue);
            dateFormat = new SimpleDateFormat("dd.MM.yyyy.", Locale.US);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
