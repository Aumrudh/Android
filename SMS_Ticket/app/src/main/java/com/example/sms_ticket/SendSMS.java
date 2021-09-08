package com.example.sms_ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMS extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        String mob = (((EditText) findViewById(R.id.mobile)).getText()).toString();

        Bundle extras = getIntent().getExtras();
        String src = extras.getString("Source");
        String dest = extras.getString("Destination");
        String mode = extras.getString("Mode of Vehicle");
        String date = extras.getString("Date");

        Button s = (Button) findViewById(R.id.sms);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(getApplicationContext(), SendSMS.class);
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                    SmsManager sms = SmsManager.getDefault();
                    String msg = "Source  : " + src + "\nDestination : " + dest + "\nMode of Transport : " + mode + "\nDate : " + date + "\n";
                    sms.sendTextMessage(mob, null, msg, pi, null);
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Message Sent Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button b = (Button) findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}