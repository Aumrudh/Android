package com.example.popup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText e = (EditText)findViewById(R.id.value);
        Button b = (Button)findViewById(R.id.convert);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, b);
                popup.getMenuInflater().inflate(R.menu.pop_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String choice=item.getTitle().toString();
                        if(choice.equals("Dollar to Rupees")){
                            String res =String.valueOf(Float.parseFloat(String.valueOf(e.getText()))*72);
                            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                        }
                        else if(choice.equals("Rupees to Dollar")){
                            String res =String.valueOf(Float.parseFloat(String.valueOf(e.getText()))/72);
                            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}