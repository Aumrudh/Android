package com.example.employeemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class deleteEmployee extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DBHelper db;
    int empid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);
        db = new DBHelper(this);

        Cursor cursor= db.viewUserData();
        StringBuffer buffer = new StringBuffer();
        buffer.append(" ,");
        while(cursor.moveToNext()){
            buffer.append(cursor.getString(0)+",");
        }
        String employeeID[]=buffer.toString().split(",");

        Spinner desg= findViewById(R.id.eid);
        desg.setOnItemSelectedListener(this);
        ArrayAdapter aa=new ArrayAdapter(this, android.R.layout.simple_spinner_item,employeeID);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desg.setAdapter(aa);

        Button del = findViewById(R.id.delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkdelete = db.deleteUserData(empid);
                if(checkdelete==true){
                    Toast.makeText(getApplicationContext(), "Deleted Successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       Spinner desg= findViewById(R.id.eid);
       if(!(desg.getSelectedItem().toString().equals(" "))) {
           empid = Integer.parseInt(desg.getSelectedItem().toString());
       }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}