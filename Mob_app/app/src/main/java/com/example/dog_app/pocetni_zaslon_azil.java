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

public class pocetni_zaslon_azil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView email_header, tipkoisnika;
    private DrawerLayout drawer;
    private static String sendemail, usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_zaslon_azil);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences a=this.getSharedPreferences("userdata", MODE_PRIVATE);
        String email=a.getString("email", null);
        String tipkorisnika=a.getString("tipkorisnika", null);
        sendemail=email;
        usertype=tipkorisnika;
        String usertype=a.getString("tipkorisnika", null);
        View headerView = navigationView.getHeaderView(0);
        email_header=(TextView) headerView.findViewById(R.id.email_header);
        tipkoisnika =(TextView) headerView.findViewById(R.id.typeofuser_header);
        email_header.setText(email);
        tipkoisnika.setText(usertype);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new obrazac_nestalipsi_azil()).commit();
            navigationView.setCheckedItem(R.id.report_missing_dog_shelter);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.report_missing_dog_shelter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new obrazac_nestalipsi_azil()).commit();
                break;
            case R.id.user_reports_missingdog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new nestalipsi_oglasi_azil()).commit();
                break;
            case R.id.shelter_reports_missingdog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new nestalipsi_oglasi_azil_azil()).commit();
                break;
            case R.id.report_adopt_dog_shelter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new obrazac_udomipsa_azil()).commit();
                break;
            case R.id.user_reports_adoptdog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new udomipsa_oglasi_korisnik()).commit();
                break;
            case R.id.shelter_reports_adoptdog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new udomipsa_oglasi_azil()).commit();
                break;
            case R.id.my_reports_shelter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new mojioglasi_nestalipsi_azil()).commit();
                break;
            case R.id.my_reports_adoptdog_shelter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new mojioglasi_udomipsa_azil()).commit();
                break;
            case R.id.my_missingdogs_reports:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myreports_missingdogs()).commit();
                break;
            case R.id.my_missingdogs_adoptdog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myreports_adopteddogs()).commit();
                break;
            case R.id.reportsonmymy_missingdogs_shelter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new reportsonmyad_shelter()).commit();
                break;
            case R.id.reportsonmymy_adopteddogs_shelter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new reportsonmy_adopteddogs_shelter()).commit();
                break;
            case R.id.userreq_missingdog_req:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new userreq_mis_req()).commit();
                break;
            case R.id.userreq_missingdog_approved:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new userreq_mis_adopt()).commit();
                break;
            case R.id.userreq_missingdog_refused:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new userreq_mis_reject()).commit();
                break;
            case R.id.userreq_adoptdog_req:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new userreq_adoptdog_req()).commit();
                break;
            case R.id.userreq_adoptdog_approved:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new userreq_adoptdog_approve()).commit();
                break;
            case R.id.userreq_adoptdog_refused:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new userreq_adoptdog_reject()).commit();
                break;
            case R.id.logout:
                Toast.makeText(this,"Odjava",Toast.LENGTH_SHORT).show();
                SharedPreferences settings = getSharedPreferences("userdata", MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent i=new Intent(pocetni_zaslon_azil.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profil()).commit();
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

    public static String getSendemail() {
        return sendemail;
    }

    public static String getUsertype() {
        return usertype;
    }
}