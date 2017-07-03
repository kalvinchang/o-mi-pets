package com.example.goodluckexe.topag;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Created by kalvin_1204 on 10/11/16.
 */

public class Player {
    //objects
    public static ArrayList<Pet> petList;
    public static ObjectOutputStream[] petListOut;
    public static ObjectInputStream[] petListIn;
    public static int indexOfCurrentPetSelected = 0;
    public static ObjectOutputStream indexOfCurrentPetSelectedOut;
    public static ObjectInputStream indexOfCurrentPetSelectedIn;
    public static int indexOfTempPetSelected = 0;

    //data fields
    public static String name;
    public static ObjectOutputStream nameOut;
    public static ObjectInputStream nameIn;
    public static int totalTreats;
    public static ObjectOutputStream totalTreatsOut;
    public static ObjectInputStream totalTreatsIn;
    public static int maxPets;
    public static ObjectOutputStream maxPetsOut;
    public static ObjectInputStream maxPetsIn;
    public static Exp exp; //will be zeroed by levelUp()
    public static ObjectOutputStream expOut;
    public static ObjectInputStream expIn;
    public static int pCoins;
    public static ObjectOutputStream pCoinsOut;
    public static ObjectInputStream pCoinsIn;
    public static double longestDistanceOneWorkout;
    public static ObjectOutputStream longestDistanceOneWorkoutOut;
    public static ObjectInputStream longestDistanceOneWorkoutIn;
    public static double totalDistance; //will be incremented by an ActivityScreen method
    public static ObjectOutputStream totalDistanceOut;
    public static ObjectInputStream totalDistanceIn;
    public static long longestWorkout;      //in milliseconds, will be updated when stop button clicked
    public static ObjectOutputStream longestWorkoutOut;
    public static ObjectInputStream longestWorkoutIn;
    public static long totalTimeWorkingOut; //in milliseconds, will be updated when stop button clicked
    public static ObjectOutputStream totalTimeWorkingOutOut;
    public static ObjectInputStream totalTimeWorkingOutIn;
    public static double fastestSpeed; //in mph, will be updated when stop button clicked
    public static ObjectOutputStream fastestSpeedOut;
    public static ObjectInputStream fastestSpeedIn;
    public static int level; //will be updated by levelUp()
    public static ObjectOutputStream levelOut;
    public static ObjectInputStream levelIn;
    public static int lifetimeNumberPets;
    public static ObjectOutputStream lifetimeNumberPetsOut;
    public static ObjectInputStream lifetimeNumberPetsIn;
    public static int currentNumberPets; //will be updated when dog abandons user
    public static ObjectOutputStream currentNumberPetsOut;
    public static ObjectInputStream currentNumberPetsIn;
    public static Date lastActivityDate; //update when Stop button is clicked
    public static ObjectOutputStream lastActivityDateOut;
    public static ObjectInputStream lastActivityDateIn;
    public static boolean firstTime = true;
    public static ObjectOutputStream firstTimeOut;
    public static ObjectInputStream firstTimeIn;

    //constructor, instantiated when you start the game for the first time/ reset
    public Player() {
        petList = new ArrayList<Pet>();
        totalTreats = 4;
        maxPets = 1;
        exp = new Exp(); //will be zeroed by levelUp()
        pCoins = 20;
        longestDistanceOneWorkout = 0;
        totalDistance = 0; //will be incremented by an ActivityScreen method
        longestWorkout = 0;      //in milliseconds, will be updated when stop button clicked
        totalTimeWorkingOut = 0; //in milliseconds, will be updated when stop button clicked
        fastestSpeed = 0; //in mph, will be updated when stop button clicked
        level = 1; //will be updated by levelUp()
        lifetimeNumberPets = 0;
        currentNumberPets = lifetimeNumberPets; //will be updated when dog abandons user
        firstTime = false;
    }

    public static void levelUp(){
        level++;
    }

    public static void reset(){
        firstTime = true;
    }
    public static void save() {
        try {
            firstTimeOut.writeBoolean(firstTime);
            firstTimeOut.close();
            nameOut.write(name.getBytes());
            nameOut.close();
            levelOut.writeInt(level);
            levelOut.close();
            expOut.writeObject(exp);
            expOut.close();
            pCoinsOut.writeInt(pCoins);
            pCoinsOut.close();
            totalTreatsOut.writeInt(totalTreats);
            totalTreatsOut.close();
            totalDistanceOut.writeDouble(totalDistance);
            totalDistanceOut.close();
            totalTimeWorkingOutOut.writeLong(totalTimeWorkingOut);
            totalTimeWorkingOutOut.close();
            longestDistanceOneWorkoutOut.writeDouble(longestDistanceOneWorkout);
            longestDistanceOneWorkoutOut.close();
            longestWorkoutOut.writeDouble(longestWorkout);
            longestWorkoutOut.close();
            fastestSpeedOut.writeDouble(fastestSpeed);
            fastestSpeedOut.close();
            currentNumberPetsOut.writeInt(currentNumberPets);
            currentNumberPetsOut.close();
            maxPetsOut.writeInt(maxPets);
            maxPetsOut.close();
            indexOfCurrentPetSelectedOut.writeInt(indexOfCurrentPetSelected);
            indexOfCurrentPetSelectedOut.close();
            lastActivityDateOut.writeObject(lastActivityDateOut);
            lastActivityDateOut.close();
            petListOut = new ObjectOutputStream[currentNumberPets];
            for (int i = 0; i < currentNumberPets; i++) {
                petListOut[i].writeObject(petList.get(i));
                petListOut[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        try {
            firstTime = firstTimeIn.readBoolean();
            InputStreamReader inputStreamReader = new InputStreamReader(nameIn);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((name = bufferedReader.readLine()) != null) {
                stringBuffer.append(name + "\n");
            }
            name = stringBuffer.toString();
            level = levelIn.readInt();
            pCoins = pCoinsIn.read();
            totalTreats = totalTreatsIn.readInt();
            exp = (Exp)expIn.readObject();
            totalDistance = totalDistanceIn.readDouble();
            totalTimeWorkingOut = totalTimeWorkingOutIn.readLong();
            longestDistanceOneWorkout = longestDistanceOneWorkoutIn.readDouble();
            longestWorkout = longestWorkoutIn.readLong();
            fastestSpeed = fastestSpeedIn.readDouble();
            currentNumberPets = currentNumberPetsIn.readInt();
            maxPets = maxPetsIn.readInt();
            indexOfCurrentPetSelected = indexOfCurrentPetSelectedIn.readInt();
            lastActivityDate = (Date)lastActivityDateIn.readObject();
            for (int i = 0; i < currentNumberPets; i++) {
                petList.add((Pet)petListIn[i].readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



