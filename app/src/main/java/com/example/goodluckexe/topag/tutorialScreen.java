package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tutorialScreen extends AppCompatActivity {
    String petname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_tutorial_screen);


        final EditText petName = (EditText)findViewById(R.id.petNameInputTutorial);
        final EditText playerName = (EditText)findViewById(R.id.playerNameInputTutorial);

        final Button button = (Button)findViewById(R.id.tutorialFinishedButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petname = petName.getText().toString();
                Player.name = playerName.getText().toString();
                Player.petList.add(new Pet(petname));
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
