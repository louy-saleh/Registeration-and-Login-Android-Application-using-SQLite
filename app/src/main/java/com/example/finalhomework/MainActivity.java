package com.example.finalhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button loginButton,registerButton,updateInfoButton,deleteUserButton;
    String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflating items
        loginButton=(Button) findViewById(R.id.login_btn);
        registerButton=(Button) findViewById(R.id.register_btn);
        updateInfoButton=(Button) findViewById(R.id.update_info_btn);
        deleteUserButton=(Button) findViewById(R.id.delete_user_btn);


        //Buttons OnClick (Intents)

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent=new Intent(getBaseContext(),Register_Activity.class);
                startActivity(register_intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="1";
                Intent login_intent=new Intent(getBaseContext(),Login_Activity.class);
                login_intent.putExtra("status",status);
                startActivity(login_intent);
            }
        });

        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="2";
                Intent update_Info_intent=new Intent(getBaseContext(),Login_Activity.class);
                update_Info_intent.putExtra("status",status);
                startActivity(update_Info_intent);
            }
        });

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="3";
                Intent delete_user_intent=new Intent(getBaseContext(),Login_Activity.class);
                delete_user_intent.putExtra("status",status);
                startActivity(delete_user_intent);
            }
        });




    }
}