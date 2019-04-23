package com.example.shaufyq.labtest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubjectDetailActivity extends AppCompatActivity {

    String strmsg,gred;
    Spinner spinner;
    EditText code,name,hour;
    TextView CGPA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        Intent intent = getIntent();
        strmsg = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),"Welcome "+ strmsg,Toast.LENGTH_LONG).show();

        spinner = findViewById(R.id.spinnerGrader);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gred, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        code = findViewById(R.id.editTextCode);
        name = findViewById(R.id.editTextName);
        hour = findViewById(R.id.editTextHour);
        CGPA = findViewById(R.id.totalCGPA);


    }

    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(),"Welcome "+ strmsg,Toast.LENGTH_LONG).show();
    }

    public void saveCal(View view){
        String codeVal =  code.getText().toString();
        String nameVal = name.getText().toString();
        String hourVal = hour.getText().toString();
        String gredVal = spinner.getSelectedItem().toString();
        final ResultDbModel expenseDBModel = new ResultDbModel(codeVal, nameVal, hourVal, gredVal);

        ResultDB expensesDB = new ResultDB(getApplicationContext());
        expensesDB.fnInsertExpense(expenseDBModel);

        CGPA.setText(String.valueOf(expensesDB.fnGetAllresult()));

    }



}
