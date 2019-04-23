package com.example.shaufyq.labtest1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean entered = false;
    String username;
    //1. Declare as class members
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText uName, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uName = findViewById(R.id.edtTextName);
        pass = findViewById(R.id.editTextStudNo);



        //2. Initialize inside onCreate(), Name of file should be same through all acttivities
        sharedPreferences = getApplicationContext().getSharedPreferences("Login", 0);
        editor = sharedPreferences.edit();

        //3. Set the key and value using putString() and then commit()
        editor.putString("username", "pitau");
        editor.putString("password", "password");
        editor.commit();


    }



    public void sharedPrefereance(View view){

        //4. In other activity, repaeat step 1 and 2 to make changes
        //5. Use getString() for retrieving value from the stored file
        String user = sharedPreferences.getString("username", null);
        String passord = sharedPreferences.getString("password", null);

        String un = uName.getText().toString();
        String pa = pass.getText().toString();

        if ((user).equals(un) && (passord).equals(pa) ){
            Intent intent = new Intent(this, SubjectDetailActivity.class);
            String strMsg = user;
            intent.putExtra("name",strMsg);
            startActivity(intent);
            entered = true;
        }else {
            Toast.makeText(getApplicationContext(),"edtText "+ un + pa +" shared "+ user + passord ,Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onResume(){
        super.onResume();
        if (entered) {
            Intent intent = new Intent(this, SubjectDetailActivity.class);
            String strMsg = username;
            intent.putExtra("name",strMsg);
            startActivity(intent);
        }
    }
}
