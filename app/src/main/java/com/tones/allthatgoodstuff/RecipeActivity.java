package com.tones.allthatgoodstuff;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class RecipeActivity extends AppCompatActivity {

    private ImageView myRecipeIV;
    private TextView myFullRecipe, myTitle, myDesc;
    private String recipeDesc, recipeTitle, recipeFile, recipe = "";
    private Drawable imageToConvert;
    private int ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Reference widgets
        myTitle = (TextView) findViewById(R.id.myPicDesc);
        myDesc = (TextView) findViewById(R.id.myMenuTitle);
        myFullRecipe = (TextView) findViewById(R.id.myRecipe);
        myRecipeIV = (ImageView) findViewById(R.id.myRecipeImage);

        // Get intent value
        Intent intent = getIntent();
        String[] passedIn = intent.getStringArrayExtra("myRecipeInfo");

        Log.i("Whaaat?", passedIn[0]);

        //--- Info comes in as strings
        //--- 0 = ref      (ie. 0)
        //--- 1 = rectPic  (ie. rectchoc)
        //--- 2 = sqPic    (ie. sqchoc)
        //--- 3 = title    (ie. chocolate cake)
        //--- 4 = desc     (ie. allergy friendly)
        //--- 5 = filename (ie. choc_cake.txt)

        int picID = 0;

        // Get rectangle pic to display
        try {
            picID = getResources().getIdentifier(passedIn[1], "drawable", getPackageName());
            imageToConvert = getResources().getDrawable(picID);
            myRecipeIV.setImageDrawable(imageToConvert);
        } catch(NumberFormatException nfe) {
        }

        // Match passed in details to recipe components
        ref = Integer.parseInt(passedIn[0]);
        recipeDesc = passedIn[3].toUpperCase();
        recipeTitle = passedIn[4];
        recipeFile = passedIn[5];

        // Get recipe filename
        switch (ref)
        {
            case 0:
                recipeFile = "choc_cake.txt";
                break;
            case 1:
                recipeFile = "red_velvet.txt";
                break;
            case 2:
                recipeFile = "lemon_cake.txt";
                break;
            case 3:
                recipeFile = "choc_cheesecake.txt";
                break;
            case 4:
                recipeFile = "red_velvet_cheesecake.txt";
                break;
            case 5:
                recipeFile = "lemon_cheesecake.txt";
                break;
            case 6:
                recipeFile = "ambrosia.txt";
                break;
            case 7:
                recipeFile = "banoffee.txt";
                break;
            case 8:
                recipeFile = "mousse_cake.txt";
                break;
            case 9:
                recipeFile = "sticky_date.txt";
                break;
            case 10:
                recipeFile = "choc_pud.txt";
                break;
            default:
                recipeFile = "choc_cake.txt";
                break;
        }

        // Set title and description
        myDesc.setText(recipeDesc);
        myTitle.setText(recipeTitle);

        // Extract recipe from file
        try{
            InputStream myIS = getAssets().open(recipeFile);
            int size = myIS.available();
            byte[] buffer = new byte[size];
            myIS.read(buffer);
            recipe = new String(buffer);
            myIS.close();
        }
        catch (IOException ex){  // Catch exceptions
            ex.printStackTrace();
            Log.i("File in catch", recipeFile);
        }

        // Output recipe
        myFullRecipe.setText(recipe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}