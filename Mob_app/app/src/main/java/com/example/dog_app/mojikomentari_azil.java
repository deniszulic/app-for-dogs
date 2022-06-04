package com.example.dog_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class mojikomentari_azil extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    public TextView id;
    public TextView postavljeno, telbr, boja, dlaka, pasmina, spol, starost, napomena;
    public LinearLayout podaci;
    public ImageView slika;
    private Chip chip;
    private List<Comments> commentsList;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private TextInputLayout insertcomment;
    private Button send_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mojikomentari_azil);

        postavljeno=(TextView) findViewById(R.id.postavljeno_nestalipsi_korisnik_komentari);
        podaci = (LinearLayout) findViewById(R.id.podaci_nestalipsi_korisnik_komentari);
        slika=(ImageView) findViewById(R.id.slika_nestalipsi_korisnik_komentari);
        telbr=(TextView) findViewById(R.id.telbr_nestalipsi_korisnik_item_komentari);
        boja=(TextView) findViewById(R.id.boja_nestalipsi_korisnik_item_komentari);
        dlaka=(TextView) findViewById(R.id.dlaka_nestalipsi_korisnik_item_komentari);
        pasmina=(TextView) findViewById(R.id.pasmina_nestalipsi_korisnik_item_komentari);
        chip=(Chip) findViewById(R.id.chip_nestalipsi_korisnik_item_komentari);
        spol=(TextView) findViewById(R.id.spol_nestalipsi_korisnik_item_komentari);
        starost=(TextView) findViewById(R.id.starost_nestalipsi_korisnik_item_komentari);
        napomena=(TextView) findViewById(R.id.napomena_nestalipsi_korisnik_item_komentari);
        insertcomment=(TextInputLayout) findViewById(R.id.insertcomment);
        send_comment=(Button) findViewById(R.id.send_comment);
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
                Date date = new Date(data[0].getPostavljeno());
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                postavljeno.setText(format.format(date));
                telbr.setText(data[0].getTelefonskibr());
                boja.setText(data[0].getBoja());
                dlaka.setText(data[0].getDlaka());
                pasmina.setText(data[0].getPasmina());
                Date date1 = new Date(String.valueOf(data[0].getDatum_izgubljen()));
                chip.setText("Datum nestanka: "+format.format(date1));
                spol.setText(data[0].getSpol());
                starost.setText(String.valueOf(data[0].getStarost()));
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
                Toast.makeText(mojikomentari_azil.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        commentsList=new ArrayList<>();
        datacomments.enqueue(new Callback<Comments[]>() {
            @Override
            public void onResponse(Call<Comments[]> call, Response<Comments[]> response) {
                Comments[] data=response.body();
                commentsList.addAll(Arrays.asList(data));
                adapter = new myCommentsAdapterShelter(commentsList, id);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Comments[]> call, Throwable t) {
                Toast.makeText(mojikomentari_azil.this,t.toString(),Toast.LENGTH_SHORT).show();
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
                                adapter = new myCommentsAdapterShelter(commentsList, id);
                                recyclerView.setAdapter(adapter);

                            }

                            @Override
                            public void onFailure(Call<Comments[]> call, Throwable t) {
                                Toast.makeText(mojikomentari_azil.this,t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(mojikomentari_azil.this,t.toString(),Toast.LENGTH_SHORT).show();
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