package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class Food extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String name="";
    String mobile="";
    double height,weight,BMI;
    //static int i=0;
   // String l[] ={"","","","","","","",""};
    ArrayList<String> l = new ArrayList<String>();

    String foo[] ={"Dosa","Idly","Pasta","Pizza"};
    Spinner spin;
    TextView bmi;
    String food="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Bundle bundle = getIntent().getExtras();
        name=bundle.getString("Name");
        height=Double.parseDouble(bundle.getString("Height"));
        weight=Double.parseDouble(bundle.getString("Weight"));
        mobile=bundle.getString("MobileNo");
        BMI=height/weight;

        bmi=findViewById(R.id.bmi);
        bmi.setText(String.valueOf(BMI));


        spin= findViewById(R.id.fooditems);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_item,foo);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(a);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, l);
        ListView listView = (ListView) findViewById(R.id.listfood);
        listView.setAdapter(adapter);

        Button check = findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double cal=0.0;
                for(String i:l){
                    if(i=="Dosa"){
                        cal+=7.5;
                    }
                    if(i=="Idly"){
                        cal+=5.0;
                    }
                    if(i=="Pasta"){
                        cal+=35.5;
                    }
                    if(i=="Pizza"){
                        cal+=55.5;
                    }
                }
                if(cal>Double.parseDouble(bmi.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Reduce food items", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Food Items Have sufficient calories", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, l);
        ListView listView = (ListView) findViewById(R.id.listfood);
        switch (item.getItemId()){
            case R.id.add:
                l.add(food);
                listView.setAdapter(adapter);
                return true;
            case R.id.remove:
                if(l.contains(food)){
                        l.remove(food);
                        listView.setAdapter(adapter);
                    }

                        return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spin= findViewById(R.id.fooditems);
        food=spin.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}