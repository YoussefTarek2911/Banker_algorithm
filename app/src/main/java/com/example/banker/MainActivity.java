package com.example.banker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Number_Of_Resources_id = findViewById(R.id.Number_Of_Resources_id);
        EditText Number_Of_Processes_id=findViewById(R.id.Number_Of_Processes_id);
        Button saved_button1 =findViewById(R.id.saved_button1);
        ImageView imageView=findViewById(R.id.imageView);

        saved_button1.setOnClickListener(v -> {
            int R= Integer.parseInt(Number_Of_Resources_id.getText().toString());
            int P=Integer.parseInt(Number_Of_Processes_id.getText().toString());
            Toast.makeText(MainActivity.this, "Input saved successfully", Toast.LENGTH_SHORT).show();

            Intent intent =new Intent(this,activity_second.class);
            Bundle bundle=new Bundle();
            bundle.putInt("p",P);
            bundle.putInt("r",R);
            intent.putExtras(bundle);
            startActivity(intent);


        });







    }
}