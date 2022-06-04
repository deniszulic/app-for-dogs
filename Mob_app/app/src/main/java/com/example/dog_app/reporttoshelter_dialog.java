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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class reporttoshelter_dialog extends BottomSheetDialogFragment {
    private AutoCompleteTextView azili_reporttoshelter;
    private Button sendreqtoshelter;
    private RadioButton radio_button_reporttoshelter1, radio_button_reporttoshelter2;
    private TextInputLayout razlog_reporttoshelter, dropdown_reporttoshelter;
    private View line_reporttoshelter;
    private TextView textView48;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static List<shelters> listItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reporttoshelter, container, false);
        azili_reporttoshelter=(AutoCompleteTextView) view.findViewById(R.id.azili_reporttoshelter);
        sendreqtoshelter=(Button) view.findViewById(R.id.sendreqtoshelter);
        radio_button_reporttoshelter1=(RadioButton) view.findViewById(R.id.radio_button_reporttoshelter1);
        radio_button_reporttoshelter2=(RadioButton) view.findViewById(R.id.radio_button_reporttoshelter2);
        razlog_reporttoshelter=(TextInputLayout) view.findViewById(R.id.razlog_reporttoshelter);
        textView48=(TextView) view.findViewById(R.id.textView48);
        line_reporttoshelter=(View) view.findViewById(R.id.line_reporttoshelter);
        dropdown_reporttoshelter=(TextInputLayout) view.findViewById(R.id.dropdown_reporttoshelter);

        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        if(mymissingdogsadapter.getNapomena_azil()!=null) {
            razlog_reporttoshelter.getEditText().setText(mymissingdogsadapter.getNapomena_azil());
        }
        if(mymissingdogsadapter.getPrihvaceno()!=null) {
            if (mymissingdogsadapter.getPrihvaceno().equals("true")) {
                radio_button_reporttoshelter1.setChecked(true);
            }
            if (mymissingdogsadapter.getPrihvaceno().equals("false")) {
                radio_button_reporttoshelter2.setChecked(true);
            }
        }

        if(mymissingdogsadapter.getPrihvaceno()!=null){
            textView48.setVisibility(View.GONE);
            dropdown_reporttoshelter.setVisibility(View.GONE);
            sendreqtoshelter.setVisibility(View.GONE);
            line_reporttoshelter.setVisibility(View.GONE);
        }

        Call<shelters[]> data= retrofitInterface.getshelters();
        listItems=new ArrayList<>();
        data.enqueue(new Callback<shelters[]>() {
            @Override
            public void onResponse(Call<shelters[]> call, Response<shelters[]> response) {
                if(response.code()==200) {
                    shelters[] data = response.body();
                    listItems.addAll(Arrays.asList(data));
                    String[] sh = new String[listItems.size()];
                    for (int i = 0; i < listItems.size(); i++) {
                        sh[i] = listItems.get(i).getNaziv() + "," + listItems.get(i).getGrad();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, sh);
                        azili_reporttoshelter.setText(listItems.get(0).getNaziv() + "," + listItems.get(0).getGrad(), false);
                        azili_reporttoshelter.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<shelters[]> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        sendreqtoshelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<listItems.size();i++){
                    if(azili_reporttoshelter.getText().toString().substring(0, azili_reporttoshelter.getText().toString().indexOf(",")).equals(listItems.get(i).getNaziv())){
                        Date date = new Date();
                        long timestamp = date.getTime();
                        sendreqtoshelter data= new sendreqtoshelter("obrada", mymissingdogsadapter.getid(), listItems.get(i).getId(), timestamp);
                        Call<Void> sendreq=retrofitInterface.sendreqshelter(data);
                        sendreq.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code()==201) {
                                    Toast.makeText(getActivity(), "Podaci poslani azilu!", Toast.LENGTH_SHORT).show();
                                    textView48.setVisibility(View.GONE);
                                    dropdown_reporttoshelter.setVisibility(View.GONE);
                                    sendreqtoshelter.setVisibility(View.GONE);
                                    line_reporttoshelter.setVisibility(View.GONE);

                                    mymissingdogsdata a = new mymissingdogsdata(mymissingdogsadapter.getid(),
                                            mymissingdogsadapter.getIme(),
                                            mymissingdogsadapter.getPrezime(),
                                            mymissingdogsadapter.getAdresa(),
                                            mymissingdogsadapter.getKontakt(),
                                            mymissingdogsadapter.getGrad(),
                                            mymissingdogsadapter.getPostnum(),
                                            mymissingdogsadapter.getBoja(),
                                            mymissingdogsadapter.getStarost(),
                                            mymissingdogsadapter.getDlaka(),
                                            mymissingdogsadapter.getVetlokacija(),
                                            mymissingdogsadapter.getImepsa(),
                                            mymissingdogsadapter.getSpol(),
                                            mymissingdogsadapter.getDatumizgubljen(),
                                            mymissingdogsadapter.getNapomena(),
                                            mymissingdogsadapter.getUrl(),
                                            mymissingdogsadapter.getPostavljeno(),
                                            mymissingdogsadapter.getPasmina(),
                                            mymissingdogsadapter.isAktivan(),
                                            "obrada",
                                            mymissingdogsadapter.getNapomena_azil());
                                    mojioglasi_nestalipsi.setListItems(mymissingdogsadapter.getChange(), a);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    }
                }
            }
        });
        return view;
    }
}
