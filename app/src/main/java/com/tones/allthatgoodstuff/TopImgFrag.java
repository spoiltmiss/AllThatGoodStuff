package com.tones.allthatgoodstuff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TopImgFrag extends Fragment {

    private Toolbar myToolbar;
    private SearchView mySearchView;
    private ListView myListView;
    private SliderView myImageSliderView, myTextSliderAdapter;
    List<MenuListItem> myItemList;
    ArrayAdapter<MenuListItem> adapter;
    private String input, type, desc, rtPic, sqPic, file, ref;
    private String[] list, values;
    private Menu myMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the top fragment
        View view = inflater.inflate(R.layout.fragment_top_img, container, false);

        // Reference the widgets
        myToolbar = (Toolbar) view.findViewById(R.id.myToolbar);
//        mySearchView = (SearchView) view.findViewById(R.id.mySearchView);
        myListView = (ListView) view.findViewById(R.id.searchList);

        // Set the toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);

        // Set search bar attributes
//        mySearchView.setQuery("", false);
//        mySearchView.setFocusable(false);
//        mySearchView.setIconified(false);
//        mySearchView.clearFocus();

//        // Create a list to reference search
//        myItemList = createList();
//
//        adapter = new ArrayAdapter<MenuListItem>(view.getContext(), android.R.layout.simple_list_item_1, myItemList);
//        myListView.setAdapter(adapter);

//        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });

//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//
//            }
//        });

        myImageSliderView = view.findViewById(R.id.myImageSlider);
        ATGSSliderAdapter adapter = new ATGSSliderAdapter(this.getActivity());
        myImageSliderView.setSliderAdapter(adapter);

        myImageSliderView.setIndicatorAnimation(IndicatorAnimations.DROP); //set indicator animation
        myImageSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION); //set image transformation animation
        myImageSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT); //set cycle direction
        myImageSliderView.setScrollTimeInSec(3); //set scroll delay in seconds
        myImageSliderView.startAutoCycle();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.home:
                intent = new Intent(getActivity(), MainActivity.class);
                break;
            case R.id.cakes:
                intent = new Intent(getActivity(), CakesActivity.class);
                break;
            case R.id.cheesecakes:
                intent = new Intent(getActivity(), CheesecakesActivity.class);
                break;
            case R.id.desserts:
                intent = new Intent(getActivity(), DessertsActivity.class);
                break;
            case R.id.puddings:
                intent = new Intent(getActivity(), PuddingsActivity.class);
                break;
            default:
                intent = new Intent(getActivity(), MainActivity.class);
                break;
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    public List<MenuListItem> createList() {

        // Create an arraylist of listItems
        List<MenuListItem> items = new ArrayList<>();

        try {
            InputStream myIS = getActivity().getAssets().open("master_list.txt");
            int size = myIS.available();
            byte[] buffer = new byte[size];
            myIS.read(buffer);
            myIS.close();
            input = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.i("Splitting", "Didn't work " + input);
        }

        list = input.split("\n");

        for (int i = 0; i < list.length; i++) {

            values = list[i].split(",");
            ref = values[0];
            type = values[1];
            desc = values[3];
            rtPic = values[4];
            sqPic = values[5];
            file = values[6];

            MenuListItem listItem = new MenuListItem(ref, rtPic, sqPic, type, desc, file);

            items.add(listItem);
        }
        return items;
    }
}