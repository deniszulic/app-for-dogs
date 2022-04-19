package com.example.dog_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

//        holder.email_admin.setText(listItem.getEmail());
//        holder.status_admin.setText(listItem.getStatus());
//        holder.datum_admin.setText(listItem.getDatum());
        holder.naslov.setText(listItem.getIme());
        holder.imepsa.setText(listItem.getIme_psa());
        holder.postavljeno.setText(String.valueOf(listItem.getPostavljeno()));
//        holder.id.setText(listItem.getId());
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast
////                        .makeText(context, listItem.getId(), Toast.LENGTH_LONG)
////                        .show();
////
////                Intent i=new Intent(context, detalji.class);
////                i.putExtra("kljuc",listItem.getId());
////                i.putExtra("adresa",listItem.getAdresa());
////                i.putExtra("auto",listItem.getAuto());
////                i.putExtra("brtel",listItem.getBrtel());
////                i.putExtra("brtel2",listItem.getBrtel2());
////                i.putExtra("datum",listItem.getDatum());
////                i.putExtra("email",listItem.getEmail());
////                i.putExtra("grad",listItem.getGrad());
////                i.putExtra("ime",listItem.getIme());
////                i.putExtra("number",listItem.getNumber());
////                i.putExtra("posted_at",listItem.getPosted_at());
////                i.putExtra("prezime",listItem.getPrezime());
////                i.putExtra("radiona",listItem.getRadiona());
////                i.putExtra("status",listItem.getStatus());
////                i.putExtra("tekst",listItem.getTekst());
////                i.putExtra("vrijeme",listItem.getVrijeme());
////                i.putExtra("year",listItem.getYear());
////                i.putExtra("zip",listItem.getZip());
////                //i.putExtra("id",listItem.getId());
////                i.putExtra("url",listItem.getUrl());
////                i.putExtra("prihvacennovitermin",listItem.getPrihvacennovitermin());
////                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView naslov, imepsa, postavljeno;
        public LinearLayout podaci;

        public ViewHolder(View itemView) {
            super(itemView);
            naslov=(TextView) itemView.findViewById(R.id.naslov_nestalipsi_korisnik);
            imepsa=(TextView) itemView.findViewById(R.id.imepsa_nestalipsi_korisnik);
            postavljeno=(TextView) itemView.findViewById(R.id.postavljeno_nestalipsi_korisnik);
            podaci = (LinearLayout) itemView.findViewById(R.id.podaci_nestalipsi_korisnik);
        }
    }
}
