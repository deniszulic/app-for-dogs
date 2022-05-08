package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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

public class reportsonmyadopteddog_dialog extends BottomSheetDialogFragment {
    private TextInputLayout imevl_myreports_adopteddogs, prezimevl_myreports_adopteddogs,
            adresa_myreports_adopteddogs, telefonskibr_myreports_adopteddogs, grad_azila_myreports_adopteddogs,
            postanski_broj_myreports_adopteddogs, boja_myreports_adopteddogs, starost_myreports_adopteddogs,
            dlaka_myreports_adopteddogs, vetlokacija_myreports_adopteddogs, imepsa_myreports_adopteddogs,
            spol_myreports_adopteddogs, pasmina_myreports_adopteddogs,
            ime_myreports_adopteddogs, prezime_myreports_adopteddogs, kontakt_myreports_adopteddogs,
            napomena_myreports_adopteddogs, postavljeno_myreports_adopteddogs, adresa_udomljavanje_myreports_adopteddogs,
            kilaza_myreports_adopteddogs, kastrat_myreports_adopteddogs, opasnost_myreports_adopteddogs,
            grad_myreports_adopteddogs, postnum_myreports_adopteddogs, razlogprijave_myreports_adopteddogs,
            razlog_myreports_adopteddogs;
    private ImageView slika_myreports_adopteddogs;
    private AppCompatButton datumizgubljen_myreports_adopteddogs;
    private Button send_myreports_adopteddogs;
    private RadioButton radioButton, radio_button_1_myrep_adopteddogs, radio_button_2_myrep_adopteddogs, radio_button_3_myrep_adopteddogs;
    private RadioGroup radioGroup_myrep_adopteddogs_;

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
        View view = inflater.inflate(R.layout.reportsonmyadopteddo_dialog, container, false);
        ime_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.ime_myreports_adopteddogs_);
        imevl_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.imevl_myreports_adopteddogs_);
        prezimevl_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.prezimevl_myreports_adopteddogs_);
        telefonskibr_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.telefonskibr_myreports_adopteddogs_);
        grad_azila_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.grad_azila_myreports_adopteddogs_);
        postanski_broj_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postanski_broj_myreports_adopteddogs_);
//        napomena_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.napomena_myreports_adopteddogs);
        prezime_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.prezime_myreports_adopteddogs_);
        adresa_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.adresa_myreports_adopteddogs_);
        kontakt_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.kontakt_myreports_adopteddogs_);
        imepsa_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.imepsa_myreports_adopteddogs_);
        pasmina_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.pasmina_myreports_adopteddogs_);
        starost_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.starost_myreports_adopteddogs_);
        vetlokacija_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.vetlokacija_myreports_adopteddogs_);
        boja_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.boja_myreports_adopteddogs_);
        dlaka_myreports_adopteddogs= (TextInputLayout) view.findViewById(R.id.dlaka_myreports_adopteddogs_);
        spol_myreports_adopteddogs= (TextInputLayout) view.findViewById(R.id.spol_myreports_adopteddogs_);
        napomena_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.napomena_myreports_adopteddogs_);
        postavljeno_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postavljeno_myreports_adopteddogs_);
        slika_myreports_adopteddogs=(ImageView) view.findViewById(R.id.slika_myreports_adopteddogs_);
        send_myreports_adopteddogs=(Button) view.findViewById(R.id.send_myreports_adopteddogs_);
        adresa_udomljavanje_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.adresa_udomljavanje_myreports_adopteddogs_);
        kilaza_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.kilaza_myreports_adopteddogs_);
        kastrat_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.kastrat_myreports_adopteddogs_);
        opasnost_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.opasnost_myreports_adopteddogs_);
        grad_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.grad_myreports_adopteddogs_);
        postnum_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postnum_myreports_adopteddogs_);
        razlogprijave_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.razlogprijave_myreports_adopteddogs_);
        radio_button_1_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_1_myrep_adopteddogs_);
        radio_button_2_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_2_myrep_adopteddogs_);
        radio_button_3_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_3_myrep_adopteddogs_);
        razlog_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.razlog_myreports_adopteddogs_);
        radioGroup_myrep_adopteddogs_=(RadioGroup) view.findViewById(R.id.radioGroup_myrep_adopteddogs_);

        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
//        send_myreports_adopteddogs = view.findViewById(R.id.send_myreports_adopteddogs);

        imevl_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getIme_udomljavanje());
        prezimevl_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getPrezime_udomljavanje());
        adresa_udomljavanje_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getAdresa_udomljavanje());
        telefonskibr_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getTelefonskibr());
        grad_azila_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getGrad());
        postanski_broj_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getPostanski_broj_udomljavanje());
//        napomena_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getNapomena());
        dlaka_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getDlaka());
        spol_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getSpol());
        kilaza_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getKilaza());


        ime_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getIme());
        prezime_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getPrezime());
        adresa_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getAdresa());
        kontakt_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getKontakt());
        grad_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getGrad_udomljavanje());
        postnum_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getPostnum());
        imepsa_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getImepsa());
        pasmina_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getPasmina());
        starost_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getStarost());
        vetlokacija_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getVetlokacija());
        boja_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getBoja());
        kastrat_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getKastrat());
        opasnost_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getOpasnost());
        razlogprijave_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getRazlog_prijave());
        razlog_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getNapomena());


        napomena_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter.getNapomena_udomljavanje());
        Date date = new Date(reportsonmy_adopteddogs_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_myreports_adopteddogs.getEditText().setText(format.format(date));
        if(reportsonmy_adopteddogs_adapter.getUrl()!=null) {
            slika_myreports_adopteddogs.setVisibility(View.VISIBLE);
            Picasso.get().load(reportsonmy_adopteddogs_adapter.getUrl()).into(slika_myreports_adopteddogs);
        }
        else{
            slika_myreports_adopteddogs.setVisibility(View.GONE);
        }
        if(reportsonmy_adopteddogs_adapter.getPrihvaceno().equals("True")){
            radio_button_1_myrep_adopteddogs.setChecked(true);
        }
        else if(reportsonmy_adopteddogs_adapter.getPrihvaceno().equals("False")){
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

                //Obrada, True, False
                retrofitInterface = retrofit.create(RetrofitInterface.class);
                int radioid1 = radioGroup_myrep_adopteddogs_.getCheckedRadioButtonId();
                radioButton = radioGroup_myrep_adopteddogs_.findViewById(radioid1);
                if(radioButton.getText().toString().equals("Prihvaćeno")) {
                    updatereportsonmyadopteddog data = new updatereportsonmyadopteddog(
                            razlog_myreports_adopteddogs.getEditText().getText().toString(),
                            "True"
                    );
                    Call<updatereportsonmyadopteddog[]> call=retrofitInterface.reportsupdateonadopt(reportsonmy_adopteddogs_adapter.getid(), data);
                    call.enqueue(new Callback<updatereportsonmyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatereportsonmyadopteddog[]> call, Response<updatereportsonmyadopteddog[]> response) {
                            if(response.code()==200) {
                                Toast.makeText(getActivity(), "Ažurirano", Toast.LENGTH_SHORT).show();
                                reportsonmyadopteddog_data data= new reportsonmyadopteddog_data(
                                        reportsonmy_adopteddogs_adapter.getIme(),
                                        reportsonmy_adopteddogs_adapter.getPrezime(),
                                        reportsonmy_adopteddogs_adapter.getAdresa(),
                                        reportsonmy_adopteddogs_adapter.getGrad(),
                                        reportsonmy_adopteddogs_adapter.getPostnum(),
                                        reportsonmy_adopteddogs_adapter.getKontakt(),
                                        reportsonmy_adopteddogs_adapter.getRazlog_prijave(),
                                        "True",
                                        reportsonmy_adopteddogs_adapter.getPostavljeno(),
                                        razlog_myreports_adopteddogs.getEditText().getText().toString(),
                                        reportsonmy_adopteddogs_adapter.getid(),
                                        reportsonmy_adopteddogs_adapter.getIme_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter.getPrezime_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter.getAdresa_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter.getTelefonskibr(),
                                        reportsonmy_adopteddogs_adapter.getGrad_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter.getPostanski_broj_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter.getBoja(),
                                        reportsonmy_adopteddogs_adapter.getStarost(),
                                        reportsonmy_adopteddogs_adapter.getDlaka(),
                                        reportsonmy_adopteddogs_adapter.getVetlokacija(),
                                        reportsonmy_adopteddogs_adapter.getImepsa(),
                                        reportsonmy_adopteddogs_adapter.getSpol(),
                                        reportsonmy_adopteddogs_adapter.getPasmina(),
                                        reportsonmy_adopteddogs_adapter.getKilaza(),
                                        reportsonmy_adopteddogs_adapter.getKastrat(),
                                        reportsonmy_adopteddogs_adapter.getOpasnost(),
                                        reportsonmy_adopteddogs_adapter.getNapomena_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter.getUrl()
                                );
                                reportsonmy_adopteddogs.setListItems(reportsonmy_adopteddogs_adapter.getChange(), data);
                            }
                        }

                        @Override
                        public void onFailure(Call<updatereportsonmyadopteddog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if(radioButton.getText().toString().equals("Odbijeno")) {
                    updatereportsonmyadopteddog data = new updatereportsonmyadopteddog(
                            razlog_myreports_adopteddogs.getEditText().getText().toString(),
                            "False"
                    );
                    Call<updatereportsonmyadopteddog[]> call=retrofitInterface.reportsupdateonadopt(reportsonmy_adopteddogs_adapter.getid(), data);
                    call.enqueue(new Callback<updatereportsonmyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatereportsonmyadopteddog[]> call, Response<updatereportsonmyadopteddog[]> response) {
                            Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                            reportsonmyadopteddog_data data= new reportsonmyadopteddog_data(
                                    reportsonmy_adopteddogs_adapter.getIme(),
                                    reportsonmy_adopteddogs_adapter.getPrezime(),
                                    reportsonmy_adopteddogs_adapter.getAdresa(),
                                    reportsonmy_adopteddogs_adapter.getGrad(),
                                    reportsonmy_adopteddogs_adapter.getPostnum(),
                                    reportsonmy_adopteddogs_adapter.getKontakt(),
                                    reportsonmy_adopteddogs_adapter.getRazlog_prijave(),
                                    "False",
                                    reportsonmy_adopteddogs_adapter.getPostavljeno(),
                                    razlog_myreports_adopteddogs.getEditText().getText().toString(),
                                    reportsonmy_adopteddogs_adapter.getid(),
                                    reportsonmy_adopteddogs_adapter.getIme_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getPrezime_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getAdresa_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getTelefonskibr(),
                                    reportsonmy_adopteddogs_adapter.getGrad_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getPostanski_broj_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getBoja(),
                                    reportsonmy_adopteddogs_adapter.getStarost(),
                                    reportsonmy_adopteddogs_adapter.getDlaka(),
                                    reportsonmy_adopteddogs_adapter.getVetlokacija(),
                                    reportsonmy_adopteddogs_adapter.getImepsa(),
                                    reportsonmy_adopteddogs_adapter.getSpol(),
                                    reportsonmy_adopteddogs_adapter.getPasmina(),
                                    reportsonmy_adopteddogs_adapter.getKilaza(),
                                    reportsonmy_adopteddogs_adapter.getKastrat(),
                                    reportsonmy_adopteddogs_adapter.getOpasnost(),
                                    reportsonmy_adopteddogs_adapter.getNapomena_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getUrl()
                            );
                            reportsonmy_adopteddogs.setListItems(reportsonmy_adopteddogs_adapter.getChange(), data);
                        }

                        @Override
                        public void onFailure(Call<updatereportsonmyadopteddog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    updatereportsonmyadopteddog data = new updatereportsonmyadopteddog(
                            razlog_myreports_adopteddogs.getEditText().getText().toString(),
                            "Obrada"
                    );
                    Call<updatereportsonmyadopteddog[]> call=retrofitInterface.reportsupdateonadopt(reportsonmy_adopteddogs_adapter.getid(), data);
                    call.enqueue(new Callback<updatereportsonmyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatereportsonmyadopteddog[]> call, Response<updatereportsonmyadopteddog[]> response) {
                            Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                            reportsonmyadopteddog_data data= new reportsonmyadopteddog_data(
                                    reportsonmy_adopteddogs_adapter.getIme(),
                                    reportsonmy_adopteddogs_adapter.getPrezime(),
                                    reportsonmy_adopteddogs_adapter.getAdresa(),
                                    reportsonmy_adopteddogs_adapter.getGrad(),
                                    reportsonmy_adopteddogs_adapter.getPostnum(),
                                    reportsonmy_adopteddogs_adapter.getKontakt(),
                                    reportsonmy_adopteddogs_adapter.getRazlog_prijave(),
                                    "Obrada",
                                    reportsonmy_adopteddogs_adapter.getPostavljeno(),
                                    razlog_myreports_adopteddogs.getEditText().getText().toString(),
                                    reportsonmy_adopteddogs_adapter.getid(),
                                    reportsonmy_adopteddogs_adapter.getIme_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getPrezime_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getAdresa_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getTelefonskibr(),
                                    reportsonmy_adopteddogs_adapter.getGrad_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getPostanski_broj_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getBoja(),
                                    reportsonmy_adopteddogs_adapter.getStarost(),
                                    reportsonmy_adopteddogs_adapter.getDlaka(),
                                    reportsonmy_adopteddogs_adapter.getVetlokacija(),
                                    reportsonmy_adopteddogs_adapter.getImepsa(),
                                    reportsonmy_adopteddogs_adapter.getSpol(),
                                    reportsonmy_adopteddogs_adapter.getPasmina(),
                                    reportsonmy_adopteddogs_adapter.getKilaza(),
                                    reportsonmy_adopteddogs_adapter.getKastrat(),
                                    reportsonmy_adopteddogs_adapter.getOpasnost(),
                                    reportsonmy_adopteddogs_adapter.getNapomena_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter.getUrl()
                            );
                            reportsonmy_adopteddogs.setListItems(reportsonmy_adopteddogs_adapter.getChange(), data);
                        }

                        @Override
                        public void onFailure(Call<updatereportsonmyadopteddog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
}
