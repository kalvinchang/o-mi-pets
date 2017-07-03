package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MainActivity extends AppCompatActivity {
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
    String currentLevel, currentPcoins, currentTreats;
    ImageButton storeButton, petSelectionButton, playerStatsButton, settingsButton;

    ImageButton feed, startWorkout;
    TextView nameOfPet;
    ProgressBar happiness, hunger, experience;

    /*@Override
    protected void onPause() {
        super.onPause();
        try {
            firstTimeOut = new ObjectOutputStream(openFileOutput("first_time", MODE_PRIVATE));
            Player.firstTimeOut = firstTimeOut;
            firstTimeOut.close();
            nameOut = new ObjectOutputStream(openFileOutput("name", MODE_PRIVATE));
            Player.nameOut = nameOut;
            nameOut.close();
            levelOut = new ObjectOutputStream(openFileOutput("level", MODE_PRIVATE));
            Player.levelOut = levelOut;
            levelOut.close();
            expOut = new ObjectOutputStream(openFileOutput("exp", MODE_PRIVATE));
            Player.expOut = expOut;
            expOut.close();
            pCoinsOut = new ObjectOutputStream(openFileOutput("pcoins", MODE_PRIVATE));
            Player.pCoinsOut = pCoinsOut;
            pCoinsOut.close();
            totalTreatsOut = new ObjectOutputStream(openFileOutput("total_treats", MODE_PRIVATE));
            Player.totalTreatsOut = totalTreatsOut;
            totalTreatsOut.close();
            totalDistanceOut = new ObjectOutputStream(openFileOutput("total_distance", MODE_PRIVATE));
            Player.totalDistanceOut = totalDistanceOut;
            totalDistanceOut.close();
            totalTimeWorkingOutOut = new ObjectOutputStream(openFileOutput("total_time_working_out", MODE_PRIVATE));
            Player.totalTimeWorkingOutOut = totalTimeWorkingOutOut;
            totalTimeWorkingOutOut.close();
            longestDistanceOneWorkoutOut = new ObjectOutputStream(openFileOutput("longest_distance_one_workout", MODE_PRIVATE));
            Player.longestDistanceOneWorkoutOut = longestDistanceOneWorkoutOut;
            longestDistanceOneWorkoutOut.close();
            longestWorkoutOut = new ObjectOutputStream(openFileOutput("longest_workout", MODE_PRIVATE));
            Player.longestWorkoutOut = longestWorkoutOut;
            longestWorkoutOut.close();
            fastestSpeedOut = new ObjectOutputStream(openFileOutput("fastest_speed", MODE_PRIVATE));
            Player.fastestSpeedOut = fastestSpeedOut;
            fastestSpeedOut.close();
            currentNumberPetsOut = new ObjectOutputStream(openFileOutput("current_number_pets_in", MODE_PRIVATE));
            Player.currentNumberPetsOut = currentNumberPetsOut;
            currentNumberPetsOut.close();
            maxPetsOut = new ObjectOutputStream(openFileOutput("max_pet", MODE_PRIVATE));
            Player.maxPetsOut = maxPetsOut;
            maxPetsOut.close();
            indexOfCurrentPetSelectedOut = new ObjectOutputStream(openFileOutput("index_of_current_pet_selected", MODE_PRIVATE));
            Player.indexOfCurrentPetSelectedOut = indexOfCurrentPetSelectedOut;
            indexOfCurrentPetSelectedOut.close();
            lastActivityDateOut = new ObjectOutputStream(openFileOutput("last_activity_date", MODE_PRIVATE));
            Player.lastActivityDateOut = lastActivityDateOut;
            lastActivityDateOut.close();
            petListOut = new ObjectOutputStream[Player.currentNumberPets];
            for (int i = 0; i < Player.currentNumberPets; i++) {
                petListOut[i] = new ObjectOutputStream(openFileOutput(("pet_" + i), MODE_PRIVATE));
                Player.petListOut[i] = petListOut[i];
                petListOut[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player.save();

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen); // (for Android Built In Theme)
        this.setContentView(R.layout.activity_main);


        level = (TextView)findViewById(R.id.level);
        currentLevel = "Level " + Player.level;
        pcoins = (TextView)findViewById(R.id.pcoins);
        currentPcoins = "" + Player.pCoins;
        treats = (TextView)findViewById(R.id.treats);
        currentTreats = "" + Player.totalTreats;
        storeButton = (ImageButton)findViewById(R.id.store_button);
        petSelectionButton = (ImageButton)findViewById(R.id.pet_selection_button);
        playerStatsButton = (ImageButton)findViewById(R.id.player_stats_button);
        settingsButton = (ImageButton)findViewById(R.id.settings_button);


        nameOfPet = (TextView)findViewById(R.id.pet_name);
        feed = (ImageButton)findViewById(R.id.feedmebutton);
        startWorkout = (ImageButton)findViewById(R.id.startstopbutton);
        happiness = (ProgressBar)findViewById(R.id.happiness);
        hunger = (ProgressBar)findViewById(R.id.hunger);
        experience = (ProgressBar)findViewById(R.id.EXPprogress);


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



        nameOfPet.setText(Player.petList.get(Player.indexOfCurrentPetSelected).name);
        happiness.setProgress(Player.petList.get(Player.indexOfCurrentPetSelected).happiness.percent);
        hunger.setProgress(Player.petList.get(Player.indexOfCurrentPetSelected).hunger.percent);
        experience.setMax(Player.exp.maxEXP);
        experience.setProgress(Player.exp.currentEXP);
        feed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to feed current pet
                if (Player.totalTreats > 0) {
                    Player.petList.get(Player.indexOfCurrentPetSelected).eat();
                    Toast.makeText(MainActivity.this, "You now have " + Player.totalTreats + " left.", Toast.LENGTH_SHORT).show();
                    treats.setText((Player.totalTreats+""));
                }
                else {
                    Toast.makeText(MainActivity.this, "You have 0 treats.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        startWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to start workout
                Intent intent = new Intent(getApplicationContext(), WorkoutScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }
    /*public void popUpTheTutorialScreen(){
        Player.firstTime = false;
        layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.theTutorialLayoutStart);

        final View popupView = layoutInflater.inflate(R.layout.tutorialpopup, null);


        final PopupWindow popupWindow = new PopupWindow(popupView, 1500, 1200);
        popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY,500,500);
        final EditText petname = (EditText)popupView.findViewById(R.id.InsertPetName);
        final EditText playername = (EditText)popupView.findViewById(R.id.InsertPlayerName);
        final Button finishTutorial = (Button)findViewById(R.id.theFinishTutorialButton);
        finishTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player.petList.get(0).name = petname.getText().toString();
                nameOfPet.setText(petname.getText());
                Player.name = playername.getText().toString() + "";
                popupWindow.dismiss();
            }
        });
        nameOfPet.setText(Player.petList.get(Player.indexOfCurrentPetSelected).name);
        happiness.setProgress(Player.petList.get(Player.indexOfCurrentPetSelected).happiness.percent);
        hunger.setProgress(Player.petList.get(Player.indexOfCurrentPetSelected).hunger.percent);
        experience.setMax(Player.exp.maxEXP);
        experience.setProgress(Player.exp.currentEXP);
    }*/
}


