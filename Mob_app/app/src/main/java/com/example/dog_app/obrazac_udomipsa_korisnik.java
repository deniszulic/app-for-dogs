package com.example.dog_app;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class obrazac_udomipsa_korisnik extends Fragment {
    private TextInputLayout ime_udomipsa_korisnik, prezime_udomipsa_korisnik,
            adresa_udomipsa_korisnik, telefonskibr_udomipsa_korisnik, grad_udomipsa_korisnik,
            postnum_udomipsa_korisnik, boja_udomipsa_korisnik, starost_udomipsa_korisnik,
            vet_lokacija_udomipsa_korisnik, imepsa_udomipsa_korisnik,
            pasmina_udomipsa_korisnik, napomena_udomipsa_korisnik, kilaza_udomipsa_korisnik;
    private AutoCompleteTextView dlaka_udomipsa_korisnik, spol_udomipsa_korisnik, kastrat_udomipsa_korisnik,
            opasnazivotinja_udomipsa_korisnik;
    private AppCompatButton posalji_obrazac_udomipsa_korisnik,
            upload_slike_btn;
    private ImageView slikapsa_udomipsa_korisnik;
    private ProgressBar progressBar;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageurl;
    private String getemail;
    private int getid;
    private boolean isAllFieldsChecked = false;
    private StorageReference storageReference;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public obrazac_udomipsa_korisnik() {
        // Required empty public constructor
    }
    public static obrazac_udomipsa_korisnik newInstance(String param1, String param2) {
        obrazac_udomipsa_korisnik fragment = new obrazac_udomipsa_korisnik();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_obrazac_udomipsa_korisnik, container, false);
        ime_udomipsa_korisnik=v.findViewById(R.id.ime_udomipsa_korisnik);
        prezime_udomipsa_korisnik=v.findViewById(R.id.prezime_udomipsa_korisnik);
        adresa_udomipsa_korisnik=v.findViewById(R.id.adresa_udomipsa_korisnik);
        telefonskibr_udomipsa_korisnik=v.findViewById(R.id.telefonskibr_udomipsa_korisnik);
        grad_udomipsa_korisnik=v.findViewById(R.id.grad_udomipsa_korisnik);
        postnum_udomipsa_korisnik=v.findViewById(R.id.postnum_udomipsa_korisnik);
        boja_udomipsa_korisnik=v.findViewById(R.id.boja_udomipsa_korisnik);
        starost_udomipsa_korisnik=v.findViewById(R.id.starost_udomipsa_korisnik);
        vet_lokacija_udomipsa_korisnik=v.findViewById(R.id.vet_lokacija_udomipsa_korisnik);
        imepsa_udomipsa_korisnik=v.findViewById(R.id.imepsa_udomipsa_korisnik);
        pasmina_udomipsa_korisnik=v.findViewById(R.id.pasmina_udomipsa_korisnik);
        napomena_udomipsa_korisnik=v.findViewById(R.id.napomena_udomipsa_korisnik);
        dlaka_udomipsa_korisnik=v.findViewById(R.id.dlaka_udomipsa_korisnik);
        spol_udomipsa_korisnik=v.findViewById(R.id.spol_udomipsa_korisnik);
        posalji_obrazac_udomipsa_korisnik=v.findViewById(R.id.posalji_obrazac_udomipsa_korisnik);
        upload_slike_btn=v.findViewById(R.id.upload_slike_btn);
        slikapsa_udomipsa_korisnik=v.findViewById(R.id.slikapsa_udomipsa_korisnik);
        progressBar = (ProgressBar) v.findViewById(R.id.progres_udomipsa_korisnik);
        opasnazivotinja_udomipsa_korisnik=(AutoCompleteTextView) v.findViewById(R.id.opasnazivotinja_udomipsa_korisnik);
        kastrat_udomipsa_korisnik=(AutoCompleteTextView) v.findViewById(R.id.kastrat_udomipsa_korisnik);
        kilaza_udomipsa_korisnik=v.findViewById(R.id.kilaza_udomipsa_korisnik);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        upload_slike_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openFileChooser();
            }
        });

        String[] kastrat = {"Da", "Ne"};
        ArrayAdapter arrayAdapterkastrat = new ArrayAdapter(getActivity(), R.layout.dropdown_item, kastrat);
        kastrat_udomipsa_korisnik.setText(arrayAdapterkastrat.getItem(0).toString(), false);
        kastrat_udomipsa_korisnik.setAdapter(arrayAdapterkastrat);

        String[] opasnazivotinja = {"Da", "Ne"};
        ArrayAdapter arrayAdapteropasnazivotinja = new ArrayAdapter(getActivity(), R.layout.dropdown_item, opasnazivotinja);
        opasnazivotinja_udomipsa_korisnik.setText(arrayAdapteropasnazivotinja.getItem(0).toString(), false);
        opasnazivotinja_udomipsa_korisnik.setAdapter(arrayAdapteropasnazivotinja);

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_udomipsa_korisnik.setText(arrayAdapter.getItem(0).toString(), false);
        dlaka_udomipsa_korisnik.setAdapter(arrayAdapter);

        String[] spol = {"M", "??"};
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_udomipsa_korisnik.setText(arrayAdapter1.getItem(0).toString(), false);
        spol_udomipsa_korisnik.setAdapter(arrayAdapter1);

        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        getemail=sp1.getString("email", null);
        getid=sp1.getInt("id", 0);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        final MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();

        posalji_obrazac_udomipsa_korisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ime_udomipsa_korisnik.setError(null);
                prezime_udomipsa_korisnik.setError(null);
                adresa_udomipsa_korisnik.setError(null);
                telefonskibr_udomipsa_korisnik.setError(null);
                grad_udomipsa_korisnik.setError(null);
                postnum_udomipsa_korisnik.setError(null);
                boja_udomipsa_korisnik.setError(null);
                starost_udomipsa_korisnik.setError(null);
                vet_lokacija_udomipsa_korisnik.setError(null);
                imepsa_udomipsa_korisnik.setError(null);
                pasmina_udomipsa_korisnik.setError(null);
                kilaza_udomipsa_korisnik.setError(null);
                isAllFieldsChecked = checkfields();
                if(imageurl!=null && isAllFieldsChecked){
                    storageReference = FirebaseStorage.getInstance().getReference("adopt_dog/"+getemail);
                    uploadfile();
                }
                else if(isAllFieldsChecked){
                    Date date = new Date();
                    long timestamp = date.getTime();
                    adopteddogdata data=new adopteddogdata(ime_udomipsa_korisnik.getEditText().getText().toString(), prezime_udomipsa_korisnik.getEditText().getText().toString(), adresa_udomipsa_korisnik.getEditText().getText().toString(), grad_udomipsa_korisnik.getEditText().getText().toString(), Integer.parseInt(postnum_udomipsa_korisnik.getEditText().getText().toString()), boja_udomipsa_korisnik.getEditText().getText().toString(), Integer.parseInt(starost_udomipsa_korisnik.getEditText().getText().toString()), dlaka_udomipsa_korisnik.getText().toString(), vet_lokacija_udomipsa_korisnik.getEditText().getText().toString(), imepsa_udomipsa_korisnik.getEditText().getText().toString(), spol_udomipsa_korisnik.getText().toString(), napomena_udomipsa_korisnik.getEditText().getText().toString(), timestamp, sp1.getInt("id", 0), telefonskibr_udomipsa_korisnik.getEditText().getText().toString(), pasmina_udomipsa_korisnik.getEditText().getText().toString(), Integer.parseInt(kilaza_udomipsa_korisnik.getEditText().getText().toString()), kastrat_udomipsa_korisnik.getText().toString(), opasnazivotinja_udomipsa_korisnik.getText().toString(), true, null, false);
                    Call<Void> call = retrofitInterface.adoptdog(data);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201) {
                                try{
                                    ime_udomipsa_korisnik.getEditText().getText().clear();
                                    prezime_udomipsa_korisnik.getEditText().getText().clear();
                                    adresa_udomipsa_korisnik.getEditText().getText().clear();
                                    grad_udomipsa_korisnik.getEditText().getText().clear();
                                    postnum_udomipsa_korisnik.getEditText().getText().clear();
                                    boja_udomipsa_korisnik.getEditText().getText().clear();
                                    starost_udomipsa_korisnik.getEditText().getText().clear();
                                    vet_lokacija_udomipsa_korisnik.getEditText().getText().clear();
                                    imepsa_udomipsa_korisnik.getEditText().getText().clear();
                                    napomena_udomipsa_korisnik.getEditText().getText().clear();
                                    telefonskibr_udomipsa_korisnik.getEditText().getText().clear();
                                    pasmina_udomipsa_korisnik.getEditText().getText().clear();
                                    kilaza_udomipsa_korisnik.getEditText().getText().clear();
                                }catch(Exception e){System.out.println(e);}
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return v;
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageurl = data.getData();
            Picasso.get().load(imageurl).into(slikapsa_udomipsa_korisnik);
        }
    }

    private String getfileextension(Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private boolean checkfields() {
        if (TextUtils.isEmpty(ime_udomipsa_korisnik.getEditText().getText().toString())) {
            ime_udomipsa_korisnik.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_udomipsa_korisnik.getEditText().getText().toString())) {
            prezime_udomipsa_korisnik.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(adresa_udomipsa_korisnik.getEditText().getText().toString())) {
            adresa_udomipsa_korisnik.setError("Potrebna je adresa!");
            return false;
        }
        if (TextUtils.isEmpty(telefonskibr_udomipsa_korisnik.getEditText().getText().toString())) {
            telefonskibr_udomipsa_korisnik.setError("Potreban je telefonski broj!");
            return false;
        }
        if (TextUtils.isEmpty(grad_udomipsa_korisnik.getEditText().getText().toString())) {
            grad_udomipsa_korisnik.setError("Potreban je grad!");
            return false;
        }
        if (TextUtils.isEmpty(postnum_udomipsa_korisnik.getEditText().getText().toString())) {
            postnum_udomipsa_korisnik.setError("Potreban je po??tanski broj!");
            return false;
        }
        if (TextUtils.isEmpty(boja_udomipsa_korisnik.getEditText().getText().toString())) {
            boja_udomipsa_korisnik.setError("Potrebna je boja psa!");
            return false;
        }
        if (TextUtils.isEmpty(starost_udomipsa_korisnik.getEditText().getText().toString())) {
            starost_udomipsa_korisnik.setError("Potrebna je starost psa!");
            return false;
        }
        if (TextUtils.isEmpty(vet_lokacija_udomipsa_korisnik.getEditText().getText().toString())) {
            vet_lokacija_udomipsa_korisnik.setError("Potrebna je veterinarska lokacija!");
            return false;
        }
        if (TextUtils.isEmpty(imepsa_udomipsa_korisnik.getEditText().getText().toString())) {
            imepsa_udomipsa_korisnik.setError("Potrebno je ime psa!");
            return false;
        }
        if (TextUtils.isEmpty(kilaza_udomipsa_korisnik.getEditText().getText().toString())) {
            kilaza_udomipsa_korisnik.setError("Potrebna je kila??a!");
            return false;
        }
        else if (TextUtils.isEmpty(pasmina_udomipsa_korisnik.getEditText().getText().toString())) {
            pasmina_udomipsa_korisnik.setError("Potrebna je pasmina!");
            return false;
        }
        return true;
    }
    private void uploadfile() {
        if (imageurl != null) {
            StorageReference filereference = storageReference.child(System.currentTimeMillis() + "." + getfileextension(imageurl));
            UploadTask uploadTask = filereference.putFile(imageurl);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return filereference.getDownloadUrl();
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },5000);
                    Toast.makeText(getActivity(), "Uspje??no poslani podaci", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Upload upload=new Upload(downloadUri.toString());
                        Date date = new Date();
                        long timestamp = date.getTime();
                        adopteddogdata data=new adopteddogdata(ime_udomipsa_korisnik.getEditText().getText().toString(), prezime_udomipsa_korisnik.getEditText().getText().toString(), adresa_udomipsa_korisnik.getEditText().getText().toString(), grad_udomipsa_korisnik.getEditText().getText().toString(), Integer.parseInt(postnum_udomipsa_korisnik.getEditText().getText().toString()), boja_udomipsa_korisnik.getEditText().getText().toString(), Integer.parseInt(starost_udomipsa_korisnik.getEditText().getText().toString()), dlaka_udomipsa_korisnik.getText().toString(), vet_lokacija_udomipsa_korisnik.getEditText().getText().toString(), imepsa_udomipsa_korisnik.getEditText().getText().toString(), spol_udomipsa_korisnik.getText().toString(), napomena_udomipsa_korisnik.getEditText().getText().toString(), timestamp, getid, telefonskibr_udomipsa_korisnik.getEditText().getText().toString(), pasmina_udomipsa_korisnik.getEditText().getText().toString(), Integer.parseInt(kilaza_udomipsa_korisnik.getEditText().getText().toString()), kastrat_udomipsa_korisnik.getText().toString(), opasnazivotinja_udomipsa_korisnik.getText().toString(), true, upload.getImageurl(), false);
                        Call<Void> call=retrofitInterface.adoptdog(data);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                try{
                                    ime_udomipsa_korisnik.getEditText().getText().clear();
                                    prezime_udomipsa_korisnik.getEditText().getText().clear();
                                    adresa_udomipsa_korisnik.getEditText().getText().clear();
                                    grad_udomipsa_korisnik.getEditText().getText().clear();
                                    postnum_udomipsa_korisnik.getEditText().getText().clear();
                                    boja_udomipsa_korisnik.getEditText().getText().clear();
                                    starost_udomipsa_korisnik.getEditText().getText().clear();
                                    vet_lokacija_udomipsa_korisnik.getEditText().getText().clear();
                                    imepsa_udomipsa_korisnik.getEditText().getText().clear();
                                    napomena_udomipsa_korisnik.getEditText().getText().clear();
                                    telefonskibr_udomipsa_korisnik.getEditText().getText().clear();
                                    pasmina_udomipsa_korisnik.getEditText().getText().clear();
                                    kilaza_udomipsa_korisnik.getEditText().getText().clear();
                                    imageurl=null;
                                    Picasso.get().load(imageurl).into(slikapsa_udomipsa_korisnik);
                                }catch(Exception e){System.out.println(e);}
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress= (100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                }
            });
        }
    }
}