package com.tones.allthatgoodstuff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DessertsActivity extends AppCompatActivity implements MenuAdapter.OnItemListener {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<MenuListItem> myMenuListItems;
    private TextView title;
    private String type, name, desc, head, rtPic, sqPic, file, ref;
    private String input = "";
    private String[] list, values;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts);

        title = (TextView) findViewById(R.id.myMenuDesc);

        // Reference the recycler
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        myPic = (ImageView) findViewById(R.id.myRecipeImage);

        // Create an arraylist of listItems
        myMenuListItems = new ArrayList<>();

        try{
            InputStream myIS = getAssets().open("master_list.txt");
            int size = myIS.available();
            byte[] buffer = new byte[size];
            myIS.read(buffer);
            myIS.close();
            input = new String(buffer);
        }
        catch (IOException ex){
            ex.printStackTrace();
            Log.i("Splitting", "Didn't work " + input);
        }

        list = input.split("\n");

        for(int i = 0; i < list.length; i++) {
            values = list[i].split(",");
            ref = values[0];
            type = values[1];
            name = values[2];
            desc = values[3];
            head = name;
            rtPic = values[4];
            sqPic = values[5];
            file = values[6];

            if (type.equals("dessert")) {

                title.setText(type.toUpperCase());
                MenuListItem listItem = new MenuListItem(ref, rtPic, sqPic, head, desc, file);
                myMenuListItems.add(listItem);
            }

//                int id = getResources().getIdentifier(values[3], "drawable", getPackageName());
//                imageToConvert = getResources().getDrawable(id);
//                myPic.setImageDrawable(imageToConvert);
        }
//        // Declare array of strings (desserts) to list
//        final int[] cakePics = {R.drawable.sqchoc, R.drawable.sqredvelcake, R.drawable.sqlemon};
//        final String[] cakes = {"chocolate cake", "red velvet cake", "lemon cake"};
//        final String[] cakeDesc = {"ALLERGY FRIENDLY", "DELICIOUS AND INDULGENT", "SWEET AND TANGY"};
//        final String[] file = {"choc_cake.txt", "red_velvet.txt", "lemon_cake.txt"};

//        // Create list items
//        for (int i = 0; i < cakes.length; i++) {
//
//            MenuListItem listItem = new MenuListItem(
//                    myPic,
//                    cakes[i],
//                    cakeDesc[i],
//                    file[i]
//            );
//
//            myMenuListItems.add(listItem);
//        }

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
        MenuListItem myMLI = myMenuListItems.get(position);
        String[] items = {myMLI.getRef(), myMLI.getRtPic(), myMLI.getSqPic(), myMLI.getHead(), myMLI.getDesc(), myMLI.getFilename()};

        Intent intent = new Intent(this, MainActivity.class);

        if(position < myMenuListItems.size()){
            intent = new Intent(this, RecipeActivity.class);
            intent.putExtra("myRecipeInfo", items);
        }
        else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
    }

}