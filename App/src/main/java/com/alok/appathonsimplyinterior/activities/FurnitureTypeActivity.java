package com.alok.appathonsimplyinterior.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alok.appathonsimplyinterior.R;

public class FurnitureTypeActivity extends AppCompatActivity {

    ConstraintLayout typeOfFurniture;
    String l, b, h;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_furniture);

        typeOfFurniture = findViewById(R.id.typeOfFurnitureLayout);
        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(view -> {
            onBackPressed();
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) typeOfFurniture.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Intent intent = this.getIntent();
        l = intent.getStringExtra("length");
        b = intent.getStringExtra("width");
        h = intent.getStringExtra("height");

    }


    public void backButton(View view) {
        Intent home = new Intent(getApplicationContext(), MeasurementInputActivity.class);
        startActivity(home);
        finish();
    }

    public void sofaCardSelected(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResultActivity.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","sofa");
        startActivity(i);
    }

    public void sofaCardSelected1(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResultActivity.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","desk");
        startActivity(i);
    }
    public void sofaCardSelected2(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResultActivity.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","drawer");
        startActivity(i);
    }
    public void sofaCardSelected3(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResultActivity.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","wardrobe");
        startActivity(i);
    }
}