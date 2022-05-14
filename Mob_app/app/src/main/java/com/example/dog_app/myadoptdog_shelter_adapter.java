package com.example.dog_app;

import android.content.Context;
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

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class myadoptdog_shelter_adapter extends RecyclerView.Adapter<myadoptdog_shelter_adapter.ViewHolder>{
    private static List<myadopteddogsdata> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime, prezime, adresa, grad, postnum, kontakt, imepsa, pasmina,
            starost, vetlokacija, boja, dlaka, spol, napomena, url, prihvaceno, napomena_azil, kilaza, kastrat, opasnost;
    private static boolean aktivan;
    private static int change;
    private static long postavljeno;

    public myadoptdog_shelter_adapter(List<myadopteddogsdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public myadoptdog_shelter_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myadopteddogs_shelter, parent, false);
        return new myadoptdog_shelter_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myadoptdog_shelter_adapter.ViewHolder holder, int position) {

        final myadopteddogsdata listItem = listItems.get(position);
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
        holder.kilaza.setText(listItem.getKilaza());
        holder.kastrat.setText(listItem.getKastrat());
        holder.opasnost.setText(listItem.getOpasnost());
        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
        SharedPreferences sp1=myadoptdog_shelter_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        holder.promjenapodataka_myadopteddogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change=holder.getAdapterPosition();
                url=null;
                url=listItem.getUrl_slike();
                prihvaceno=null;
                prihvaceno=listItem.getPrihvaceno();
                lid=listItem.getId();
                ime=listItem.getIme();
                prezime=listItem.getPrezime();
                adresa=listItem.getAdresa();
                grad=listItem.getGrad();
                postnum=listItem.getPostanski_broj();
                kontakt=listItem.getTelefonskibr();
                imepsa=listItem.getIme_psa();
                pasmina=listItem.getPasmina();
                starost=listItem.getStarost();
                vetlokacija=listItem.getVet_lokacija();
                boja=listItem.getBoja();
                dlaka=listItem.getDlaka();
                spol=listItem.getSpol();
                postavljeno=listItem.getPostavljeno();
                aktivan=listItem.isAktivan();
                napomena_azil=listItem.getAzil_udomljavanje_napomena();
                kilaza=listItem.getKilaza();
                kastrat=listItem.getKastrat();
                opasnost=listItem.getOpasnost();
                kilaza=listItem.getKilaza();
                kastrat=listItem.getKastrat();
                opasnost=listItem.getOpasnost();
                napomena=listItem.getNapomena();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                mydata_adopteddog_dialog_shelter myadopteddogs=new mydata_adopteddog_dialog_shelter();
                myadopteddogs.show(manager, "myadopteddogsshelter");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView imepsa, postavljeno, telbr, boja, dlaka, pasmina,
                spol, starost, kilaza, kastrat, opasnost;
        public LinearLayout podaci;
        public ImageView slika;
        public Button promjenapodataka_myadopteddogs;

        public ViewHolder(View itemView) {
            super(itemView);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_myadopteddogs);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_myadopteddogs);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_myadopteddogs);
            slika=(ImageView) itemView.findViewById(R.id.slika_myadopteddogs);
            telbr=(TextView) itemView.findViewById(R.id.telbr_myadopteddogs);
            boja=(TextView) itemView.findViewById(R.id.boja_myadopteddogs);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_myadopteddogs);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_myadopteddogs);
            spol=(TextView) itemView.findViewById(R.id.spol_myadopteddogs);
            starost=(TextView) itemView.findViewById(R.id.starost_myadopteddogs);
            promjenapodataka_myadopteddogs=(Button) itemView.findViewById(R.id.promjenapodataka_myadopteddogs_shelter);
            kilaza=(TextView) itemView.findViewById(R.id.kilaza_myadopteddogs);
            kastrat=(TextView) itemView.findViewById(R.id.kastrat_myadopteddogs);
            opasnost=(TextView) itemView.findViewById(R.id.opasnost_myadopteddogs);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

    public static void setUseremail(String useremail) {
        myadoptdog_shelter_adapter.useremail = useremail;
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

    public static String getKilaza() {
        return kilaza;
    }


    public static String getKastrat() {
        return kastrat;
    }

    public static String getOpasnost() {
        return opasnost;
    }
}
