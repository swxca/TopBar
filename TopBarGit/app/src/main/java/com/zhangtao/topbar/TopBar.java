package com.zhangtao.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zhangtao on 16/1/25.
 */
public class TopBar extends LinearLayout {

    private String midTitle;
    private int midTitleColor;
    private Drawable midBackgroundColor;
    private double midTitleSize;

    private String leftTitle;
    private int leftTitleColor;
    //背景用一个drawable来填充
    private Drawable leftBackgroundColor;
    private double leftTitleSize;

    private String rightTitle;
    private int rightTitleColor;
    private Drawable rightBackgroundColor;
    private double rightTitleSize;

    private TextView mLeftButton;
    private TextView mRightButton;
    private TextView mMid;

    //左边的布局
    private LinearLayout.LayoutParams mLeftParams;
    private LinearLayout.LayoutParams mRightParams;
    private LinearLayout.LayoutParams mMidParams;

    //接口对象实现回调
    public interface topBarClickListener {
        //左边的按钮
        void leftClick();

        //中间的按钮
        void midClick();

        //右边的按钮
        void rightClick();
    }

    public topBarClickListener mListener;
    public void setOnTopBarClickListener(topBarClickListener mListener){
        this.mListener=mListener;

    }

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TypeArray这样的数据结构用来获取自定义的属性集.记得将TypeArray回收.
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TopBar, defStyleAttr, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);

            switch (attr) {
                case R.styleable.TopBar_midTitle:
                    midTitle = a.getString(attr);
                    break;
                case R.styleable.TopBar_midTitleSize:
                    midTitleSize = a.getDimension(attr, 12);
                    break;
                case R.styleable.TopBar_midTitleColor:
                    midTitleColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TopBar_midTitleBackground:
                    midBackgroundColor = a.getDrawable(attr);
                    break;

                case R.styleable.TopBar_leftTitle:
                    leftTitle = a.getString(attr);
                    break;
                case R.styleable.TopBar_leftTitleSize:
                    leftTitleSize = a.getDimension(attr, 12);
                    break;
                case R.styleable.TopBar_leftTitleColor:
                    leftTitleColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TopBar_leftBackground:
                    leftBackgroundColor = a.getDrawable(attr);
                    break;


                case R.styleable.TopBar_rightTitle:
                    rightTitle = a.getString(attr);
                    break;
                case R.styleable.TopBar_rightTitleSize:
                    rightTitleSize = a.getDimension(attr, 12);
                    break;
                case R.styleable.TopBar_rightTitleColor:
                    rightTitleColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TopBar_rightBackground:
                    rightBackgroundColor = a.getDrawable(attr);
                    break;
            }
        }
        a.recycle();

        mLeftButton = new TextView(context);
        mRightButton = new TextView(context);
        mMid = new TextView(context);
        //为每部分的组件赋值
        mLeftButton.setText(leftTitle);
        mLeftButton.setBackground(leftBackgroundColor);
        mLeftButton.setTextColor(leftTitleColor);
        mLeftButton.setGravity(Gravity.CENTER);

        mRightButton.setText(rightTitle);
        mRightButton.setBackground(rightBackgroundColor);
        mRightButton.setTextColor(rightTitleColor);
        mRightButton.setGravity(Gravity.CENTER);

        mMid.setText(midTitle);
        mMid.setTextColor(midTitleColor);
        mMid.setBackground(midBackgroundColor);
        mMid.setGravity(Gravity.CENTER);
        //width height
        mLeftParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //  mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mLeftParams.weight = 1.0f;
        addView(mLeftButton, mLeftParams);

        mMidParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        // mMidParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mMidParams.weight = 3.0f;
        addView(mMid, mMidParams);

        mRightParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //  mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightParams.weight = 1.0f;
        addView(mRightButton, mRightParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mMid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.midClick();
            }
        });


    }

    public void setButtonVisible(int id ,boolean flag){
        if (flag)
        {
            if (id==0)
            {
                mLeftButton.setVisibility(View.VISIBLE);
            }else
            {
                mRightButton.setVisibility(View.VISIBLE);
            }
        }else{
            if (id==0)
            {
                mLeftButton.setVisibility(View.GONE);
            }else
            {
                mRightButton.setVisibility(View.GONE);
            }
        }
    }
}
