package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button insert = findViewById(R.id.insert);
        Button delete = findViewById(R.id.delete);
        Button update = findViewById(R.id.update);
        Button view = findViewById(R.id.viewDB);

    }

    public void insertEmployee(View view) {
        Intent i = new Intent(getApplicationContext(), InsertEmployee.class);
        startActivity(i);
    }

    public void deleteEmployee(View view) {
        Intent i = new Intent(getApplicationContext(), deleteEmployee.class);
        startActivity(i);
    }

    public void updateEmployee(View view) {
        Intent i = new Intent(getApplicationContext(), updateEmployee.class);
        startActivity(i);
    }

    public void viewEmployee(View view) {
        Intent i = new Intent(getApplicationContext(), viewEmployee.class);
        startActivity(i);
    }


}