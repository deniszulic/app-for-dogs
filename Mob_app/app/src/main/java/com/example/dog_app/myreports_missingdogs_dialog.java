package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class myreports_missingdogs_dialog extends BottomSheetDialogFragment {
//    private TextInputLayout ime_myreports_missingdogs, prezime_myreports_missingdogs, adresa_myreports_missingdogs,
//            kontakt_myreports_missingdogs, grad_myreports_missingdogs, postnum_myreports_missingdogs, imepsa_myreports_missingdogs,
//            pasmina_myreports_missingdogs, starost_myreports_missingdogs, vetlokacija_myreports_missingdogs, boja_myreports_missingdogs,
//            napomena_myreports_missingdogs, postavljeno_myreports_missingdogs;
//    private AutoCompleteTextView dlaka_myreports_missingdogs, spol_myreports_missingdogs;
//    private AppCompatButton datumizgubljen_myreports_missingdogs;
//    private RadioGroup radioGroup;
//    private RadioButton radiobtn1, radiobtn2, radioButton;
//    private Button send_myreports_missingdogs;
    private TextInputLayout ime_nestanak_myreports_missingdogs, prezime_nestanak_myreports_missingdogs,
        adresa_myreports_missingdogs, telefonskibr_myreports_missingdogs, grad_azila_myreports_missingdogs,
        postanski_broj_myreports_missingdogs, boja_myreports_missingdogs, starost_myreports_missingdogs,
        dlaka_myreports_missingdogs, vetlokacija_myreports_missingdogs, imepsa_myreports_missingdogs,
        spol_myreports_missingdogs, pasmina_myreports_missingdogs, napomena_nestanak_myreports_missingdogs,
        ime_myreports_missingdogs, prezime_myreports_missingdogs, kontakt_myreports_missingdogs,
        adresa_za_preuzimanje_psa_myreports_missingdogs, adresa_pronalaska_myreports_missingdogs,
        napomena_myreports_missingdogs, postavljeno_myreports_missingdogs;
    private ImageView slika_myreports_missingdogs;
    private AppCompatButton datumizgubljen_myreports_missingdogs;
    private Button send_myreports_missingdogs;

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
        View view = inflater.inflate(R.layout.changemydata_myreports_missingdogs, container, false);
        ime_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.ime_myreports_missingdogs);
        ime_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.ime_nestanak_myreports_missingdogs);
        prezime_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.prezime_nestanak_myreports_missingdogs);
        telefonskibr_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.telefonskibr_myreports_missingdogs);
        grad_azila_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.grad_azila_myreports_missingdogs);
        postanski_broj_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.postanski_broj_myreports_missingdogs);
        napomena_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.napomena_nestanak_myreports_missingdogs);
        adresa_za_preuzimanje_psa_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.adresa_za_preuzimanje_psa_myreports_missingdogs);
        adresa_pronalaska_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.adresa_pronalaska_myreports_missingdogs);
        prezime_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.prezime_myreports_missingdogs);
        adresa_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.adresa_myreports_missingdogs);
        kontakt_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.kontakt_myreports_missingdogs);
        imepsa_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.imepsa_myreports_missingdogs);
        pasmina_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.pasmina_myreports_missingdogs);
        starost_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.starost_myreports_missingdogs);
        vetlokacija_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.vetlokacija_myreports_missingdogs);
        boja_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.boja_myreports_missingdogs);
        datumizgubljen_myreports_missingdogs=(AppCompatButton) view.findViewById(R.id.datumizgubljen_myreports_missingdogs);
        dlaka_myreports_missingdogs= (TextInputLayout) view.findViewById(R.id.dlaka_myreports_missingdogs);
        spol_myreports_missingdogs= (TextInputLayout) view.findViewById(R.id.spol_myreports_missingdogs);
        napomena_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.napomena_myreports_missingdogs);
        postavljeno_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.postavljeno_myreports_missingdogs);
        slika_myreports_missingdogs=(ImageView) view.findViewById(R.id.slika_myreports_missingdogs);
        send_myreports_missingdogs=(Button) view.findViewById(R.id.send_myreports_missingdogs);

        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
//        send_myreports_missingdogs = view.findViewById(R.id.send_myreports_missingdogs);

        ime_nestanak_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getIme_nestanak());
        prezime_nestanak_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getPrezime_nestanak());
        telefonskibr_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getTelefonskibr());
        grad_azila_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getGrad_azila());
        postanski_broj_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getPostnum());
        napomena_nestanak_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getNapomena_nestanak());
        adresa_za_preuzimanje_psa_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getAdresa_za_preuzimanje_psa());
        adresa_pronalaska_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getAdresa_pronalaska());
        dlaka_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getDlaka());
        spol_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getSpol());


        ime_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getIme());
        prezime_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getPrezime());
        adresa_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getAdresa());
        kontakt_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getKontakt());
//        grad_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getGrad());
//        postnum_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getPostnum());
        imepsa_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getImepsa());
        pasmina_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getPasmina());
        starost_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getStarost());
        vetlokacija_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getVetlokacija());
        boja_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getBoja());

        napomena_myreports_missingdogs.getEditText().setText(myreports_missingdogs_adapter.getNapomena());
        Date date = new Date(myreports_missingdogs_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_myreports_missingdogs.getEditText().setText(format.format(date));
        if(myreports_missingdogs_adapter.getUrl()!=null) {
            slika_myreports_missingdogs.setVisibility(View.VISIBLE);
            Picasso.get().load(myreports_missingdogs_adapter.getUrl()).into(slika_myreports_missingdogs);
        }
        else{
            slika_myreports_missingdogs.setVisibility(View.GONE);
        }

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        datum=myreports_missingdogs_adapter.getDatumizgubljen();
        materialDateBuilder.setSelection(myreports_missingdogs_adapter.getDatumizgubljen().getTime()+2*HOUR);
        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();
        datumizgubljen_myreports_missingdogs.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Date date= new Date(selection);
                datum=date;
            }
        });

        send_myreports_missingdogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                updatemymissingdog_report data=new updatemymissingdog_report(
                        ime_myreports_missingdogs.getEditText().getText().toString(),
                        prezime_myreports_missingdogs.getEditText().getText().toString(),
                        adresa_pronalaska_myreports_missingdogs.getEditText().getText().toString(),
                        adresa_za_preuzimanje_psa_myreports_missingdogs.getEditText().getText().toString(),
                        kontakt_myreports_missingdogs.getEditText().getText().toString(),
                        napomena_myreports_missingdogs.getEditText().getText().toString()
                );
                Call<updatemymissingdog_report[]> call=retrofitInterface.updatemymissingdogreport(myreports_missingdogs_adapter.getid(), data);
                call.enqueue(new Callback<updatemymissingdog_report[]>() {
                    @Override
                    public void onResponse(Call<updatemymissingdog_report[]> call, Response<updatemymissingdog_report[]> response) {
                        if(response.code()==200){
                            Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                            myreportsdisapp_data data1= new myreportsdisapp_data(
                                    ime_myreports_missingdogs.getEditText().getText().toString(),
                                    prezime_myreports_missingdogs.getEditText().getText().toString(),
                                    adresa_pronalaska_myreports_missingdogs.getEditText().getText().toString(),
                                    adresa_za_preuzimanje_psa_myreports_missingdogs.getEditText().getText().toString(),
                                    napomena_myreports_missingdogs.getEditText().getText().toString(),
                                    myreports_missingdogs_adapter.getPostavljeno(),
                                    kontakt_myreports_missingdogs.getEditText().getText().toString(),
                                    myreports_missingdogs_adapter.getid(),
                                    myreports_missingdogs_adapter.getIme_nestanak(),
                                    myreports_missingdogs_adapter.getPrezime_nestanak(),
                                    myreports_missingdogs_adapter.getAdresa(),
                                    myreports_missingdogs_adapter.getTelefonskibr(),
                                    myreports_missingdogs_adapter.getGrad(),
                                    myreports_missingdogs_adapter.getPostnum(),
                                    myreports_missingdogs_adapter.getBoja(),
                                    myreports_missingdogs_adapter.getStarost(),
                                    myreports_missingdogs_adapter.getDlaka(),
                                    myreports_missingdogs_adapter.getVetlokacija(),
                                    myreports_missingdogs_adapter.getImepsa(),
                                    myreports_missingdogs_adapter.getSpol(),
                                    myreports_missingdogs_adapter.getPasmina(),
                                    myreports_missingdogs_adapter.getDatumizgubljen(),
                                    myreports_missingdogs_adapter.getNapomena_nestanak(),
                                    myreports_missingdogs_adapter.getUrl(),
                                    myreports_missingdogs_adapter.isOglas_azila(),
                                    myreports_missingdogs_adapter.getNaziv_azila(),
                                    myreports_missingdogs_adapter.getGrad_azila()
                            );
                            myreports_missingdogs.setListItems(myreports_missingdogs_adapter.getChange(), data1);
                        }
                    }

                    @Override
                    public void onFailure(Call<updatemymissingdog_report[]> call, Throwable t) {
                        Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
//                int radioid1 = radioGroup.getCheckedRadioButtonId();
//                radioButton = radioGroup.findViewById(radioid1);
//                if(radioButton.getText().toString().equals("Aktivan oglas")){
//                    updatemymissingdog updatedata= new updatemymissingdog(
//                            ime_myreports_missingdogs.getEditText().getText().toString(),
//                            prezime_myreports_missingdogs.getEditText().getText().toString(),
//                            adresa_myreports_missingdogs.getEditText().getText().toString(),
//                            Integer.parseInt(kontakt_myreports_missingdogs.getEditText().getText().toString()),
//                            grad_myreports_missingdogs.getEditText().getText().toString(),
//                            Integer.parseInt(postnum_myreports_missingdogs.getEditText().getText().toString()),
//                            boja_myreports_missingdogs.getEditText().getText().toString(),
//                            Integer.parseInt(starost_myreports_missingdogs.getEditText().getText().toString()),
//                            dlaka_myreports_missingdogs.getText().toString(),
//                            vetlokacija_myreports_missingdogs.getEditText().getText().toString(),
//                            imepsa_myreports_missingdogs.getEditText().getText().toString(),
//                            spol_myreports_missingdogs.getText().toString(),
//                            pasmina_myreports_missingdogs.getEditText().getText().toString(),
//                            napomena_myreports_missingdogs.getEditText().getText().toString(),
//                            datum, true);
//                    Call<updatemymissingdog[]> call= retrofitInterface.updatemymissingdog(myreports_missingdogs_adapter.getid(), updatedata);
//
//                    call.enqueue(new Callback<updatemymissingdog[]>() {
//                        @Override
//                        public void onResponse(Call<updatemymissingdog[]> call, Response<updatemymissingdog[]> response) {
//                            if(response.code()==200){
//                                mymissingdogsdata a= new mymissingdogsdata(myreports_missingdogs_adapter.getid(),
//                                        ime_myreports_missingdogs.getEditText().getText().toString(),
//                                        prezime_myreports_missingdogs.getEditText().getText().toString(),
//                                        adresa_myreports_missingdogs.getEditText().getText().toString(),
//                                        kontakt_myreports_missingdogs.getEditText().getText().toString(),
//                                        grad_myreports_missingdogs.getEditText().getText().toString(),
//                                        postnum_myreports_missingdogs.getEditText().getText().toString(),
//                                        boja_myreports_missingdogs.getEditText().getText().toString(),
//                                        starost_myreports_missingdogs.getEditText().getText().toString(),
//                                        dlaka_myreports_missingdogs.getText().toString(),
//                                        vetlokacija_myreports_missingdogs.getEditText().getText().toString(),
//                                        imepsa_myreports_missingdogs.getEditText().getText().toString(),
//                                        spol_myreports_missingdogs.getText().toString(), datum,
//                                        napomena_myreports_missingdogs.getEditText().getText().toString(),
//                                        myreports_missingdogs_adapter.getUrl(),
//                                        myreports_missingdogs_adapter.getPostavljeno(),
//                                        pasmina_myreports_missingdogs.getEditText().getText().toString(), true,
//                                        myreports_missingdogs_adapter.getPrihvaceno(),
//                                        myreports_missingdogs_adapter.getNapomena_azil());
//                                mojioglasi_nestalipsi.setListItems(myreports_missingdogs_adapter.getChange(), a);
////                                myreports_missingdogs_adapter.setAktivan(true);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<updatemymissingdog[]> call, Throwable t) {
//
//                        }
//                    });
//                }

            }
        });
        return view;
    }
}
