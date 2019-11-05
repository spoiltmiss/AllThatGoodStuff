package com.tones.allthatgoodstuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class ATGSSliderAdapter extends SliderViewAdapter<ATGSSliderAdapter.SliderAdapterVH> {

    private Context context;

    public ATGSSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        switch (position) {
            case 0:
                ChooseImage(viewHolder, R.drawable.rectraspamb);
                break;
            case 1:
                ChooseImage(viewHolder, R.drawable.rectgalaxymousse);
                break;
            case 2:
                ChooseImage(viewHolder, R.drawable.rectstickydate);
                break;
            default:
                ChooseImage(viewHolder, R.drawable.rectredvelslice);
                break;
        }
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

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
