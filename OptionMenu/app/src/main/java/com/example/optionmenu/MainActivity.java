package com.example.optionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int i=0;
    String l[] ={"Test","","","","","","",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview, l);
        ListView listView = (ListView) findViewById(R.id.contact);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuxml,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ListView listView = (ListView) findViewById(R.id.contact);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview, l);
        switch (id){
            case R.id.add:
                l[i++]=String.valueOf(((EditText)findViewById(R.id.name)).getText());
                listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete:
                String res=String.valueOf(((EditText)findViewById(R.id.name)).getText());
                for(int i=0;i<8;i++){
                    if(res.equals(l[i])){
                        l[i]="";
                        listView.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Name Not found",Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}