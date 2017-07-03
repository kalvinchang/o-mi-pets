package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class PetStatsScreen extends AppCompatActivity {
    TextView petName, birthday, age, favoriteFood, favoriteExercise;
    String ageInDays;
    ProgressBar happiness, hunger;
    Button selectButton, feedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_pet_stats_screen);

        petName = (TextView)findViewById(R.id.pet_name);
        birthday = (TextView)findViewById(R.id.birthday);
        age = (TextView)findViewById(R.id.age);
        favoriteFood = (TextView)findViewById(R.id.favorite_food);
        favoriteExercise = (TextView)findViewById(R.id.favorite_exercise);
        happiness = (ProgressBar)findViewById(R.id.happiness);
        hunger = (ProgressBar)findViewById(R.id.hunger);
        selectButton = (Button)findViewById(R.id.select_button);
        feedButton = (Button)findViewById(R.id.feed_button);

        petName.setText(Player.petList.get(Player.indexOfTempPetSelected).name);
        birthday.setText(Player.petList.get(Player.indexOfTempPetSelected).birthday);
        Player.petList.get(Player.indexOfTempPetSelected).age = ((int)(System.currentTimeMillis() - Player.petList.get(Player.indexOfTempPetSelected).birthDate.getTime())) / 1000 / 60 / 60 / 24;
        ageInDays = "Age: " + Player.petList.get(Player.indexOfTempPetSelected).age;
        age.setText(ageInDays);
        favoriteFood.setText(Player.petList.get(Player.indexOfTempPetSelected).favFood);
        favoriteExercise.setText(Player.petList.get(Player.indexOfTempPetSelected).favExercise);
        happiness.setProgress(Player.petList.get(Player.indexOfTempPetSelected).happiness.percent);
        hunger.setProgress(Player.petList.get(Player.indexOfTempPetSelected).hunger.percent);
        feedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to feed current pet
                if (Player.totalTreats > 0) {
                    Player.petList.get(Player.indexOfTempPetSelected).eat();
                    Toast.makeText(PetStatsScreen.this, "You now have " + Player.totalTreats + " left.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PetStatsScreen.this, "You have 0 treats.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        selectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to select current pet
                Player.indexOfCurrentPetSelected = Player.indexOfTempPetSelected;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
