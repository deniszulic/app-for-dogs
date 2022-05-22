package com.example.dog_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//implements ExampleBottomSheetDialog.BottomSheetListener
public class register extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private FirebaseAuth mAuth;
    //private EditText email, password;
    // private Button register_btn;
    private TextInputLayout email, password, ime_reg, prezime_reg, oib, naziv, ulica_reg, kucnibr_reg, grad_reg, postnum_reg;
    private AppCompatButton register_btn, login_transition;
    //private ImageView imageView;
    private TextView textView2;
    //private ProgressBar progres_registracija;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageurl;
    private AutoCompleteTextView autoCompleteTextView;
    private boolean isAllFieldsChecked = false;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";

    public register() {
        // Required empty public constructor
    }

    public static register newInstance(String param1, String param2) {
        register fragment = new register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        email = (TextInputLayout) view.findViewById(R.id.email_reg);
        password = (TextInputLayout) view.findViewById(R.id.pass_reg);
        register_btn = (AppCompatButton) view.findViewById(R.id.register_btn);
        login_transition = (AppCompatButton) view.findViewById(R.id.login_transition);
        //imageView = (ImageView) view.findViewById(R.id.imageView_reg);
        textView2 = (TextView) view.findViewById(R.id.textView2_reg);
        ime_reg = (TextInputLayout) view.findViewById(R.id.ime_reg);
        prezime_reg = (TextInputLayout) view.findViewById(R.id.prezime_reg);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.autoComplete_reg);
        oib = (TextInputLayout) view.findViewById(R.id.oib_reg);
        naziv = (TextInputLayout) view.findViewById(R.id.naziv_reg);
        ulica_reg= (TextInputLayout) view.findViewById(R.id.ulica_reg);
        kucnibr_reg=(TextInputLayout) view.findViewById(R.id.kucnibr_reg);
        grad_reg=(TextInputLayout) view.findViewById(R.id.grad_reg);
        postnum_reg=(TextInputLayout) view.findViewById(R.id.postnum_reg);

        String[] usertype = {"Korisnik", "Azil"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, usertype);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);
        //db=FirebaseFirestore.getInstance();
        if (autoCompleteTextView.getText().toString().equals("Korisnik")) {
            oib.setVisibility(View.GONE);
            naziv.setVisibility(View.GONE);
            ulica_reg.setVisibility(View.GONE);
            kucnibr_reg.setVisibility(View.GONE);
            grad_reg.setVisibility(View.GONE);
            postnum_reg.setVisibility(View.GONE);
        }
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (autoCompleteTextView.getText().toString().equals("Korisnik")) {
                    oib.setVisibility(View.GONE);
                    naziv.setVisibility(View.GONE);
                    ulica_reg.setVisibility(View.GONE);
                    kucnibr_reg.setVisibility(View.GONE);
                    grad_reg.setVisibility(View.GONE);
                    postnum_reg.setVisibility(View.GONE);
                }
                if (autoCompleteTextView.getText().toString().equalsIgnoreCase("Azil")) {
                    oib.setVisibility(View.VISIBLE);
                    naziv.setVisibility(View.VISIBLE);
                    ulica_reg.setVisibility(View.VISIBLE);
                    kucnibr_reg.setVisibility(View.VISIBLE);
                    grad_reg.setVisibility(View.VISIBLE);
                    postnum_reg.setVisibility(View.VISIBLE);
                }
            }
        });
        login_transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), transition_login.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoCompleteTextView.getText().toString().equals("Korisnik")) {
                isAllFieldsChecked = checkfields();
                if(isAllFieldsChecked && password.getEditText().getText().length()>=8){
                    Date date = new Date();
                    long timestamp = date.getTime();
                    registerdata data=new registerdata(ime_reg.getEditText().getText().toString(), email.getEditText().getText().toString(), password.getEditText().getText().toString(), prezime_reg.getEditText().getText().toString(), "korisnik", timestamp);
                    Call<Void> call=retrofitInterface.postJson(data);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code()==201){
                                ime_reg.getEditText().getText().clear();
                                prezime_reg.getEditText().getText().clear();
                                email.getEditText().getText().clear();
                                password.getEditText().getText().clear();
                                ExampleBottomSheetDialog dialog = new ExampleBottomSheetDialog();
                                dialog.show(getParentFragmentManager(), "exampleBottomSheet");
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            //System.out.println(t);
                            Toast.makeText(getActivity(), t.toString(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            if (autoCompleteTextView.getText().toString().equals("Azil")) {
                isAllFieldsChecked = checkfields_azil();
                if(isAllFieldsChecked && password.getEditText().getText().length()>=8){
                    Date date = new Date();
                    long timestamp = date.getTime();
                    registerdata data=new registerdata(ime_reg.getEditText().getText().toString(), email.getEditText().getText().toString(), password.getEditText().getText().toString(), prezime_reg.getEditText().getText().toString(), "azil", timestamp);

                    Call<Integer> call = retrofitInterface.register(data);

                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if (response.code() == 201) {
                                //Integer result= response.body();
                                int userid=response.body();
                                if(response.body()>=0){
                                    registershelter shelter= new registershelter(oib.getEditText().getText().toString(), ulica_reg.getEditText().getText().toString(), kucnibr_reg.getEditText().getText().toString(), grad_reg.getEditText().getText().toString(), postnum_reg.getEditText().getText().toString(), naziv.getEditText().getText().toString(), response.body());
                                    Call<Integer> regshelter =retrofitInterface.shelterregister(shelter);
                                    regshelter.enqueue(new Callback<Integer>() {
                                        @Override
                                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                            if(response.code()==201){
                                                ime_reg.getEditText().getText().clear();
                                                prezime_reg.getEditText().getText().clear();
                                                email.getEditText().getText().clear();
                                                password.getEditText().getText().clear();
                                                oib.getEditText().getText().clear();
                                                naziv.getEditText().getText().clear();
                                                ulica_reg.getEditText().getText().clear();
                                                kucnibr_reg.getEditText().getText().clear();
                                                grad_reg.getEditText().getText().clear();
                                                postnum_reg.getEditText().getText().clear();
                                                ExampleBottomSheetDialog dialog = new ExampleBottomSheetDialog();
                                                dialog.show(getParentFragmentManager(), "exampleBottomSheet");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Integer> call, Throwable t) {
                                            Call<Void> delete=retrofitInterface.deleteuser(userid);
                                            Toast.makeText(getActivity(), t.toString(),
                                                    Toast.LENGTH_LONG).show();
                                            delete.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call, Response<Void> response) {

                                                }

                                                @Override
                                                public void onFailure(Call<Void> call, Throwable t) {
                                                    Toast.makeText(getActivity(), t.toString(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            //System.out.println(t);
                            Toast.makeText(getActivity(), t.toString(),
                                    Toast.LENGTH_LONG).show();
                            System.out.println(t);
                        }
                    });
                }
            }
            }
        });
        return view;
    }

    private boolean checkfields() {
        if (TextUtils.isEmpty(ime_reg.getEditText().getText().toString())) {
            ime_reg.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_reg.getEditText().getText().toString())) {
            prezime_reg.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(email.getEditText().getText().toString())) {
            email.setError("Potreban je email!");
            return false;
        } else if (TextUtils.isEmpty(password.getEditText().getText().toString())) {
            password.setError("Potrebna je lozinka!");
            return false;
        }
        return true;
    }

    private boolean checkfields_azil() {
        if (TextUtils.isEmpty(ime_reg.getEditText().getText().toString())) {
            ime_reg.setError("Potrebno je ime!");
            return false;
        }
        if (TextUtils.isEmpty(prezime_reg.getEditText().getText().toString())) {
            prezime_reg.setError("Potrebno je prezime!");
            return false;
        }
        if (TextUtils.isEmpty(email.getEditText().getText().toString())) {
            email.setError("Potreban je email!");
            return false;
        }
        if (TextUtils.isEmpty(password.getEditText().getText().toString())) {
            password.setError("Potrebna je lozinka!");
            return false;
        }
        if (TextUtils.isEmpty(oib.getEditText().getText().toString())) {
            oib.setError("Potreban je oib!");
            return false;
        }
        if (TextUtils.isEmpty(naziv.getEditText().getText().toString())) {
            naziv.setError("Potreban je naziv azila!");
            return false;
        }
        if (TextUtils.isEmpty(ulica_reg.getEditText().getText().toString())) {
            ulica_reg.setError("Potrebna je ulica!");
            return false;
        }
        if (TextUtils.isEmpty(kucnibr_reg.getEditText().getText().toString())) {
            kucnibr_reg.setError("Potreban je kućni broj!");
            return false;
        }
        if (TextUtils.isEmpty(grad_reg.getEditText().getText().toString())) {
            grad_reg.setError("Potreban je grad!");
            return false;
        }
        else if (TextUtils.isEmpty(postnum_reg.getEditText().getText().toString())) {
            postnum_reg.setError("Potreban je poštanski broj!");
            return false;
        }
        return true;
    }

//    @Override
//    public void onButtonClicked(String text) {
//        textView2.setText(text);
//    }
}