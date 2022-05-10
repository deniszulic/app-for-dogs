package com.example.dog_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sp1=this.getSharedPreferences("userdata", MODE_PRIVATE);

        String getemail=sp1.getString("email", null);
        String getusertype = sp1.getString("tipkorisnika", null);
        System.out.println("Email:"+getemail+" tipkorisnika:"+getusertype);
        if(getusertype!=null && getemail!=null) {
//            if (getusertype.equals("Poduzeće")) {
//                Intent i = new Intent(MainActivity.this, pocetni_zaslon_poduzece.class);
//                startActivity(i);
//                MainActivity.this.finish();
//            }
            if (getusertype.equals("korisnik")) {
                Intent i = new Intent(MainActivity.this, pocetni_zaslon_korisnik.class);
                startActivity(i);
                MainActivity.this.finish();
            }
            if (getusertype.equals("azil")) {
                Intent i = new Intent(MainActivity.this, pocetni_zaslon_azil.class);
                startActivity(i);
                MainActivity.this.finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item){
                    Fragment selectedFragment=null;
                    switch(item.getItemId()){
                        case R.id.login:
                            selectedFragment = new login();
                            break;
                        case R.id.register:
                            selectedFragment = new register();
                            break;
                        case R.id.home:
                            selectedFragment = new home();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}