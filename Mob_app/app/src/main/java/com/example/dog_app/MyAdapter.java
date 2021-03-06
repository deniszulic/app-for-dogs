package com.example.dog_app;

import static java.security.AccessController.getContext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;
    private static int lid;
    private static String useremail;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

        holder.ime.setText(listItem.getIme());
        holder.imepsa.setText(listItem.getIme_psa());
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljeno.setText(format.format(date));
        holder.prezime.setText(listItem.getPrezime());
        holder.telbr.setText(listItem.getTelefonskibr());
        holder.adresa.setText(listItem.getAdresa());
        holder.boja.setText(listItem.getBoja());
        holder.dlaka.setText(listItem.getDlaka());
        holder.pasmina.setText(listItem.getPasmina());
        holder.grad.setText(listItem.getGrad());
        holder.postnum.setText(String.valueOf(listItem.getPostanski_broj()));
        holder.vet_lok.setText(listItem.getVet_lokacija());
        holder.spol.setText(listItem.getSpol());
        holder.starost.setText(String.valueOf(listItem.getStarost()));
        Date date1 = new Date(String.valueOf(listItem.getDatum_izgubljen()));
        holder.chip.setText("Izgubljen: "+format.format(date1));
        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
        holder.komentiraj_nestalipsi_korisnik_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useremail="";
                Missingdogs_user_shelter_adapter.setEmail_azila("");
                useremail=listItem.getEmail();
                Intent i=new Intent(context, komentari_korisnik.class);
                i.putExtra("id", listItem.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        SharedPreferences sp1=MyAdapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        if(!getemail.equals(listItem.getEmail())){
            holder.pronadenpas_nestalipsi_korisnik.setVisibility(View.VISIBLE);
        }
        else{holder.pronadenpas_nestalipsi_korisnik.setVisibility(View.GONE);}
        holder.pronadenpas_nestalipsi_korisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid=listItem.getId();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                Dogfind_dialog dialog= new Dogfind_dialog();
                dialog.show(manager, "dogfind_dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad, postnum, vet_lok, spol, starost;
        public LinearLayout podaci;
        public ImageView slika;
        private Chip chip;
        public Button komentiraj_nestalipsi_korisnik_item, pronadenpas_nestalipsi_korisnik;

        public ViewHolder(View itemView) {
            super(itemView);
            ime=(TextView) itemView.findViewById(R.id.ime_nestalipsi_korisnik_item);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_nestalipsi_korisnik_item);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_nestalipsi_korisnik);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_nestalipsi_korisnik);
            slika=(ImageView) itemView.findViewById(R.id.slika_nestalipsi_korisnik);
            adresa=(TextView) itemView.findViewById(R.id.adresa_nestalipsi_korisnik_item);
            telbr=(TextView) itemView.findViewById(R.id.telbr_nestalipsi_korisnik_item);
            boja=(TextView) itemView.findViewById(R.id.boja_nestalipsi_korisnik_item);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_nestalipsi_korisnik_item);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_nestalipsi_korisnik_item);
            prezime=(TextView) itemView.findViewById(R.id.prezime_nestalipsi_korisnik_item);
            chip=(Chip) itemView.findViewById(R.id.chip_nestalipsi_korisnik_item);
            postnum=(TextView) itemView.findViewById(R.id.postnum_nestalipsi_korisnik_item);
            vet_lok=(TextView) itemView.findViewById(R.id.vet_lokacija_nestalipsi_korisnik_item);
            spol=(TextView) itemView.findViewById(R.id.spol_nestalipsi_korisnik_item);
            starost=(TextView) itemView.findViewById(R.id.starost_nestalipsi_korisnik_item);
            grad=(TextView) itemView.findViewById(R.id.grad_nestalipsi_korisnik_item);
            komentiraj_nestalipsi_korisnik_item=(Button) itemView.findViewById(R.id.komentiraj_nestalipsi_korisnik_item);
            pronadenpas_nestalipsi_korisnik=(Button) itemView.findViewById(R.id.pronadenpas_nestalipsi_korisnik);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

    public static void setUseremail(String useremail) {
        MyAdapter.useremail = useremail;
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
