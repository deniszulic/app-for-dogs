package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mydata_missingdog_dialog_shelter extends BottomSheetDialogFragment {
    private TextInputLayout kontakt_mydata_missingdog, imepsa_mydata_missingdog,
            pasmina_mydata_missingdog, starost_mydata_missingdog, boja_mydata_missingdog,
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
        View view = inflater.inflate(R.layout.changemydata_shelter, container, false);
        kontakt_mydata_missingdog = (TextInputLayout) view.findViewById(R.id.kontakt_mydata_missingdog_shelter);
        imepsa_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.imepsa_mydata_missingdog_shelter);
        pasmina_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.pasmina_mydata_missingdog_shelter);
        starost_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.starost_mydata_missingdog_shelter);
        boja_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.boja_mydata_missingdog_shelter);
        datumizgubljen_mydata_missingdog=(AppCompatButton) view.findViewById(R.id.datumizgubljen_mydata_missingdog_shelter);
        dlaka_mydata_missingdog=(AutoCompleteTextView) view.findViewById(R.id.dlaka_mydata_missingdog_shelter);
        spol_mydata_missingdog=(AutoCompleteTextView) view.findViewById(R.id.spol_mydata_missingdog_shelter);
        napomena_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.napomena_mydata_missingdog_shelter);
        postavljeno_mydata_missingdog=(TextInputLayout) view.findViewById(R.id.postavljeno_mydata_missingdog_shelter);
        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup_shelter);
        radiobtn1=(RadioButton) view.findViewById(R.id.radio_button_1_shelter);
        radiobtn2=(RadioButton) view.findViewById(R.id.radio_button_2_shelter);
        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        send_mydata_missingdog = view.findViewById(R.id.send_mydata_missingdog_shelter);

        kontakt_mydata_missingdog.getEditText().setText(mymissingdogsadapter_shelter.getKontakt());
        imepsa_mydata_missingdog.getEditText().setText(mymissingdogsadapter_shelter.getImepsa());
        pasmina_mydata_missingdog.getEditText().setText(mymissingdogsadapter_shelter.getPasmina());
        starost_mydata_missingdog.getEditText().setText(mymissingdogsadapter_shelter.getStarost());
        boja_mydata_missingdog.getEditText().setText(mymissingdogsadapter_shelter.getBoja());

        napomena_mydata_missingdog.getEditText().setText(mymissingdogsadapter_shelter.getNapomena());
        Date date = new Date(mymissingdogsadapter_shelter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_mydata_missingdog.getEditText().setText(format.format(date));

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapterdlaka = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_mydata_missingdog.setText(mymissingdogsadapter_shelter.getDlaka(), false);
        dlaka_mydata_missingdog.setAdapter(arrayAdapterdlaka);

        String[] spol = {"M", "Ž"};
        ArrayAdapter arrayAdapterspol = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_mydata_missingdog.setText(mymissingdogsadapter_shelter.getSpol(), false);
        spol_mydata_missingdog.setAdapter(arrayAdapterspol);


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        datum=mymissingdogsadapter_shelter.getDatumizgubljen();
        materialDateBuilder.setSelection(mymissingdogsadapter_shelter.getDatumizgubljen().getTime()+2*HOUR);
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
                Date date= new Date(selection);
                datum=date;
            }
        });
        if(mymissingdogsadapter_shelter.isAktivan()){
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
                            null,
                            null,
                            null,
                            Integer.parseInt(kontakt_mydata_missingdog.getEditText().getText().toString()),
                            null,
                            0,
                            boja_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_missingdog.getEditText().getText().toString()),
                            dlaka_mydata_missingdog.getText().toString(),
                            null,
                            imepsa_mydata_missingdog.getEditText().getText().toString(),
                            spol_mydata_missingdog.getText().toString(),
                            pasmina_mydata_missingdog.getEditText().getText().toString(),
                            napomena_mydata_missingdog.getEditText().getText().toString(),
                            datum, true);
                    Call<updatemymissingdog[]> call= retrofitInterface.updatemymissingdog(mymissingdogsadapter_shelter.getid(), updatedata);

                    call.enqueue(new Callback<updatemymissingdog[]>() {
                        @Override
                        public void onResponse(Call<updatemymissingdog[]> call, Response<updatemymissingdog[]> response) {
                            if(response.code()==200){
                                try{
                                    Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                                    mymissingdogsdata a= new mymissingdogsdata(mymissingdogsadapter_shelter.getid(),
                                            null, null, null,
                                            kontakt_mydata_missingdog.getEditText().getText().toString(),
                                            null,
                                            mymissingdogsadapter_shelter.getPostnum(),
                                            boja_mydata_missingdog.getEditText().getText().toString(),
                                            starost_mydata_missingdog.getEditText().getText().toString(),
                                            dlaka_mydata_missingdog.getText().toString(),
                                            null,
                                            imepsa_mydata_missingdog.getEditText().getText().toString(),
                                            spol_mydata_missingdog.getText().toString(), datum,
                                            napomena_mydata_missingdog.getEditText().getText().toString(),
                                            mymissingdogsadapter_shelter.getUrl(),
                                            mymissingdogsadapter_shelter.getPostavljeno(),
                                            pasmina_mydata_missingdog.getEditText().getText().toString(), true,
                                            mymissingdogsadapter_shelter.getPrihvaceno(),
                                            mymissingdogsadapter_shelter.getNapomena_azil());
                                    mojioglasi_nestalipsi_azil.setListItems(mymissingdogsadapter_shelter.getChange(), a);
                                }catch(Exception e){System.out.println(e);}
                            }
                        }

                        @Override
                        public void onFailure(Call<updatemymissingdog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    updatemymissingdog updatedata= new updatemymissingdog(
                            null, null, null,
                            Integer.parseInt(kontakt_mydata_missingdog.getEditText().getText().toString()),
                            null,
                            0,
                            boja_mydata_missingdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_missingdog.getEditText().getText().toString()),
                            dlaka_mydata_missingdog.getText().toString(),
                            null,
                            imepsa_mydata_missingdog.getEditText().getText().toString(),
                            spol_mydata_missingdog.getText().toString(),
                            pasmina_mydata_missingdog.getEditText().getText().toString(),
                            napomena_mydata_missingdog.getEditText().getText().toString(),
                            datum, false);
                    Call<updatemymissingdog[]> call= retrofitInterface.updatemymissingdog(mymissingdogsadapter_shelter.getid(), updatedata);

                    call.enqueue(new Callback<updatemymissingdog[]>() {
                        @Override
                        public void onResponse(Call<updatemymissingdog[]> call, Response<updatemymissingdog[]> response) {
                            if(response.code()==200){
                                try{
                                    Toast.makeText(getActivity(),"Ažurirano",Toast.LENGTH_SHORT).show();
                                    mymissingdogsdata a= new mymissingdogsdata(mymissingdogsadapter_shelter.getid(),
                                            null, null, null,
                                            kontakt_mydata_missingdog.getEditText().getText().toString(),
                                            null,
                                            mymissingdogsadapter_shelter.getPostnum(),
                                            boja_mydata_missingdog.getEditText().getText().toString(),
                                            starost_mydata_missingdog.getEditText().getText().toString(),
                                            dlaka_mydata_missingdog.getText().toString(),
                                            null,
                                            imepsa_mydata_missingdog.getEditText().getText().toString(),
                                            spol_mydata_missingdog.getText().toString(), datum,
                                            napomena_mydata_missingdog.getEditText().getText().toString(),
                                            mymissingdogsadapter_shelter.getUrl(),
                                            mymissingdogsadapter_shelter.getPostavljeno(),
                                            pasmina_mydata_missingdog.getEditText().getText().toString(), false,
                                            mymissingdogsadapter_shelter.getPrihvaceno(),
                                            mymissingdogsadapter_shelter.getNapomena_azil());
                                    mojioglasi_nestalipsi_azil.setListItems(mymissingdogsadapter_shelter.getChange(), a);
                                }catch(Exception e){System.out.println(e);}
                            }
                        }

                        @Override
                        public void onFailure(Call<updatemymissingdog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
}
