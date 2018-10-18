package com.evaceasy.codefundo.evacuator;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ProgressBar progressBar;
    EditText email;
    EditText password;
    EditText name;
    EditText phone;
    Button signup;
    Button login;
    FirebaseAuth firebaseAuth;
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                signup.setVisibility(View.INVISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        signup.setVisibility(View.VISIBLE);
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
//                            Map<String,Object> user = new HashMap<>();
//                            user.put("Email",email);
//                            user.put("Name",name);
//                            user.put("Phone",phone);
//                            db.collection("AdminData")
//                                    .add(user)
//                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Log.d(TAG,"DocumentSnapshot added with ID: "+documentReference.getId());
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.w(TAG,"Error adding document",e);
//                                        }
//                                    });
                        }
                        else{
                            Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

    }
}
