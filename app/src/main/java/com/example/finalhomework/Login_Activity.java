package com.example.finalhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    ImageView backIntentbutton;
    EditText username_editText,password_editText;
    Button login_main_button;
    String username,password;
    DatabaseManager dbManager;
    public static final String CHANNEL_ID="my_channel_id";
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //inflate items
        backIntentbutton=(ImageView) findViewById(R.id.backArrowButton);
        username_editText=(EditText) findViewById(R.id.userName_editText);
        password_editText=(EditText)findViewById(R.id.password_editText);
        login_main_button=(Button) findViewById(R.id.login_main_btn);
        dbManager=new DatabaseManager(this);
        Intent receiving_intent=getIntent();
        status= Integer.parseInt(receiving_intent.getStringExtra("status"));

        try{
            //opening database
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }


        login_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=username_editText.getText().toString();
                password=password_editText.getText().toString();

                if(dbManager.checking_username_password(username,password)){
                    Toast.makeText(getBaseContext(), "Logged In successfully", Toast.LENGTH_LONG).show();
                    if(status==1){
                        Intent goHome=new Intent(getBaseContext(),MainActivity.class);
                        startActivity(goHome);
                        displayNotification();
                    }else if(status==2){
                        Intent goUpdate=new Intent(getBaseContext(),Update_Info_Activity.class);
                        goUpdate.putExtra("username",username);
                        startActivity(goUpdate);
                    }else{
                        Intent goDelete=new Intent(getBaseContext(),Delete_User_Activity.class);
                        goDelete.putExtra("username",username);
                        startActivity(goDelete);
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Please sign up first", Toast.LENGTH_LONG).show();
                    username_editText.setText("");
                    password_editText.setText("");
                }


            }
        });



        backIntentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent=new Intent(getBaseContext(),MainActivity.class);
                startActivity(HomeIntent);
            }
        });
    }

    private void displayNotification(){

        //Scanning for API Version
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            //if Android Oreo (API 26) or higher we will need to create a channel :
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "finalHomework_channel",
                    android.app.NotificationManager.IMPORTANCE_DEFAULT);
            //setting description for this channel:
            channel.setDescription("This is a description for the Channel");
            //Connecting our channel to Notification Manager:
            android.app.NotificationManager nm = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(channel);
        }


        //Creating pending intent:
        Intent intent=new Intent(this,Login_Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(
                this,0,intent,0);

        //Creating the Notification:
        NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(
                getBaseContext(),CHANNEL_ID);

        mBuilder.setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle("Final Homework")
                .setContentText("Logged in successfully")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //pressing any where on the notification
                .setContentIntent(pendingIntent);
        //viewing  our Notification:
        NotificationManagerCompat nmc= NotificationManagerCompat.from(this);
        //pushing | notify our notification:
        nmc.notify(10, mBuilder.build());

    }

}