package com.tones.allthatgoodstuff;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CakesActivity extends AppCompatActivity implements MenuAdapter.OnItemListener {
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<MenuListItem> myMenuListItems;
    private TextView title;
    private String type, name, desc, head, pic, file, ref;
    private String input = "", rtPic, sqPic;
    private String[] list, values;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes);

        // Reference the title
        title = (TextView) findViewById(R.id.myMenuDesc);

        // Reference the recycler
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create an arraylist of listItems
        myMenuListItems = new ArrayList<>();

        // Extract arraylist items from the masterlist
        try {
            InputStream myIS = getAssets().open("master_list.txt");
            int size = myIS.available();
            byte[] buffer = new byte[size];
            myIS.read(buffer);
            myIS.close();
            input = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        list = input.split("\n");

        for (int i = 0; i < list.length; i++) {
            values = list[i].split(",");
            ref = values[0];
            type = values[1];
            name = values[2];
            desc = values[3];
            head = name + " " + type;
            rtPic = values[4];
            sqPic = values[5];
            file = values[6];

            if (type.equals("cake")) {

                title.setText(type.toUpperCase());
                MenuListItem listItem = new MenuListItem(ref, rtPic, sqPic, head, desc, file);
                myMenuListItems.add(listItem);
            }

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
        MenuListItem myMLI = myMenuListItems.get(position);
        String[] items = {myMLI.getRef(), myMLI.getRtPic(), myMLI.getSqPic(), myMLI.getHead(), myMLI.getDesc(), myMLI.getFilename()};

        Intent intent;

        if (position < myMenuListItems.size()) {
            intent = new Intent(this, RecipeActivity.class);
            intent.putExtra("myRecipeInfo", items);
        } else {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
    }
}