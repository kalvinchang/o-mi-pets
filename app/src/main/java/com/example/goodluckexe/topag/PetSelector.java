package com.example.goodluckexe.topag;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by chees_000 on 10/30/2016.
 */

public class PetSelector {
    public Pet petSelected;
    public ImageButton petButton;
    public TextView petName;
    public ProgressBar happinessBar, hungerBar;
    public LinearLayout progressBarsLayout, happinessLayout,  hungerLayout,  petBlock;

    public PetSelector(int petId) {
        petSelected = Player.petList.get(petId);
    }
}