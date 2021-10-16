package com.example.assignment;

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
        Button res=findViewById(R.id.calculate);
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Food.class);
                i.putExtra("Name",String.valueOf(((EditText) findViewById(R.id.name)).getText()));
                i.putExtra("Height", String.valueOf(((EditText) findViewById(R.id.height)).getText()));
                i.putExtra("Weight",String.valueOf(((EditText) findViewById(R.id.weight)).getText()));
                i.putExtra("MobileNo",String.valueOf(((EditText) findViewById(R.id.mobile)).getText()));
                startActivity(i);
            }
        });
    }
}