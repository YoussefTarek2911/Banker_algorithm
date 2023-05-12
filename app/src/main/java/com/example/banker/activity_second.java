package com.example.banker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class activity_second extends AppCompatActivity {

    private int rows, cols, proc;
    private int[] Max, Allocation, Total;
    LinearLayout matrixContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button maxButton = findViewById(R.id.maxButton);
        Button allocButton = findViewById(R.id.allocButton);
        Button totalButton = findViewById(R.id.totalButton);
        Button Assign = findViewById(R.id.Assign);
        Button next = findViewById(R.id.next);

        System.out.print("test");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            rows = bundle.getInt("p");
            cols = bundle.getInt("r");
            Max = new int[cols * rows];
            Allocation = new int[cols * rows];
            Total = new int[cols];
        }

        maxButton.setOnClickListener(view -> {
            showTable(0);
            proc = 0;
        });
        allocButton.setOnClickListener(view -> {
            showTable(0);
            proc = 1;
        });
        totalButton.setOnClickListener(view -> {
            showTable(1);
            proc = 2;
        });

        Assign.setOnClickListener(v -> {
            int count = 0;
            for (int i=0;i<rows;i++)
            {
                for (int j = 0; j < cols; j++) {
                    if (proc == 0){
                        String input = ((TextView)((LinearLayout) matrixContainer.getChildAt(i)).getChildAt(j)) .getText().toString();
                        Max[count] = Integer.parseInt(input);
                        count++;
                    } else if (proc == 1) {
                        String input = ((TextView)((LinearLayout) matrixContainer.getChildAt(i)).getChildAt(j)) .getText().toString();
                        Allocation[count] = Integer.parseInt(input);
                        count++;
                    } else if (proc == 2) {
                        String input = ((TextView)((LinearLayout) matrixContainer.getChildAt(0)).getChildAt(j)) .getText().toString();
                        Total[j] = Integer.parseInt(input);
                    }
                }
            }
        });

        next.setOnClickListener(v -> {
            Intent intent = new Intent(this, activity_request.class);
            Bundle bundle1 = new Bundle();
            bundle1.putIntArray("Max", Max);
            bundle1.putIntArray("Allocation", Allocation);
            bundle1.putIntArray("Total", Total);
            bundle1.putInt("rows", rows);
            bundle1.putInt("cols", cols);
            intent.putExtras(bundle1);
            startActivity(intent);
        });

    }
    private void showTable(int proc)
    {
        // Get a reference to the LinearLayout container
        matrixContainer = findViewById(R.id.matrix_container);
        matrixContainer.removeAllViews();
        matrixContainer.setGravity(Gravity.CENTER);
        int count = 0;
        if (proc == 0)
        {
            count = rows;
        }
        else if (proc == 1)
        {
            count = 1;
        }

        // Loop through the rows
        for (int i = 0; i < count; i++) {
            LinearLayout rowLayout = createLayout();
            // Add the row LinearLayout to the matrix container
            matrixContainer.addView(rowLayout);
        }
    }
    private LinearLayout createLayout()
    {
        // Create a new row LinearLayout
        LinearLayout rowLayout = new LinearLayout(this);
        rowLayout.setGravity(Gravity.CENTER);
        rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Loop through the columns
        for (int j = 0; j < cols; j++) {
            // Create a new EditText view
            EditText editText = new EditText(this);
            editText.setTextSize(30);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,40,20);
            editText.setLayoutParams(params);
            editText.setWidth(120);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setText("");
            editText.setBackgroundColor(Color.CYAN);

            // Add the EditText view to the row LinearLayout
            rowLayout.addView(editText);
        }
        return rowLayout;
    }
}