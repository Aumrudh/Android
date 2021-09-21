package com.example.employeemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class updateEmployee extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] desEmp={"Data Analyst","Senior Data Analyst"};
    String[] expEmp={"0","1","2","3","4","5","6","7","8","9"};
    DBHelper db;
    int eid,experience;
    String design="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        db = new DBHelper(this);

        Cursor cursor= db.viewUserData();
        StringBuffer buffer = new StringBuffer();
        buffer.append(" ,");
        while(cursor.moveToNext()){
            buffer.append(cursor.getString(0)+",");
        }
        String employeeID[]=buffer.toString().split(",");

        Spinner empid= findViewById(R.id.empid);
        empid.setOnItemSelectedListener( this);
        ArrayAdapter aa=new ArrayAdapter(this, android.R.layout.simple_spinner_item,employeeID);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        empid.setAdapter(aa);

        Spinner desg= findViewById(R.id.design);
        desg.setOnItemSelectedListener(this);
        ArrayAdapter aa2=new ArrayAdapter(this, android.R.layout.simple_spinner_item,desEmp);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desg.setAdapter(aa2);

        Spinner exp= findViewById(R.id.exp);
        exp.setOnItemSelectedListener(this);
        ArrayAdapter aa1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,expEmp);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exp.setAdapter(aa1);

        Button updateEmployee = findViewById(R.id.updateEmp);
        updateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=((EditText)findViewById(R.id.name)).getText().toString();
                Boolean checkupdate = db.updateUserData(eid, name, design,experience);
                if(checkupdate==true){
                    Toast.makeText(getApplicationContext(), "Update Successfully",Toast.LENGTH_LONG).show();
                    Cursor res = db.updateViewUserData(eid);
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Employee Id :"+res.getString(0)+"\n");
                        buffer.append("Name :"+res.getString(1)+"\n");
                        buffer.append("Designation:"+res.getString(2)+"\n");
                        buffer.append("Experiance:"+res.getString(3)+"\n");
                        buffer.append("\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(updateEmployee.this);
                    builder.setCancelable(true);
                    builder.setTitle("Updated Details");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not Updated",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner s=findViewById(R.id.empid);
        if(!(s.getSelectedItem().toString().equals(" "))) {
            eid = Integer.parseInt(s.getSelectedItem().toString());
        }

        Spinner desg= findViewById(R.id.design);
        design = desg.getSelectedItem().toString();

        Spinner exp= findViewById(R.id.exp);
        experience = Integer.parseInt(exp.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}