package com.example.dog_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;

public class reportsonmymisingdog_dialog extends BottomSheetDialogFragment {
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
        View view = inflater.inflate(R.layout.reportsonmymissingdogs_dialog, container, false);
        ime_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.ime_myreports_missingdogs_);
        ime_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.ime_nestanak_myreports_missingdogs_);
        prezime_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.prezime_nestanak_myreports_missingdogs_);
        telefonskibr_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.telefonskibr_myreports_missingdogs_);
        grad_azila_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.grad_azila_myreports_missingdogs_);
        postanski_broj_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.postanski_broj_myreports_missingdogs_);
        napomena_nestanak_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.napomena_nestanak_myreports_missingdogs_);
        adresa_za_preuzimanje_psa_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.adresa_za_preuzimanje_psa_myreports_missingdogs_);
        adresa_pronalaska_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.adresa_pronalaska_myreports_missingdogs_);
        prezime_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.prezime_myreports_missingdogs_);
        adresa_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.adresa_myreports_missingdogs_);
        kontakt_myreports_missingdogs = (TextInputLayout) view.findViewById(R.id.kontakt_myreports_missingdogs_);
        imepsa_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.imepsa_myreports_missingdogs_);
        pasmina_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.pasmina_myreports_missingdogs_);
        starost_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.starost_myreports_missingdogs_);
        vetlokacija_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.vetlokacija_myreports_missingdogs_);
        boja_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.boja_myreports_missingdogs_);
        datumizgubljen_myreports_missingdogs=(AppCompatButton) view.findViewById(R.id.datumizgubljen_myreports_missingdogs_);
        dlaka_myreports_missingdogs= (TextInputLayout) view.findViewById(R.id.dlaka_myreports_missingdogs_);
        spol_myreports_missingdogs= (TextInputLayout) view.findViewById(R.id.spol_myreports_missingdogs_);
        napomena_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.napomena_myreports_missingdogs_);
        postavljeno_myreports_missingdogs=(TextInputLayout) view.findViewById(R.id.postavljeno_myreports_missingdogs_);
        slika_myreports_missingdogs=(ImageView) view.findViewById(R.id.slika_myreports_missingdogs_);

        SharedPreferences sp1 = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String getemail=sp1.getString("email", null);

        ime_nestanak_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getIme_nestanak());
        prezime_nestanak_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getPrezime_nestanak());
        telefonskibr_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getTelefonskibr());
        grad_azila_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getGrad());
        postanski_broj_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getPostnum());
        napomena_nestanak_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getNapomena_nestanak());
        adresa_za_preuzimanje_psa_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getAdresa_za_preuzimanje_psa());
        adresa_pronalaska_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getAdresa_pronalaska());
        dlaka_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getDlaka());
        spol_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getSpol());


        ime_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getIme());
        prezime_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getPrezime());
        adresa_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getAdresa());
        kontakt_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getKontakt());
        imepsa_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getImepsa());
        pasmina_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getPasmina());
        starost_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getStarost());
        vetlokacija_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getVetlokacija());
        boja_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getBoja());

        napomena_myreports_missingdogs.getEditText().setText(reportsonmymissingdogs_adapter.getNapomena());
        Date date = new Date(reportsonmymissingdogs_adapter.getPostavljeno());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        postavljeno_myreports_missingdogs.getEditText().setText(format.format(date));
        if(reportsonmymissingdogs_adapter.getUrl()!=null) {
            slika_myreports_missingdogs.setVisibility(View.VISIBLE);
            Picasso.get().load(reportsonmymissingdogs_adapter.getUrl()).into(slika_myreports_missingdogs);
        }
        else{
            slika_myreports_missingdogs.setVisibility(View.GONE);
        }

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        datum=reportsonmymissingdogs_adapter.getDatumizgubljen();
        materialDateBuilder.setSelection(reportsonmymissingdogs_adapter.getDatumizgubljen().getTime()+2*HOUR);
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
        return view;
    }
}
