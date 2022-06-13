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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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

public class mydata_adopteddog_dialog_shelter extends BottomSheetDialogFragment {
    private TextInputLayout kontakt_mydata_adoptdog, imepsa_mydata_adoptdog,
            pasmina_mydata_adoptdog, starost_mydata_adoptdog, boja_mydata_adoptdog, kilaza_mydata_adoptdog,
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
        View view = inflater.inflate(R.layout.changemydata_adopteddog_shelter, container, false);
        kontakt_mydata_adoptdog = (TextInputLayout) view.findViewById(R.id.kontakt_mydata_adoptdog_shelter);
        imepsa_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.imepsa_mydata_adoptdog_shelter);
        pasmina_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.pasmina_mydata_adoptdog_shelter);
        starost_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.starost_mydata_adoptdog_shelter);
        boja_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.boja_mydata_adoptdog_shelter);
        dlaka_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.dlaka_mydata_adoptdog_shelter);
        spol_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.spol_mydata_adoptdog_shelter);
        kastrat_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.kastrat_mydata_adoptdog_shelter);
        opasnost_mydata_adoptdog=(AutoCompleteTextView) view.findViewById(R.id.opasnost_mydata_adoptdog_shelter);
        napomena_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.napomena_mydata_adoptdog_shelter);
        postavljeno_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.postavljeno_mydata_adoptdog_shelter);
        kilaza_mydata_adoptdog=(TextInputLayout) view.findViewById(R.id.kilaza_mydata_adoptdog_shelter);
        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup_adoptdog_shelter);
        radiobtn1=(RadioButton) view.findViewById(R.id.radio_button_1_adoptdog_shelter);
        radiobtn2=(RadioButton) view.findViewById(R.id.radio_button_2_adoptdog_shelter);
        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);
        send_mydata_adoptdog = view.findViewById(R.id.send_mydata_adoptdog_shelter);

        kontakt_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getKontakt());
        imepsa_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getImepsa());
        pasmina_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getPasmina());
        starost_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getStarost());
        boja_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getBoja());

        kilaza_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getKilaza());

        napomena_mydata_adoptdog.getEditText().setText(myadoptdog_shelter_adapter.getNapomena());
        Date date = new Date(myadoptdog_shelter_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_mydata_adoptdog.getEditText().setText(format.format(date));

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapterdlaka = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_mydata_adoptdog.setText(myadoptdog_shelter_adapter.getDlaka(), false);
        dlaka_mydata_adoptdog.setAdapter(arrayAdapterdlaka);

        String[] spol = {"M", "Ž"};
        ArrayAdapter arrayAdapterspol = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_mydata_adoptdog.setText(myadoptdog_shelter_adapter.getSpol(), false);
        spol_mydata_adoptdog.setAdapter(arrayAdapterspol);

        String[] kastrat = {"Da", "Ne"};
        ArrayAdapter arrayAdapterkastrat = new ArrayAdapter(getActivity(), R.layout.dropdown_item, kastrat);
        kastrat_mydata_adoptdog.setText(myadoptdog_shelter_adapter.getKastrat(), false);
        kastrat_mydata_adoptdog.setAdapter(arrayAdapterkastrat);

        String[] opasnost = {"Da", "Ne"};
        ArrayAdapter arrayAdapteropasnost = new ArrayAdapter(getActivity(), R.layout.dropdown_item, opasnost);
        opasnost_mydata_adoptdog.setText(myadoptdog_shelter_adapter.getOpasnost(), false);
        opasnost_mydata_adoptdog.setAdapter(arrayAdapteropasnost);

        if(myadoptdog_shelter_adapter.isAktivan()){
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
                            myadoptdog_shelter_adapter.getIme(),
                            myadoptdog_shelter_adapter.getPrezime(),
                            myadoptdog_shelter_adapter.getAdresa(),
                            Integer.parseInt(kontakt_mydata_adoptdog.getEditText().getText().toString()),
                            null,
                            null,
                            boja_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_adoptdog.getEditText().getText().toString()),
                            dlaka_mydata_adoptdog.getText().toString(),
                            null,
                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                            spol_mydata_adoptdog.getText().toString(),
                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(kilaza_mydata_adoptdog.getEditText().getText().toString()),
                            kastrat_mydata_adoptdog.getText().toString(),
                            opasnost_mydata_adoptdog.getText().toString(),
                            napomena_mydata_adoptdog.getEditText().getText().toString(), true
                    );
                    Call<updatemyadopteddog[]> call= retrofitInterface.updatemyadopteddog(myadoptdog_shelter_adapter.getid(), updatedata);

                    call.enqueue(new Callback<updatemyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatemyadopteddog[]> call, Response<updatemyadopteddog[]> response) {
                            if(response.code()==200){
                                try{
                                    myadopteddogsdata a = new myadopteddogsdata(
                                            myadoptdog_shelter_adapter.getid(),
                                            null,null, null,
                                            kontakt_mydata_adoptdog.getEditText().getText().toString(),
                                            null,null,
                                            boja_mydata_adoptdog.getEditText().getText().toString(),
                                            starost_mydata_adoptdog.getEditText().getText().toString(),
                                            dlaka_mydata_adoptdog.getText().toString(),
                                            null,
                                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                                            spol_mydata_adoptdog.getText().toString(),
                                            napomena_mydata_adoptdog.getEditText().getText().toString(),
                                            myadoptdog_shelter_adapter.getUrl(),
                                            myadoptdog_shelter_adapter.getPostavljeno(),
                                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                                            kilaza_mydata_adoptdog.getEditText().getText().toString(),
                                            kastrat_mydata_adoptdog.getText().toString(),
                                            opasnost_mydata_adoptdog.getText().toString(),
                                            true, myadoptdog_shelter_adapter.getNapomena_azil(),
                                            myadoptdog_shelter_adapter.getPrihvaceno()
                                    );
                                    mojioglasi_udomipsa_azil.setListItems(myadoptdog_shelter_adapter.getChange(), a);
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
                            null,null, null,
                            Integer.parseInt(kontakt_mydata_adoptdog.getEditText().getText().toString()),
                            null, null,
                            boja_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(starost_mydata_adoptdog.getEditText().getText().toString()),
                            dlaka_mydata_adoptdog.getText().toString(),
                            null,
                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                            spol_mydata_adoptdog.getText().toString(),
                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                            Integer.parseInt(kilaza_mydata_adoptdog.getEditText().getText().toString()),
                            kastrat_mydata_adoptdog.getText().toString(),
                            opasnost_mydata_adoptdog.getText().toString(),
                            napomena_mydata_adoptdog.getEditText().getText().toString(), false
                    );
                    Call<updatemyadopteddog[]> call= retrofitInterface.updatemyadopteddog(myadoptdog_shelter_adapter.getid(), updatedata);

                    call.enqueue(new Callback<updatemyadopteddog[]>() {
                        @Override
                        public void onResponse(Call<updatemyadopteddog[]> call, Response<updatemyadopteddog[]> response) {
                            if(response.code()==200){
                                try{
                                    myadopteddogsdata a = new myadopteddogsdata(
                                            myadoptdog_shelter_adapter.getid(),
                                            null,null, null,
                                            kontakt_mydata_adoptdog.getEditText().getText().toString(),
                                            null,null,
                                            boja_mydata_adoptdog.getEditText().getText().toString(),
                                            starost_mydata_adoptdog.getEditText().getText().toString(),
                                            dlaka_mydata_adoptdog.getText().toString(),
                                            null,
                                            imepsa_mydata_adoptdog.getEditText().getText().toString(),
                                            spol_mydata_adoptdog.getText().toString(),
                                            napomena_mydata_adoptdog.getEditText().getText().toString(),
                                            myadoptdog_shelter_adapter.getUrl(),
                                            myadoptdog_shelter_adapter.getPostavljeno(),
                                            pasmina_mydata_adoptdog.getEditText().getText().toString(),
                                            kilaza_mydata_adoptdog.getEditText().getText().toString(),
                                            kastrat_mydata_adoptdog.getText().toString(),
                                            opasnost_mydata_adoptdog.getText().toString(),
                                            false, myadoptdog_shelter_adapter.getNapomena_azil(),
                                            myadoptdog_shelter_adapter.getPrihvaceno()
                                    );
                                    mojioglasi_udomipsa_azil.setListItems(myadoptdog_shelter_adapter.getChange(), a);
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
