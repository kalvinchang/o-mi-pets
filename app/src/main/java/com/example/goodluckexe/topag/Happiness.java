package com.example.goodluckexe.topag;

/**
 * Created by kalvin_1204 on 10/14/16.
 */

public class Happiness {
    int percent;
    boolean[] hasPassed = new boolean[101];
    public Happiness(int percent){
        this.percent = percent;
    }
    /**
     * This method will change the percent of the happiness0
     * @param timeSincePetCreated, will be given the time since app opened
     * @return nothing, only changes the percent
     */
    void decrease(int timeSincePetCreated){
        timeSincePetCreated /= 1000; // turns ms to s
        if(timeSincePetCreated % 10000 < 3 && percent > 0 && !hasPassed[percent]){
            hasPassed[percent] = !hasPassed[percent];
            percent--;
        }
    }
}

