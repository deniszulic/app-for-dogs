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
import android.widget.TextView;
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

public class mydata_adopteddog_dialog extends BottomSheetDialogFragment {
    private TextInputLayout ime_mydata_adoptdog, prezime_mydata_adoptdog, adresa_mydata_adoptdog,
            kontakt_mydata_adoptdog, grad_mydata_adoptdog, postnum_mydata_adoptdog, imepsa_mydata_adoptdog,
            pasmina_mydata_adoptdog, starost_mydata_adoptdog, vetlokacija_mydata_adoptdog, boja_mydata_adoptdog, kilaza_mydata_adoptdog,
            napomena_mydata_adoptdog, postavljeno_mydata_adoptdog;
    private AutoCompleteTextView kastrat_mydata_adoptdog, opasnost_mydata_adoptdog, dlaka_mydata_adoptdog, spol_mydata_adoptdog;
    private RadioGroup radioGroup;
    private RadioButton radiobtn1, radiobtn2, radioButton;
    private Button send_mydata_adoptdog;

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
        View view = inflater.inflate(R.layout.changemydata_adopteddog, container, false);
        ime_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.ime_mydata_adoptdog);
        prezime_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.prezime_mydata_adoptdog);
        adresa_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.adresa_mydata_adoptdog);
        kontakt_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.kontakt_mydata_adoptdog);
        grad_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.grad_mydata_adoptdog);
        postnum_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.postnum_mydata_adoptdog);
        imepsa_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.imepsa_mydata_adoptdog);
        pasmina_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.pasmina_mydata_adoptdog);
        starost_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.starost_mydata_adoptdog);
        vetlokacija_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.vetlokacija_mydata_adoptdog);
        boja_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.boja_mydata_adoptdog);
        dlaka_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.dlaka_mydata_adoptdog);
        spol_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.spol_mydata_adoptdog);
        kastrat_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.kastrat_mydata_adoptdog);
        opasnost_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.opasnost_mydata_adoptdog);
        napomena_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.napomena_mydata_adoptdog);
        postavljeno_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.postavljeno_mydata_adoptdog);
        kilaza_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.kilaza_mydata_adoptdog);
        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup_adoptdog);
        radiobtn1=(RadioButton) view.findViewById(R.id.radio_button_1_adoptdog);
        radiobtn2=(RadioButton) view.findViewById(R.id.radio_button_2_adoptdog);
        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        send_mydata_adoptdog = view.findViewById(R.id.send_mydata_adoptdog);

        ime_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getIme());
        prezime_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getPrezime());
        adresa_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getAdresa());
        kontakt_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getKontakt());
        grad_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getGrad());
        postnum_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getPostnum());
        imepsa_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getImepsa());
        pasmina_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getPasmina());
        starost_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getStarost());
        vetlokacija_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getVetlokacija());
        boja_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getBoja());

        kilaza_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getKilaza());

        napomena_mydata_adoptdog.getEditText().setText(myadoptdog_user_adapter.getNapomena());
        Date date = new Date(myadoptdog_user_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_mydata_adoptdog.getEditText().setText(format.format(date));

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapterdlaka = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_mydata_adoptdog.setText(myadoptdog_user_adapter.getDlaka(), false);
        dlaka_mydata_adoptdog.setAdapter(arrayAdapterdlaka);

        String[] spol = {"M", "Ž"};
        ArrayAdapter arrayAdapterspol = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_mydata_adoptdog.setText(myadoptdog_user_adapter.getSpol(), false);
        spol_mydata_adoptdog.setAdapter(arrayAdapterspol);

        String[] kastrat = {"Da", "Ne"};
        ArrayAdapter arrayAdapterkastrat = new ArrayAdapter(getActivity(), R.layout.dropdown_item, kastrat);
        kastrat_mydata_adoptdog.setText(myadoptdog_user_adapter.getKastrat(), false);
        kastrat_mydata_adoptdog.setAdapter(arrayAdapterkastrat);

        String[] opasnost = {"Da", "Ne"};
        ArrayAdapter arrayAdapteropasnost = new ArrayAdapter(getActivity(), R.layout.dropdown_item, opasnost);
        opasnost_mydata_adoptdog.setText(myadoptdog_user_adapter.getOpasnost(), false);
        opasnost_mydata_adoptdog.setAdapter(arrayAdapteropasnost);

        if(myadoptdog_user_adapter.isAktivan()){
            radiobtn1.setChecked(true);
        }
        else radiobtn2.setChecked(true);

        send_mydata_adoptdog.setOnClickListener(new View.OnClickListener() {
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
                    updatemyadopteddog updatedata= new updatemyadopteddog(
                         ime_mydata_adoptdog.getEditText().getText().toString(),
                            prezime_mydata_adoptdog.getEditText().getText().toString(),
                            adresa_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(kontakt_mydata_adoptdog.getEditText().getText().toString()),
                         grad_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(postnum_mydata_adoptdog.getEditText().getText().toString()),
                            boja_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_adoptdog.getEditText().getText().toString()),
                            dlaka_mydata_adoptdog.getText().toString(),
                            vetlokacija_mydata_adoptdog.getEditText().getText().toString(),
                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                            spol_mydata_adoptdog.getText().toString(),
                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(kilaza_mydata_adoptdog.getEditText().getText().toString()),
                            kastrat_mydata_adoptdog.getText().toString(),
                            opasnost_mydata_adoptdog.getText().toString(),
                            napomena_mydata_adoptdog.getEditText().getText().toString(), true
                    );
                    Call<updatemyadopteddog[]> call= retrofitInterface.updatemyadopteddog(myadoptdog_user_adapter.getid(), updatedata);

                    call.enqueue(new Callback<updatemyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatemyadopteddog[]> call, Response<updatemyadopteddog[]> response) {
                            if(response.code()==200){
                                try{
                                    myadopteddogsdata a = new myadopteddogsdata(
                                        myadoptdog_user_adapter.getid(),
                                            ime_mydata_adoptdog.getEditText().getText().toString(),
                                            prezime_mydata_adoptdog.getEditText().getText().toString(),
                                            adresa_mydata_adoptdog.getEditText().getText().toString(),
                                            kontakt_mydata_adoptdog.getEditText().getText().toString(),
                                            grad_mydata_adoptdog.getEditText().getText().toString(),
                                            postnum_mydata_adoptdog.getEditText().getText().toString(),
                                            boja_mydata_adoptdog.getEditText().getText().toString(),
                                            starost_mydata_adoptdog.getEditText().getText().toString(),
                                            dlaka_mydata_adoptdog.getText().toString(),
                                            vetlokacija_mydata_adoptdog.getEditText().getText().toString(),
                                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                                            spol_mydata_adoptdog.getText().toString(),
                                            napomena_mydata_adoptdog.getEditText().getText().toString(),
                                            myadoptdog_user_adapter.getUrl(),
                                            myadoptdog_user_adapter.getPostavljeno(),
                                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                                            kilaza_mydata_adoptdog.getEditText().getText().toString(),
                                            kastrat_mydata_adoptdog.getText().toString(),
                                            opasnost_mydata_adoptdog.getText().toString(),
                                            true, myadoptdog_user_adapter.getNapomena_azil(),
                                            myadoptdog_user_adapter.getPrihvaceno()
                                    );
                                    mojioglasi_udomipsa.setListItems(myadoptdog_user_adapter.getChange(), a);
                                    Toast.makeText(getActivity(),"Ažurirano!",Toast.LENGTH_SHORT).show();
                                }catch(Exception e){System.out.println(e);}
                            }
                        }

                        @Override
                        public void onFailure(Call<updatemyadopteddog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    updatemyadopteddog updatedata= new updatemyadopteddog(
                            ime_mydata_adoptdog.getEditText().getText().toString(),
                            prezime_mydata_adoptdog.getEditText().getText().toString(),
                            adresa_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(kontakt_mydata_adoptdog.getEditText().getText().toString()),
                            grad_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(postnum_mydata_adoptdog.getEditText().getText().toString()),
                            boja_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_adoptdog.getEditText().getText().toString()),
                            dlaka_mydata_adoptdog.getText().toString(),
                            vetlokacija_mydata_adoptdog.getEditText().getText().toString(),
                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                            spol_mydata_adoptdog.getText().toString(),
                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(kilaza_mydata_adoptdog.getEditText().getText().toString()),
                            kastrat_mydata_adoptdog.getText().toString(),
                            opasnost_mydata_adoptdog.getText().toString(),
                            napomena_mydata_adoptdog.getEditText().getText().toString(), false
                    );
                    Call<updatemyadopteddog[]> call= retrofitInterface.updatemyadopteddog(myadoptdog_user_adapter.getid(), updatedata);

                    call.enqueue(new Callback<updatemyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatemyadopteddog[]> call, Response<updatemyadopteddog[]> response) {
                            if(response.code()==200){
                                try{
                                    myadopteddogsdata a = new myadopteddogsdata(
                                            myadoptdog_user_adapter.getid(),
                                            ime_mydata_adoptdog.getEditText().getText().toString(),
                                            prezime_mydata_adoptdog.getEditText().getText().toString(),
                                            adresa_mydata_adoptdog.getEditText().getText().toString(),
                                            kontakt_mydata_adoptdog.getEditText().getText().toString(),
                                            grad_mydata_adoptdog.getEditText().getText().toString(),
                                            postnum_mydata_adoptdog.getEditText().getText().toString(),
                                            boja_mydata_adoptdog.getEditText().getText().toString(),
                                            starost_mydata_adoptdog.getEditText().getText().toString(),
                                            dlaka_mydata_adoptdog.getText().toString(),
                                            vetlokacija_mydata_adoptdog.getEditText().getText().toString(),
                                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                                            spol_mydata_adoptdog.getText().toString(),
                                            napomena_mydata_adoptdog.getEditText().getText().toString(),
                                            myadoptdog_user_adapter.getUrl(),
                                            myadoptdog_user_adapter.getPostavljeno(),
                                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                                            kilaza_mydata_adoptdog.getEditText().getText().toString(),
                                            kastrat_mydata_adoptdog.getText().toString(),
                                            opasnost_mydata_adoptdog.getText().toString(),
                                            false, myadoptdog_user_adapter.getNapomena_azil(),
                                            myadoptdog_user_adapter.getPrihvaceno()
                                    );
                                    mojioglasi_udomipsa.setListItems(myadoptdog_user_adapter.getChange(), a);
                                    Toast.makeText(getActivity(),"Ažurirano!",Toast.LENGTH_SHORT).show();
                                }catch(Exception e){System.out.println(e);}
                            }
                        }

                        @Override
                        public void onFailure(Call<updatemyadopteddog[]> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
}
