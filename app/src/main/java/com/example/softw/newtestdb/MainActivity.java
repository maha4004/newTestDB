package com.example.softw.newtestdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText namee , pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namee = (EditText) findViewById(R.id.nameedt);
        pass = (EditText) findViewById(R.id.passedt);
    }

    public void save(View view){
        String name = namee.getText().toString();
        String password = pass.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,name,password);


    }

    public void wrong (){

        Toast.makeText(getApplicationContext(),"wrong in if",Toast.LENGTH_LONG).show();
    }

}
