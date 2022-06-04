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

public class reportsonmyadopteddog_dialog_shelter extends BottomSheetDialogFragment {
    private TextInputLayout telefonskibr_myreports_adopteddogs, boja_myreports_adopteddogs, starost_myreports_adopteddogs,
            dlaka_myreports_adopteddogs, imepsa_myreports_adopteddogs,
            spol_myreports_adopteddogs, pasmina_myreports_adopteddogs,
            ime_myreports_adopteddogs, prezime_myreports_adopteddogs, kontakt_myreports_adopteddogs,
            napomena_myreports_adopteddogs, postavljeno_myreports_adopteddogs,
            kilaza_myreports_adopteddogs, kastrat_myreports_adopteddogs, opasnost_myreports_adopteddogs,
            postnum_myreports_adopteddogs, razlogprijave_myreports_adopteddogs,
            razlog_myreports_adopteddogs, adresa_myreports_adopteddogs_, grad_azila_myreports_adopteddogs_;
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
        View view = inflater.inflate(R.layout.reportsonmyadopteddog_dialog_shelter, container, false);
        ime_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.ime_myreports_adopteddogs_);
        telefonskibr_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.telefonskibr_myreports_adopteddogs_);
        prezime_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.prezime_myreports_adopteddogs_);
        kontakt_myreports_adopteddogs = (TextInputLayout) view.findViewById(R.id.kontakt_myreports_adopteddogs_);
        imepsa_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.imepsa_myreports_adopteddogs_);
        pasmina_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.pasmina_myreports_adopteddogs_);
        starost_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.starost_myreports_adopteddogs_);
        boja_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.boja_myreports_adopteddogs_);
        dlaka_myreports_adopteddogs= (TextInputLayout) view.findViewById(R.id.dlaka_myreports_adopteddogs_);
        spol_myreports_adopteddogs= (TextInputLayout) view.findViewById(R.id.spol_myreports_adopteddogs_);
        napomena_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.napomena_myreports_adopteddogs_);
        postavljeno_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postavljeno_myreports_adopteddogs_);
        slika_myreports_adopteddogs=(ImageView) view.findViewById(R.id.slika_myreports_adopteddogs_);
        send_myreports_adopteddogs=(Button) view.findViewById(R.id.send_myreports_adopteddogs_shelter);
        adresa_myreports_adopteddogs_=(TextInputLayout) view.findViewById(R.id.adresa_myreports_adopteddogs_);
        grad_azila_myreports_adopteddogs_ = (TextInputLayout) view.findViewById(R.id.grad_azila_myreports_adopteddogs_);
        kilaza_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.kilaza_myreports_adopteddogs_);
        kastrat_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.kastrat_myreports_adopteddogs_);
        opasnost_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.opasnost_myreports_adopteddogs_);
        postnum_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.postnum_myreports_adopteddogs_);
        razlogprijave_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.razlogprijave_myreports_adopteddogs_);
        radio_button_1_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_1_myrep_adopteddogs_shelter);
        radio_button_2_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_2_myrep_adopteddogs_shelter);
        radio_button_3_myrep_adopteddogs=(RadioButton) view.findViewById(R.id.radio_button_3_myrep_adopteddogs_shelter);
        razlog_myreports_adopteddogs=(TextInputLayout) view.findViewById(R.id.razlog_myreports_adopteddogs_shelter);
        radioGroup_myrep_adopteddogs_=(RadioGroup) view.findViewById(R.id.radioGroup_myrep_adopteddogs_shelter);

        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        telefonskibr_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getTelefonskibr());
        dlaka_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getDlaka());
        spol_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getSpol());
        kilaza_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getKilaza());

        grad_azila_myreports_adopteddogs_.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getGrad());
        adresa_myreports_adopteddogs_.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getAdresa());



        ime_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getIme());
        prezime_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getPrezime());
        kontakt_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getKontakt());
        postnum_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getPostnum());
        imepsa_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getImepsa());
        pasmina_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getPasmina());
        starost_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getStarost());
        boja_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getBoja());
        kastrat_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getKastrat());
        opasnost_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getOpasnost());
        razlogprijave_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getRazlog_prijave());
        razlog_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getNapomena());


        napomena_myreports_adopteddogs.getEditText().setText(reportsonmy_adopteddogs_adapter_shelter.getNapomena_udomljavanje());
        Date date = new Date(reportsonmy_adopteddogs_adapter_shelter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_myreports_adopteddogs.getEditText().setText(format.format(date));
        if(reportsonmy_adopteddogs_adapter_shelter.getUrl()!=null) {
            slika_myreports_adopteddogs.setVisibility(View.VISIBLE);
            Picasso.get().load(reportsonmy_adopteddogs_adapter_shelter.getUrl()).into(slika_myreports_adopteddogs);
        }
        else{
            slika_myreports_adopteddogs.setVisibility(View.GONE);
        }
        if(reportsonmy_adopteddogs_adapter_shelter.getPrihvaceno().equals("True")){
            radio_button_1_myrep_adopteddogs.setChecked(true);
        }
        else if(reportsonmy_adopteddogs_adapter_shelter.getPrihvaceno().equals("False")){
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
                int radioid1 = radioGroup_myrep_adopteddogs_.getCheckedRadioButtonId();
                radioButton = radioGroup_myrep_adopteddogs_.findViewById(radioid1);
                if(radioButton.getText().toString().equals("Prihvaćeno")) {
                    updatereportsonmyadopteddog data = new updatereportsonmyadopteddog(
                            razlog_myreports_adopteddogs.getEditText().getText().toString(),
                            "True"
                    );
                    Call<updatereportsonmyadopteddog[]> call=retrofitInterface.reportsupdateonadopt(reportsonmy_adopteddogs_adapter_shelter.getid(), data);
                    call.enqueue(new Callback<updatereportsonmyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatereportsonmyadopteddog[]> call, Response<updatereportsonmyadopteddog[]> response) {
                            if(response.code()==200) {
                                Toast.makeText(getActivity(), "Ažurirano", Toast.LENGTH_SHORT).show();
                                reportsonmyadopteddog_data data= new reportsonmyadopteddog_data(
                                        reportsonmy_adopteddogs_adapter_shelter.getIme(),
                                        reportsonmy_adopteddogs_adapter_shelter.getPrezime(),
                                        reportsonmy_adopteddogs_adapter_shelter.getAdresa(),
                                        reportsonmy_adopteddogs_adapter_shelter.getGrad(),
                                        reportsonmy_adopteddogs_adapter_shelter.getPostnum(),
                                        reportsonmy_adopteddogs_adapter_shelter.getKontakt(),
                                        reportsonmy_adopteddogs_adapter_shelter.getRazlog_prijave(),
                                        "True",
                                        reportsonmy_adopteddogs_adapter_shelter.getPostavljeno(),
                                        razlog_myreports_adopteddogs.getEditText().getText().toString(),
                                        reportsonmy_adopteddogs_adapter_shelter.getid(),
                                        reportsonmy_adopteddogs_adapter_shelter.getIme_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter_shelter.getPrezime_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter_shelter.getAdresa_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter_shelter.getTelefonskibr(),
                                        reportsonmy_adopteddogs_adapter_shelter.getGrad_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter_shelter.getPostanski_broj_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter_shelter.getBoja(),
                                        reportsonmy_adopteddogs_adapter_shelter.getStarost(),
                                        reportsonmy_adopteddogs_adapter_shelter.getDlaka(),
                                        reportsonmy_adopteddogs_adapter_shelter.getVetlokacija(),
                                        reportsonmy_adopteddogs_adapter_shelter.getImepsa(),
                                        reportsonmy_adopteddogs_adapter_shelter.getSpol(),
                                        reportsonmy_adopteddogs_adapter_shelter.getPasmina(),
                                        reportsonmy_adopteddogs_adapter_shelter.getKilaza(),
                                        reportsonmy_adopteddogs_adapter_shelter.getKastrat(),
                                        reportsonmy_adopteddogs_adapter_shelter.getOpasnost(),
                                        reportsonmy_adopteddogs_adapter_shelter.getNapomena_udomljavanje(),
                                        reportsonmy_adopteddogs_adapter_shelter.getUrl()
                                );
                                reportsonmy_adopteddogs_shelter.setListItems(reportsonmy_adopteddogs_adapter_shelter.getChange(), data);
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
                    Call<updatereportsonmyadopteddog[]> call=retrofitInterface.reportsupdateonadopt(reportsonmy_adopteddogs_adapter_shelter.getid(), data);
                    call.enqueue(new Callback<updatereportsonmyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatereportsonmyadopteddog[]> call, Response<updatereportsonmyadopteddog[]> response) {
                            Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                            reportsonmyadopteddog_data data= new reportsonmyadopteddog_data(
                                    reportsonmy_adopteddogs_adapter_shelter.getIme(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPrezime(),
                                    reportsonmy_adopteddogs_adapter_shelter.getAdresa(),
                                    reportsonmy_adopteddogs_adapter_shelter.getGrad(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPostnum(),
                                    reportsonmy_adopteddogs_adapter_shelter.getKontakt(),
                                    reportsonmy_adopteddogs_adapter_shelter.getRazlog_prijave(),
                                    "False",
                                    reportsonmy_adopteddogs_adapter_shelter.getPostavljeno(),
                                    razlog_myreports_adopteddogs.getEditText().getText().toString(),
                                    reportsonmy_adopteddogs_adapter_shelter.getid(),
                                    reportsonmy_adopteddogs_adapter_shelter.getIme_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPrezime_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getAdresa_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getTelefonskibr(),
                                    reportsonmy_adopteddogs_adapter_shelter.getGrad_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPostanski_broj_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getBoja(),
                                    reportsonmy_adopteddogs_adapter_shelter.getStarost(),
                                    reportsonmy_adopteddogs_adapter_shelter.getDlaka(),
                                    reportsonmy_adopteddogs_adapter_shelter.getVetlokacija(),
                                    reportsonmy_adopteddogs_adapter_shelter.getImepsa(),
                                    reportsonmy_adopteddogs_adapter_shelter.getSpol(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPasmina(),
                                    reportsonmy_adopteddogs_adapter_shelter.getKilaza(),
                                    reportsonmy_adopteddogs_adapter_shelter.getKastrat(),
                                    reportsonmy_adopteddogs_adapter_shelter.getOpasnost(),
                                    reportsonmy_adopteddogs_adapter_shelter.getNapomena_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getUrl()
                            );
                            reportsonmy_adopteddogs_shelter.setListItems(reportsonmy_adopteddogs_adapter_shelter.getChange(), data);
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
                    Call<updatereportsonmyadopteddog[]> call=retrofitInterface.reportsupdateonadopt(reportsonmy_adopteddogs_adapter_shelter.getid(), data);
                    call.enqueue(new Callback<updatereportsonmyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatereportsonmyadopteddog[]> call, Response<updatereportsonmyadopteddog[]> response) {
                            Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                            reportsonmyadopteddog_data data= new reportsonmyadopteddog_data(
                                    reportsonmy_adopteddogs_adapter_shelter.getIme(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPrezime(),
                                    reportsonmy_adopteddogs_adapter_shelter.getAdresa(),
                                    reportsonmy_adopteddogs_adapter_shelter.getGrad(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPostnum(),
                                    reportsonmy_adopteddogs_adapter_shelter.getKontakt(),
                                    reportsonmy_adopteddogs_adapter_shelter.getRazlog_prijave(),
                                    "Obrada",
                                    reportsonmy_adopteddogs_adapter_shelter.getPostavljeno(),
                                    razlog_myreports_adopteddogs.getEditText().getText().toString(),
                                    reportsonmy_adopteddogs_adapter_shelter.getid(),
                                    reportsonmy_adopteddogs_adapter_shelter.getIme_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPrezime_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getAdresa_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getTelefonskibr(),
                                    reportsonmy_adopteddogs_adapter_shelter.getGrad_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPostanski_broj_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getBoja(),
                                    reportsonmy_adopteddogs_adapter_shelter.getStarost(),
                                    reportsonmy_adopteddogs_adapter_shelter.getDlaka(),
                                    reportsonmy_adopteddogs_adapter_shelter.getVetlokacija(),
                                    reportsonmy_adopteddogs_adapter_shelter.getImepsa(),
                                    reportsonmy_adopteddogs_adapter_shelter.getSpol(),
                                    reportsonmy_adopteddogs_adapter_shelter.getPasmina(),
                                    reportsonmy_adopteddogs_adapter_shelter.getKilaza(),
                                    reportsonmy_adopteddogs_adapter_shelter.getKastrat(),
                                    reportsonmy_adopteddogs_adapter_shelter.getOpasnost(),
                                    reportsonmy_adopteddogs_adapter_shelter.getNapomena_udomljavanje(),
                                    reportsonmy_adopteddogs_adapter_shelter.getUrl()
                            );
                            reportsonmy_adopteddogs_shelter.setListItems(reportsonmy_adopteddogs_adapter_shelter.getChange(), data);
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
