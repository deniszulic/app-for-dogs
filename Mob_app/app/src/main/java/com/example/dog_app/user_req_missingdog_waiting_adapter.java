package com.example.dog_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class user_req_missingdog_waiting_adapter extends RecyclerView.Adapter<user_req_missingdog_waiting_adapter.ViewHolder>{
    private List<userreq_missingdog_data> listItems;
    private Context context;
    private static int lid;
    private static String ime, prezime, adresa, telefonskibr, grad, postanski_broj, boja, starost, dlaka, vet_lokacija, ime_psa, spol, pasmina, nestanak_napomena, url_slike, azil_nestanak_napomena, prihvaceno;
    private static int id, azil_nestanak_nestanak_id, nestanak_id, change;
    private static long postavljeno;
    private static Date datum_izgubljen;

    public user_req_missingdog_waiting_adapter(List<userreq_missingdog_data> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public user_req_missingdog_waiting_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_missingdog_req, parent, false);
        return new user_req_missingdog_waiting_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(user_req_missingdog_waiting_adapter.ViewHolder holder, int position) {

        final userreq_missingdog_data listItem = listItems.get(position);

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
        holder.chip.setText("Izgubljen: "+ format.format(date1));
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
                Intent i=new Intent(context, komentari_azil.class);
                i.putExtra("id", listItem.getAzil_nestanak_nestanak_id());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.potvrdaoglasa_nestalipsi_korisnik_item_userreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid=listItem.getId();

                change=holder.getAdapterPosition();
                ime=listItem.getIme();
                prezime=listItem.getPrezime();
                adresa=listItem.getAdresa();
                telefonskibr=listItem.getTelefonskibr();
                grad=listItem.getGrad();
                postanski_broj=listItem.getPostanski_broj();
                boja=listItem.getBoja();
                starost=listItem.getStarost();
                dlaka=listItem.getDlaka();
                vet_lokacija=listItem.getVet_lokacija();
                ime_psa=listItem.getIme_psa();
                spol=listItem.getSpol();
                pasmina=listItem.getPasmina();
                nestanak_napomena=listItem.getNestanak_napomena();
                url_slike=listItem.getUrl_slike();
                azil_nestanak_napomena=listItem.getAzil_nestanak_napomena();
                prihvaceno=listItem.getPrihvaceno();
                azil_nestanak_nestanak_id=listItem.getAzil_nestanak_nestanak_id();
                postavljeno=listItem.getPostavljeno();
                datum_izgubljen=listItem.getDatum_izgubljen();

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                missingdog_userreq_dialog dialog=new missingdog_userreq_dialog();
                dialog.show(manager, "mymissingdogs");
            }
        });
        SharedPreferences sp1=user_req_missingdog_waiting_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad, postnum, vet_lok, spol, starost;
        public ImageView slika;
        private Chip chip;
        public Button komentiraj_nestalipsi_korisnik_item, potvrdaoglasa_nestalipsi_korisnik_item_userreq;

        public ViewHolder(View itemView) {
            super(itemView);
            ime=(TextView) itemView.findViewById(R.id.ime_nestalipsi_korisnik_item_userreq);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_nestalipsi_korisnik_item_userreq);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_nestalipsi_korisnik_userreq);
            slika=(ImageView) itemView.findViewById(R.id.slika_nestalipsi_korisnik_userreq);
            adresa=(TextView) itemView.findViewById(R.id.adresa_nestalipsi_korisnik_item_userreq);
            telbr=(TextView) itemView.findViewById(R.id.telbr_nestalipsi_korisnik_item_userreq);
            boja=(TextView) itemView.findViewById(R.id.boja_nestalipsi_korisnik_item_userreq);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_nestalipsi_korisnik_item_userreq);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_nestalipsi_korisnik_item_userreq);
            prezime=(TextView) itemView.findViewById(R.id.prezime_nestalipsi_korisnik_item_userreq);
            chip=(Chip) itemView.findViewById(R.id.chip_nestalipsi_korisnik_item_userreq);
            postnum=(TextView) itemView.findViewById(R.id.postnum_nestalipsi_korisnik_item_userreq);
            vet_lok=(TextView) itemView.findViewById(R.id.vet_lokacija_nestalipsi_korisnik_item_userreq);
            spol=(TextView) itemView.findViewById(R.id.spol_nestalipsi_korisnik_item_userreq);
            starost=(TextView) itemView.findViewById(R.id.starost_nestalipsi_korisnik_item_userreq);
            grad=(TextView) itemView.findViewById(R.id.grad_nestalipsi_korisnik_item_userreq);
            komentiraj_nestalipsi_korisnik_item=(Button) itemView.findViewById(R.id.komentiraj_nestalipsi_korisnik_item_userreq);
            potvrdaoglasa_nestalipsi_korisnik_item_userreq = (Button) itemView.findViewById(R.id.potvrdaoglasa_nestalipsi_korisnik_item_userreq);
        }
    }
    public static int getid(){return lid;}

    public static String getIme() {
        return ime;
    }

    public static String getPrezime() {
        return prezime;
    }

    public static String getAdresa() {
        return adresa;
    }

    public static String getTelefonskibr() {
        return telefonskibr;
    }

    public static String getGrad() {
        return grad;
    }

    public static String getPostanski_broj() {
        return postanski_broj;
    }

    public static String getBoja() {
        return boja;
    }

    public static String getStarost() {
        return starost;
    }

    public static String getDlaka() {
        return dlaka;
    }

    public static String getVet_lokacija() {
        return vet_lokacija;
    }

    public static String getIme_psa() {
        return ime_psa;
    }

    public static String getSpol() {
        return spol;
    }

    public static String getPasmina() {
        return pasmina;
    }

    public static String getNestanak_napomena() {
        return nestanak_napomena;
    }

    public static String getUrl_slike() {
        return url_slike;
    }

    public static String getAzil_nestanak_napomena() {
        return azil_nestanak_napomena;
    }

    public static String getPrihvaceno() {
        return prihvaceno;
    }

    public static int getId() {
        return id;
    }

    public static int getAzil_nestanak_nestanak_id() {
        return azil_nestanak_nestanak_id;
    }

    public static int getNestanak_id() {
        return nestanak_id;
    }

    public static int getChange() {
        return change;
    }

    public static long getPostavljeno() {
        return postavljeno;
    }

    public static Date getDatum_izgubljen() {
        return datum_izgubljen;
    }
}
