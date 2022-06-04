package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class reportsonmy_adopteddogs_adapter extends RecyclerView.Adapter<reportsonmy_adopteddogs_adapter.ViewHolder>{
    private static List<reportsonmyadopteddog_data> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime, prezime, adresa, grad, postnum, kontakt, imepsa, pasmina,
            starost, vetlokacija, boja, dlaka, spol, napomena, url, telefonskibr, razlog_prijave,
            prihvaceno, ime_udomljavanje, prezime_udomljavanje, adresa_udomljavanje,
            grad_udomljavanje, postanski_broj_udomljavanje, kilaza, kastrat, opasnost, napomena_udomljavanje;
    private static Date datumizgubljen;
    private static boolean aktivan, oglas_azila;
    private static int change;
    private static long postavljeno;
    public reportsonmy_adopteddogs_adapter(List<reportsonmyadopteddog_data> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public reportsonmy_adopteddogs_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myreports_adopteddogs_cards, parent, false);
        return new reportsonmy_adopteddogs_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(reportsonmy_adopteddogs_adapter.ViewHolder holder, int position) {

        final reportsonmyadopteddog_data listItem = listItems.get(position);
        holder.imeadopteddog_adapter.setText(listItem.getIme());
        holder.prezimeadopteddog_adapter.setText(listItem.getPrezime());
        holder.adresa_adopteddog_adapter.setText(listItem.getAdresa());
        holder.gradadopteddog_adapter.setText(listItem.getGrad());
        holder.postnumadopteddog_adapter.setText(listItem.getPostanski_broj());
        holder.razlogprijaveadopteddog_adapter.setText(listItem.getRazlog_prijave());
        holder.kontaktadopteddog_adapter.setText(listItem.getKontakt());
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljenoadopteddog_adapter.setText(format.format(date));
        if(listItem.getPrihvaceno().equals("True")){
            holder.prihvaceno_adopteddog_adapter.setText("Prihvaćeno");
        }
        else if(listItem.getPrihvaceno().equals("False")){
            holder.prihvaceno_adopteddog_adapter.setText("Odbijeno");
        }
        else {
            holder.prihvaceno_adopteddog_adapter.setText(listItem.getPrihvaceno());
        }

        SharedPreferences sp1=reportsonmy_adopteddogs_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        holder.moreadopteddog_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change=holder.getAdapterPosition();
                kontakt=listItem.getKontakt();
                napomena=listItem.getNapomena();
                url=null;
                url=listItem.getUrl_slike();
                lid=listItem.getId();
                ime=listItem.getIme();
                prezime=listItem.getPrezime();
                adresa=listItem.getAdresa();
                grad=listItem.getGrad();
                postnum=listItem.getPostanski_broj();
                telefonskibr=listItem.getTelefonskibr();
                imepsa=listItem.getIme_psa();
                pasmina=listItem.getPasmina();
                starost=listItem.getStarost();
                vetlokacija=listItem.getVet_lokacija();
                boja=listItem.getBoja();
                datumizgubljen=null;
                dlaka=listItem.getDlaka();
                spol=listItem.getSpol();
                postavljeno=listItem.getPostavljeno();
                prihvaceno=listItem.getPrihvaceno();
                razlog_prijave=listItem.getRazlog_prijave();
                ime_udomljavanje=listItem.getUdomljavanje_ime();
                prezime_udomljavanje=listItem.getUdomljavanje_prezime();
                adresa_udomljavanje=listItem.getUdomljavanje_adresa();
                grad_udomljavanje=listItem.getUdomljavanje_grad();
                postanski_broj_udomljavanje=listItem.getUdomljavanje_postanski_broj();
                kilaza=listItem.getKilaza();
                kastrat=listItem.getKastrat();
                opasnost=listItem.getOpasnost();
                napomena_udomljavanje=listItem.getUdomljavanje_napomena();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                reportsonmyadopteddog_dialog dialog=new reportsonmyadopteddog_dialog();
                dialog.show(manager, "mymissingdogs");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView imeadopteddog_adapter, prezimeadopteddog_adapter, adresa_adopteddog_adapter,
                kontaktadopteddog_adapter, postavljenoadopteddog_adapter, gradadopteddog_adapter,
                postnumadopteddog_adapter, razlogprijaveadopteddog_adapter, prihvaceno_adopteddog_adapter;
        public Button moreadopteddog_adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            imeadopteddog_adapter=(TextView) itemView.findViewById(R.id.ime_adopteddog_adapter);
            prezimeadopteddog_adapter=(TextView) itemView.findViewById(R.id.prezime_adopteddog_adapter);
            adresa_adopteddog_adapter=(TextView) itemView.findViewById(R.id.adresa_adopteddog_adapter);
            gradadopteddog_adapter=(TextView) itemView.findViewById(R.id.grad_adopteddog_adapter);
            postnumadopteddog_adapter=(TextView) itemView.findViewById(R.id.postnum_adopteddog_adapter);
            razlogprijaveadopteddog_adapter=(TextView) itemView.findViewById(R.id.razloprijave_adopteddog_adapter);
            kontaktadopteddog_adapter=(TextView) itemView.findViewById(R.id.kontakt_adopteddog_adapter);
            postavljenoadopteddog_adapter=(TextView) itemView.findViewById(R.id.postavljeno_adopteddog_adapter);
            prihvaceno_adopteddog_adapter=(TextView) itemView.findViewById(R.id.prihvaceno_adopteddog_adapter);
            moreadopteddog_adapter=(Button) itemView.findViewById(R.id.more_adopteddog_adapter);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

    public static String getIme() {
        return ime;
    }

    public static String getPrezime() {
        return prezime;
    }

    public static String getAdresa() {
        return adresa;
    }

    public static String getGrad() {
        return grad;
    }

    public static String getPostnum() {
        return postnum;
    }

    public static String getKontakt() {
        return kontakt;
    }

    public static String getImepsa() {
        return imepsa;
    }

    public static String getPasmina() {
        return pasmina;
    }

    public static String getStarost() {
        return starost;
    }

    public static String getVetlokacija() {
        return vetlokacija;
    }

    public static String getBoja() {
        return boja;
    }

    public static Date getDatumizgubljen() {
        return datumizgubljen;
    }

    public static String getDlaka() {
        return dlaka;
    }

    public static String getSpol() {
        return spol;
    }

    public static String getNapomena() {
        return napomena;
    }

    public static long getPostavljeno() {
        return postavljeno;
    }

    public static boolean isAktivan() {
        return aktivan;
    }

    public static int getChange() {
        return change;
    }

    public static String getUrl() {
        return url;
    }

    public static String getTelefonskibr() {
        return telefonskibr;
    }

    public static boolean isOglas_azila() {
        return oglas_azila;
    }

    public static String getRazlog_prijave() {
        return razlog_prijave;
    }

    public static String getPrihvaceno() {
        return prihvaceno;
    }

    public static String getIme_udomljavanje() {
        return ime_udomljavanje;
    }

    public static String getPrezime_udomljavanje() {
        return prezime_udomljavanje;
    }

    public static String getAdresa_udomljavanje() {
        return adresa_udomljavanje;
    }

    public static String getGrad_udomljavanje() {
        return grad_udomljavanje;
    }

    public static String getPostanski_broj_udomljavanje() {
        return postanski_broj_udomljavanje;
    }

    public static String getKilaza() {
        return kilaza;
    }

    public static String getKastrat() {
        return kastrat;
    }

    public static String getOpasnost() {
        return opasnost;
    }

    public static String getNapomena_udomljavanje() {
        return napomena_udomljavanje;
    }
}
