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

public class mymissingdogsadapter_shelter extends RecyclerView.Adapter<mymissingdogsadapter_shelter.ViewHolder> {
    private static List<mymissingdogsdata> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime, prezime, adresa, grad, postnum, kontakt, imepsa, pasmina,
            starost, vetlokacija, boja, dlaka, spol, napomena, url, prihvaceno, napomena_azil;
    private static Date datumizgubljen;
    private static boolean aktivan;
    private static int change;
    private static long postavljeno;

    public mymissingdogsadapter_shelter(List<mymissingdogsdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public mymissingdogsadapter_shelter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mymissingdogs_shelter, parent, false);
        return new mymissingdogsadapter_shelter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(mymissingdogsadapter_shelter.ViewHolder holder, int position) {

        final mymissingdogsdata listItem = listItems.get(position);
        holder.imepsa.setText(listItem.getIme_psa());
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljeno.setText(format.format(date));
        holder.telbr.setText(listItem.getTelefonskibr());
        holder.boja.setText(listItem.getBoja());
        holder.dlaka.setText(listItem.getDlaka());
        holder.pasmina.setText(listItem.getPasmina());
        holder.spol.setText(listItem.getSpol());
        holder.starost.setText(listItem.getStarost());
        Date date1 = new Date(String.valueOf(listItem.getDatum_izgubljen()));
        holder.chip.setText(format.format(date1));
        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
        holder.komentiraj_mymissingdogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, mojikomentari_azil.class);
                i.putExtra("id", listItem.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        SharedPreferences sp1=mymissingdogsadapter_shelter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        holder.promjenapodataka_mymissingdogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change=holder.getAdapterPosition();
                url=null;
                url=listItem.getUrl_slike();
                prihvaceno=null;
                prihvaceno=listItem.getPrihvaceno();
                lid=listItem.getId();
                ime="";
                ime=listItem.getIme();
                prezime="";
                prezime=listItem.getPrezime();
                adresa="";
                adresa=listItem.getAdresa();
                grad="";
                grad=listItem.getGrad();
                postnum="";
                postnum=listItem.getPostanski_broj();
                kontakt="";
                kontakt=listItem.getTelefonskibr();
                imepsa="";
                imepsa=listItem.getIme_psa();
                pasmina="";
                pasmina=listItem.getPasmina();
                starost="";
                starost=listItem.getStarost();
                vetlokacija="";
                vetlokacija=listItem.getVet_lokacija();
                boja="";
                boja=listItem.getBoja();
                datumizgubljen=null;
                datumizgubljen=listItem.getDatum_izgubljen();
                dlaka="";
                dlaka=listItem.getDlaka();
                spol="";
                spol=listItem.getSpol();
                napomena="";
                napomena=listItem.getNestanak_napomena();
                postavljeno=listItem.getPostavljeno();
                aktivan=listItem.isAktivan();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                mydata_missingdog_dialog_shelter mymissingdogs=new mydata_missingdog_dialog_shelter();
                mymissingdogs.show(manager, "mymissingdogsshelter");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView  imepsa, postavljeno, telbr, boja, dlaka, pasmina, spol, starost;
        public LinearLayout podaci;
        public ImageView slika;
        private Chip chip;
        public Button komentiraj_mymissingdogs, promjenapodataka_mymissingdogs;

        public ViewHolder(View itemView) {
            super(itemView);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_mymissingdogs_shelter);

            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_mymissingdogs_shelter);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_mymissingdogs_shelter);
            slika=(ImageView) itemView.findViewById(R.id.slika_mymissingdogs_shelter);

            telbr=(TextView) itemView.findViewById(R.id.telbr_mymissingdogs_shelter);
            boja=(TextView) itemView.findViewById(R.id.boja_mymissingdogs_shelter);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_mymissingdogs_shelter);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_mymissingdogs_shelter);
            chip=(Chip) itemView.findViewById(R.id.chip_mymissingdogs_shelter);
            spol=(TextView) itemView.findViewById(R.id.spol_mymissingdogs_shelter);
            starost=(TextView) itemView.findViewById(R.id.starost_mymissingdogs_shelter);
            komentiraj_mymissingdogs=(Button) itemView.findViewById(R.id.komentiraj_mymissingdogs_shelter);
            promjenapodataka_mymissingdogs=(Button) itemView.findViewById(R.id.promjenapodataka_mymissingdogs_shelter);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

    public static void setUseremail(String useremail) {
        mymissingdogsadapter_shelter.useremail = useremail;
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

    public static String getPrihvaceno() {
        return prihvaceno;
    }

    public static String getNapomena_azil() {
        return napomena_azil;
    }
}
