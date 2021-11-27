package com.alok.appathonsimplyinterior.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alok.appathonsimplyinterior.R;
import com.alok.appathonsimplyinterior.activities.FurnitureTypeActivity;
import com.alok.appathonsimplyinterior.activities.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class MeasurementInputActivity extends AppCompatActivity {

    EditText length, width, height;
    ConstraintLayout haveMeasureLayout;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_input);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        haveMeasureLayout = findViewById(R.id.haveMeasurementLayout);
        backButton = findViewById(R.id.backButton);
        AnimationDrawable animationDrawable = (AnimationDrawable) haveMeasureLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        length = findViewById(R.id.length);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);

        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    public void backButton(View view) {
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
        finish();
    }

    public void onMeasurementClick(View view) {
        if (length.getText().toString().isEmpty() || width.getText().toString().isEmpty() || height.getText().toString().isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Fields cannot be empty.", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(getApplicationContext(), FurnitureTypeActivity.class);
            i.putExtra("length", length.getText().toString());
            i.putExtra("width", width.getText().toString());
            i.putExtra("height", height.getText().toString());
            startActivity(i);
        }
    }
}