package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertEmployee extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] desEmp={"Data Analyst","Senior Data Analyst"};
    String[] expEmp={"0","1","2","3","4","5","6","7","8","9"};
    String designEmp="";
    int experEmp=0;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_employee);
        Spinner desg= findViewById(R.id.design);
        desg.setOnItemSelectedListener(this);
        ArrayAdapter aa=new ArrayAdapter(this, android.R.layout.simple_spinner_item,desEmp);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desg.setAdapter(aa);

        Spinner exp= findViewById(R.id.exp);
        exp.setOnItemSelectedListener(this);
        ArrayAdapter aa1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,expEmp);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exp.setAdapter(aa1);

        db = new DBHelper(this);

        Button insert= findViewById(R.id.insertEmp);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(String.valueOf(((EditText)findViewById(R.id.empid)).getText()));
                String name=String.valueOf(((EditText)findViewById(R.id.name)).getText());
                Boolean checkinsert = db.insertUserData(id, name, designEmp, experEmp);
                if(checkinsert==true){
                    Toast.makeText(getApplicationContext(), "Inserted Successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not Inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner des= findViewById(R.id.design);
        designEmp=String.valueOf(des.getSelectedItem());
        Spinner exp= findViewById(R.id.exp);
        experEmp=Integer.parseInt(exp.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}