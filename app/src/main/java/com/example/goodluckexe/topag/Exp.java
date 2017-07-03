package com.example.goodluckexe.topag;

/**
 * Created by kalvin_1204 on 10/14/16.
 */

public class Exp {
    int maxEXP;
    int currentEXP;
    public Exp(){
        maxEXP = 300;
        currentEXP = 0;
    }
    void reset(){
        currentEXP = maxEXP - currentEXP;
        maxEXP = (maxEXP * 15) / 10; //Increases the Max Exp by 50% every reset
    }
    void update(){
        if(currentEXP >= maxEXP){
            reset();
            Player.levelUp();
        }
    }

}


