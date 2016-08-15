package com.example.z.newsapp;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;

public class ADshowActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<ImageView> imageViews;
    private int[] images = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adshow);

        initView();
        initDate();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(new GuaidAdapter());
    }

    private void initDate() {
        imageViews = new ArrayList<>();
        for(int i=0;i<images.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            imageViews.add(imageView);
        }
    }

    private class GuaidAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = imageViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
