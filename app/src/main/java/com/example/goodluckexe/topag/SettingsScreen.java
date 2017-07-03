package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SettingsScreen extends AppCompatActivity {
    ObjectOutputStream firstTimeOut;
    ObjectOutputStream nameOut;
    ObjectOutputStream levelOut;
    ObjectOutputStream expOut;
    ObjectOutputStream pCoinsOut;
    ObjectOutputStream totalTreatsOut;
    ObjectOutputStream totalDistanceOut;
    ObjectOutputStream totalTimeWorkingOutOut;
    ObjectOutputStream longestDistanceOneWorkoutOut;
    ObjectOutputStream longestWorkoutOut;
    ObjectOutputStream fastestSpeedOut;
    ObjectOutputStream currentNumberPetsOut;
    ObjectOutputStream maxPetsOut;
    ObjectOutputStream indexOfCurrentPetSelectedOut;
    ObjectOutputStream lastActivityDateOut;
    ObjectOutputStream[] petListOut;

    // POP UP STUFF
    Button button_yes,button_no;
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;


    TextView level, pcoins, treats;
    String currentLevel, currentPcoins, currentTreats;
    ImageButton storeButton, petSelectionButton, homeButton, playerStatsButton;

    Switch aSwitch;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_settings_screen);

        level = (TextView)findViewById(R.id.level);
        currentLevel = "Level " + Player.level;
        pcoins = (TextView)findViewById(R.id.pcoins);
        currentPcoins = "" + Player.pCoins;
        treats = (TextView)findViewById(R.id.treats);
        currentTreats = "" + Player.totalTreats;
        storeButton = (ImageButton)findViewById(R.id.store_button);
        petSelectionButton = (ImageButton)findViewById(R.id.pet_selection_button);
        homeButton = (ImageButton)findViewById(R.id.home_button);
        playerStatsButton = (ImageButton)findViewById(R.id.player_stats_button);

        storeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to store screen
                Intent intent = new Intent(getApplicationContext(), StoreScreen.class);
                startActivity(intent);
                finish();
            }
        });
        petSelectionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to pet selection screen
                Intent intent = new Intent(getApplicationContext(), PetSelectionScreen.class);
                startActivity(intent);
                finish();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to home screen
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        playerStatsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to player stats screen
                Intent intent = new Intent(getApplicationContext(), PlayerStatsScreen.class);
                startActivity(intent);
                finish();
            }
        });
        level.setText(currentLevel);
        pcoins.setText(currentPcoins);
        treats.setText(currentTreats);


        aSwitch = (Switch)findViewById(R.id.notificationswitch);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    //enable notifications
                }
                else{
                    //disable notifications
                }
            }
        });


        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.reset_popup,null);

                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.theLinearSettingsLayout);

                popupWindow = new PopupWindow(popupView, 1500, 1200);
                popupWindow.showAtLocation(linearLayout,Gravity.NO_GRAVITY,500,500);

                button_yes = (Button)popupView.findViewById(R.id.reset_yes);
                button_no = (Button)popupView.findViewById(R.id.reset_no);
                button_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Player.reset();
                        Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
                        startActivity(intent);
                        finish();
                        popupWindow.dismiss();
                    }
                });
                button_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

            }
        });


    }
}

