package com.example.goodluckexe.topag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.Gravity;

public class StoreScreen extends AppCompatActivity {
    TextView level, pcoins, treats;
    String currentLevel, currentPcoins, currentTreats;
    ImageButton petSelectionButton, homeButton, playerStatsButton, settingsButton;
    ImageButton buyPet, buyTreat, buyPetSlot;
    LayoutInflater layoutInflater;
    PopupWindow buyPetsPopUpWindow;

    int order = 0; //default order = 1 pet, place elsewhere?
    int cost = 0; //default cost = 20 pcoins

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_store_screen);

        //status/ menu bar
        level = (TextView)findViewById(R.id.level);
        currentLevel = "Level " + Player.level;
        pcoins = (TextView)findViewById(R.id.pcoins);
        currentPcoins = "" + Player.pCoins;
        treats = (TextView)findViewById(R.id.treats);
        currentTreats = "" + Player.totalTreats;
        petSelectionButton = (ImageButton)findViewById(R.id.pet_selection_button);
        homeButton = (ImageButton)findViewById(R.id.home_button);
        playerStatsButton = (ImageButton)findViewById(R.id.player_stats_button);
        settingsButton = (ImageButton)findViewById(R.id.settings_button);

        petSelectionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to pet selection screen
                Intent intent = new Intent(getApplicationContext(), PetSelectionScreen.class);
                startActivity(intent);
                finish();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to home screen
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        playerStatsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to player stats screen
                Intent intent = new Intent(getApplicationContext(), PlayerStatsScreen.class);
                startActivity(intent);
                finish();
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to switch to settings screen
                Intent intent = new Intent(getApplicationContext(), SettingsScreen.class);
                startActivity(intent);
                finish();
            }
        });
        level.setText(currentLevel);
        pcoins.setText(currentPcoins);
        treats.setText(currentTreats);

        //store buttons

        buyPet = (ImageButton)findViewById(R.id.buy_pet);
        buyPetSlot = (ImageButton)findViewById(R.id.buy_pet_slot);
        buyTreat = (ImageButton)findViewById(R.id.buy_treat);

        /*
        buyPetSlot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to add slots
                if(Player.pCoins >= 50) {
                    Player.maxPets++;
                    Player.pCoins -= 50;
                    Toast.makeText(StoreScreen.this, "You now have " + Player.maxPets + " pet slots."
                            , Toast.LENGTH_SHORT).show();
                    updateCoinsTreats();
                }
                else{
                    Toast.makeText(StoreScreen.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
                }

                //copy, paste, adjust code for buyPet.setOnClickListener()
            }
        });
        */


        buyPet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                final View buyPetsPopUpView = layoutInflater.inflate(R.layout.buypetspopup, null);
                LinearLayout buypetspopuplayout = (LinearLayout)findViewById(R.id.linearPopLayout);
                buyPetsPopUpWindow = new PopupWindow(buyPetsPopUpView, LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
                buyPetsPopUpWindow.showAtLocation(buypetspopuplayout, Gravity.NO_GRAVITY, 500, 500);

                Button buyPetYes = (Button)buyPetsPopUpView.findViewById(R.id.buypet_yes);
                Button buyPetNo = (Button)buyPetsPopUpView.findViewById(R.id.buypet_no);

                //dismiss the popup if you click no
                buyPetNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buyPetsPopUpWindow.dismiss();
                    }
                });

                 //1. go onto buypetspopupyes.xml if you click yes
                 //2. user specifies amount
                 //3. display toast "insufficient funds" if not enough money
                 //4. display toast "You have bought " + order + " pets" if enough money
                 //5. go to buypetspopupyesenough.xml if enough money
                 //6. instantiate new pet when "SUBMIT" is clicked


                //1
                buyPetYes.setOnClickListener(new View.OnClickListener() {
                    //is moving this here ok? (it's supposed be to inside onClick()


                    // final Button incrementButton = (Button)buyPetsPopUpYesView.findViewById(R.id.)

                    View buyPetsPopUpYesView;
                    @Override
                    public void onClick(View v) {

                        buyPetsPopUpYesView = layoutInflater.inflate(R.layout.buypetspopupyes, null);
                        final PopupWindow buyPetsPopUpYesWindow = new PopupWindow(buyPetsPopUpYesView, LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT);
                        buyPetsPopUpYesWindow.showAtLocation(findViewById(R.id.linearPopLayout), Gravity.NO_GRAVITY, 400, 500);
                        //display buypetspopupyes
                        layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                        buyPetsPopUpWindow.dismiss();
                        displayOrderCost(order, cost);



                        // Increment/Decrement Buttons
                        Button incrementButton = (Button)buyPetsPopUpYesView.findViewById(R.id.increment);
                        Button decrementButton = (Button)buyPetsPopUpYesView.findViewById(R.id.decrement);
                        Button submitOrderButton = (Button)buyPetsPopUpYesView.findViewById(R.id.submitOrder);

                        incrementButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if ((Player.currentNumberPets + order) >= Player.maxPets) {
                                    Toast.makeText(StoreScreen.this, "You need to buy more PetSlots.", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    order++;
                                    cost += 20;
                                    displayOrderCost(order, cost);
                                }
                            }
                        });
                        decrementButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if ((order - 1) < 1) {
                                    Toast.makeText(StoreScreen.this, "You can't sell Pets.", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    order--;
                                    cost -= 20;
                                    displayOrderCost(order, cost);
                                }
                            }
                        });
                        submitOrderButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Player.pCoins < 20) {
                                    Toast.makeText(StoreScreen.this, "Insufficient Funds.", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    //4
                                    //charge user for purchase
                                    Player.pCoins -= cost;
                                    updateCoinsTreats();

                                    Toast.makeText(StoreScreen.this, "You have bought " + order + " pets.", Toast.LENGTH_SHORT).show();
                                    //go to screen buypetspopupyesenough (order) amount of times

                                    for (int i = 0; i < order; i++) {
                                        layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                                        final View buyPetsPopUpYesEnoughView = layoutInflater.inflate(R.layout.buypetspopupyesenough,null);
                                        final PopupWindow buyPetsPopUpYesEnoughWindow = new PopupWindow(buyPetsPopUpYesEnoughView, LayoutParams.WRAP_CONTENT,
                                                LayoutParams.WRAP_CONTENT);
                                        final EditText petName = (EditText)buyPetsPopUpYesEnoughView.findViewById(R.id.user_enterred_name);;
                                        buyPetsPopUpYesEnoughWindow.showAtLocation(findViewById(R.id.linearPopLayout),Gravity.NO_GRAVITY,400,500);
                                        final String enteredName = petName.getText().toString();
                                        Button submitButton = (Button)buyPetsPopUpYesEnoughView.findViewById(R.id.submitButtonOnEnough);
                                        buyPetsPopUpYesEnoughWindow.setFocusable(true);
                                        buyPetsPopUpYesEnoughWindow.update();
                                        submitButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Player.petList.add(new Pet(enteredName));
                                                buyPetsPopUpYesEnoughWindow.dismiss();
                                            }
                                        });
                                    }
                                }
                                buyPetsPopUpYesWindow.dismiss();
                            }
                        });

                    }

                    //displays the number of pets user is attempting to order (order) and cost of order (cost)
                    public void displayOrderCost(int orderVariable, int costVariable) {
                        TextView order = (TextView)buyPetsPopUpYesView.findViewById(R.id.order);
                        order.setText(("" + orderVariable));
                        TextView cost = (TextView)buyPetsPopUpYesView.findViewById(R.id.cost);
                        cost.setText(("" + costVariable));
                    }
                });








                //later: stop allowing the user to press increment button at max # pets / level
            }
        });

        buyPetSlot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to add slots
                if(Player.pCoins >= 50) {
                    Player.maxPets++;
                    Player.pCoins -= 50;
                    Toast.makeText(StoreScreen.this, "You now have " + Player.maxPets + " pet slots."
                            , Toast.LENGTH_SHORT).show();
                    updateCoinsTreats();
                }
                else{
                    Toast.makeText(StoreScreen.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
                }

                //copy, paste, adjust code for buyPet.setOnClickListener()
            }
        });

        buyTreat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //insert code to add treats
                if(Player.pCoins >= 8){
                    Player.totalTreats += 1;
                    Player.pCoins -= 8;
                    Toast.makeText(StoreScreen.this, "You Have Bought One Treat", Toast.LENGTH_SHORT).show();
                    updateCoinsTreats();
                }
                else{
                    Toast.makeText(StoreScreen.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
                }
                //copy, paste, adjust code for buyPet.setOnClickListener()
            }
        });
    }
    public void updateCoinsTreats(){
        String coins = Player.pCoins +"";
        pcoins.setText(coins);
        String treat = Player.totalTreats+"";
        treats.setText(treat);
    }
}
