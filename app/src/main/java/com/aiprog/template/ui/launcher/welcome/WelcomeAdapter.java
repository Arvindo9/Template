package com.aiprog.template.ui.launcher.welcome;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.aiprog.template.R;

/**
 * Author       : Arvindo Mondal
 * Created on   : 25-12-2018
 * Email        : arvindomondal@gmail.com
 */
public class WelcomeAdapter extends PagerAdapter {

    private Context context;

    WelcomeAdapter(Context context) {
        this.context = context;
    }

    //Array
    private int[] list_images={
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3,
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
    };

    public String[] list_title={
            "Phone",
            "Flight",
            "Bus",
            "Train"
    };

    public String[] list_description={
            "Big discounts on Smart Phones",
            "Upto 25% off on Domestic Flights",
            "Enjoy Travelling on bus with flat 100 off",
            "10% cashback on first train booking"
    };
    public int[] list_color={
            Color.rgb(193, 66, 44),
            Color.rgb(193, 172, 44),
            Color.rgb(193, 41, 249),
            Color.rgb(68, 83, 242)

    };

    @Override
    public int getCount() {
        return list_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view== o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.welcome_slider,container,false);

        RelativeLayout linearLayout = view.findViewById(R.id.slidelinearlayout);
        ImageView img = (ImageView)view.findViewById(R.id.image);

        img.setImageResource(list_images[position]);
//        linearLayout.setBackgroundColor(list_color[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

}
