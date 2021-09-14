package com.example.sms_ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatePickerDialog.OnDateSetListener mDateSetListener ;
    String date="";
    String src ="";
    String dst="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] in={"","Madurai","Chennai","Bengaluru","Hyderabad"};
        Spinner spin = (Spinner) findViewById(R.id.src);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,in);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        Spinner spin2 = (Spinner) findViewById(R.id.dst);
        //ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,in);
        //aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setOnItemSelectedListener(this);
        spin2.setAdapter(aa);
        TextView mDisplayDate = (TextView) findViewById(R.id.Date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = day + "/" + month  + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        Button b=(Button) findViewById(R.id.sms);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Source  : " + src + "\nDestination : " + dst + "\nDate : " + date + "\n";
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+"9444769094"));
                sendIntent.putExtra("sms_body", msg);
                //sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner) findViewById(R.id.src);
        src=spin.getSelectedItem().toString();
        Spinner spin2 = (Spinner) findViewById(R.id.dst);
        dst=spin2.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), "inside onItem", Toast.LENGTH_SHORT).show();
        if(src=="Madurai") {
            Toast.makeText(getApplicationContext(), "madurai", Toast.LENGTH_SHORT).show();
            if (dst == "Bengaluru") {
                Toast.makeText(getApplicationContext(), "bangalore", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                String t[] = {"Mysore Express", "Nagerkovil Express"};
                Spinner train = (Spinner) findViewById(R.id.train);
                ArrayAdapter aat = new ArrayAdapter(this, android.R.layout.simple_spinner_item, t);
                aat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                train.setOnItemSelectedListener(this);
                train.setAdapter(aat);
            }
            else{
                String t[] = {"No trains Available"};
                Spinner train = (Spinner) findViewById(R.id.train);
                ArrayAdapter aat = new ArrayAdapter(this, android.R.layout.simple_spinner_item, t);
                aat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                train.setOnItemSelectedListener(this);
                train.setAdapter(aat);
            }
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}