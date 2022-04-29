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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class adopt_shelter_adapter extends RecyclerView.Adapter<adopt_shelter_adapter.ViewHolder>{
    private List<shelteradopteddogdata> listItems;
    private Context context;
    private static int lid;
    private static String ulica, postanski_broj, grad, ime_psa, pasmina, kilaza, naziv_azila, grad_azila;

    public adopt_shelter_adapter(List<shelteradopteddogdata> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public adopt_shelter_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_adoptdog_shelter, parent, false);
        return new adopt_shelter_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(adopt_shelter_adapter.ViewHolder holder, int position) {

        final shelteradopteddogdata listItem = listItems.get(position);
        Date date = new Date(listItem.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        holder.postavljeno_shelter_adoptdog.setText(format.format(date));
        holder.imeazila_shelter_adoptdog.setText(listItem.getNaziv());
        holder.grad_shelter_adoptdog.setText(listItem.getGrad());
        holder.ulica_shelter_adoptdog.setText(listItem.getUlica());
        holder.postnum_shelter_adoptdog.setText(listItem.getPostanski_broj());
        holder.imepsa_shelter_adoptdog.setText(listItem.getIme_psa());
        holder.telbr_shelter_adoptdog.setText(listItem.getTelefonskibr());
        holder.boja_shelter_adoptdog.setText(listItem.getBoja());
        holder.dlaka_shelter_adoptdog.setText(listItem.getDlaka());
        holder.pasmina_shelter_adoptdog.setText(listItem.getPasmina());
        holder.spol_shelter_adoptdog.setText(listItem.getSpol());
        holder.starost_shelter_adoptdog.setText(listItem.getStarost());
        holder.kastrat_shelter_adoptdog.setText(listItem.getKastrat());
        holder.kilaza_shelter_adoptdog.setText(listItem.getKilaza());
        holder.opasnost_shelter_adoptdog.setText(listItem.getOpasnost());
        holder.napomena_shelter_adoptdog.setText(listItem.getNapomena());

        if(listItem.getUrl_slike()!=null) {
            holder.slika.setVisibility(View.VISIBLE);
            Picasso.get().load(listItem.getUrl_slike()).into(holder.slika);
        }
        else{
            holder.slika.setVisibility(View.GONE);
        }
        SharedPreferences sp1=adopt_shelter_adapter.this.context.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        if(!getemail.equals(listItem.getEmail())){
            holder.udomipsa_shelter_adoptdog.setVisibility(View.VISIBLE);
        }
        else{holder.udomipsa_shelter_adoptdog.setVisibility(View.GONE);}
        holder.udomipsa_shelter_adoptdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lid=listItem.getId();
                naziv_azila=listItem.getNaziv();
                grad_azila=listItem.getGrad();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                grad="";
                grad=listItem.getGrad();
                ulica="";
                ulica=listItem.getUlica();
                postanski_broj="";
                postanski_broj=listItem.getPostanski_broj();
                ime_psa="";
                ime_psa=listItem.getIme_psa();
                pasmina="";
                pasmina=listItem.getPasmina();
                kilaza="";
                kilaza=listItem.getKilaza();
                dogfind_dialog_adoptdog_shelter dialog_adoptdog=new dogfind_dialog_adoptdog_shelter();
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
        public TextView imeazila_shelter_adoptdog, grad_shelter_adoptdog, ulica_shelter_adoptdog,
                postnum_shelter_adoptdog, postavljeno_shelter_adoptdog, imepsa_shelter_adoptdog,
                telbr_shelter_adoptdog, boja_shelter_adoptdog, dlaka_shelter_adoptdog, pasmina_shelter_adoptdog,
                spol_shelter_adoptdog, starost_shelter_adoptdog, kastrat_shelter_adoptdog, kilaza_shelter_adoptdog,
                opasnost_shelter_adoptdog, napomena_shelter_adoptdog;
        public LinearLayout podaci;
        public ImageView slika;
        public Button udomipsa_shelter_adoptdog;

        public ViewHolder(View itemView) {
            super(itemView);
            imeazila_shelter_adoptdog=(TextView) itemView.findViewById(R.id.imeazila_shelter_adoptdog);
            grad_shelter_adoptdog=(TextView) itemView.findViewById(R.id.grad_shelter_adoptdog);
            ulica_shelter_adoptdog=(TextView) itemView.findViewById(R.id.ulica_shelter_adoptdog);
            postnum_shelter_adoptdog=(TextView) itemView.findViewById(R.id.postnum_shelter_adoptdog);
            postavljeno_shelter_adoptdog=(TextView) itemView.findViewById(R.id.postavljeno_shelter_adoptdog);
            imepsa_shelter_adoptdog=(TextView) itemView.findViewById(R.id.imepsa_shelter_adoptdog);
            telbr_shelter_adoptdog=(TextView) itemView.findViewById(R.id.telbr_shelter_adoptdog);
            boja_shelter_adoptdog=(TextView) itemView.findViewById(R.id.boja_shelter_adoptdog);
            dlaka_shelter_adoptdog=(TextView) itemView.findViewById(R.id.dlaka_shelter_adoptdog);
            pasmina_shelter_adoptdog=(TextView) itemView.findViewById(R.id.pasmina_shelter_adoptdog);
            spol_shelter_adoptdog=(TextView) itemView.findViewById(R.id.spol_shelter_adoptdog);
            starost_shelter_adoptdog=(TextView) itemView.findViewById(R.id.starost_shelter_adoptdog);
            kastrat_shelter_adoptdog=(TextView) itemView.findViewById(R.id.kastrat_shelter_adoptdog);
            kilaza_shelter_adoptdog=(TextView) itemView.findViewById(R.id.kilaza_shelter_adoptdog);
            opasnost_shelter_adoptdog=(TextView) itemView.findViewById(R.id.opasnost_shelter_adoptdog);
            napomena_shelter_adoptdog=(TextView) itemView.findViewById(R.id.napomena_shelter_adoptdog);
            udomipsa_shelter_adoptdog=(Button) itemView.findViewById(R.id.udomipsa_shelter_adoptdog);
            slika=(ImageView) itemView.findViewById(R.id.slika_shelter_adoptdog);

        }
    }
    public static int getid(){return lid;}

//    public static String getUseremail() {
//        return useremail;
//    }
//
//    public static void setUseremail(String useremail) {
//        adopt_shelter_adapter.useremail = useremail;
//    }

    public static String getUlica() {
        return ulica;
    }

    public static String getPostanski_broj() {
        return postanski_broj;
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

    public static String getKilaza() {
        return kilaza;
    }

    public static String getNaziv_azila() {
        return naziv_azila;
    }

    public static String getGrad_azila() {
        return grad_azila;
    }
}
