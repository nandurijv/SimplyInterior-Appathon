package com.alok.appathonsimplyinterior;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TypeOfFurniture extends AppCompatActivity {

    ConstraintLayout typeOfFurniture;
    String l, b, h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_furniture);

        typeOfFurniture = findViewById(R.id.typeOfFurnitureLayout);

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
        Intent home = new Intent(getApplicationContext(), com.alok.appathonsimplyinterior.MeasurementInputActivity.class);
        startActivity(home);
        finish();
    }

    public void sofaCardSelected(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResult.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","sofa");
        startActivity(i);
    }

    public void sofaCardSelected1(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResult.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","desk");
        startActivity(i);
    }
    public void sofaCardSelected2(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResult.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","drawer");
        startActivity(i);
    }
    public void sofaCardSelected3(View view) {
        Intent i = new Intent(getApplicationContext(), FurnitureResult.class);
        i.putExtra("length", l);
        i.putExtra("width",b);
        i.putExtra("height",h);
        i.putExtra("type","wardrobe");
        startActivity(i);
    }
}