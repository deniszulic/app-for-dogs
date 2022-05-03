package com.example.dog_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mydata_missingdog_dialog extends BottomSheetDialogFragment {
    private TextInputLayout ime_mydata_missingdog, prezime_mydata_missingdog, adresa_mydata_missingdog,
            kontakt_mydata_missingdog, grad_mydata_missingdog, postnum_mydata_missingdog, imepsa_mydata_missingdog,
    pasmina_mydata_missingdog, starost_mydata_missingdog, vetlokacija_mydata_missingdog, boja_mydata_missingdog,
            napomena_mydata_missingdog, postavljeno_mydata_missingdog;
    private AutoCompleteTextView dlaka_mydata_missingdog, spol_mydata_missingdog;
    private AppCompatButton datumizgubljen_mydata_missingdog;
    private RadioGroup radioGroup;
    private RadioButton radiobtn1, radiobtn2, radioButton;
    private Button send_mydata_missingdog;

    private Date datum;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private boolean isAllFieldsChecked = false;
    private static final long HOUR = 3600*1000;
    private List<mymissingdogsdata> listItems;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.changemydata, container, false);
        ime_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.ime_mydata_missingdog);
        prezime_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.prezime_mydata_missingdog);
        adresa_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.adresa_mydata_missingdog);
        kontakt_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.kontakt_mydata_missingdog);
        grad_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.grad_mydata_missingdog);
        postnum_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.postnum_mydata_missingdog);
        imepsa_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.imepsa_mydata_missingdog);
        pasmina_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.pasmina_mydata_missingdog);
        starost_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.starost_mydata_missingdog);
        vetlokacija_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.vetlokacija_mydata_missingdog);
        boja_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.boja_mydata_missingdog);
        datumizgubljen_mydata_missingdog=(AppCompatButton) view.findViewById(R.id.datumizgubljen_mydata_missingdog);
        dlaka_mydata_missingdog=(AutoCompleteTextView) view.findViewById(R.id.dlaka_mydata_missingdog);
        spol_mydata_missingdog=(AutoCompleteTextView) view.findViewById(R.id.spol_mydata_missingdog);
        napomena_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.napomena_mydata_missingdog);
        postavljeno_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.postavljeno_mydata_missingdog);
        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup);
        radiobtn1=(RadioButton) view.findViewById(R.id.radio_button_1);
        radiobtn2=(RadioButton) view.findViewById(R.id.radio_button_2);
        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        send_mydata_missingdog = view.findViewById(R.id.send_mydata_missingdog);

        ime_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getIme());
        prezime_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getPrezime());
        adresa_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getAdresa());
        kontakt_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getKontakt());
        grad_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getGrad());
        postnum_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getPostnum());
        imepsa_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getImepsa());
        pasmina_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getPasmina());
        starost_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getStarost());
        vetlokacija_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getVetlokacija());
        boja_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getBoja());

        napomena_mydata_missingdog.getEditText().setText(mymissingdogsadapter.getNapomena());
        Date date = new Date(mymissingdogsadapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_mydata_missingdog.getEditText().setText(format.format(date));

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapterdlaka = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_mydata_missingdog.setText(mymissingdogsadapter.getDlaka(), false);
        dlaka_mydata_missingdog.setAdapter(arrayAdapterdlaka);

        String[] spol = {"M", "Ž"};
        ArrayAdapter arrayAdapterspol = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_mydata_missingdog.setText(mymissingdogsadapter.getSpol(), false);
        spol_mydata_missingdog.setAdapter(arrayAdapterspol);


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        datum=mymissingdogsadapter.getDatumizgubljen();
        materialDateBuilder.setSelection(mymissingdogsadapter.getDatumizgubljen().getTime()+2*HOUR);
        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();
        datumizgubljen_mydata_missingdog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
//                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//                calendar.setTimeInMillis(selection);
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                String formattedDate  = format.format(calendar.getTime());
                Date date= new Date(selection);
                datum=date;
            }
        });
        if(mymissingdogsadapter.isAktivan()){
            radiobtn1.setChecked(true);
        }
        else radiobtn2.setChecked(true);

        send_mydata_missingdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                int radioid1 = radioGroup.getCheckedRadioButtonId();
                radioButton = radioGroup.findViewById(radioid1);
                if(radioButton.getText().toString().equals("Aktivan oglas")){
                    updatemymissingdog updatedata= new updatemymissingdog(
                            ime_mydata_missingdog.getEditText().getText().toString(),
                            prezime_mydata_missingdog.getEditText().getText().toString(),
                            adresa_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(kontakt_mydata_missingdog.getEditText().getText().toString()),
                            grad_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(postnum_mydata_missingdog.getEditText().getText().toString()),
                            boja_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_missingdog.getEditText().getText().toString()),
                            dlaka_mydata_missingdog.getText().toString(),
                            vetlokacija_mydata_missingdog.getEditText().getText().toString(),
                            imepsa_mydata_missingdog.getEditText().getText().toString(),
                            spol_mydata_missingdog.getText().toString(),
                            pasmina_mydata_missingdog.getEditText().getText().toString(),
                            napomena_mydata_missingdog.getEditText().getText().toString(),
                            datum, true);
                    Call<updatemymissingdog[]> call= retrofitInterface.updatemymissingdog(mymissingdogsadapter.getid(), updatedata);

                    call.enqueue(new Callback<updatemymissingdog[]>() {
                        @Override
                        public void onResponse(Call<updatemymissingdog[]> call, Response<updatemymissingdog[]> response) {
                            if(response.code()==200){
//                                Call<mymissingdogsdata[]> data = retrofitInterface.getmymissingdogs(getemail);
//
//                                listItems=new ArrayList<>();
//                                data.enqueue(new Callback<mymissingdogsdata[]>() {
//                                    @Override
//                                    public void onResponse(Call<mymissingdogsdata[]> call, Response<mymissingdogsdata[]> response) {
//                                        mymissingdogsdata[] data = response.body();
//                                        listItems.addAll(Arrays.asList(data));
//                                        mojioglasi_nestalipsi.setAdapter(new mymissingdogsadapter(listItems, getContext()));
//                                        mojioglasi_nestalipsi.getRecyclerView().setAdapter(mojioglasi_nestalipsi.getAdapter());
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<mymissingdogsdata[]> call, Throwable t) {
//
//                                    }
//                                });

//                                mojioglasi_nestalipsi.getAdapter().notifyItemChanged(mymissingdogsadapter.getChange());

                                mymissingdogsdata a= new mymissingdogsdata(mymissingdogsadapter.getid(),
                                        ime_mydata_missingdog.getEditText().getText().toString(),
                                        prezime_mydata_missingdog.getEditText().getText().toString(),
                                        adresa_mydata_missingdog.getEditText().getText().toString(),
                                        kontakt_mydata_missingdog.getEditText().getText().toString(),
                                        grad_mydata_missingdog.getEditText().getText().toString(),
                                        postnum_mydata_missingdog.getEditText().getText().toString(),
                                        boja_mydata_missingdog.getEditText().getText().toString(),
                                        starost_mydata_missingdog.getEditText().getText().toString(),
                                        dlaka_mydata_missingdog.getText().toString(),
                                        vetlokacija_mydata_missingdog.getEditText().getText().toString(),
                                        imepsa_mydata_missingdog.getEditText().getText().toString(),
                                        spol_mydata_missingdog.getText().toString(), datum,
                                        napomena_mydata_missingdog.getEditText().getText().toString(),
                                        mymissingdogsadapter.getUrl(),
                                        mymissingdogsadapter.getPostavljeno(),
                                        pasmina_mydata_missingdog.getEditText().getText().toString(), true,
                                        mymissingdogsadapter.getPrihvaceno(),
                                        napomena_mydata_missingdog.getEditText().getText().toString());
                                mojioglasi_nestalipsi.setListItems(mymissingdogsadapter.getChange(), a);
//                                mymissingdogsadapter.setAktivan(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<updatemymissingdog[]> call, Throwable t) {

                        }
                    });
                }
                else {
                    updatemymissingdog updatedata= new updatemymissingdog(
                            ime_mydata_missingdog.getEditText().getText().toString(),
                            prezime_mydata_missingdog.getEditText().getText().toString(),
                            adresa_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(kontakt_mydata_missingdog.getEditText().getText().toString()),
                            grad_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(postnum_mydata_missingdog.getEditText().getText().toString()),
                            boja_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_missingdog.getEditText().getText().toString()),
                            dlaka_mydata_missingdog.getText().toString(),
                            vetlokacija_mydata_missingdog.getEditText().getText().toString(),
                            imepsa_mydata_missingdog.getEditText().getText().toString(),
                            spol_mydata_missingdog.getText().toString(),
                            pasmina_mydata_missingdog.getEditText().getText().toString(),
                            napomena_mydata_missingdog.getEditText().getText().toString(),
                            datum, false);
                    Call<updatemymissingdog[]> call= retrofitInterface.updatemymissingdog(mymissingdogsadapter.getid(), updatedata);

                    call.enqueue(new Callback<updatemymissingdog[]>() {
                        @Override
                        public void onResponse(Call<updatemymissingdog[]> call, Response<updatemymissingdog[]> response) {
                            if(response.code()==200){
                                mymissingdogsdata a= new mymissingdogsdata(mymissingdogsadapter.getid(),
                                        ime_mydata_missingdog.getEditText().getText().toString(),
                                        prezime_mydata_missingdog.getEditText().getText().toString(),
                                        adresa_mydata_missingdog.getEditText().getText().toString(),
                                        kontakt_mydata_missingdog.getEditText().getText().toString(),
                                        grad_mydata_missingdog.getEditText().getText().toString(),
                                        postnum_mydata_missingdog.getEditText().getText().toString(),
                                        boja_mydata_missingdog.getEditText().getText().toString(),
                                        starost_mydata_missingdog.getEditText().getText().toString(),
                                        dlaka_mydata_missingdog.getText().toString(),
                                        vetlokacija_mydata_missingdog.getEditText().getText().toString(),
                                        imepsa_mydata_missingdog.getEditText().getText().toString(),
                                        spol_mydata_missingdog.getText().toString(), datum,
                                        napomena_mydata_missingdog.getEditText().getText().toString(),
                                        mymissingdogsadapter.getUrl(),
                                        mymissingdogsadapter.getPostavljeno(),
                                        pasmina_mydata_missingdog.getEditText().getText().toString(), true,
                                        mymissingdogsadapter.getPrihvaceno(),
                                        napomena_mydata_missingdog.getEditText().getText().toString());
                                mojioglasi_nestalipsi.setListItems(mymissingdogsadapter.getChange(), a);
//                                mymissingdogsadapter.setAktivan(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<updatemymissingdog[]> call, Throwable t) {

                        }
                    });
                }
            }
        });
        return view;
    }
}
