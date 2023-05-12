package com.example.banker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class answer_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TextView answerText = findViewById(R.id.answerText);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            boolean safeFlag = bundle.getBoolean("safeFlag");
            ArrayList<Integer> seq = bundle.getIntegerArrayList("seq");
            if (safeFlag)
            {
               String seqString = "";
                for (Integer i:
                    seq) {
                    seqString += " P" + i;
                }
                answerText.setText("Safe Request with Sequence:\n" + seqString);
            }
            else
                answerText.setText("Unsafe Request");
        }
    }
}