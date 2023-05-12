package com.example.banker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class activity_request extends AppCompatActivity {

    private int rows, cols;
    private int[] Maxtemp, Allocationtemp, Total, Available;
    private int[][] Max, Allocation;

    private ArrayList<Integer> sequence;
    private boolean[] safe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Button request = findViewById(R.id.request);
        EditText procNum = findViewById(R.id.procNum);
        EditText resNum = findViewById(R.id.resNum);
        EditText numRes = findViewById(R.id.numRes);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            Maxtemp = bundle.getIntArray("Max");
            Allocationtemp = bundle.getIntArray("Allocation");
            Total = bundle.getIntArray("Total");
            rows = bundle.getInt("rows");
            cols = bundle.getInt("cols");
            Max = new int[rows][cols];
            Allocation = new int[rows][cols];
            Available = new int[cols];
            sequence = new ArrayList<>(rows);
            safe = new boolean[rows];
            transform();
        }
        request.setOnClickListener(v -> {
            int i = Integer.parseInt(procNum.getText().toString());
            int j = Integer.parseInt(resNum.getText().toString());
            int num = Integer.parseInt(numRes.getText().toString());

            if(i <= rows && j <= cols) {
                calc(i, j, num);
                Intent intent = new Intent(this, answer_Activity.class);
                intent.putExtra("safeFlag", completed(safe));
                intent.putExtra("seq", sequence);
                startActivity(intent);
            }
        });
    }
    private void transform()
    {
        int count = 0;
        for (int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                Max[i][j] = Maxtemp[count];
                Allocation[i][j] = Allocationtemp[count];
                count++;
            }
            safe[i] =false;
        }
    }
    private void calc(int proc, int res, int num)
    {
        int sum = 0;
        Allocation[proc - 1][res - 1] += num;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                sum += Allocation[j][i];
            }
            Available[i] = Total[i] - sum;
        }
        int rem;
        for (int k = 0; k < 5; k++) {
            if (completed(safe))
                break;
            for (int i = 0; i < rows; i++) {
                if(safe[i] == true)
                    continue;
                for (int j = 0; j < cols; j++) {
                    rem = Max[i][j] - Allocation[i][j];
                    if(rem <= Available[j]) {
                        Available[j] += Allocation[i][j];
                        safe[i] = true;
                        sequence.add(i + 1);
                    }
                }
            }
        }

    }

    private boolean completed(boolean[] flags){
        for (boolean flag :
                flags) {
            if (flag == false)
                return false;
        }
        return true;
    }
}