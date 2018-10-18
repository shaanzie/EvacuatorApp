package com.evaceasy.codefundo.evacuator;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText userEmail;
    EditText userPass;
    Button userLogin;

    FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actibity_login2);

        progressBar = findViewById(R.id.progressBar2);
        userEmail = findViewById(R.id.etUserEmail);
        userPass = findViewById(R.id.etUserPass);
        userLogin = findViewById(R.id.btnUserLogin);


        firebaseAuth = FirebaseAuth.getInstance();


//        userLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toolbar.setVisibility(View.VISIBLE);
//                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPass.getText().toString())
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        toolbar.setVisibility(View.INVISIBLE);
//                        if(task.isSuccessful()){
//                            startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
//                        }
//                        else{
//                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//            }
//        });

        }
        public void logclick (View view){
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }
}

