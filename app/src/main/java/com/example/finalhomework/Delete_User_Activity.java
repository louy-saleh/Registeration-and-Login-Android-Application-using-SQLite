package com.example.finalhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Delete_User_Activity extends AppCompatActivity {

    ImageView backIntentbutton;
    TextView delete_user_textView;
    EditText password_editText;
    Button delete_user_main_btn;
    DatabaseManager dbManager;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        //inflate items
        backIntentbutton=(ImageView) findViewById(R.id.backArrowButton);
        delete_user_textView=(TextView)findViewById(R.id.delete_user_textView);
        password_editText=(EditText) findViewById(R.id.password_editText);
        delete_user_main_btn=(Button) findViewById(R.id.delete_user_main_btn);
        dbManager=new DatabaseManager(this);
        Intent receive_username=getIntent();
        String username=receive_username.getStringExtra("username").toString();


        try{
            //opening database
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }

        delete_user_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=password_editText.getText().toString();
                if(dbManager.checking_username_password(username,password)){
                    if(dbManager.delete(username)) {
                        Toast.makeText(getBaseContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                }
        });

        //back onClick listener
        backIntentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome=new Intent(getBaseContext(),MainActivity.class);
                startActivity(backHome);
            }
        });
    }
}