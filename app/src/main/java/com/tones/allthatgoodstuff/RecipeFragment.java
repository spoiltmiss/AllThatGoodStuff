package com.tones.allthatgoodstuff;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class RecipeFragment extends Fragment {

    private Toolbar myToolbar;
    private SearchView mySearchView;
    private ImageView myImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe, container,false);

        myToolbar = (Toolbar) view.findViewById(R.id.myToolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("All That Good Stuff");

//        mySearchView = (SearchView) view.findViewById(R.id.mySearchView);
//        mySearchView.setQuery("", false);
//        mySearchView.setFocusable(false);
//        mySearchView.setIconified(false);
//        mySearchView.clearFocus();
//        mySearchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

        myImageView = view.findViewById(R.id.myRecipeImage);

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

}