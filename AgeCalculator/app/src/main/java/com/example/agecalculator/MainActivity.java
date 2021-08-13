package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener mDateSetListener;
    String date="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUserTypeOnButtonClick();
        TextView mDisplayDate = (TextView) findViewById(R.id.Date);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                //dialog.getWindow();//.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = day + "/" + month  + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        //calAge();


    }

    private void setUserTypeOnButtonClick() {
       /* Button button = (Button) findViewById(R.id.calculateage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
            }
        });
        */
        calAge();

    }

    public void calAge(){
        Button button = (Button) findViewById(R.id.calculateage);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date current_date= new Date();
                String formattedDate = simpleDateFormat.format(current_date);
                String[] d1=formattedDate.split("/");
                String[] d2=date.split("/");
               //Toast.makeText(getApplicationContext(),formattedDate,Toast.LENGTH_LONG).show();

                String y=String.valueOf(Integer.parseInt(d1[2])-Integer.parseInt(d2[2]));
                String m=String.valueOf(Integer.parseInt(d1[1])-Integer.parseInt(d2[1]));
                String d=String.valueOf(Math.abs(Integer.parseInt(d1[0])-Integer.parseInt(d2[0])));
                Toast.makeText(getApplicationContext(),"You are "+ y +" years "+ m +" months " + d +" days old!",Toast.LENGTH_LONG).show();
            }
        });
    }
}