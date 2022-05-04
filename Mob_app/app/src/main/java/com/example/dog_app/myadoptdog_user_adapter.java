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

public class myadoptdog_user_adapter extends RecyclerView.Adapter<myadoptdog_user_adapter.ViewHolder>{
    private static List<myadopteddogsdata> listItems;
    private Context context;
    private static int lid;
    private static String useremail, ime, prezime, adresa, grad, postnum, kontakt, imepsa, pasmina,
            starost, vetlokacija, boja, dlaka, spol, napomena, url, prihvaceno, napomena_azil, kilaza, kastrat, opasnost;
//    private static Date datumizgubljen;
    private static boolean aktivan;
    private static int change;
    private static long postavljeno;
//    private static myadoptdog_user_adapter.ViewHolder hold;

    public myadoptdog_user_adapter(List<myadopteddogsdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public myadoptdog_user_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myadopteddogs, parent, false);
        return new myadoptdog_user_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myadoptdog_user_adapter.ViewHolder holder, int position) {

        final myadopteddogsdata listItem = listItems.get(position);
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
        holder.kilaza.setText(listItem.getKilaza());
        holder.kastrat.setText(listItem.getKastrat());
        holder.opasnost.setText(listItem.getOpasnost());
                        //        Date date1 = new Date(String.valueOf(listItem.getDatum_izgubljen()));
                        //        holder.chip.setText(format.format(date1));
        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
//        holder.komentiraj_mymissingdogs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                SharedPreferences sp1=myadoptdog_user_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
////                SharedPreferences sp1=context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
//                //useremail="";
//                //Missingdogs_user_shelter_adapter.setEmail_azila("");
//                //useremail=listItem.getEmail();
//                Intent i=new Intent(context, komentari_korisnik.class);
//                i.putExtra("id", listItem.getId());
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//            }
//        });
        SharedPreferences sp1=myadoptdog_user_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
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
                mydata_adopteddog_dialog myadopteddogs=new mydata_adopteddog_dialog();
                myadopteddogs.show(manager, "myadopteddogs");
            }
        });
        holder.azil_myadopteddogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                lid=listItem.getId();
//                prihvaceno=listItem.getPrihvaceno();
//                napomena_azil=listItem.getNapomena();
//                change=holder.getAdapterPosition();
//                url=null;
//                url=listItem.getUrl_slike();
//                ime=listItem.getIme();
//                prezime=listItem.getPrezime();
//                adresa=listItem.getAdresa();
//                grad=listItem.getGrad();
//                postnum=listItem.getPostanski_broj();
//                kontakt=listItem.getTelefonskibr();
//                imepsa=listItem.getIme_psa();
//                pasmina=listItem.getPasmina();
//                starost=listItem.getStarost();
//                vetlokacija=listItem.getVet_lokacija();
//                boja=listItem.getBoja();
//                dlaka=listItem.getDlaka();
//                spol=listItem.getSpol();
//                postavljeno=listItem.getPostavljeno();
//                aktivan=listItem.isAktivan();
//                kilaza=listItem.getKilaza();
//                kastrat=listItem.getKastrat();
//                opasnost=listItem.getOpasnost();

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
                FragmentManager manager1 = ((AppCompatActivity)context).getSupportFragmentManager();
                reporttoshelter_dialog_adoptdog dialog=new reporttoshelter_dialog_adoptdog();
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
        public TextView ime, imepsa, postavljeno, prezime, adresa, telbr, boja, dlaka, pasmina, grad,
                postnum, vet_lok, spol, starost, kilaza, kastrat, opasnost;
        public LinearLayout podaci;
        public ImageView slika;
        public Button promjenapodataka_myadopteddogs, azil_myadopteddogs;

        public ViewHolder(View itemView) {
            super(itemView);
            ime=(TextView) itemView.findViewById(R.id.ime_myadopteddogs);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_myadopteddogs);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_myadopteddogs);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_myadopteddogs);
            slika=(ImageView) itemView.findViewById(R.id.slika_myadopteddogs);
            adresa=(TextView) itemView.findViewById(R.id.adresa_myadopteddogs);
            telbr=(TextView) itemView.findViewById(R.id.telbr_myadopteddogs);
            boja=(TextView) itemView.findViewById(R.id.boja_myadopteddogs);
            dlaka=(TextView) itemView.findViewById(R.id.dlaka_myadopteddogs);
            pasmina=(TextView) itemView.findViewById(R.id.pasmina_myadopteddogs);
            prezime=(TextView) itemView.findViewById(R.id.prezime_myadopteddogs);
            postnum=(TextView) itemView.findViewById(R.id.postnum_myadopteddogs);
            vet_lok=(TextView) itemView.findViewById(R.id.vet_lokacija_myadopteddogs);
            spol=(TextView) itemView.findViewById(R.id.spol_myadopteddogs);
            starost=(TextView) itemView.findViewById(R.id.starost_myadopteddogs);
            grad=(TextView) itemView.findViewById(R.id.grad_myadopteddogs);
            azil_myadopteddogs=(Button) itemView.findViewById(R.id.azil_myadopteddogs);
            promjenapodataka_myadopteddogs=(Button) itemView.findViewById(R.id.promjenapodataka_myadopteddogs);
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
        myadoptdog_user_adapter.useremail = useremail;
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

//    public static Date getDatumizgubljen() {
//        return datumizgubljen;
//    }

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
