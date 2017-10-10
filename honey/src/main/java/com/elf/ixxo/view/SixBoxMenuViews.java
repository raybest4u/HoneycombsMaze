package com.elf.ixxo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.elf.ixxo.R;
import com.elf.ixxo.bean.ViewBean;
import com.elf.ixxo.util.PixelUtil;
import com.elf.ixxo.view.SexangleImageViews.OnSexangleImageClickListener;

 

public class SixBoxMenuViews extends AppCompatImageView {

	private int mWidth;
	private int mHeight;
	private int centreX;
	private int centreY;
	private int mLenght;
	private Paint paint;
	
	private int color;
	private float textsize=14;

	private int home;
	private String texts;
	private Bitmap home_flight;
	private int state = 1; // 按下
    private boolean is_open = false;

	private OnSexangleImageClickListener listener;
	private Context mContext;
	
	private Bitmap[] bitmaps = {
			BitmapFactory.decodeResource(getResources(), R.drawable.menu_1),
			BitmapFactory.decodeResource(getResources(),R.drawable.menu_2),
			BitmapFactory.decodeResource(getResources(),R.drawable.menu_3),
			BitmapFactory.decodeResource(getResources(),R.drawable.menu_4),
			BitmapFactory.decodeResource(getResources(),R.drawable.menu_1),
			BitmapFactory.decodeResource(getResources(),R.drawable.menu_2),
			BitmapFactory.decodeResource(getResources(), R.drawable.menu_3),

			BitmapFactory.decodeResource(getResources(),R.drawable.menu_4),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_setting)

	};
	private Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.question);
/*		{getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_user),
			getDrawable(R.drawable.icon_setting)};*/
/*	= {
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),

			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user),
			BitmapFactory.decodeResource(getResources(),R.drawable.icon_setting)

	};*/
	
	public SixBoxMenuViews(Context context) {
		super(context);

	}
	public SixBoxMenuViews(Context context,ViewBean bean) {
		super(context);
		mContext = context;
		//setBitmaps();
		setCustomAttributes(bean);
		
	} 
	private void setCustomAttributes(ViewBean bean) {
		// TODO Auto-generated method stub
		//color = bean.getColor();
		textsize = PixelUtil.sp2px(14);
		home = bean.getHome();
		texts = bean.getTexts();
		is_open = bean.isIs_open();
		home_flight = bitmaps[home];
		color=colors[bean.getColor()%9];
	}
	private Bitmap getDrawable(int id) {   
        BitmapFactory.Options opts = new BitmapFactory.Options();  
        opts.inJustDecodeBounds = true;   
        Bitmap bitmap = null;  
        try {  
            bitmap = BitmapFactory.decodeResource(getResources(), id, opts);  
        } catch (OutOfMemoryError e) {  
        }  
        if (bitmap == null) {  
            return null;  
        }  
        return bitmap;  
  
    }  
	@SuppressLint("Recycle")
	public SixBoxMenuViews(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.sexangleImageView);
		color = typedArray.getInt(R.styleable.sexangleImageView_backcolor, 0);
		textsize = typedArray.getDimension(R.styleable.sexangleImageView_textSize, PixelUtil.sp2px(14));
		home = typedArray.getInt(R.styleable.sexangleImageView_home, 0);
		texts = typedArray.getString(R.styleable.sexangleImageView_texts);
		 
		home_flight = bitmaps[home];
	 
	}
	
	private int[] colors = { getResources().getColor(R.color.color_sales ),
			getResources().getColor(R.color.color_train),
			getResources().getColor(R.color.color_hotel),
			getResources().getColor(R.color.color_sales),
			getResources().getColor(R.color.color_hotel),
			getResources().getColor(R.color.color_flight),
			getResources().getColor(R.color.color_setting),

			getResources().getColor(R.color.color_user),
			getResources().getColor(R.color.color_remind )};

	

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mWidth = getWidth();
		mHeight = getHeight();

		//中心点
		centreX = mWidth / 2;
		centreY = mHeight / 2;
		mLenght = mWidth / 2;

		double radian30 = 30 * Math.PI / 180;
		float a = (float) (mLenght * Math.sin(radian30));
		float b = (float) (mLenght * Math.cos(radian30));
		float c = (mHeight - 2 * b) / 2;
 	
		//int color=Color.parseColor("#FFD700");//十六进制颜色代码,转为int类型
		if (null == paint) {
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setStyle(Style.FILL);
			//paint.setColor(Color.BLACK);
			paint.setColor(color);
			paint.setAlpha(150);
		}
		//画六边形
		Path path = new Path();
		path.moveTo(getWidth(), getHeight() / 2);
		path.lineTo(getWidth() - a, getHeight() - c);
		path.lineTo(getWidth() - a - mLenght, getHeight() - c);
		path.lineTo(0, getHeight() / 2);
		path.lineTo(a, c);
		path.lineTo(getWidth() - a, c);
		path.close();		
		canvas.drawPath(path, paint);
		
		
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(textsize);
		// 去锯齿
		paint.setAntiAlias(true);
		Bitmap icon = is_open?home_flight:bitmap;
		//画背景
		Matrix matrix = new Matrix();
		matrix.postTranslate(this.getWidth() / 2 - icon.getWidth() / 2,
				this.getHeight() / 2 - icon.getHeight() / 2);
		canvas.drawBitmap(icon, matrix, paint);
		int _space = PixelUtil.dp2px(12);

		if(texts.length()>3){
			_space = PixelUtil.dp2px(22);;
		}
		canvas.drawText(texts, this.getWidth() /2 - _space, this.getHeight() / 2 +PixelUtil.sp2px(14)/2, paint);
		//canvas.drawText(texts,centreX,centreY, paint);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(mWidth, mHeight);
	}

	public boolean isIs_open() {
		return is_open;
	}
	public void setIs_open(boolean is_open) {
		this.is_open = is_open;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float start = 1.0f;
		float end = 0.94f;
		Animation scaleAnimation = new ScaleAnimation(start, end, start, end,
		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		Animation endAnimation = new ScaleAnimation(end, start, end, start,
		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		scaleAnimation.setDuration(100);
		scaleAnimation.setFillAfter(true);
		endAnimation.setDuration(100);
		endAnimation.setFillAfter(true);
			
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.startAnimation(scaleAnimation);
			float edgeLength = ((float) getWidth()) / 2;
			float radiusSquare = edgeLength * edgeLength * 3 / 4;
			float dist = (event.getX() - getWidth() / 2)
					* (event.getX() - getWidth() / 2)
					+ (event.getY() - getHeight() / 2)
					* (event.getY() - getHeight() / 2);
			if (dist <= radiusSquare) {// 点中六边形区域
				paint.setColor(Color.BLUE);
				paint.setAlpha(50);
				invalidate();
			}

			break;

		case MotionEvent.ACTION_UP:
			this.startAnimation(endAnimation);
			//paint.setColor(Color.BLACK);
			paint.setColor(color);
			paint.setAlpha(150);
			if(listener!=null){
				listener.onClick(this);
			}
			invalidate();

			break;
			// 滑动出去不会调用action_up,调用action_cancel
		case MotionEvent.ACTION_CANCEL:
			this.startAnimation(endAnimation);
			paint.setColor(Color.BLACK);
			//paint.setColor(color);
			paint.setAlpha(60);

			invalidate();
			break;
					
		}

		return true;
	}

	public void setOnSexangleImageClick(OnSexangleImageClickListener listener ){
		this.listener=listener;
	}
}
