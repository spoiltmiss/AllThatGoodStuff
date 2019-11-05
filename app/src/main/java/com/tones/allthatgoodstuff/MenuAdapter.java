package com.tones.allthatgoodstuff;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    // Variables to define lists to hold recycler view objects
    private List<MenuListItem> myMenuListItems;
    private Context context;
    private Drawable myPic;
    private OnItemListener myOnItemListener;
    private int picID = 0;

    // Constructor for List and Context objects
    public MenuAdapter(List<MenuListItem> myMenuListItems, Context context, OnItemListener onItemListener) {
        this.myMenuListItems = myMenuListItems;
        this.context = context;
        this.myOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Returns the view holder
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_item, parent, false);
        return new ViewHolder(v, myOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getAdapterPosition();

        // Bind the data to the list items
        MenuListItem listItem = myMenuListItems.get(position);

        try {
            picID = context.getResources().getIdentifier(listItem.getSqPic(), "drawable", context.getPackageName());
            myPic = context.getDrawable(picID);
        } catch(NumberFormatException nfe) {
        }

        holder.myPic.setImageDrawable(myPic);
        holder.myHeading.setText(listItem.getHead());
        holder.myDesc.setText(listItem.getDesc());
    }

    @Override
    public int getItemCount() {
        // Returns amount of items in the recycler view
        return myMenuListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Define string for image view objects
        public ImageView myPic;

        // Define text view objects
        public TextView myHeading, myDesc;

        OnItemListener myListener;

        public ViewHolder(@NonNull View itemView, OnItemListener myListener) {
            super(itemView);

            // Get the rectangle image from the xml
            myPic = (ImageView) itemView.findViewById(R.id.myPic);

            // Get the text views from the xml
            myHeading = (TextView) itemView.findViewById(R.id.myHeading);
            myDesc = (TextView) itemView.findViewById(R.id.myDesc);

            this.myListener = myListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}