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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatePickerDialog.OnDateSetListener mDateSetListener ;
    String date="";
    String src ="";
    String dst="";
    String trainname="";
    String berth="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        String[] in={"","Madurai","Chennai","Bengaluru","Hyderabad"};
        Spinner spin = (Spinner) findViewById(R.id.src);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,in);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        Spinner spin2 = (Spinner) findViewById(R.id.dst);
        spin2.setOnItemSelectedListener(this);
        spin2.setAdapter(aa);
        Spinner train = (Spinner) findViewById(R.id.train);
        String t[] = {"No trains Available"};
        ArrayAdapter aat = new ArrayAdapter(this, android.R.layout.simple_spinner_item, t);
        aat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        train.setOnItemSelectedListener(this);
        train.setAdapter(aat);
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
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton genderradioButton = (RadioButton) findViewById(selectedId);
                genderradioButton = (RadioButton) findViewById(selectedId);
               // Toast.makeText(getApplicationContext(),genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                berth=String.valueOf(genderradioButton.getText());
                int pnr = (int)(Math.random()*(100000));
                String msg = "Pnr Number : "+pnr+"\nSource  : " + src + "\nDestination : " + dst + "\nDate : " + date + "\nTrain name : "+trainname+"\n Coach : D7"+"\nBerth : "+berth+"\nStatus : Confirmed"+"\n";
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+"9597554507"));
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
        //Toast.makeText(getApplicationContext(), "inside onItem", Toast.LENGTH_SHORT).show();
        Spinner train = (Spinner) findViewById(R.id.train);
        if(src=="Madurai") {
            //Toast.makeText(getApplicationContext(), "madurai", Toast.LENGTH_SHORT).show();
            if (dst == "Bengaluru") {
                //Toast.makeText(getApplicationContext(), "bangalore", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                String t[] = {"Mysore Express", "Nagerkovil Express"};

                ArrayAdapter aat = new ArrayAdapter(this, android.R.layout.simple_spinner_item, t);
                aat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                train.setOnItemSelectedListener(this);
                train.setAdapter(aat);
            }
            else{

            }
        }
       trainname=train.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}