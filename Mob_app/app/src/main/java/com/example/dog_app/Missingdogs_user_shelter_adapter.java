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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Missingdogs_user_shelter_adapter extends RecyclerView.Adapter<Missingdogs_user_shelter_adapter.ViewHolder>{
    private List<Missingdogs_user_shelter_data> listItems;
    private Context context;
    private static int lid;
    private static String naziv_azila, grad_azila, email_azila;

    public Missingdogs_user_shelter_adapter(List<Missingdogs_user_shelter_data> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public Missingdogs_user_shelter_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_shelter, parent, false);
        return new Missingdogs_user_shelter_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Missingdogs_user_shelter_adapter.ViewHolder holder, int position) {

        final Missingdogs_user_shelter_data listItem = listItems.get(position);

        holder.imeazila_user_shelter.setText(listItem.getNaziv());
        holder.grad_user_shelter.setText(listItem.getGrad());
        holder.ulica_user_shelter.setText(listItem.getUlica());
        holder.postnum_user_shelter.setText(listItem.getPostanski_broj());
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljeno_nestalipsi_korisnik_azil.setText(format.format(date));
        holder.imepsa_nestalipsi_korisnik_azil.setText(listItem.getIme_psa());
        holder.telbr_nestalipsi_korisnik_azil.setText(listItem.getTelefonskibr());
        holder.boja_nestalipsi_korisnik_azil.setText(listItem.getBoja());
        holder.dlaka_nestalipsi_korisnik_azil.setText(listItem.getDlaka());
        holder.pasmina_nestalipsi_korisnik_azil.setText(listItem.getPasmina());
        holder.spol_nestalipsi_korisnik_azil.setText(listItem.getSpol());
        holder.starost_nestalipsi_korisnik_azil.setText(listItem.getStarost());
        holder.chip_nestalipsi_korisnik_azil.setText(listItem.getDatum_izgubljen());
        if(listItem.getUrl_slike()!=null) {
            holder.slika_nestalipsi_korisnik_azil.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika_nestalipsi_korisnik_azil);
        }
        else{
            holder.slika_nestalipsi_korisnik_azil.setVisibility(View.GONE);
        }


        holder.komentiraj_nestalipsi_korisnik_azil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences sp1=MyAdapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
//                SharedPreferences sp1=context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
                email_azila="";
                MyAdapter.setUseremail("");
                email_azila=listItem.getEmail();
                Intent i=new Intent(context, komentari_korisnik_azil.class);
                i.putExtra("id", listItem.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        SharedPreferences sp1=Missingdogs_user_shelter_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        if(!getemail.equals(listItem.getEmail())){
            holder.pronadenpas_nestalipsi_korisnik_azil.setVisibility(View.VISIBLE);
        }
        else{holder.pronadenpas_nestalipsi_korisnik_azil.setVisibility(View.GONE);}
        holder.pronadenpas_nestalipsi_korisnik_azil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid=listItem.getId();
                naziv_azila=listItem.getNaziv();
                grad_azila=listItem.getGrad();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                Dogfind_dialog_shelter dialog= new Dogfind_dialog_shelter();
                dialog.show(manager, "dogfind_dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView slika_nestalipsi_korisnik_azil;
        private TextView imeazila_user_shelter, grad_user_shelter, ulica_user_shelter,
                postnum_user_shelter, postavljeno_nestalipsi_korisnik_azil,
                imepsa_nestalipsi_korisnik_azil, telbr_nestalipsi_korisnik_azil,
                boja_nestalipsi_korisnik_azil, dlaka_nestalipsi_korisnik_azil,
                pasmina_nestalipsi_korisnik_azil, spol_nestalipsi_korisnik_azil,
                starost_nestalipsi_korisnik_azil;
        private Chip chip_nestalipsi_korisnik_azil;
        private Button komentiraj_nestalipsi_korisnik_azil, pronadenpas_nestalipsi_korisnik_azil;

        public ViewHolder(View itemView) {
            super(itemView);
            slika_nestalipsi_korisnik_azil=(ImageView) itemView.findViewById(R.id.slika_nestalipsi_korisnik_azil);
            imeazila_user_shelter=(TextView) itemView.findViewById(R.id.imeazila_user_shelter);
            grad_user_shelter=(TextView) itemView.findViewById(R.id.grad_user_shelter);
            ulica_user_shelter=(TextView) itemView.findViewById(R.id.ulica_user_shelter);
            postnum_user_shelter=(TextView) itemView.findViewById(R.id.postnum_user_shelter);
            postavljeno_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.postavljeno_nestalipsi_korisnik_azil);
            imepsa_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.imepsa_nestalipsi_korisnik_azil);
            telbr_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.telbr_nestalipsi_korisnik_azil);
            boja_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.boja_nestalipsi_korisnik_azil);
            dlaka_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.dlaka_nestalipsi_korisnik_azil);
            pasmina_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.pasmina_nestalipsi_korisnik_azil);
            spol_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.spol_nestalipsi_korisnik_azil);
            starost_nestalipsi_korisnik_azil=(TextView) itemView.findViewById(R.id.starost_nestalipsi_korisnik_azil);
            chip_nestalipsi_korisnik_azil=(Chip) itemView.findViewById(R.id.chip_nestalipsi_korisnik_azil);
            komentiraj_nestalipsi_korisnik_azil=(Button) itemView.findViewById(R.id.komentiraj_nestalipsi_korisnik_azil);
            pronadenpas_nestalipsi_korisnik_azil=(Button) itemView.findViewById(R.id.pronadenpas_nestalipsi_korisnik_azil);
        }
    }
    public static int getid(){return lid;}
    public static String getNaziv_azila(){return naziv_azila;}
    public static String getGrad_azila(){return grad_azila;}
    public static String getEmail_azila() {
        return email_azila;
    }

    public static void setEmail_azila(String email_azila) {
        Missingdogs_user_shelter_adapter.email_azila = email_azila;
    }
}
