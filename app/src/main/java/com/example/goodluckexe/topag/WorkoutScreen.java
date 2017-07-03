package com.example.goodluckexe.topag;

import android.content.Intent;
        import android.os.Handler;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import java.io.FileOutputStream;

        import android.view.View;
        import android.widget.Chronometer;
        import android.widget.ImageButton;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;


public class WorkoutScreen extends AppCompatActivity {
    TextView level, pcoins, treats;
    String currentLevel, currentPcoins, currentTreats;


    Handler handler = new Handler();
    Exercise currentWorkout;
    boolean workoutHasStopped = false;
    ImageButton stop;
    TextView time, speed, distanceNumber, pcoinsNumber, expNumber, treatNumber;
    ProgressBar speedBar;
    int hours, minutes, seconds;
    String exerciseSpeed, exercisePcoins, exerciseExp, exerciseTreats, exerciseDistance, exerciseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_workout_screen);

        level = (TextView)findViewById(R.id.level);
        currentLevel = "Level " + Player.level;
        pcoins = (TextView)findViewById(R.id.pcoins);
        currentPcoins = "" + Player.pCoins;
        treats = (TextView)findViewById(R.id.treats);
        currentTreats = "" + Player.totalTreats;
        level.setText(currentLevel);
        pcoins.setText(currentPcoins);
        treats.setText(currentTreats);

        stop = (ImageButton)findViewById(R.id.stop);
        speedBar = (ProgressBar)findViewById(R.id.speedbar);
        time = (TextView)findViewById(R.id.time);
        speed = (TextView)findViewById(R.id.speed);
        distanceNumber = (TextView)findViewById(R.id.distancenumber);
        pcoinsNumber = (TextView)findViewById(R.id.pcoinsnumber);
        expNumber = (TextView)findViewById(R.id.EXPnumber);
        treatNumber = (TextView)findViewById(R.id.treatnumber);
        currentWorkout = new Exercise();

        runConstantly();
    }
    public void runConstantly(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                methodForRunConstantly();
            }
        };
        new Thread(runnable).start();
    }
    public void methodForRunConstantly(){
        for(int i = 0;i <= 1000000000; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run(){
                    runWorkout();
                }
            });
        }
    }
    public void runWorkout() {
        if (!workoutHasStopped) {
            currentWorkout.updateData();
        }
        hours = (int)(currentWorkout.currentTime / 1000 / 60 / 60);
        minutes = (int)(currentWorkout.currentTime / 1000 / 60 % 60);
        seconds = (int)(currentWorkout.currentTime / 1000 % 60 % 60);
        exerciseTime = hours + ":" + minutes + ":" + seconds;
        exerciseSpeed = String.format("%.2f", currentWorkout.currentSpeed) + " mph";
        exerciseDistance = String.format("%.3f", currentWorkout.distanceTraveled) + " miles";
        exercisePcoins = "" + currentWorkout.pCoinsGained;
        exerciseExp = "" + currentWorkout.experienceGained;
        exerciseTreats = "" + currentWorkout.treatsGained;

        time.setText(exerciseTime);
        speed.setText(exerciseSpeed);
        speedBar.setProgress((int)currentWorkout.currentSpeed);
        distanceNumber.setText(exerciseDistance);
        pcoinsNumber.setText(exercisePcoins);
        expNumber.setText(exerciseExp);
        treatNumber.setText(exerciseTreats);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to end workout
                workoutHasStopped = true;
                stop();}
        });
    }
    public void stop(){
        currentWorkout.bonusRewards();
        Toast.makeText(WorkoutScreen.this, "Total Time: " + hours + ":" + minutes + ":" + seconds, Toast.LENGTH_SHORT).show();
        Toast.makeText(WorkoutScreen.this, "Total Distance Traveled: " + String.format("%.3f", currentWorkout.distanceTraveled), Toast.LENGTH_SHORT).show();
        Toast.makeText(WorkoutScreen.this, "Number of Stopping Periods: " + currentWorkout.numberOfStoppingPeriods, Toast.LENGTH_SHORT).show();
        Toast.makeText(WorkoutScreen.this, "Fastest Speed: " + currentWorkout.fastestSpeed, Toast.LENGTH_SHORT).show();
        Toast.makeText(WorkoutScreen.this, "Experience Earned: " + currentWorkout.experienceGained, Toast.LENGTH_SHORT).show();
        Toast.makeText(WorkoutScreen.this, "PCoins Earned: " + currentWorkout.pCoinsGained, Toast.LENGTH_SHORT).show();
        Toast.makeText(WorkoutScreen.this, "Treats Earned: " + currentWorkout.treatsGained, Toast.LENGTH_SHORT).show();

        if (Player.fastestSpeed < currentWorkout.fastestSpeed)
            Player.fastestSpeed = currentWorkout.fastestSpeed;
        if (Player.longestWorkout < currentWorkout.currentTime)
            Player.longestWorkout = currentWorkout.currentTime;
        if (Player.longestDistanceOneWorkout < currentWorkout.distanceTraveled)
            Player.longestDistanceOneWorkout = currentWorkout.distanceTraveled;

        Player.exp.currentEXP += currentWorkout.experienceGained;
        Player.pCoins += currentWorkout.pCoinsGained;
        Player.totalTreats += currentWorkout.treatsGained;
        Player.totalTimeWorkingOut += currentWorkout.currentTime;
        Player.totalDistance += currentWorkout.distanceTraveled;
        Player.lastActivityDate = currentWorkout.dateOfExercise;

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(13500);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}

