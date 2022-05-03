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

import retrofit2.Call;

public class mymissingdogsadapter extends RecyclerView.Adapter<mymissingdogsadapter.ViewHolder> {
    private static List<mymissingdogsdata> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime, prezime, adresa, grad, postnum, kontakt, imepsa, pasmina,
            starost, vetlokacija, boja, dlaka, spol, napomena, url, prihvaceno, napomena_azil;
    private static Date datumizgubljen;
    private static boolean aktivan;
    private static int change;
    private static long postavljeno;
//    private static mymissingdogsadapter.ViewHolder hold;

    public mymissingdogsadapter(List<mymissingdogsdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public mymissingdogsadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mymissingdogs, parent, false);
        return new mymissingdogsadapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(mymissingdogsadapter.ViewHolder holder, int position) {

        final mymissingdogsdata listItem = listItems.get(position);
//        change= holder.getAdapterPosition();

//        holder.email_admin.setText(listItem.getEmail());
//        holder.status_admin.setText(listItem.getStatus());
//        holder.datum_admin.setText(listItem.getDatum());
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
        holder.postnum.setText(listItem.getPostanski_broj());
        holder.vet_lok.setText(listItem.getVet_lokacija());
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
//                SharedPreferences sp1=mymissingdogsadapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
//                SharedPreferences sp1=context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
                //useremail="";
                //Missingdogs_user_shelter_adapter.setEmail_azila("");
                //useremail=listItem.getEmail();
                Intent i=new Intent(context, komentari_korisnik.class);
                i.putExtra("id", listItem.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        SharedPreferences sp1=mymissingdogsadapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
//        if(!getemail.equals(listItem.getEmail())){
//            holder.pronadenpas_nestalipsi_korisnik.setVisibility(View.VISIBLE);
//        }
//        else{holder.pronadenpas_nestalipsi_korisnik.setVisibility(View.GONE);}
//        holder.pronadenpas_nestalipsi_korisnik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lid=listItem.getId();
//                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
//                Dogfind_dialog dialog= new Dogfind_dialog();
//                dialog.show(manager, "dogfind_dialog");
//            }
//        });
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
                //Date date2= new Date(String.valueOf(listItem.getDatum_izgubljen()));
                //datumizgubljen=listItem.getDatum_izgubljen();
                datumizgubljen=listItem.getDatum_izgubljen();
//                System.out.println("aaaa:"+datumizgubljen);
                dlaka="";
                dlaka=listItem.getDlaka();
                spol="";
                spol=listItem.getSpol();
                napomena="";
                napomena=listItem.getNestanak_napomena();
                postavljeno=listItem.getPostavljeno();
//                Date date = new Date(listItem.getPostavljeno());
//                DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
//                postavljeno=format.format(date);
                aktivan=listItem.isAktivan();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                mydata_missingdog_dialog mymissingdogs=new mydata_missingdog_dialog();
                mymissingdogs.show(manager, "mymissingdogs");
            }
        });
        holder.azil_mymissingdogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid=listItem.getId();
                prihvaceno=listItem.getPrihvaceno();
                napomena_azil=listItem.getNapomena();
                FragmentManager manager1 = ((AppCompatActivity)context).getSupportFragmentManager();
                reporttoshelter_dialog dialog=new reporttoshelter_dialog();
                dialog.show(manager1, "shelters");
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
        public Button komentiraj_mymissingdogs, promjenapodataka_mymissingdogs, azil_mymissingdogs;

        public ViewHolder(View itemView) {
            super(itemView);
            ime=(TextView) itemView.findViewById(R.id.ime_mymissingdogs);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_mymissingdogs);

            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_mymissingdogs);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_mymissingdogs);
            slika=(ImageView) itemView.findViewById(R.id.slika_mymissingdogs);

            adresa=(TextView) itemView.findViewById(R.id.adresa_mymissingdogs);
            telbr=(TextView) itemView.findViewById(R.id.telbr_mymissingdogs);
            boja=(TextView) itemView.findViewById(R.id.boja_mymissingdogs);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_mymissingdogs);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_mymissingdogs);
            prezime=(TextView) itemView.findViewById(R.id.prezime_mymissingdogs);
            chip=(Chip) itemView.findViewById(R.id.chip_mymissingdogs);
            postnum=(TextView) itemView.findViewById(R.id.postnum_mymissingdogs);
            vet_lok=(TextView) itemView.findViewById(R.id.vet_lokacija_mymissingdogs);
            spol=(TextView) itemView.findViewById(R.id.spol_mymissingdogs);
            starost=(TextView) itemView.findViewById(R.id.starost_mymissingdogs);
            grad=(TextView) itemView.findViewById(R.id.grad_mymissingdogs);
            komentiraj_mymissingdogs=(Button) itemView.findViewById(R.id.komentiraj_mymissingdogs);
            azil_mymissingdogs=(Button) itemView.findViewById(R.id.azil_mymissingdogs);
            promjenapodataka_mymissingdogs=(Button) itemView.findViewById(R.id.promjenapodataka_mymissingdogs);
        }
    }
    public static int getid(){return lid;}

    public static String getUseremail() {
        return useremail;
    }

    public static void setUseremail(String useremail) {
        mymissingdogsadapter.useremail = useremail;
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

//    public static void setAktivan(boolean aktivan) {
//        mymissingdogsadapter.aktivan = aktivan;
//    }
    //
//    public static List<mymissingdogsdata> getListItems() {
//        return listItems;
//    }
}
