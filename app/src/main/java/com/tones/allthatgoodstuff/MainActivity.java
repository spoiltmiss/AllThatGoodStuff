package com.tones.allthatgoodstuff;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemListener {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<MenuListItem> myMenuListItems;
    private ImageView myPic;
    private String input = "", type = "", ref, file, rtPic, sqPic;
    private String[] list, values;
    private int recipeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference the recycler
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create an arraylist of listItems
        myMenuListItems = new ArrayList<>();

        // Extract recipe information from the masterlist
        try{
            InputStream myIS = getAssets().open("master_list.txt");
            int size = myIS.available();
            byte[] buffer = new byte[size];
            myIS.read(buffer);
            myIS.close();
            input = new String(buffer);
        }
        catch (IOException ex){  // Catch exceptions
            ex.printStackTrace();
        }

        // Split the list into rows
        list = input.split("\n");

        // Count the number of cake recipes
        for (int i = 0; i < list.length; i++) {
            values = list[i].split(",");
            if (values[1].equals("cake")) {
                recipeCount++;
            }
        }

        // Count the number of other recipes
        for(int i = 0; i < list.length; i++) {

            values = list[i].split(",");

            if (!type.equals(values[1])) {
                ref = values[0];
                type = values[1];
                rtPic = values[4];
                sqPic = values[5];
                file = values[6];

                MenuListItem listItem = new MenuListItem(
                        ref,
                        rtPic,
                        sqPic,
                        type,
                        recipeCount + " recipes",
                        file
                );

                myMenuListItems.add(listItem);
                recipeCount = 0;
            }
            recipeCount++;
        }

        // Use an adapter to put the list items into the recycler
        adapter = new MenuAdapter(myMenuListItems, this, this);

        myRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(int position) {
        myMenuListItems.get(position);
        Intent intent;

        switch (position) {
            case 0:
                intent = new Intent(this, CakesActivity.class);
                break;
            case 1:
                intent = new Intent(this, CheesecakesActivity.class);
                break;
            case 2:
                intent = new Intent(this, DessertsActivity.class);
                break;
            case 3:
                intent = new Intent(this, PuddingsActivity.class);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
                break;
        }

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}