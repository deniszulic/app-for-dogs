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

public class adoptdog_user_adapter extends RecyclerView.Adapter<adoptdog_user_adapter.ViewHolder>{
    private List<useradopteddogdata> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime_vlasnika, prezime_vlasnika, adresa, grad, ime_psa, pasmina;
    private static int postanski_broj, kilaza;

    public adoptdog_user_adapter(List<useradopteddogdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public adoptdog_user_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_adoptdog, parent, false);
        return new adoptdog_user_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adoptdog_user_adapter.ViewHolder holder, int position) {

        final useradopteddogdata listItem = listItems.get(position);
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
        holder.kilaza.setText(String.valueOf(listItem.getKilaza()));
        holder.kastrat.setText(listItem.getKastrat());
        holder.opasnost.setText(listItem.getOpasnost());
        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
        SharedPreferences sp1=adoptdog_user_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        if(!getemail.equals(listItem.getEmail())){
            holder.udomipsa_udomljenipsi_korisnik.setVisibility(View.VISIBLE);
        }
        else{holder.udomipsa_udomljenipsi_korisnik.setVisibility(View.GONE);}
        holder.udomipsa_udomljenipsi_korisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid=listItem.getId();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
//                Dogfind_dialog dialog= new Dogfind_dialog();
                ime_vlasnika="";
                ime_vlasnika=listItem.getIme();
                prezime_vlasnika="";
                prezime_vlasnika=listItem.getPrezime();
                adresa="";
                adresa=listItem.getAdresa();
                grad="";
                grad=listItem.getGrad();
                postanski_broj=0;
                postanski_broj=listItem.getPostanski_broj();
                ime_psa="";
                ime_psa=listItem.getIme_psa();
                kilaza=0;
                kilaza=listItem.getKilaza();
                pasmina="";
                pasmina=listItem.getPasmina();
                Dogfind_dialog_adoptdog dialog_adoptdog=new Dogfind_dialog_adoptdog();
                dialog_adoptdog.show(manager, "dogfind_dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad, postnum, vet_lok, spol, starost, kastrat, kilaza, opasnost;
        public LinearLayout podaci;
        public ImageView slika;
        public Button udomipsa_udomljenipsi_korisnik;

        public ViewHolder(View itemView) {
            super(itemView);
            ime=(TextView) itemView.findViewById(R.id.ime_udomljenipsi_korisnik_item);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_udomljenipsi_korisnik_item);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_udomljenipsi_korisnik);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_udomljenipsi_korisnik);
            slika=(ImageView) itemView.findViewById(R.id.slika_udomljenipsi_korisnik);
            adresa=(TextView) itemView.findViewById(R.id.adresa_udomljenipsi_korisnik_item);
            telbr=(TextView) itemView.findViewById(R.id.telbr_udomljenipsi_korisnik_item);
            boja=(TextView) itemView.findViewById(R.id.boja_udomljenipsi_korisnik_item);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_udomljenipsi_korisnik_item);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_udomljenipsi_korisnik_item);
            prezime=(TextView) itemView.findViewById(R.id.prezime_udomljenipsi_korisnik_item);
            postnum=(TextView) itemView.findViewById(R.id.postnum_udomljenipsi_korisnik_item);
            vet_lok=(TextView) itemView.findViewById(R.id.vet_lokacija_udomljenipsi_korisnik_item);
            spol=(TextView) itemView.findViewById(R.id.spol_udomljenipsi_korisnik_item);
            starost=(TextView) itemView.findViewById(R.id.starost_udomljenipsi_korisnik_item);
            grad=(TextView) itemView.findViewById(R.id.grad_udomljenipsi_korisnik_item);
            kastrat=(TextView) itemView.findViewById(R.id.kastrat_udomljenipsi_korisnik_item);
            kilaza=(TextView) itemView.findViewById(R.id.kilaza_udomljenipsi_korisnik_item);
            opasnost=(TextView) itemView.findViewById(R.id.opasnost_udomljenipsi_korisnik_item);
            udomipsa_udomljenipsi_korisnik=(Button) itemView.findViewById(R.id.udomipsa_udomljenipsi_korisnik);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

    public static void setUseremail(String useremail) {
        adoptdog_user_adapter.useremail = useremail;
    }

    public static String getIme_vlasnika() {
        return ime_vlasnika;
    }

    public static String getPrezime_vlasnika() {
        return prezime_vlasnika;
    }

    public static String getAdresa() {
        return adresa;
    }

    public static String getGrad() {
        return grad;
    }

    public static String getIme_psa() {
        return ime_psa;
    }

    public static String getPasmina() {
        return pasmina;
    }

    public static int getPostanski_broj() {
        return postanski_broj;
    }

    public static int getKilaza() {
        return kilaza;
    }
}
