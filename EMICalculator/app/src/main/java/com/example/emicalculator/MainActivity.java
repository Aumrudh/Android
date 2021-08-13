package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    final String[] year = {""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] in={"1","2","3","4","5","6","7","8","9","10"};
        Spinner spin = (Spinner) findViewById(R.id.rate);
        //Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,in);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekyear);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                year[0] =String.valueOf(progress);
                TextView editText = (TextView)findViewById(R.id.y);
                editText.setText(year[0]);
                //Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }
    String text ="";
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner) findViewById(R.id.rate);
        text=spin.getSelectedItem().toString();
        //.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void finish(){
        Spinner spin = (Spinner) findViewById(R.id.rate);
        text=spin.getSelectedItem().toString();
        Button b1 = (Button)findViewById(R.id.calculate);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String principle = ((EditText)findViewById(R.id.amount)).getText().toString();
                float P= Float.parseFloat(principle);
                int Rate= Integer.parseInt(text);
                float r=(float)Rate/12/100;
                int n=Integer.parseInt(year[0]);
                float f = (float) Math.pow((1+r),n);
                float EMI = (P*r*f)/(f-1);
                //float EMI =(float) P * r * (1 + r)*n/((1 + r)*n -1);
                //float EMI = (float) (P * r * Math.pow((1 + r),n)/ (Math.pow((1 + r),n) - 1));
                Toast.makeText(getApplicationContext(), " EMI : "+ EMI, Toast.LENGTH_LONG).show();
            }
        });
    }
}