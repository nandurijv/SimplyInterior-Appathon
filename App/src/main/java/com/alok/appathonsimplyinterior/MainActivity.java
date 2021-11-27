package com.alok.appathonsimplyinterior;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private DatabaseReference db;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);

        AnimationDrawable animationDrawable = (AnimationDrawable) drawerLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

//    public void haveMeasurement(View view) {
//        Intent home = new Intent(getApplicationContext(), com.alok.simplyinteriorhciproject.MeasurementInputActivity.class);
//        startActivity(home);
//        finish();
//    }

    public void takeMeasurement(View view) {
//        Intent i = new Intent(Intent.ACTION_MAIN);
//        PackageManager managerclock = getPackageManager();
//        i = managerclock.getLaunchIntentForPackage("com.ThamanMuthappa.ARRuler2");
//        i.addCategory(Intent.CATEGORY_LAUNCHER);
//        startActivity(i);
//        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, com.alok.simplyinteriorhciproject.login_signup.class));
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu:
                Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_LONG).show();
                break;
            case R.id.logout_menu:
                Intent about = new Intent(getApplicationContext(), com.alok.simplyinteriorhciproject.MeasurementInputActivity.class);
                startActivity(about);
                break;
        }
        return true;
    }
}