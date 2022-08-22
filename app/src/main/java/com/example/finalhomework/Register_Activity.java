package com.example.finalhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    ImageView backIntentbutton;
    EditText username_editText,password_editText,confirm_password_editText;
    Button register_btn;
    String username,password,confirm;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //inflate items
        backIntentbutton=(ImageView) findViewById(R.id.backArrowButton);
        username_editText=(EditText) findViewById(R.id.userName_editText);
        password_editText=(EditText)findViewById(R.id.password_editText);
        confirm_password_editText=(EditText)findViewById(R.id.confirm_password_editText);
        register_btn=(Button) findViewById(R.id.register_main_btn);

        dbManager=new DatabaseManager(this);

        try{
            //opening database
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=username_editText.getText().toString();
                password=password_editText.getText().toString();
                confirm=confirm_password_editText.getText().toString();
                if(username.equals("")||password.equals("")||confirm.equals("")){
                    Toast.makeText(getBaseContext(), "All Fields Must be Filled", Toast.LENGTH_LONG).show();
                }else if(!(password.equals(confirm))){
                    Toast.makeText(getBaseContext(), "Password Not Matching !!", Toast.LENGTH_LONG).show();
                    password_editText.setText("");
                    confirm_password_editText.setText("");
                }else{
                    dbManager.insert(username,password);
                    Toast.makeText(getBaseContext(), "Registration Successfully", Toast.LENGTH_LONG).show();
                    Intent goHome=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(goHome);
                }
            }
        });



        backIntentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome=new Intent(getBaseContext(),MainActivity.class);
                startActivity(backHome);
            }
        });
    }
}