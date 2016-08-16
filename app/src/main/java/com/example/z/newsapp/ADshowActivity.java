package com.example.z.newsapp;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.z.newsapp.Utils.DensityUtils;

import java.util.ArrayList;

public class ADshowActivity extends Activity {

    private ViewPager mViewPager;
    private ArrayList<ImageView> imageViews;
    private int[] images = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private LinearLayout mLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adshow);

        initView();
        initDate();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == images.length-1){
                    findViewById(R.id.bt).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.bt).setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(new GuaidAdapter());
        mLL = (LinearLayout) findViewById(R.id.ll_icon);
    }

    private void initDate() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            imageViews.add(imageView);

            //添加小圆点
            View view = new View(this);
            view.setBackgroundResource(R.drawable.grayoval);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(this,10), DensityUtils.dp2px(this,10));
            if (i > 0) {
                layoutParams.leftMargin = DensityUtils.dp2px(this,10);
            }
            view.setLayoutParams(layoutParams);
            mLL.addView(view);
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
            container.removeView((View) object);
        }
    }
}
