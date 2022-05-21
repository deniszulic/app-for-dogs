package com.example.dog_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class user_req_adoptdog_waiting_adapter extends RecyclerView.Adapter<user_req_adoptdog_waiting_adapter.ViewHolder>{
    private List<userreq_adoptdog_data> listItems;
    private Context context;
    private static int lid;
    private static String ime, prezime, adresa, telefonskibr, grad, napomena, postanski_broj, boja, starost, opasnost, dlaka, vet_lokacija, ime_psa, spol, pasmina, nestanak_napomena, url_slike, azil_nestanak_napomena, prihvaceno, kilaza, kastrat, azil_udomljavanje_napomena;
    private static int id, udomljavanje_id, change;
    private static long postavljeno;
//    private static String useremail;

    public user_req_adoptdog_waiting_adapter(List<userreq_adoptdog_data> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public user_req_adoptdog_waiting_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_adoptdog_req, parent, false);
        return new user_req_adoptdog_waiting_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(user_req_adoptdog_waiting_adapter.ViewHolder holder, int position) {

        final userreq_adoptdog_data listItem = listItems.get(position);

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
        holder.kilaza.setText(listItem.getKilaza());
        holder.kastrat.setText(listItem.getKastrat());
        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
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
                opasnost=listItem.getOpasnost();
                napomena=listItem.getNapomena();
//                nestanak_napomena=listItem.getNestanak_napomena();
                url_slike=listItem.getUrl_slike();
//                azil_nestanak_napomena=listItem.getAzil_nestanak_napomena();
                prihvaceno=listItem.getPrihvaceno();
//                azil_nestanak_nestanak_id=listItem.getAzil_nestanak_nestanak_id();
                azil_udomljavanje_napomena = listItem.getAzil_udomljavanje_napomena();
                udomljavanje_id=listItem.getUdomljavanje_id();
                kilaza=listItem.getKilaza();
                kastrat=listItem.getKastrat();
                postavljeno=listItem.getPostavljeno();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                adopteddog_userreq_dialog dialog=new adopteddog_userreq_dialog();
                dialog.show(manager, "userreqadopteddog");
            }
        });
        SharedPreferences sp1=user_req_adoptdog_waiting_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad, postnum, vet_lok, spol, starost, kilaza, kastrat;
        public ImageView slika;
        public Button potvrdaoglasa_nestalipsi_korisnik_item_userreq;

        public ViewHolder(View itemView) {
            super(itemView);
            ime=(TextView) itemView.findViewById(R.id.ime_udomljenipsi_korisnik_item_userreq);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_udomljenipsi_korisnik_item_userreq);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_nestalipsi_korisnik_userreq);
            slika=(ImageView) itemView.findViewById(R.id.slika_nestalipsi_korisnik_userreq);
            adresa=(TextView) itemView.findViewById(R.id.adresa_udomljenipsi_korisnik_item_userreq);
            telbr=(TextView) itemView.findViewById(R.id.telbr_udomljenipsi_korisnik_item_userreq);
            boja=(TextView) itemView.findViewById(R.id.boja_udomljenipsi_korisnik_item_userreq);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_udomljenipsi_korisnik_item_userreq);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_udomljenipsi_korisnik_item_userreq);
            prezime=(TextView) itemView.findViewById(R.id.prezime_udomljenipsi_korisnik_item_userreq);
            postnum=(TextView) itemView.findViewById(R.id.postnum_udomljenipsi_korisnik_item_userreq);
            vet_lok=(TextView) itemView.findViewById(R.id.vet_lokacija_udomljenipsi_korisnik_item_userreq);
            spol=(TextView) itemView.findViewById(R.id.spol_udomljenipsi_korisnik_item_userreq);
            starost=(TextView) itemView.findViewById(R.id.starost_udomljenipsi_korisnik_item_userreq);
            grad=(TextView) itemView.findViewById(R.id.grad_udomljenipsi_korisnik_item_userreq);
            kilaza = (TextView) itemView.findViewById(R.id.kilaza_udomljenipsi_korisnik_item_userreq);
            kastrat=(TextView) itemView.findViewById(R.id.kastrat_udomljenipsi_korisnik_item_userreq);
            potvrdaoglasa_nestalipsi_korisnik_item_userreq = (Button) itemView.findViewById(R.id.potvrdaoglasa_udomljenipsi_korisnik_item_userreq);
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

//    public static String getNestanak_napomena() {
//        return nestanak_napomena;
//    }

    public static String getUrl_slike() {
        return url_slike;
    }

//    public static String getAzil_nestanak_napomena() {
//        return azil_nestanak_napomena;
//    }

    public static String getPrihvaceno() {
        return prihvaceno;
    }

    public static int getChange() {
        return change;
    }

    public static long getPostavljeno() {
        return postavljeno;
    }

    public static String getKilaza() {
        return kilaza;
    }

    public static String getKastrat() {
        return kastrat;
    }

    public static String getAzil_udomljavanje_napomena() {
        return azil_udomljavanje_napomena;
    }

    public static int getUdomljavanje_id() {
        return udomljavanje_id;
    }

    public static String getOpasnost() {
        return opasnost;
    }

    public static String getNapomena() {
        return napomena;
    }
}
