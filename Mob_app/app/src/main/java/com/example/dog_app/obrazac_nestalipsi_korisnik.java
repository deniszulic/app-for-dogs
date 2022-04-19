package com.example.dog_app;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
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
import android.util.Log;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class obrazac_nestalipsi_korisnik extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextInputLayout ime_nestalipsi_korisnik, prezime_nestalipsi_korisnik,
            adresa_nestalipsi_korisnik, telefonskibr_nestalipsi_korisnik, grad_nestalipsi_korisnik,
            postnum_nestalipsi_korisnik, boja_nestalipsi_korisnik, starost_nestalipsi_korisnik,
            vet_lokacija_nestalipsi_korisnik, imepsa_nestalipsi_korisnik,
            pasmina_nestalipsi_korisnik, napomena_nestalipsi_korisnik;
    private AutoCompleteTextView dlaka_nestalipsi_korisnik, spol_nestalipsi_korisnik;
    private AppCompatButton datumizgubljen_nestalipsi_korisnik, posalji_obrazac_nestalipsi_korisnik,
            upload_slike_btn;
    private String datum;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageurl;
    private ImageView slikapsa_nestalipsi_korisnik;
    private StorageReference storageReference;
    private ProgressBar progressBar;
    private String getemail;
    private int getid;
    private boolean isAllFieldsChecked = false;

    private String mParam1;
    private String mParam2;

    public obrazac_nestalipsi_korisnik() {
        // Required empty public constructor
    }

    public static obrazac_nestalipsi_korisnik newInstance(String param1, String param2) {
        obrazac_nestalipsi_korisnik fragment = new obrazac_nestalipsi_korisnik();
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_obrazac_nestalipsi_korisnik, container, false);
        View v=inflater.inflate(R.layout.fragment_obrazac_nestalipsi_korisnik, container, false);
        ime_nestalipsi_korisnik=v.findViewById(R.id.ime_nestalipsi_korisnik);
        prezime_nestalipsi_korisnik=v.findViewById(R.id.prezime_nestalipsi_korisnik);
        adresa_nestalipsi_korisnik=v.findViewById(R.id.adresa_nestalipsi_korisnik);
        telefonskibr_nestalipsi_korisnik=v.findViewById(R.id.telefonskibr_nestalipsi_korisnik);
        grad_nestalipsi_korisnik=v.findViewById(R.id.grad_nestalipsi_korisnik);
        postnum_nestalipsi_korisnik=v.findViewById(R.id.postnum_nestalipsi_korisnik);
        boja_nestalipsi_korisnik=v.findViewById(R.id.boja_nestalipsi_korisnik);
        starost_nestalipsi_korisnik=v.findViewById(R.id.starost_nestalipsi_korisnik);
        vet_lokacija_nestalipsi_korisnik=v.findViewById(R.id.vet_lokacija_nestalipsi_korisnik);
        imepsa_nestalipsi_korisnik=v.findViewById(R.id.imepsa_nestalipsi_korisnik);
        pasmina_nestalipsi_korisnik=v.findViewById(R.id.pasmina_nestalipsi_korisnik);
        napomena_nestalipsi_korisnik=v.findViewById(R.id.napomena_nestalipsi_korisnik);
        dlaka_nestalipsi_korisnik=v.findViewById(R.id.dlaka_nestalipsi_korisnik);
        spol_nestalipsi_korisnik=v.findViewById(R.id.spol_nestalipsi_korisnik);
        datumizgubljen_nestalipsi_korisnik=v.findViewById(R.id.datumizgubljen_nestalipsi_korisnik);
        posalji_obrazac_nestalipsi_korisnik=v.findViewById(R.id.posalji_obrazac_nestalipsi_korisnik);
        upload_slike_btn=v.findViewById(R.id.upload_slike_btn);
        slikapsa_nestalipsi_korisnik=v.findViewById(R.id.slikapsa_nestalipsi_korisnik);
        progressBar = (ProgressBar) v.findViewById(R.id.progres_nestalipsi_korisnik);
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

        String[] dlaka = {"Kratka", "Duga"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, dlaka);
        dlaka_nestalipsi_korisnik.setText(arrayAdapter.getItem(0).toString(), false);
        dlaka_nestalipsi_korisnik.setAdapter(arrayAdapter);

        String[] spol = {"M", "Ž"};
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getActivity(), R.layout.dropdown_item, spol);
        spol_nestalipsi_korisnik.setText(arrayAdapter1.getItem(0).toString(), false);
        spol_nestalipsi_korisnik.setAdapter(arrayAdapter1);

        SharedPreferences sp1=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        getemail=sp1.getString("email", null);
        getid=sp1.getInt("id", 0);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Datum kada je pas izgubljen");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        datumizgubljen_nestalipsi_korisnik.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        datum = materialDatePicker.getHeaderText();
                        System.out.println("datum:" + datum);
                    }
                });
        posalji_obrazac_nestalipsi_korisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ime_nestalipsi_korisnik.setError(null);
                prezime_nestalipsi_korisnik.setError(null);
                adresa_nestalipsi_korisnik.setError(null);
                telefonskibr_nestalipsi_korisnik.setError(null);
                grad_nestalipsi_korisnik.setError(null);
                postnum_nestalipsi_korisnik.setError(null);
                boja_nestalipsi_korisnik.setError(null);
                starost_nestalipsi_korisnik.setError(null);
                vet_lokacija_nestalipsi_korisnik.setError(null);
                imepsa_nestalipsi_korisnik.setError(null);
                datumizgubljen_nestalipsi_korisnik.setError(null);
                pasmina_nestalipsi_korisnik.setError(null);
                isAllFieldsChecked = checkfields();
                if(imageurl!=null && isAllFieldsChecked){
                    storageReference = FirebaseStorage.getInstance().getReference("missing_dogs/"+getemail);
                    uploadfile();
                }
                else if(isAllFieldsChecked){
                    Date date = new Date();
                    long timestamp = date.getTime();
                    missingdogsdata data = new missingdogsdata(ime_nestalipsi_korisnik.getEditText().getText().toString(), prezime_nestalipsi_korisnik.getEditText().getText().toString(), adresa_nestalipsi_korisnik.getEditText().getText().toString(), grad_nestalipsi_korisnik.getEditText().getText().toString(), Integer.parseInt(postnum_nestalipsi_korisnik.getEditText().getText().toString()), boja_nestalipsi_korisnik.getEditText().getText().toString(), Integer.parseInt(starost_nestalipsi_korisnik.getEditText().getText().toString()), dlaka_nestalipsi_korisnik.getText().toString(), vet_lokacija_nestalipsi_korisnik.getEditText().getText().toString(), imepsa_nestalipsi_korisnik.getEditText().getText().toString(), spol_nestalipsi_korisnik.getText().toString(), datum, napomena_nestalipsi_korisnik.getEditText().getText().toString(), timestamp, sp1.getInt("id", 0), telefonskibr_nestalipsi_korisnik.getEditText().getText().toString(), pasmina_nestalipsi_korisnik.getEditText().getText().toString(), true, false, null);
                    Call<Void> call = retrofitInterface.missingdog(data);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            ime_nestalipsi_korisnik.getEditText().getText().clear();
                            prezime_nestalipsi_korisnik.getEditText().getText().clear();
                            adresa_nestalipsi_korisnik.getEditText().getText().clear();
                            grad_nestalipsi_korisnik.getEditText().getText().clear();
                            postnum_nestalipsi_korisnik.getEditText().getText().clear();
                            boja_nestalipsi_korisnik.getEditText().getText().clear();
                            starost_nestalipsi_korisnik.getEditText().getText().clear();
                            vet_lokacija_nestalipsi_korisnik.getEditText().getText().clear();
                            imepsa_nestalipsi_korisnik.getEditText().getText().clear();
                            datum=null;
                            napomena_nestalipsi_korisnik.getEditText().getText().clear();
                            telefonskibr_nestalipsi_korisnik.getEditText().getText().clear();
                            pasmina_nestalipsi_korisnik.getEditText().getText().clear();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

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
            Picasso.get().load(imageurl).into(slikapsa_nestalipsi_korisnik);
        }
    }

    private String getfileextension(Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
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

                    // Continue with the task to get the download URL
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
                    Toast.makeText(getActivity(), "Uspješno", Toast.LENGTH_LONG).show();
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
                        missingdogsdata data= new missingdogsdata(ime_nestalipsi_korisnik.getEditText().getText().toString(), prezime_nestalipsi_korisnik.getEditText().getText().toString(), adresa_nestalipsi_korisnik.getEditText().getText().toString(), grad_nestalipsi_korisnik.getEditText().getText().toString(), Integer.parseInt(postnum_nestalipsi_korisnik.getEditText().getText().toString()), boja_nestalipsi_korisnik.getEditText().getText().toString(), Integer.parseInt(starost_nestalipsi_korisnik.getEditText().getText().toString()), dlaka_nestalipsi_korisnik.getText().toString(), vet_lokacija_nestalipsi_korisnik.getEditText().getText().toString(), imepsa_nestalipsi_korisnik.getEditText().getText().toString(), spol_nestalipsi_korisnik.getText().toString(), datum, napomena_nestalipsi_korisnik.getEditText().getText().toString(), timestamp, getid, telefonskibr_nestalipsi_korisnik.getEditText().getText().toString(), pasmina_nestalipsi_korisnik.getEditText().getText().toString(), true, false, upload.getImageurl());
                        Call<Void> call=retrofitInterface.missingdog(data);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                ime_nestalipsi_korisnik.getEditText().getText().clear();
                                prezime_nestalipsi_korisnik.getEditText().getText().clear();
                                adresa_nestalipsi_korisnik.getEditText().getText().clear();
                                grad_nestalipsi_korisnik.getEditText().getText().clear();
                                postnum_nestalipsi_korisnik.getEditText().getText().clear();
                                boja_nestalipsi_korisnik.getEditText().getText().clear();
                                starost_nestalipsi_korisnik.getEditText().getText().clear();
                                vet_lokacija_nestalipsi_korisnik.getEditText().getText().clear();
                                imepsa_nestalipsi_korisnik.getEditText().getText().clear();
                                datum=null;
                                napomena_nestalipsi_korisnik.getEditText().getText().clear();
                                telefonskibr_nestalipsi_korisnik.getEditText().getText().clear();
                                pasmina_nestalipsi_korisnik.getEditText().getText().clear();
                                imageurl=null;
                                Picasso.get().load(imageurl).into(slikapsa_nestalipsi_korisnik);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
//                    else {
//                    }
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
//        else {
//
//        }
    }
    private boolean checkfields() {
        if (TextUtils.isEmpty(ime_nestalipsi_korisnik.getEditText().getText().toString())) {
            ime_nestalipsi_korisnik.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_nestalipsi_korisnik.getEditText().getText().toString())) {
            prezime_nestalipsi_korisnik.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(adresa_nestalipsi_korisnik.getEditText().getText().toString())) {
            adresa_nestalipsi_korisnik.setError("Potrebna je adresa!");
            return false;
        }
        if (TextUtils.isEmpty(telefonskibr_nestalipsi_korisnik.getEditText().getText().toString())) {
            telefonskibr_nestalipsi_korisnik.setError("Potreban je telefonski broj!");
            return false;
        }
        if (TextUtils.isEmpty(grad_nestalipsi_korisnik.getEditText().getText().toString())) {
            grad_nestalipsi_korisnik.setError("Potreban je grad!");
            return false;
        }
        if (TextUtils.isEmpty(postnum_nestalipsi_korisnik.getEditText().getText().toString())) {
            postnum_nestalipsi_korisnik.setError("Potreban je poštanski broj!");
            return false;
        }
        if (TextUtils.isEmpty(boja_nestalipsi_korisnik.getEditText().getText().toString())) {
            boja_nestalipsi_korisnik.setError("Potrebna je boja psa!");
            return false;
        }
        if (TextUtils.isEmpty(starost_nestalipsi_korisnik.getEditText().getText().toString())) {
            starost_nestalipsi_korisnik.setError("Potrebna je starost psa!");
            return false;
        }
        if (TextUtils.isEmpty(vet_lokacija_nestalipsi_korisnik.getEditText().getText().toString())) {
            vet_lokacija_nestalipsi_korisnik.setError("Potrebna je veterinarska lokacija!");
            return false;
        }
        if (TextUtils.isEmpty(imepsa_nestalipsi_korisnik.getEditText().getText().toString())) {
            imepsa_nestalipsi_korisnik.setError("Potrebno je ime psa!");
            return false;
        }
        if(datum==null){
            datumizgubljen_nestalipsi_korisnik.setError("Potreban je datum");
            return false;
        }
        else if (TextUtils.isEmpty(pasmina_nestalipsi_korisnik.getEditText().getText().toString())) {
            pasmina_nestalipsi_korisnik.setError("Potrebna je pasmina!");
            return false;
        }
        return true;
    }
}