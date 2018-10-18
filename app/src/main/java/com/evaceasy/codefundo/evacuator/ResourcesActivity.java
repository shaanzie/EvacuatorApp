package com.evaceasy.codefundo.evacuator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ResourcesActivity extends AppCompatActivity {

    EditText ShelterLoc,ResReq;
    Button btn_res_req;
    String Loc,Req,Phone;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_main);

        ShelterLoc = findViewById(R.id.ShelterLoc);
        ResReq = findViewById(R.id.ResReq);
        btn_res_req = findViewById(R.id.btn_res_req);


        db.collection("Admin")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Loc = ShelterLoc.getText().toString();
                            Req = ResReq.getText().toString();
                            for (QueryDocumentSnapshot document : task.getResult()) {

//                              Toast.makeText(Broadcast.this,document.get("Name").toString(),Toast.LENGTH_LONG);
                                Phone = document.get("Phone").toString();

                                btn_res_req.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        sendSMSMessage(Phone,"Require " + Req + " at " + Loc);
                                    }
                                });

                            }

                        } else {
                            Toast.makeText(ResourcesActivity.this,"Request not sent",Toast.LENGTH_LONG);
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