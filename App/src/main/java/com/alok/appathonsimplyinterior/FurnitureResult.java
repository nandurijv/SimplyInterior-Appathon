package com.alok.appathonsimplyinterior;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FurnitureResult extends AppCompatActivity {

    String l, b, h, cat;
    ConstraintLayout resultLayout;
    RecyclerView recyclerView;
    List<Furniture> furnitures;
    FurnitureResultAdaptor adaptor;

    String apiurl = "https://sheetdb.io/api/v1/9j4n3euk9qvc3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_result);

        resultLayout = findViewById(R.id.requiredResultLayout);
        recyclerView = findViewById(R.id.recyclerView);

        AnimationDrawable animationDrawable = (AnimationDrawable) resultLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

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
        RequestQueue queue;
        queue = Volley.newRequestQueue(FurnitureResult.this);
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
                        adaptor = new FurnitureResultAdaptor(this, furnitures);
                        recyclerView.setAdapter(adaptor);
                    }
                }, error -> {
            Log.d("jsonError", "onCreate Failed!!");
        });
        queue.add(jsonArrayRequest);
    }

    public void backButton(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), TypeOfFurniture.class);
        i.putExtra("length", l);
        i.putExtra("width", b);
        i.putExtra("height", h);
        startActivity(i);
    }
}

//  && (Integer.parseInt(jsonObject.getString("length")) < Integer.parseInt(l))