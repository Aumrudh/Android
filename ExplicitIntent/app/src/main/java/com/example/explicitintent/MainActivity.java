package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b=(Button) findViewById(R.id.register);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Display.class);
                i.putExtra("Name",String.valueOf(((EditText) findViewById(R.id.name)).getText()));
                i.putExtra("RollNumber", String.valueOf(((EditText) findViewById(R.id.rollno)).getText()));
                i.putExtra("Course Name",String.valueOf(((EditText) findViewById(R.id.coursename)).getText()));
                i.putExtra("Course Duration",String.valueOf(((EditText) findViewById(R.id.courseduration)).getText()));
                startActivity(i);
            }
        });
    }
}