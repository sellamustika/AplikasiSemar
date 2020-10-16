package com.example.aplikasisemar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aplikasisemar.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public  SlideAdapter(Context context){
        this.context = context;
    }


    //array

    public  int[] slide_image = {
      R.drawable.a,
      R.drawable.b,
      R.drawable.c
    };
    public String[] slide_heading ={
            "Pengisian Form",
            "Persyaratan",
            "Mobile JKN"

    };

    public String[] slide_desc ={
            " Mengisi Formulir Daftar Isian Peserta (DIP) yang disediakan oleh kantor BPJS Kesehatan di seluruh Indonesia.  ",
            "1. Fotokopi Kartu Keluarga (KK) \n" +
                    "2. fotokopi KTP/Paspor masing-masing satu lembar \n" +
                    "3. Fotokopi Buku Tabungan dari penanggung iuran yang tercantum di KK\n" +
                    " 4. Pas foto berwarna dengan ukuran 3×4 masing-masing satu lembar\n ",
            "Pendaftaran dapat dilakukan juga secara Online melalui Mobile Aplikasi JKN "

    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;

    }

    @Override
    public  Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideimage);
        TextView slideHeading= (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_heading[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);



        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }




}
