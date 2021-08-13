package com.example.explicitintent;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Display  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("Name");
        String rollno = extras.getString("RollNumber");
        String course_name = extras.getString("Course Name");
        String course_duration = extras.getString("Course Duration");
        //Toast.makeText(getApplicationContext(),"Values are:\n First value: "+value1+
        //        "\n Second Value: "+value2, Toast.LENGTH_LONG).show();
        TextView r=(TextView)findViewById(R.id.name);
        TextView rn=(TextView)findViewById(R.id.rollno);
        TextView cn=(TextView)findViewById(R.id.courseduration);
        TextView cd=(TextView)findViewById(R.id.coursename);
        r.setText(name);
        rn.setText(rollno);
        cn.setText(course_name);
        cd.setText(course_duration);
        Button b=(Button) findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
