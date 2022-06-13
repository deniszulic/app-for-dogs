package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myreports_adopteddogs_dialog extends BottomSheetDialogFragment {
    private TextInputLayout imevl_myreports_adopteddogs, prezimevl_myreports_adopteddogs,
            adresa_myreports_adopteddogs, telefonskibr_myreports_adopteddogs, grad_azila_myreports_adopteddogs,
            postanski_broj_myreports_adopteddogs, boja_myreports_adopteddogs, starost_myreports_adopteddogs,
            dlaka_myreports_adopteddogs, vetlokacija_myreports_adopteddogs, imepsa_myreports_adopteddogs,
            spol_myreports_adopteddogs, pasmina_myreports_adopteddogs,
            ime_myreports_adopteddogs, prezime_myreports_adopteddogs, kontakt_myreports_adopteddogs,
            napomena_myreports_adopteddogs, postavljeno_myreports_adopteddogs, adresa_udomljavanje_myreports_adopteddogs,
    kilaza_myreports_adopteddogs, kastrat_myreports_adopteddogs, opasnost_myreports_adopteddogs,
    grad_myreports_adopteddogs, postnum_myreports_adopteddogs, razlogprijave_myreports_adopteddogs,
            razlog_myreports_adopteddogs, nazivazila_udomljavanje_myreports_adopteddogs,
            gradazila_udomljavanje_myreports_adopteddogs;
    private ImageView slika_myreports_adopteddogs;
    private AppCompatButton datumizgubljen_myreports_adopteddogs;
    private Button send_myreports_adopteddogs;
    private RadioButton radio_button_1_myrep_adopteddogs, radio_button_2_myrep_adopteddogs, radio_button_3_myrep_adopteddogs;

    private Date datum;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private boolean isAllFieldsChecked = false;
    private static final long HOUR = 3600*1000;
    private List<myreportsadopteddogs_data> listItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.changemydata_myreports_adopteddogs, container, false);
        ime_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.ime_myreports_adopteddogs);
        imevl_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.imevl_myreports_adopteddogs);
        prezimevl_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.prezimevl_myreports_adopteddogs);
        telefonskibr_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.telefonskibr_myreports_adopteddogs);
        grad_azila_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.grad_azila_myreports_adopteddogs);
        postanski_broj_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postanski_broj_myreports_adopteddogs);
        prezime_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.prezime_myreports_adopteddogs);
        adresa_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.adresa_myreports_adopteddogs);
        kontakt_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.kontakt_myreports_adopteddogs);
        imepsa_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.imepsa_myreports_adopteddogs);
        pasmina_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.pasmina_myreports_adopteddogs);
        starost_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.starost_myreports_adopteddogs);
        vetlokacija_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.vetlokacija_myreports_adopteddogs);
        boja_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.boja_myreports_adopteddogs);
        dlaka_myreports_adopteddogs= (TextInputLayout) view.findViewById(R.id.dlaka_myreports_adopteddogs);
        spol_myreports_adopteddogs= (TextInputLayout) view.findViewById(R.id.spol_myreports_adopteddogs);
        napomena_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.napomena_myreports_adopteddogs);
        postavljeno_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postavljeno_myreports_adopteddogs);
        slika_myreports_adopteddogs=(ImageView) view.findViewById(R.id.slika_myreports_adopteddogs);
        send_myreports_adopteddogs=(Button) view.findViewById(R.id.send_myreports_adopteddogs);
        adresa_udomljavanje_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.adresa_udomljavanje_myreports_adopteddogs);
        kilaza_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.kilaza_myreports_adopteddogs);
        kastrat_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.kastrat_myreports_adopteddogs);
        opasnost_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.opasnost_myreports_adopteddogs);
        grad_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.grad_myreports_adopteddogs);
        postnum_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postnum_myreports_adopteddogs);
        razlogprijave_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.razlogprijave_myreports_adopteddogs);
        radio_button_1_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_1_myrep_adopteddogs);
        radio_button_2_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_2_myrep_adopteddogs);
        radio_button_3_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_3_myrep_adopteddogs);
        razlog_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.razlog_myreports_adopteddogs);
        gradazila_udomljavanje_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.gradazila_udomljavanje_myreports_adopteddogs);
        nazivazila_udomljavanje_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.nazivazila_udomljavanje_myreports_adopteddogs);

        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        if(!myreports_adopteddogs_adapter.isOglas_azila()){
            imevl_myreports_adopteddogs.setVisibility(View.VISIBLE);
            prezimevl_myreports_adopteddogs.setVisibility(View.VISIBLE);
            adresa_udomljavanje_myreports_adopteddogs.setVisibility(View.VISIBLE);
            telefonskibr_myreports_adopteddogs.setVisibility(View.VISIBLE);
            grad_azila_myreports_adopteddogs.setVisibility(View.VISIBLE);
            postanski_broj_myreports_adopteddogs.setVisibility(View.VISIBLE);
            dlaka_myreports_adopteddogs.setVisibility(View.VISIBLE);
            spol_myreports_adopteddogs.setVisibility(View.VISIBLE);
            kilaza_myreports_adopteddogs.setVisibility(View.VISIBLE);
            ime_myreports_adopteddogs.setVisibility(View.VISIBLE);
            prezime_myreports_adopteddogs.setVisibility(View.VISIBLE);
            adresa_myreports_adopteddogs.setVisibility(View.VISIBLE);
            kontakt_myreports_adopteddogs.setVisibility(View.VISIBLE);
            grad_myreports_adopteddogs.setVisibility(View.VISIBLE);
            postnum_myreports_adopteddogs.setVisibility(View.VISIBLE);
            imepsa_myreports_adopteddogs.setVisibility(View.VISIBLE);
            pasmina_myreports_adopteddogs.setVisibility(View.VISIBLE);
            starost_myreports_adopteddogs.setVisibility(View.VISIBLE);
            vetlokacija_myreports_adopteddogs.setVisibility(View.VISIBLE);
            boja_myreports_adopteddogs.setVisibility(View.VISIBLE);
            kastrat_myreports_adopteddogs.setVisibility(View.VISIBLE);
            opasnost_myreports_adopteddogs.setVisibility(View.VISIBLE);
            razlogprijave_myreports_adopteddogs.setVisibility(View.VISIBLE);
            razlog_myreports_adopteddogs.setVisibility(View.VISIBLE);
            nazivazila_udomljavanje_myreports_adopteddogs.setVisibility(View.GONE);
            gradazila_udomljavanje_myreports_adopteddogs.setVisibility(View.GONE);
        }
        else {
            imevl_myreports_adopteddogs.setVisibility(View.GONE);
            prezimevl_myreports_adopteddogs.setVisibility(View.GONE);
            adresa_udomljavanje_myreports_adopteddogs.setVisibility(View.GONE);
            grad_myreports_adopteddogs.setVisibility(View.GONE);
            postanski_broj_myreports_adopteddogs.setVisibility(View.GONE);
            vetlokacija_myreports_adopteddogs.setVisibility(View.GONE);
            nazivazila_udomljavanje_myreports_adopteddogs.setVisibility(View.VISIBLE);
            gradazila_udomljavanje_myreports_adopteddogs.setVisibility(View.VISIBLE);
        }

        nazivazila_udomljavanje_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getNaziv_azila());
        gradazila_udomljavanje_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getGrad_azila());
        imevl_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getIme_udomljavanje());
        prezimevl_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getPrezime_udomljavanje());
        adresa_udomljavanje_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getAdresa_udomljavanje());
        telefonskibr_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getTelefonskibr());
        grad_azila_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getGrad());
        postanski_broj_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getPostanski_broj_udomljavanje());
        dlaka_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getDlaka());
        spol_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getSpol());
        kilaza_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getKilaza());


        ime_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getIme());
        prezime_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getPrezime());
        adresa_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getAdresa());
        kontakt_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getKontakt());
        grad_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getGrad_udomljavanje());
        postnum_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getPostnum());
        imepsa_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getImepsa());
        pasmina_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getPasmina());
        starost_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getStarost());
        vetlokacija_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getVetlokacija());
        boja_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getBoja());
        kastrat_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getKastrat());
        opasnost_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getOpasnost());
        razlogprijave_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getRazlog_prijave());
        razlog_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getNapomena());


        napomena_myreports_adopteddogs.getEditText().setText(myreports_adopteddogs_adapter.getNapomena_udomljavanje());
        Date date = new Date(myreports_adopteddogs_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_myreports_adopteddogs.getEditText().setText(format.format(date));
        if(myreports_adopteddogs_adapter.getUrl()!=null) {
            slika_myreports_adopteddogs.setVisibility(View.VISIBLE);
            Picasso.get().load(myreports_adopteddogs_adapter.getUrl()).into(slika_myreports_adopteddogs);
        }
        else{
            slika_myreports_adopteddogs.setVisibility(View.GONE);
        }
        if(myreports_adopteddogs_adapter.getPrihvaceno().equals("True")){
            radio_button_1_myrep_adopteddogs.setChecked(true);
        }
        else if(myreports_adopteddogs_adapter.getPrihvaceno().equals("False")){
            radio_button_2_myrep_adopteddogs.setChecked(true);
        }
        else {
            radio_button_3_myrep_adopteddogs.setChecked(true);
        }

        send_myreports_adopteddogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                updatemyreports_adopteddogs data=new updatemyreports_adopteddogs(
                        ime_myreports_adopteddogs.getEditText().getText().toString(),
                        prezime_myreports_adopteddogs.getEditText().getText().toString(),
                        kontakt_myreports_adopteddogs.getEditText().getText().toString(),
                        adresa_myreports_adopteddogs.getEditText().getText().toString(),
                        grad_azila_myreports_adopteddogs.getEditText().getText().toString(),
                        postnum_myreports_adopteddogs.getEditText().getText().toString(),
                        razlogprijave_myreports_adopteddogs.getEditText().getText().toString()
                );

                Call<updatemyreports_adopteddogs[]> call=retrofitInterface.updatemyreports_adopteddogs(myreports_adopteddogs_adapter.getid(), data);
                call.enqueue(new Callback<updatemyreports_adopteddogs[]>() {
                    @Override
                    public void onResponse(Call<updatemyreports_adopteddogs[]> call, Response<updatemyreports_adopteddogs[]> response) {
                        if(response.code()==200){
                            try{
                                Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                                myreportsadopteddogs_data data = new myreportsadopteddogs_data(
                                        ime_myreports_adopteddogs.getEditText().getText().toString(),
                                        prezime_myreports_adopteddogs.getEditText().getText().toString(),
                                        adresa_myreports_adopteddogs.getEditText().getText().toString(),
                                        grad_azila_myreports_adopteddogs.getEditText().getText().toString(),
                                        postnum_myreports_adopteddogs.getEditText().getText().toString(),
                                        kontakt_myreports_adopteddogs.getEditText().getText().toString(),
                                        razlogprijave_myreports_adopteddogs.getEditText().getText().toString(),
                                        myreports_adopteddogs_adapter.getPrihvaceno(),
                                        myreports_adopteddogs_adapter.getPostavljeno(),
                                        myreports_adopteddogs_adapter.getNapomena(),
                                        myreports_adopteddogs_adapter.getid(),
                                        myreports_adopteddogs_adapter.getNaziv_azila(),
                                        myreports_adopteddogs_adapter.getGrad_azila(),
                                        myreports_adopteddogs_adapter.getIme_udomljavanje(),
                                        myreports_adopteddogs_adapter.getPrezime_udomljavanje(),
                                        myreports_adopteddogs_adapter.getAdresa_udomljavanje(),
                                        myreports_adopteddogs_adapter.getTelefonskibr(),
                                        myreports_adopteddogs_adapter.getGrad_udomljavanje(),
                                        myreports_adopteddogs_adapter.getPostanski_broj_udomljavanje(),
                                        myreports_adopteddogs_adapter.getBoja(),
                                        myreports_adopteddogs_adapter.getStarost(),
                                        myreports_adopteddogs_adapter.getDlaka(),
                                        myreports_adopteddogs_adapter.getVetlokacija(),
                                        myreports_adopteddogs_adapter.getImepsa(),
                                        myreports_adopteddogs_adapter.getSpol(),
                                        myreports_adopteddogs_adapter.getPasmina(),
                                        myreports_adopteddogs_adapter.getKilaza(),
                                        myreports_adopteddogs_adapter.getKastrat(),
                                        myreports_adopteddogs_adapter.getOpasnost(),
                                        myreports_adopteddogs_adapter.getNapomena_udomljavanje(),
                                        myreports_adopteddogs_adapter.getUrl(),
                                        myreports_adopteddogs_adapter.isOglas_azila()
                                );
                                myreports_adopteddogs.setListItems(myreports_adopteddogs_adapter.getChange(), data);
                            }catch(Exception e){System.out.println(e);}
                        }
                    }

                    @Override
                    public void onFailure(Call<updatemyreports_adopteddogs[]> call, Throwable t) {
                        Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
}
