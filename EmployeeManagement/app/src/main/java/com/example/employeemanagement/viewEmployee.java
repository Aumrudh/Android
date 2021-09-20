package com.example.employeemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class viewEmployee extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);
        db = new DBHelper(this);
        Cursor res = db.viewUserData();
        if(res.getCount()==0){
            Toast.makeText(viewEmployee.this, "No entry exist",Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Employee Id :"+res.getString(0)+"\n");
            buffer.append("Name :"+res.getString(1)+"\n");
            buffer.append("Designation:"+res.getString(2)+"\n");
            buffer.append("Experiance:"+res.getString(3)+"\n");
            buffer.append("\n");
        }
        TextView t = findViewById(R.id.resultSet);
        t.setText(buffer.toString());
        /*AlertDialog.Builder builder=new AlertDialog.Builder(viewEmployee.this);
        builder.setCancelable(true);
        builder.setTitle("Book Enteries");
        builder.setMessage(buffer.toString());
        builder.show();*/
       /* Intent i = new Intent(getApplicationContext(), ViewResult.class);
        i.putExtra("Result", buffer.toString());
        startActivity(i);*/
    }
}