package com.tones.allthatgoodstuff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class ATGSSliderAdapter extends SliderViewAdapter<ATGSSliderAdapter.SliderAdapterVH> {

    private Context context;
    private String[] toPass;
    private String ref = "", rect = "", sq = "", title = "", desc = "", fname = "hi";

    public ATGSSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        switch (position) {
            case 0:
                ChooseImage(viewHolder, R.drawable.rectraspamb);
                viewHolder.picTitle.setText("raspberry ambrosia");
                break;
            case 1:
                ChooseImage(viewHolder, R.drawable.rectgalaxymousse);
                viewHolder.picTitle.setText("chocolate entremet");
                break;
            case 2:
                ChooseImage(viewHolder, R.drawable.rectstickydate);
                viewHolder.picTitle.setText("sticky date pudding");
                break;
            default:
                ChooseImage(viewHolder, R.drawable.rectchoccheese);
                viewHolder.picTitle.setText("chocolate cheesecake");
                break;
        }

        //--- Info comes in as strings
        //--- 0 = ref      (ie. 0)
        //--- 1 = rectPic  (ie. rectchoc)
        //--- 2 = sqPic    (ie. sqchoc)
        //--- 3 = title    (ie. chocolate cake)
        //--- 4 = desc     (ie. allergy friendly)
        //--- 5 = filename (ie. choc_cake.txt)

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipeActivity.class);

                switch (position) {
                    case 0:
                        ref = "6";
                        rect = "rectraspamb";
                        sq = "sqraspamb";
                        title = "raspberry ambrosia";
                        desc = "light and creamy";
                        fname = "ambrosia.txt";
                        break;
                    case 1:
                        ref = "8";
                        rect = "rectgalaxymousse";
                        sq = "sqgalaxymousse";
                        title = "chocolate entremet";
                        desc = "indulgent and unexpected";
                        fname = "mousse_cake.txt";
                        break;
                    case 2:
                        ref = "9";
                        rect = "rectstickydate";
                        sq = "sqstickydate";
                        title = "sticky date pudding";
                        desc = "sticky and sweet";
                        fname = "sticky_date.txt";
                        break;
                    default:
                        ref = "3";
                        rect = "rectchoccheese";
                        sq = "sqchoccheese";
                        title = "chocolate cheesecake";
                        desc = "deliciously rich";
                        fname = "choc_cheesecake.txt";
                        break;
                }
                toPass = new String[] {ref, rect, sq, title, desc, fname};
                intent.putExtra("myRecipeInfo", toPass);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    public void ChooseImage(SliderAdapterVH viewHolder, int drawID){
        Glide.with(viewHolder.itemView)
                .load(drawID)
                .into(viewHolder.imageViewBackground);
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView picTitle;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myImageSlider);
            picTitle = itemView.findViewById(R.id.myPicDesc);
            this.itemView = itemView;
        }
    }
}