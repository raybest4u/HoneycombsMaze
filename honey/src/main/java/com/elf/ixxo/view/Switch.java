package com.elf.ixxo.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.elf.ixxo.R;

public class Switch extends AppCompatImageView {
    private static final String TAG = "Switch";
    private static final int TEXT_SIZE = 18;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private boolean mChecked;
    private Paint mPaint;
    private String mCheckedString = "";
    private String mUnCheckedString = "";

    public boolean ismChecked() {
        return mChecked;
    }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    public String getmCheckedString() {
        return mCheckedString;
    }

    public void setmCheckedString(String mCheckedString) {
        this.mCheckedString = mCheckedString;
    }

    public String getmUnCheckedString() {
        return mUnCheckedString;
    }

    public void setmUnCheckedString(String mUnCheckedString) {
        this.mUnCheckedString = mUnCheckedString;
    }

    public interface OnCheckedChangeListener {
        public void onCheckedChanged(Switch switchView, boolean isChecked);
    }

    public Switch(Context context) {
        this(context, null);
    }

    public Switch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Switch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.uxswitch);
        int count = ta.getIndexCount();
        for (int i = 0; i < count; i++) {
            int itemId = ta.getIndex(i);
            System.out.println("itemId::" + itemId); // 获取属性在R.java文件中的id  
            switch (itemId) {
                case R.styleable.uxswitch_mCheckedString:
                    mCheckedString = ta.getString(itemId);
                    System.out.println("mCheckedString::" + mCheckedString);
                    break;
                case R.styleable.uxswitch_mUnCheckedString:
                    mUnCheckedString = ta.getString(itemId);
                    System.out.println("mUnCheckedString" + mUnCheckedString);
                    break;
                default:
                    break;
            }
        }
        ta.recycle();
        setBackgroundResource(R.drawable.u_dt_f_kg02);// 设置开关按钮的背景
        setChecked(false);// 初始化控件为关的状态
        configPaint();// 设置画布属性（去掉密度，直接以字体12显示）
    }

    public Switch(Context context, AttributeSet attrs, int defStyle, boolean sex) {
        super(context, attrs, defStyle);
        mCheckedString = "";
        //context.getResources().getString(R.string.switch_on);//业务功能开启时的图片显示文字字符串：即ON
        mUnCheckedString = "";
        //context.getResources().getString(R.string.switch_off);//OFF
        setBackgroundResource(R.drawable.u_dt_f_kg02);// 设置开关按钮的背景
        setChecked(false);// 初始化控件为关的状态
        configPaint();// 设置画布属性（去掉密度，直接以字体12显示）
    }

    private void configPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 去文字锯齿
        // mPaint.setTextSize(TEXT_SIZE * mDensity);
        mPaint.setTextSize(TEXT_SIZE);
        mPaint.setColor(Color.BLACK);// 画布背景即文字字体的颜色
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        if (mChecked) {
            setImageResource(R.drawable.u_dt_f_kg01);
        } else {
            setImageResource(R.drawable.u_dt_f_kg02);
        }
        postInvalidate();
    }

    public boolean isChecked() {
        return mChecked;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mChecked) {
            // draw checked
            canvas.drawText(mCheckedString, (getWidth() * 0.65f), getHeight() * 0.7f, mPaint);
        } else {
            // draw unchecked
            canvas.drawText(mUnCheckedString, getWidth() * 0.15f, getHeight() * 0.7f, mPaint);
        }
        //画VIEW的时候开始画的点(0,0)是在左上角，图片从左上往下画，画文字的时候开始画的点是在左下角，文字从左下往上画
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                setChecked(!mChecked);
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
                }
                break;
            default:
                // Do nothing
                break;
        }

        return true;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

}
