package com.example.goodluckexe.topag;

/**
 * Created by kalvin_1204 on 10/14/16.
 */

public class Hunger {
    int percent;
    boolean[] hasPassed = new boolean[101];
    public Hunger(int percent){
        this.percent = percent;
    }
    /**
     * This method will change the percent of the hunger0
     * @param timeSinceAppOpened, will be given the time since app opened
     * @return nothing, only changes the percent
     */
    void decrease(int timeSinceAppOpened){
        timeSinceAppOpened /= 1000; // turns ms to s
        if(timeSinceAppOpened % 150 < 3 && percent > 0 && !hasPassed[percent]){
            hasPassed[percent] = !hasPassed[percent];
            percent--;
        }
    }
}

