package com.example.z.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.z.newsapp.Utils.DensityUtils;
import com.example.z.newsapp.Utils.PrefUtils;

import java.util.ArrayList;


public class ADshowActivity extends Activity implements View.OnClickListener {

    private ViewPager mViewPager;
    private ArrayList<ImageView> imageViews;
    private int[] images = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private LinearLayout mLL;
    private ImageView mIvRed;
    private int distence;
    private Button mBt;

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

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mIvRed.getLayoutParams();
                layoutParams.leftMargin = (int) (position*distence + positionOffset*distence);
                mIvRed.setLayoutParams(layoutParams);
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
        mIvRed = (ImageView) findViewById(R.id.iv_redoval);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
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

        mIvRed.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mIvRed.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                distence = mLL.getChildAt(1).getLeft() - mLL.getChildAt(0).getLeft();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                PrefUtils.putBoolean(this,"isreadguaid",true);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
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
