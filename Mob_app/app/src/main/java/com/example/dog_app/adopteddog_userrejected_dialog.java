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

public class adopteddog_userrejected_dialog extends BottomSheetDialogFragment {
    private TextInputLayout ime_nestanak_myreports_missingdogs, prezime_nestanak_myreports_missingdogs,
            adresa_myreports_missingdogs, telefonskibr_myreports_missingdogs, grad_azila_myreports_missingdogs,
            postanski_broj_myreports_missingdogs, boja_myreports_missingdogs, starost_myreports_missingdogs,
            dlaka_myreports_missingdogs, vetlokacija_myreports_missingdogs, imepsa_myreports_missingdogs,
            spol_myreports_missingdogs, pasmina_myreports_missingdogs,
            napomena_userreq_missingdog, dostavljeno_nestanak_myreports_missingdogs_, azilnapomena_userreq_adopteddog;
    private ImageView slika_myreports_missingdogs;
    private RadioGroup radioGroup_userreq_missingdog;
    private RadioButton radio_button_1_userreq_missingdog, radio_button_2_userreq_missingdog, radioButton;
    private Button spremi_userreq_missingdog;

    private Date datum;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private boolean isAllFieldsChecked = false;
    private static final long HOUR = 3600*1000;
    private List<myreportsdisapp_data> listItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adopteddog_userreq_dialog, container, false);
        radioGroup_userreq_missingdog=(RadioGroup) view.findViewById(R.id.radioGroup_userreq_adopteddog);
        radio_button_1_userreq_missingdog=(RadioButton) view.findViewById(R.id.radio_button_1_userreq_adopteddog);
        radio_button_2_userreq_missingdog=(RadioButton) view.findViewById(R.id.radio_button_2_userreq_adopteddog);
        napomena_userreq_missingdog=(TextInputLayout) view.findViewById(R.id.napomena_userreq_adopteddogs);
        spremi_userreq_missingdog=(Button) view.findViewById(R.id.spremi_userreq_adopteddog);
        ime_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.ime_userreq_adopteddogs);
        prezime_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.prezime_userreq_adopteddogs);
        telefonskibr_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.telefonskibr_myreports_adopteddogs_);
        grad_azila_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.grad_azila_myreports_adopteddogs_);
        postanski_broj_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.postanski_broj_myreports_adopteddogs_);
        adresa_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.adresa_myreports_adopteddogs_);
        imepsa_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.imepsa_myreports_adopteddogs_);
        pasmina_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.pasmina_myreports_adopteddogs_);
        starost_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.starost_myreports_adopteddogs_);
        vetlokacija_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.vetlokacija_myreports_adopteddogs_);
        boja_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.boja_myreports_adopteddogs_);
        dlaka_myreports_missingdogs= (TextInputLayout) view.findViewById(R.id.dlaka_myreports_adopteddogs_);
        spol_myreports_missingdogs= (TextInputLayout) view.findViewById(R.id.spol_myreports_adopteddogs_);
        slika_myreports_missingdogs=(ImageView) view.findViewById(R.id.slika_myreports_adopteddogs_);
        dostavljeno_nestanak_myreports_missingdogs_=(TextInputLayout) view.findViewById(R.id.dostavljeno_userreq_adopteddogs);
        azilnapomena_userreq_adopteddog=(TextInputLayout) view.findViewById(R.id.azilnapomena_userreq_adopteddog);

        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);

        if(user_req_adoptdog_rejected_adapter.getPrihvaceno().equals("true")){
            radio_button_1_userreq_missingdog.setChecked(true);
        }
        if(user_req_adoptdog_rejected_adapter.getPrihvaceno().equals("false")){
            radio_button_2_userreq_missingdog.setChecked(true);
        }

        ime_nestanak_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getIme());
        prezime_nestanak_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getPrezime());
        telefonskibr_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getTelefonskibr());
        grad_azila_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getGrad());
        postanski_broj_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getPostanski_broj());
        azilnapomena_userreq_adopteddog.getEditText().setText(user_req_adoptdog_rejected_adapter.getAzil_udomljavanje_napomena());
        dlaka_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getDlaka());
        spol_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getSpol());


        adresa_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getAdresa());
        imepsa_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getIme_psa());
        pasmina_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getPasmina());
        starost_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getStarost());
        vetlokacija_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getVet_lokacija());
        boja_myreports_missingdogs.getEditText().setText(user_req_adoptdog_rejected_adapter.getBoja());

        napomena_userreq_missingdog.getEditText().setText(user_req_adoptdog_rejected_adapter.getNapomena());

        Date date = new Date(user_req_adoptdog_rejected_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        dostavljeno_nestanak_myreports_missingdogs_.getEditText().setText(format.format(date));

        if(user_req_adoptdog_rejected_adapter.getUrl_slike()!=null) {
            slika_myreports_missingdogs.setVisibility(View.VISIBLE);
            Picasso.get().load(user_req_adoptdog_rejected_adapter.getUrl_slike()).into(slika_myreports_missingdogs);
        }
        else{
            slika_myreports_missingdogs.setVisibility(View.GONE);
        }

        spremi_userreq_missingdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                int radioid1 = radioGroup_userreq_missingdog.getCheckedRadioButtonId();
                radioButton = radioGroup_userreq_missingdog.findViewById(radioid1);
                try {
                    if (radioButton.getText().toString().equals("Prihvati")) {
                        updateuserreq_missingdog data= new updateuserreq_missingdog("true", azilnapomena_userreq_adopteddog.getEditText().getText().toString());
                        Call<updateuserreq_missingdog[]> call=retrofitInterface.updatereportadopteddog(user_req_adoptdog_rejected_adapter.getid(), data);
                        call.enqueue(new Callback<updateuserreq_missingdog[]>() {
                            @Override
                            public void onResponse(Call<updateuserreq_missingdog[]> call, Response<updateuserreq_missingdog[]> response) {
                                if(response.code()==200){
                                    try{
                                        userreq_adoptdog_reject.setListItems(user_req_adoptdog_rejected_adapter.getChange());
                                        Toast.makeText(getActivity(),"Zahtjev ažuriran!",Toast.LENGTH_SHORT).show();
                                    }catch(Exception e){System.out.println(e);}
                                }
                            }

                            @Override
                            public void onFailure(Call<updateuserreq_missingdog[]> call, Throwable t) {
                                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (radioButton.getText().toString().equals("Odbij")) {
                        updateuserreq_missingdog data= new updateuserreq_missingdog("false", azilnapomena_userreq_adopteddog.getEditText().getText().toString());
                        Call<updateuserreq_missingdog[]> call=retrofitInterface.updatereportadopteddog(user_req_adoptdog_rejected_adapter.getid(), data);
                        call.enqueue(new Callback<updateuserreq_missingdog[]>() {
                            @Override
                            public void onResponse(Call<updateuserreq_missingdog[]> call, Response<updateuserreq_missingdog[]> response) {
                                if(response.code()==200){
                                    try{
                                        Toast.makeText(getActivity(),"Zahtjev ažuriran!",Toast.LENGTH_SHORT).show();
                                        userreq_adoptdog_data data = new userreq_adoptdog_data(
                                                user_req_adoptdog_rejected_adapter.getIme(),
                                                user_req_adoptdog_rejected_adapter.getPrezime(),
                                                user_req_adoptdog_rejected_adapter.getAdresa(),
                                                user_req_adoptdog_rejected_adapter.getTelefonskibr(),
                                                user_req_adoptdog_rejected_adapter.getGrad(),
                                                user_req_adoptdog_rejected_adapter.getPostanski_broj(),
                                                user_req_adoptdog_rejected_adapter.getBoja(),
                                                user_req_adoptdog_rejected_adapter.getStarost(),
                                                user_req_adoptdog_rejected_adapter.getDlaka(),
                                                user_req_adoptdog_rejected_adapter.getVet_lokacija(),
                                                user_req_adoptdog_rejected_adapter.getIme_psa(),
                                                user_req_adoptdog_rejected_adapter.getSpol(),
                                                user_req_adoptdog_rejected_adapter.getPasmina(),
                                                user_req_adoptdog_rejected_adapter.getKilaza(),
                                                user_req_adoptdog_rejected_adapter.getKastrat(),
                                                user_req_adoptdog_rejected_adapter.getOpasnost(),
                                                user_req_adoptdog_rejected_adapter.getNapomena(),
                                                user_req_adoptdog_rejected_adapter.getUrl_slike(),
                                                azilnapomena_userreq_adopteddog.getEditText().getText().toString(),
                                                "false", user_req_adoptdog_rejected_adapter.getPostavljeno(),
                                                user_req_adoptdog_rejected_adapter.getid(),
                                                user_req_adoptdog_rejected_adapter.getUdomljavanje_id());
                                        userreq_adoptdog_reject.setListItemschange(user_req_adoptdog_rejected_adapter.getChange(), data);
                                    }catch(Exception e){System.out.println(e);}
                                }
                            }

                            @Override
                            public void onFailure(Call<updateuserreq_missingdog[]> call, Throwable t) {
                                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }catch(Exception e){System.out.println(e);}
            }
        });
        return view;
    }
}
