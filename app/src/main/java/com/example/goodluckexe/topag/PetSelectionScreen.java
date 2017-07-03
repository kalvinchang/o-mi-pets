package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goodluckexe.topag.PetSelector;

import java.util.ArrayList;


public class PetSelectionScreen extends AppCompatActivity {
    TextView level, pcoins, treats;
    String currentLevel, currentPcoins, currentTreats;
    ImageButton storeButton, homeButton, playerStatsButton, settingsButton;
    PopupWindow popupWindow;
    LinearLayout selectionLayout;
    public PetSelector[] petSelectorList = new PetSelector[Player.currentNumberPets];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_pet_selection_screen);

        level = (TextView)findViewById(R.id.level);
        currentLevel = "Level " + Player.level;
        pcoins = (TextView)findViewById(R.id.pcoins);
        currentPcoins = "" + Player.pCoins;
        treats = (TextView)findViewById(R.id.treats);
        currentTreats = "" + Player.totalTreats;
        storeButton = (ImageButton)findViewById(R.id.store_button);
        homeButton = (ImageButton)findViewById(R.id.home_button);
        playerStatsButton = (ImageButton)findViewById(R.id.player_stats_button);
        settingsButton = (ImageButton)findViewById(R.id.settings_button);
        storeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to store screen
                Intent intent = new Intent(getApplicationContext(), StoreScreen.class);
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
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to settings screen
                Intent intent = new Intent(getApplicationContext(), SettingsScreen.class);
                startActivity(intent);
                finish();
            }
        });
        level.setText(currentLevel);
        pcoins.setText(currentPcoins);
        treats.setText(currentTreats);


        selectionLayout = (LinearLayout)findViewById(R.id.selection_layout);
        for (int i = 0; i < Player.currentNumberPets; i++) {
            petSelectorList[i] = new PetSelector(i);
            /*LinearLayout currentPetSelector = (LinearLayout)findViewById(R.id.pet_block);
            petSelectorList[i].petName = (TextView)findViewById(R.id.current_pet_name);
            petSelectorList[i].petName.setText(petSelectorList[i].petSelected.name);
            petSelectorList[i].happinessBar = (ProgressBar)findViewById(R.id.current_happiness);
            petSelectorList[i].happinessBar.setProgress(petSelectorList[i].petSelected.happiness.percent);
            petSelectorList[i].hungerBar = (ProgressBar)findViewById(R.id.current_hunger);
            petSelectorList[i].hungerBar.setProgress(petSelectorList[i].petSelected.hunger.percent);
            petSelectorList[i].petButton = (ImageButton)findViewById(R.id.current_pet_button);
            final int finalI = i;
            petSelectorList[i].petButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //insert code to switch to settings screen
                    Player.indexOfTempPetSelected = petSelectorList[finalI].petSelected.idNumber;
                    Intent intent = new Intent(getApplicationContext(), PetStatsScreen.class);
                    startActivity(intent);
                    finish();
                }
            }); */
            petSelectorList[i].petBlock = new LinearLayout(this);
            petSelectorList[i].petName = new TextView(this);
            petSelectorList[i].petName.setText(petSelectorList[i].petSelected.name);
            petSelectorList[i].happinessBar = new ProgressBar(this);
            petSelectorList[i].happinessBar.setProgress(petSelectorList[i].petSelected.happiness.percent);
            petSelectorList[i].hungerBar = new ProgressBar(this);
            petSelectorList[i].hungerBar.setProgress(petSelectorList[i].petSelected.hunger.percent);
            petSelectorList[i].petButton = new ImageButton(this);

            petSelectorList[i].petBlock.addView(petSelectorList[i].petButton, 0, 90);
            petSelectorList[i].petBlock.addView(petSelectorList[i].petName, 0, 90);
            selectionLayout.addView(findViewById(R.id.pet_block));
        }


    }
}








