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

public class myreports_missingdogs_adapter extends RecyclerView.Adapter<myreports_missingdogs_adapter.ViewHolder>{
    private static List<myreportsdisapp_data> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime, prezime, adresa, grad, postnum, kontakt, imepsa, pasmina,
            starost, vetlokacija, boja, dlaka, spol, napomena, url, telefonskibr,
    adresa_pronalaska, adresa_za_preuzimanje_psa, ime_nestanak, prezime_nestanak, napomena_nestanak,
    naziv_azila, grad_azila;
    private static Date datumizgubljen;
    private static boolean aktivan, oglas_azila;
    private static int change;
    private static long postavljeno;
    public myreports_missingdogs_adapter(List<myreportsdisapp_data> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public myreports_missingdogs_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myreports_missingdogs_cards, parent, false);
        return new myreports_missingdogs_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myreports_missingdogs_adapter.ViewHolder holder, int position) {

        final myreportsdisapp_data listItem = listItems.get(position);
//        change= holder.getAdapterPosition();

//        holder.ime.setText(listItem.getIme());
        holder.ime_missingdog_adapter.setText(listItem.getIme());
        holder.prezime_missingdog_adapter.setText(listItem.getPrezime());
        holder.adresapronalaska_missingdog_adapter.setText(listItem.getAdresa_pronalaska());
        holder.adresapreuzimanja_missingdog_adapter.setText(listItem.getAdresa_za_preuzimanje_psa());
        holder.napomena_missingdog_adapter.setText(listItem.getNapomena());
        holder.kontakt_missingdog_adapter.setText(listItem.getKontakt());
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljeno_missingdog_adapter.setText(format.format(date));

        SharedPreferences sp1=myreports_missingdogs_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        holder.more_missingdog_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change=holder.getAdapterPosition();
                napomena_nestanak=listItem.getNapomena_nestanak();
                adresa_pronalaska=listItem.getAdresa_pronalaska();
                adresa_za_preuzimanje_psa=listItem.getAdresa_za_preuzimanje_psa();
                ime_nestanak=listItem.getIme_nestanak();
                prezime_nestanak=listItem.getPrezime_nestanak();
                naziv_azila=listItem.getNaziv_azila();
                grad_azila=listItem.getGrad_azila();
                kontakt=listItem.getKontakt();
                oglas_azila=listItem.isOglas_azila();
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
                datumizgubljen=listItem.getDatum_izgubljen();
                dlaka=listItem.getDlaka();
                spol=listItem.getSpol();
                postavljeno=listItem.getPostavljeno();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                myreports_missingdogs_dialog mymissingdogs=new myreports_missingdogs_dialog();
                mymissingdogs.show(manager, "mymissingdogs");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
//        public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad, postnum, vet_lok, spol, starost;
        public TextView ime_missingdog_adapter, prezime_missingdog_adapter, adresapronalaska_missingdog_adapter,
                adresapreuzimanja_missingdog_adapter, napomena_missingdog_adapter, kontakt_missingdog_adapter,
                postavljeno_missingdog_adapter;
        public Button more_missingdog_adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            ime_missingdog_adapter=(TextView) itemView.findViewById(R.id.ime_missingdog_adapter);
            prezime_missingdog_adapter=(TextView) itemView.findViewById(R.id.prezime_missingdog_adapter);
            adresapronalaska_missingdog_adapter=(TextView) itemView.findViewById(R.id.adresapronalaska_missingdog_adapter);
            adresapreuzimanja_missingdog_adapter=(TextView) itemView.findViewById(R.id.adresapreuzimanja_missingdog_adapter);
            napomena_missingdog_adapter=(TextView) itemView.findViewById(R.id.napomena_missingdog_adapter);
            kontakt_missingdog_adapter=(TextView) itemView.findViewById(R.id.kontakt_missingdog_adapter);
            postavljeno_missingdog_adapter=(TextView) itemView.findViewById(R.id.postavljeno_missingdog_adapter);
            more_missingdog_adapter=(Button) itemView.findViewById(R.id.more_missingdog_adapter);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

//    public static void setUseremail(String useremail) {
//        myreports_missingdogs_adapter.useremail = useremail;
//    }
//

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

//    public static int getLid() {
//        return lid;
//    }

    public static String getTelefonskibr() {
        return telefonskibr;
    }

    public static String getAdresa_pronalaska() {
        return adresa_pronalaska;
    }

    public static String getAdresa_za_preuzimanje_psa() {
        return adresa_za_preuzimanje_psa;
    }

    public static String getIme_nestanak() {
        return ime_nestanak;
    }

    public static String getPrezime_nestanak() {
        return prezime_nestanak;
    }

    public static String getNapomena_nestanak() {
        return napomena_nestanak;
    }

    public static String getNaziv_azila() {
        return naziv_azila;
    }

    public static String getGrad_azila() {
        return grad_azila;
    }

    public static boolean isOglas_azila() {
        return oglas_azila;
    }
}
