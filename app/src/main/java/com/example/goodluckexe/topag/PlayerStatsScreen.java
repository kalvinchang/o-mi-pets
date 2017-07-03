package com.example.goodluckexe.topag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ObjectOutputStream;

public class PlayerStatsScreen extends AppCompatActivity {
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
    TextView level, pcoins, treats;
    String currentLevel, currentPcoins, currentTreats; //currentpcoins will be used for the player stats as well
    ImageButton storeButton, petSelectionButton, homeButton, settingsButton;
    EditText playerNameEdit;

    TextView miles, hourMin, numberPCoins, numberEXP, petNumber, maxPetNumber, maxDistMile, longestWorkoutTime, fastestPaceTime;
    int totalHours, totalMins, workoutHours, workoutMins;
    String currentMiles, currentHourMin, currentNumberEXP, currentPetNumber, currentMaxDistMile, currentLongestWorkoutTime, currentFastestPaceTime; //currentPcoins will be used for pcoins


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        this.setContentView(R.layout.activity_player_stats_screen);

        //PlayerName
        playerNameEdit = (EditText)findViewById(R.id.player_name);

        //status bar
        level = (TextView)findViewById(R.id.level);
        currentLevel = "Level " + Player.level;
        pcoins = (TextView)findViewById(R.id.pcoins);
        currentPcoins = "" + Player.pCoins;
        treats = (TextView)findViewById(R.id.treats);
        currentTreats = "" + Player.totalTreats;

        //menu bar
        storeButton = (ImageButton)findViewById(R.id.store_button);
        petSelectionButton = (ImageButton)findViewById(R.id.pet_selection_button);
        homeButton = (ImageButton)findViewById(R.id.home_button);
        settingsButton = (ImageButton)findViewById(R.id.settings_button);

        numberPCoins = (TextView) findViewById(R.id.numberpcoins);
        numberPCoins.setText(currentPcoins);
        //player stats table: concatenate every other row into Strings b/c .setText only accepts Strings
        miles = (TextView)findViewById(R.id.miles);
        currentMiles = Player.totalDistance + " miles";
        hourMin = (TextView)findViewById(R.id.hourmin);
        totalHours = (int)(Player.totalTimeWorkingOut / 1000 / 60 / 60);
        totalMins = (int)(Player.totalTimeWorkingOut / 1000 / 60 % 60);
        currentHourMin = totalHours + " hours " +  totalMins  + " min";        //pull from FormatTime (arg = player.totalTimeWorkingOut)
        numberEXP = (TextView)findViewById(R.id.numberEXP);
        currentNumberEXP = Player.exp.currentEXP + "/" + Player.exp.maxEXP;
        petNumber = (TextView)findViewById(R.id.petnumber);
        currentPetNumber = "" + Player.currentNumberPets;
        maxPetNumber = (TextView)findViewById(R.id.maxpetnumber);
        maxDistMile = (TextView)findViewById(R.id.maxdistmile);
        currentMaxDistMile = (Math.round(Player.longestDistanceOneWorkout * 1000) / 1000.0) + " miles";
        workoutHours = (int)(Player.longestWorkout / 1000 / 60 / 60);
        workoutMins = (int)(Player.longestWorkout / 1000 / 60 % 60);
        longestWorkoutTime = (TextView)findViewById(R.id.longestworkouttime);
        currentLongestWorkoutTime =  workoutHours  + " hours " +  workoutMins   + " min";    //pull from FormatTime (arg = player.longestWorkout)
        fastestPaceTime = (TextView)findViewById(R.id.fastestpacetime); //this name is counterintuitive, it's mph not hours
        currentFastestPaceTime = Player.fastestSpeed + " mph";

        //menu bar buttons
        storeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to store screen
                updateName();
                Intent intent = new Intent(getApplicationContext(), StoreScreen.class);
                startActivity(intent);
                finish();
            }
        });
        petSelectionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to pet selection screen
                updateName();
                Intent intent = new Intent(getApplicationContext(), PetSelectionScreen.class);
                startActivity(intent);
                finish();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to home screen
                updateName();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to settings screen
                updateName();
                Intent intent = new Intent(getApplicationContext(), SettingsScreen.class);
                startActivity(intent);
                finish();
            }
        });

        //for the status bar
        level.setText(currentLevel);
        pcoins.setText(currentPcoins);
        treats.setText(currentTreats);

        //player stats table
        playerNameEdit.setText(Player.name);
        miles.setText(currentMiles);
        hourMin.setText(currentHourMin);
        numberEXP.setText(currentNumberEXP);
        petNumber.setText(currentPetNumber);
        maxPetNumber.setText(("" + Player.maxPets));
        maxDistMile.setText(currentMaxDistMile);
        longestWorkoutTime.setText(currentLongestWorkoutTime);
        fastestPaceTime.setText(currentFastestPaceTime);

        //create a method that constantly updates the player stats data

    }
    public void updateName(){
        String playerName = playerNameEdit.getText().toString();
        Player.name = playerName + "";
    }
}

