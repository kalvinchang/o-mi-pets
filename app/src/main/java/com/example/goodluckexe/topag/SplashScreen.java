package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SplashScreen extends AppCompatActivity {
    ObjectInputStream firstTimeIn;
    ObjectInputStream nameIn;
    ObjectInputStream levelIn;
    ObjectInputStream expIn;
    ObjectInputStream pCoinsIn;
    ObjectInputStream totalTreatsIn;
    ObjectInputStream totalDistanceIn;
    ObjectInputStream totalTimeWorkingOutIn;
    ObjectInputStream longestDistanceOneWorkoutIn;
    ObjectInputStream longestWorkoutIn;
    ObjectInputStream fastestSpeedIn;
    ObjectInputStream currentNumberPetsIn;
    ObjectInputStream maxPetsIn;
    ObjectInputStream indexOfCurrentPetSelectedIn;
    ObjectInputStream lastActivityDateIn;
    ObjectInputStream[] petListIn;


    /*@Override
    protected void onStart() {
        super.onStart();
        try {
            firstTimeIn = new ObjectInputStream(openFileInput("first_time"));
            Player.firstTimeIn = firstTimeIn;
            nameIn = new ObjectInputStream(openFileInput("name"));
            Player.nameIn = nameIn;
            levelIn = new ObjectInputStream(openFileInput("level"));
            Player.levelIn = levelIn;
            expIn = new ObjectInputStream(openFileInput("exp"));
            Player.expIn = expIn;
            pCoinsIn = new ObjectInputStream(openFileInput("pcoins"));
            Player.pCoinsIn = pCoinsIn;
            totalTreatsIn = new ObjectInputStream(openFileInput("total_treats"));
            Player.totalTreatsIn = totalTreatsIn;
            totalDistanceIn = new ObjectInputStream(openFileInput("total_distance"));
            Player.totalDistanceIn = totalDistanceIn;
            totalTimeWorkingOutIn = new ObjectInputStream(openFileInput("total_time_working_out"));
            Player.totalTimeWorkingOutIn = totalTimeWorkingOutIn;
            longestDistanceOneWorkoutIn = new ObjectInputStream(openFileInput("longest_distance_one_workout"));
            Player.longestDistanceOneWorkoutIn = longestDistanceOneWorkoutIn;
            longestWorkoutIn = new ObjectInputStream(openFileInput("longest_workout"));
            Player.longestWorkoutIn = longestWorkoutIn;
            fastestSpeedIn = new ObjectInputStream(openFileInput("fastest_speed"));
            Player.fastestSpeedIn = fastestSpeedIn;
            currentNumberPetsIn = new ObjectInputStream(openFileInput("current_number_pets_in"));
            Player.currentNumberPetsIn = currentNumberPetsIn;
            maxPetsIn = new ObjectInputStream(openFileInput("max_pet"));
            Player.maxPetsIn = maxPetsIn;
            indexOfCurrentPetSelectedIn = new ObjectInputStream(openFileInput("index_of_current_pet_selected"));
            Player.indexOfCurrentPetSelectedIn = indexOfCurrentPetSelectedIn;
            lastActivityDateIn = new ObjectInputStream(openFileInput("last_activity_date"));
            Player.lastActivityDateIn = lastActivityDateIn;
            for (int i = 0; i < Player.currentNumberPets; i++) {
                petListIn[i] = new ObjectInputStream(openFileInput(("pet_" + i)));
                Player.petListIn[i] = petListIn[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player.update();
    )*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if(Player.firstTime) {
                        final Player player = new Player();
                        Intent intent = new Intent(getApplicationContext(), tutorialScreen.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}

