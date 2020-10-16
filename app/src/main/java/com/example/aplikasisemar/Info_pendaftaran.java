package com.example.aplikasisemar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aplikasisemar.adapter.SlideAdapter;

public class Info_pendaftaran extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SlideAdapter slideAdapter;
    private Button mNext;
    private  Button mPrev;

    private  int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pendaftaran);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNext = (Button) findViewById(R.id.nextBtn);
        mPrev = (Button) findViewById(R.id.prev);

        slideAdapter = new SlideAdapter(this);
        mSlideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i = 0; i < mDots.length; i++) {
            mDots [i]= new  TextView(this);
//            mDots[i].setText(Html.fromHtml("&#8266;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorText));

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length>0) {
            mDots [position].setTextColor(getResources().getColor(R.color.colorText));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {


        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;

            if ( i == 0) {
                mNext.setEnabled(true);
                mPrev.setEnabled(false);
                mPrev.setVisibility(View.INVISIBLE);

                mNext.setText("Next");
                mPrev.setText("");
            }else if (i == mDots.length - 1) {
                mNext.setEnabled(true);
                mPrev.setEnabled(true);
                mPrev.setVisibility(View.VISIBLE);

                mNext.setText("Finish");
                mPrev.setText("Back");

            }else  {
                mNext.setEnabled(true);
                mPrev.setEnabled(true);
                mPrev.setVisibility(View.VISIBLE);

                mNext.setText("Next");
                mPrev.setText("Back");

            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
