package com.example.goodluckexe.topag;

import android.widget.Toast;

import java.util.Date;

/**
 * Created by chees_000 on 10/9/2016.
 */

public class Pet {
    public int idNumber;
    public String name;
    public String birthday = "Birthday: ";
    public Date birthDate;
    public int age; //in days
    public String favFood = "Favorite Food: ";
    public String favExercise = "Favorite Exercise: ";
    public Happiness happiness;
    public Hunger hunger;

    public Pet() {
        Player.currentNumberPets++;
        idNumber = Player.currentNumberPets;
        name = "Needs Name";
        birthDate = new Date();
        birthday += birthDate.toString().substring(4, 10) + birthDate.toString().substring(24, 28);
        age = 0;
        favFood += foods[(int)(Math.random() * 10)];
        favExercise += exercises[(int)(Math.random() * 10)];
        happiness = new Happiness(100);
        hunger = new Hunger(100);
    }

    public Pet(String enteredName) {
        Player.currentNumberPets++;
        idNumber = Player.currentNumberPets;
        name = enteredName;
        birthDate = new Date();
        birthday += birthDate.toString().substring(4, 10) + birthDate.toString().substring(24, 28);
        age = 0;
        favFood += foods[(int)(Math.random() * 10)];
        favExercise += exercises[(int)(Math.random() * 10)];
        happiness = new Happiness(100);
        hunger = new Hunger(100);
    }

    public String[] foods = {"Fried Rice", "Bacon", "Peanut Butter", "Chicken", "Boba", "Pizza",
        "Apples", "Pineapples", "Spam Musubi", "Sushi"};
    public String[] exercises = {"Sleeping", "Walking", "Running", "Jumping Jacks", "Playing Fetch",
        "Push Ups", "Kayaking", "Biking", "Swimming", "Skydiving"};

    public void eat() {
        Player.totalTreats--;
        happiness.percent += 5;
        if (happiness.percent > 100)
            happiness.percent = 100;
        hunger.percent += 10;
        if (hunger.percent > 100)
            hunger.percent = 100;
    }

    public void exercise() {
        happiness.percent += 15;
        if (happiness.percent > 100)
            happiness.percent = 100;
        hunger.percent -= 20;
        if (hunger.percent < 0)
            hunger.percent = 0;
    }

    public void updateAge(Date currentDate) {
        long ageInMilliseconds = currentDate.getTime() - birthDate.getTime();
        age = (int)(ageInMilliseconds / 1000 / 60 / 60 / 24);
    }
}
