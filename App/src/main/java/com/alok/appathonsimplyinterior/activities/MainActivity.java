package com.alok.appathonsimplyinterior.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alok.appathonsimplyinterior.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private DatabaseReference db;
    ConstraintLayout drawerLayout;
    BottomNavigationView navigationView;
    Toolbar toolbar;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        logout = findViewById(R.id.logout);
        AnimationDrawable animationDrawable = (AnimationDrawable) drawerLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginSignupActivity.class));
            finish();
            Toast.makeText(this, "Successfully Logged Out!!", Toast.LENGTH_SHORT).show();
        });

    }

    public void haveMeasurement(View view) {
        Intent home = new Intent(getApplicationContext(), MeasurementInputActivity.class);
        startActivity(home);
    }

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
            startActivity(new Intent(MainActivity.this, LoginSignupActivity.class));
            finish();
        }
    }
}