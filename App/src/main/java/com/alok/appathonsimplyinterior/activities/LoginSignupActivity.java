package com.alok.appathonsimplyinterior.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.alok.appathonsimplyinterior.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSignupActivity extends AppCompatActivity {
    ConstraintLayout loginSignup;
    Switch loginSignupSwitch;
    TextView signinTextView;
    TextView signupTextView;
    Button signinButton;
    Button signupButton;
    LottieAnimationView lottie;
    ConstraintLayout loginSignupLayout;
    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    TextView welcome;
    ImageView userPersonMale;
    ImageView userPersonFemale;
    TextView missYou;
    private AlertDialog loadingDialog;



    public void createAccount(View view) {
        loadingDialog.show();
        if (email.getText().toString() != "" && password.getText().toString() != "") {
            try {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Snackbar.make(findViewById(android.R.id.content),"Account Created Successfully",Snackbar.LENGTH_SHORT).show();
                                    loadingDialog.hide();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("failure:", task.getException().toString());
                                    loadingDialog.hide();
                                    Snackbar.make(findViewById(android.R.id.content),"Authentication Failed",Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch(Exception e) {
                Snackbar.make(findViewById(android.R.id.content),"Fields cannot be empty.",Snackbar.LENGTH_SHORT).show();
                loadingDialog.hide();
            }
        } else {
            Snackbar.make(findViewById(android.R.id.content),"Fields cannot be empty.",Snackbar.LENGTH_SHORT).show();
            loadingDialog.hide();
        }

    }

    public void loginAccount(View view) {
        loadingDialog.show();
        if (email.getText().toString() != "" && password.getText().toString() != "") {
            try {
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("signInWithEmail:", "success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    String emailOfUser = email.getText().toString();
                                    SharedPreferences sharedPreferences = getSharedPreferences("email",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("email", emailOfUser);
                                    editor.apply();

                                    Snackbar.make(findViewById(android.R.id.content),"Login Successfully",Snackbar.LENGTH_SHORT).show();
                                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                                    home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(home);
                                    finish();
                                    loadingDialog.hide();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("signInWithEmail:failure", task.getException().toString());
                                    Snackbar.make(findViewById(android.R.id.content),"Login Failed",Snackbar.LENGTH_SHORT).show();
                                    loadingDialog.hide();
                                }
                            }
                        });
            } catch (Exception e){
                Snackbar.make(findViewById(android.R.id.content),"Fields cannot be empty.",Snackbar.LENGTH_SHORT).show();
                loadingDialog.hide();

            }

        } else {
            Snackbar.make(findViewById(android.R.id.content),"Fields cannot be empty.",Snackbar.LENGTH_SHORT).show();
            loadingDialog.hide();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        loginSignup = findViewById(R.id.loginSignup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_animation, null);
        loadingDialog = new AlertDialog.Builder(LoginSignupActivity.this)
                .setView(dialogView)
                .setCancelable(false)
                .create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AnimationDrawable animationDrawable = (AnimationDrawable) loginSignup.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        missYou = findViewById(R.id.missYou);
        welcome = findViewById(R.id.welcome);
//        userPersonMale = findViewById(R.id.userPersonMale);
//        userPersonFemale = findViewById(R.id.userPersonFemale);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        loginSignupSwitch = findViewById(R.id.loginSignupSwitch);
        signinTextView = findViewById(R.id.signinTextView);
        signupTextView = findViewById(R.id.signupTextView);
        signinButton = findViewById(R.id.signinButton);
        signupButton = findViewById(R.id.signupButton);
        loginSignupLayout = findViewById(R.id.loginSignupLayout);
        lottie=findViewById(R.id.noSearchResult);
        lottie.setVisibility(View.VISIBLE);
        loginSignupLayout.animate().translationYBy(-2000f).setDuration(1500);

        welcome.animate().translationXBy(2000f).setDuration(1000);
        missYou.animate().translationXBy(2000f).setDuration(1200);
        lottie.setVisibility(View.GONE);

        loginSignupSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginSignupSwitch.isChecked()) {
                    signupTextView.setTextColor(Color.parseColor("#0B393C"));
                    signinTextView.setTextColor(Color.parseColor("#f24789"));
                    signupButton.animate().translationXBy(-2000f).setDuration(700);
                    signinButton.animate().translationXBy(-2000f).setDuration(700);
                } else {
                    signinTextView.setTextColor(Color.parseColor("#0B393C"));
                    signupTextView.setTextColor(Color.parseColor("#f24789"));
                    signupButton.animate().translationXBy(2000f).setDuration(700);
                    signinButton.animate().translationXBy(2000f).setDuration(700);
                }
            }
        });

    }
}