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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class reporttoshelter_dialog_adoptdog extends BottomSheetDialogFragment {
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
        View view = inflater.inflate(R.layout.reporttoshelter_dialog, container, false);
        azili_reporttoshelter=(AutoCompleteTextView) view.findViewById(R.id.azili_reporttoshelter_adoptdog);
        sendreqtoshelter=(Button) view.findViewById(R.id.sendreqtoshelter_adoptdog);
        radio_button_reporttoshelter1=(RadioButton) view.findViewById(R.id.radio_button_reporttoshelter_adoptdog1);
        radio_button_reporttoshelter2=(RadioButton) view.findViewById(R.id.radio_button_reporttoshelter_adoptdog2);
        razlog_reporttoshelter=(TextInputLayout) view.findViewById(R.id.razlog_reporttoshelter_adoptdog);
        textView48=(TextView) view.findViewById(R.id.textView_48);
        line_reporttoshelter=(View) view.findViewById(R.id.line_reporttoshelter_adoptdog);
        dropdown_reporttoshelter=(TextInputLayout) view.findViewById(R.id.dropdown_reporttoshelter_adoptdog);

        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        if(myadoptdog_user_adapter.getNapomena_azil()!=null) {
            razlog_reporttoshelter.getEditText().setText(myadoptdog_user_adapter.getNapomena_azil());
        }
        if(myadoptdog_user_adapter.getPrihvaceno()!=null) {
            if (myadoptdog_user_adapter.getPrihvaceno().equals("true")) {
                radio_button_reporttoshelter1.setChecked(true);
            }
            if (myadoptdog_user_adapter.getPrihvaceno().equals("false")) {
                radio_button_reporttoshelter2.setChecked(true);
            }
        }

        if(myadoptdog_user_adapter.getPrihvaceno()!=null){
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
                    try{
                        shelters[] data = response.body();
                        listItems.addAll(Arrays.asList(data));
                        String[] sh = new String[listItems.size()];
                        for (int i = 0; i < listItems.size(); i++) {
                            sh[i] = listItems.get(i).getNaziv() + "," + listItems.get(i).getGrad();
                            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, sh);
                            azili_reporttoshelter.setText(listItems.get(0).getNaziv() + "," + listItems.get(0).getGrad(), false);
                            azili_reporttoshelter.setAdapter(arrayAdapter);
                        }
                    }catch(Exception e){System.out.println(e);}
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
                        sendreqtoshelter_adoptdog data= new sendreqtoshelter_adoptdog(myadoptdog_user_adapter.getid(), listItems.get(i).getId(), timestamp, "obrada");
                        Call<Void> sendreq=retrofitInterface.sendreqadopt(data);
                        sendreq.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code()==201) {
                                    try{
                                        Toast.makeText(getActivity(), "Podaci poslani azilu!", Toast.LENGTH_SHORT).show();
                                        textView48.setVisibility(View.GONE);
                                        dropdown_reporttoshelter.setVisibility(View.GONE);
                                        sendreqtoshelter.setVisibility(View.GONE);
                                        line_reporttoshelter.setVisibility(View.GONE);
                                        myadopteddogsdata a = new myadopteddogsdata(myadoptdog_user_adapter.getid(),
                                                myadoptdog_user_adapter.getIme(),
                                                myadoptdog_user_adapter.getPrezime(),
                                                myadoptdog_user_adapter.getAdresa(),
                                                myadoptdog_user_adapter.getKontakt(),
                                                myadoptdog_user_adapter.getGrad(),
                                                myadoptdog_user_adapter.getPostnum(),
                                                myadoptdog_user_adapter.getBoja(),
                                                myadoptdog_user_adapter.getStarost(),
                                                myadoptdog_user_adapter.getDlaka(),
                                                myadoptdog_user_adapter.getVetlokacija(),
                                                myadoptdog_user_adapter.getImepsa(),
                                                myadoptdog_user_adapter.getSpol(),
                                                myadoptdog_user_adapter.getNapomena(),
                                                myadoptdog_user_adapter.getUrl(),
                                                myadoptdog_user_adapter.getPostavljeno(),
                                                myadoptdog_user_adapter.getPasmina(),
                                                myadoptdog_user_adapter.getKilaza(),
                                                myadoptdog_user_adapter.getKastrat(),
                                                myadoptdog_user_adapter.getOpasnost(),
                                                myadoptdog_user_adapter.isAktivan(),
                                                myadoptdog_user_adapter.getNapomena_azil(),
                                                "obrada"
                                                );
                                        mojioglasi_udomipsa.setListItems(myadoptdog_user_adapter.getChange(), a);
                                    }catch(Exception e){System.out.println(e);}
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
