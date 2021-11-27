package com.alok.appathonsimplyinterior.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alok.appathonsimplyinterior.R;
import com.alok.appathonsimplyinterior.adapters.FurnitureResultAdapter;
import com.alok.appathonsimplyinterior.models.Furniture;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FurnitureResultActivity extends AppCompatActivity {

    String l, b, h, cat;
    ConstraintLayout resultLayout;
    RecyclerView recyclerView;
    List<Furniture> furnitures;
    FurnitureResultAdapter adaptor;
    private AlertDialog loadingDialog;
    Button goBack;

    String apiurl = "https://sheetdb.io/api/v1/9j4n3euk9qvc3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_result);

        resultLayout = findViewById(R.id.requiredResultLayout);
        recyclerView = findViewById(R.id.recyclerView);
        goBack = findViewById(R.id.goBack2);
        goBack.setOnClickListener(view -> {
            onBackPressed();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        AnimationDrawable animationDrawable = (AnimationDrawable) resultLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_animation, null);
        loadingDialog = new AlertDialog.Builder(FurnitureResultActivity.this)
                .setView(dialogView)
                .setCancelable(false)
                .create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Intent intent = this.getIntent();
        l = intent.getStringExtra("length");
        b = intent.getStringExtra("width");
        h = intent.getStringExtra("height");
        cat = intent.getStringExtra("type");
//        Log.i("Category check", "Categaroy is " + cat + l + " " + b + " " + h);

        furnitures = new ArrayList<>();
        extractFurnitureDetail();

    }

    public void extractFurnitureDetail() {
        loadingDialog.show();
        RequestQueue queue;
        queue = Volley.newRequestQueue(FurnitureResultActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiurl, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            try {
                                if ((jsonObject.getString("product_type").equals(cat))
                                        && Integer.parseInt(jsonObject.getString("length")) < Integer.parseInt(l) &&
                                        Integer.parseInt(jsonObject.getString("breadth")) < Integer.parseInt(b) &&
                                        Integer.parseInt(jsonObject.getString("height")) < Integer.parseInt(h)
                                ) {
                                    Furniture f = new Furniture(jsonObject.getString("product_name"), jsonObject.getString("product_selection1"), jsonObject.getString("product_selection2"), jsonObject.getString("product_url"));
                                    furnitures.add(f);
//                                Log.d("Datatype check", "extractFurnitureDetail: "+(Integer.parseInt(jsonObject.getString("length"))).getClass().getName());
                                    Log.d("json", "onCreate" + jsonObject.getString("product_name") + jsonObject.getString("length"));
                                }
                            } catch (Exception e) {
                                Log.i("if exception", "extractFurnitureDetail: " + e.getMessage());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adaptor = new FurnitureResultAdapter(this, furnitures);
                        recyclerView.setAdapter(adaptor);
                        loadingDialog.hide();
                    }
                }, error -> {
            Log.d("jsonError", "onCreate Failed!!");
            loadingDialog.hide();
        });
        queue.add(jsonArrayRequest);
    }

    public void backButton(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), FurnitureTypeActivity.class);
        i.putExtra("length", l);
        i.putExtra("width", b);
        i.putExtra("height", h);
        startActivity(i);
        finish();
    }
}

//  && (Integer.parseInt(jsonObject.getString("length")) < Integer.parseInt(l))