package com.example.goodluckexe.topag;

import android.content.Context;
import android.location.Location;
import android.view.View;

import java.io.FileOutputStream;
import java.util.Date;
import java.io.File;
import java.util.logging.Handler;

/**
 * Created by chees_000 on 10/11/2016.
 */

public class Exercise {

    public int idNumber;
    public static int totalExercises = 0;
    public long startTime; //milliseconds
    public long currentTime; //milliseconds
    public Location currentLocation;
    public Location previousLocation;
    public double distanceTraveled; //miles
    public double currentSpeed; //miles per hour
    public int pCoinsGained;
    public int experienceGained;
    public int treatsGained;

    public double fastestSpeed = currentSpeed;
    public long longestTimeInMotion = currentTime;
    public int numberOfStoppingPeriods = 0;

    public int bonusExperience;
    public Date dateOfExercise;


    public Exercise() {
        totalExercises++;
        idNumber = totalExercises;
        startTime = System.currentTimeMillis();
        currentTime = startTime;
        currentLocation = new Location("current");
        previousLocation = new Location(currentLocation);
        distanceTraveled = 0;
        currentSpeed = 0;
        pCoinsGained = 0;
        experienceGained = 0;
        treatsGained = 0;
        dateOfExercise = new Date();
    }

    public void updateCurrentTime() {
        currentTime = System.currentTimeMillis() - startTime;
    }
    public void updateLocation() {
        previousLocation = new Location(currentLocation);
        currentLocation = new Location("current");
    }
    public void updateDistanceTraveled() {
        distanceTraveled += (currentLocation.distanceTo(previousLocation) * 0.000621371);
    }
    public void updateCurrentSpeed() {
        currentSpeed = (currentLocation.getSpeed() * 2.23694);
    }
    public void updatePCoinsGained() {
        pCoinsGained = (int)distanceTraveled * 8;
        if (distanceTraveled >= 3)
            pCoinsGained += (int)distanceTraveled * 7;
        if (distanceTraveled >= 10)
            pCoinsGained += (int)distanceTraveled * 5;
    }
    public void updateExperienceGained() {
        experienceGained = (int)distanceTraveled * 400;
        if (distanceTraveled >= 2)
            experienceGained += (int)distanceTraveled * 250;
        if (distanceTraveled >= 10)
            experienceGained += (int)distanceTraveled * 100;
    }
    public void updateTreatsGained() {
        treatsGained = (int)distanceTraveled;
    }
    public void updateFastestSpeed() {
        if (fastestSpeed < currentSpeed) {
            fastestSpeed = currentSpeed;
        }
    }

    public void updateData() {
        updateCurrentTime();
        updateLocation();
        updateDistanceTraveled();
        updateCurrentSpeed();
        updateFastestSpeed();
        updatePCoinsGained();
        updateExperienceGained();
        updateTreatsGained();
    }

    public void bonusRewards() {
        bonusExperience = ((int)longestTimeInMotion / 6000) * 10;
        switch (numberOfStoppingPeriods) {
            case 1:
                bonusExperience += 50;
                break;
            case 2:
                bonusExperience += 40;
                break;
            case 3:
                bonusExperience += 30;
                break;
            case 4:
                bonusExperience += 20;
                break;
            case 5:
                bonusExperience += 10;
                break;
            default:
                break;
        }
        if (fastestSpeed >= 15) {
            bonusExperience += 100;
            bonusExperience += (fastestSpeed - 15) * 5;
        }
    }

}

