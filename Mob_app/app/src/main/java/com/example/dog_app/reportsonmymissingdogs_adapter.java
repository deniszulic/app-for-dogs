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

public class reportsonmymissingdogs_adapter extends RecyclerView.Adapter<reportsonmymissingdogs_adapter.ViewHolder>{
    private static List<reportsonmymissingdogsdata> listItems;
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
    public reportsonmymissingdogs_adapter(List<reportsonmymissingdogsdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public reportsonmymissingdogs_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reportsonmymissingdogs_cards, parent, false);
        return new reportsonmymissingdogs_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(reportsonmymissingdogs_adapter.ViewHolder holder, int position) {

        final reportsonmymissingdogsdata listItem = listItems.get(position);
//        change= holder.getAdapterPosition();

//        holder.ime.setText(listItem.getIme());

//        holder.imeadopteddog_adapter.setText(listItem.getIme());
//        holder.prezimeadopteddog_adapter.setText(listItem.getPrezime());
//        holder.adresa_adopteddog_adapter.setText(listItem.getAdresa());
//        holder.gradadopteddog_adapter.setText(listItem.getGrad());
//        holder.postnumadopteddog_adapter.setText(listItem.getPostanski_broj());
//        holder.razlogprijaveadopteddog_adapter.setText(listItem.getRazlog_prijave());
//        holder.kontaktadopteddog_adapter.setText(listItem.getKontakt());
        holder.ime_reportsonmymissingdog.setText(listItem.getIme());
        holder.prezime_reportsonmymissingdog.setText(listItem.getPrezime());
        holder.adresapronalaska_reportsonmymissingdog.setText(listItem.getAdresa_pronalaska());
        holder.adresapreuzimanja_reportsonmymissingdog.setText(listItem.getAdresa_za_preuzimanje_psa());
        holder.kontakt_reportsonmymissingdog.setText(listItem.getKontakt());
        holder.napomena_reportsonmymissingdog.setText(listItem.getNapomena());
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljeno_reportsonmymissingdog.setText(format.format(date));

        SharedPreferences sp1=reportsonmymissingdogs_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        holder.more_reportsonmymissingdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change=holder.getAdapterPosition();
                napomena_nestanak=listItem.getNestanak_napomena();
                adresa_pronalaska=listItem.getAdresa_pronalaska();
                adresa_za_preuzimanje_psa=listItem.getAdresa_za_preuzimanje_psa();
                ime_nestanak=listItem.getNestanak_ime();
                prezime_nestanak=listItem.getNestanak_prezime();
//                naziv_azila=listItem.getNaziv_azila();
//                grad_azila=listItem.getGrad_azila();
                kontakt=listItem.getKontakt();
//                oglas_azila=listItem.isOglas_azila();
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
                reportsonmymisingdog_dialog dialog=new reportsonmymisingdog_dialog();
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
        public TextView ime_reportsonmymissingdog, prezime_reportsonmymissingdog, adresapronalaska_reportsonmymissingdog,
                adresapreuzimanja_reportsonmymissingdog, kontakt_reportsonmymissingdog, napomena_reportsonmymissingdog,
                postavljeno_reportsonmymissingdog;
        public Button more_reportsonmymissingdog;

        public ViewHolder(View itemView) {
            super(itemView);
            ime_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.ime_reportsonmymissingdog);
            prezime_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.prezime_reportsonmymissingdog);
            adresapronalaska_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.adresapronalaska_reportsonmymissingdog);
            adresapreuzimanja_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.adresapreuzimanja_reportsonmymissingdog);
            kontakt_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.kontakt_reportsonmymissingdog);
            napomena_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.napomena_reportsonmymissingdog);
            postavljeno_reportsonmymissingdog=(TextView) itemView.findViewById(R.id.postavljeno_reportsonmymissingdog);
            more_reportsonmymissingdog=(Button) itemView.findViewById(R.id.more_reportsonmymissingdog);
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
