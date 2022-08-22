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

import org.w3c.dom.Text;

public class Update_Info_Activity extends AppCompatActivity {
    ImageView backIntentbutton;
    TextView update_info_main;
    EditText update_username_editText;
    Button update_info_main_btn;
    String username_new;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        //inflate items
        backIntentbutton=(ImageView) findViewById(R.id.backArrowButton);
        update_info_main=(TextView)findViewById(R.id.update_info_textView);
        update_username_editText=(EditText) findViewById(R.id.userName_editText);
        update_info_main_btn=(Button) findViewById(R.id.update_info_main_btn);
        dbManager=new DatabaseManager(this);
        Intent receive_username_old=getIntent();
        String username_old=receive_username_old.getStringExtra("username").toString();

        try{
            //opening database
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }


        update_info_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_new=update_username_editText.getText().toString();
                if(dbManager.update_username(username_old,username_new)){
                    Toast.makeText(getBaseContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
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