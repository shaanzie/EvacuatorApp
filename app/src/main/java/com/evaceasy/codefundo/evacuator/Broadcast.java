package com.evaceasy.codefundo.evacuator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Broadcast extends AppCompatActivity {

    EditText text_message;
    String Phone,text_msg_string;
    Button btn_send_sms;
    private static final String TAG = "Broadcast";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_main);

        text_message = findViewById(R.id.broadcastMessage);
        btn_send_sms = findViewById(R.id.btn_send_sms);



        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

//                                Toast.makeText(Broadcast.this,document.get("Name").toString(),Toast.LENGTH_LONG);
                                Phone = document.get("Phone").toString();
                                text_msg_string = text_message.getText().toString();
                                btn_send_sms.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        sendSMSMessage(Phone,text_msg_string);
                                    }
                                });

                            }

                        } else {
                            Log.w(TAG, "ERRORERRORERROR.", task.getException());
                        }
                    }
                });

    }

    private void sendSMSMessage(String phoneNo,String text_message) {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, text_message, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();

    }



}
