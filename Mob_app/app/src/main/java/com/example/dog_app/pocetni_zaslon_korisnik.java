package com.example.dog_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class pocetni_zaslon_korisnik extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView email_header, tipkoisnika;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_zaslon_korisnik);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences a=this.getSharedPreferences("userdata", MODE_PRIVATE);
        String email=a.getString("email", null);
        String usertype=a.getString("tipkorisnika", null);
        //email_header.setText(email);
        View headerView = navigationView.getHeaderView(0);
        email_header=(TextView) headerView.findViewById(R.id.email_header);
        tipkoisnika =(TextView) headerView.findViewById(R.id.typeofuser_header);
        email_header.setText(email);
        tipkoisnika.setText(usertype);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new obrazac_nestalipsi_korisnik()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new obrazac_nestalipsi_korisnik()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new nestalipsi_oglasi_korisnik()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this,"Send",Toast.LENGTH_SHORT).show();
                SharedPreferences settings = getSharedPreferences("userdata", MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent i=new Intent(pocetni_zaslon_korisnik.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}