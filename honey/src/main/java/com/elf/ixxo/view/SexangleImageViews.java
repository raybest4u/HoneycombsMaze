package com.elf.ixxo.view;

import com.elf.ixxo.R;
import com.elf.ixxo.bean.ViewBean;
import com.elf.ixxo.bean.ViewInLayout;
import com.elf.ixxo.util.PixelUtil;
import com.elf.ixxo.util.UtilTools;

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
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation; 

 

public class SexangleImageViews extends View {

	private int mWidth;
	private int mHeight;
	private int centreX;
	private int centreY;
	private int mLenght;
	private Paint paint;
	private int row;
	private int coll;
	private int color;
	private float textsize=24;
    private boolean isKey = false;
	private int home;
	private String texts;
	private byte[] ways;
	private Bitmap home_flight;
	private int state = 1; // 按下
	
    private ViewInLayout v_layout;

	private OnSexangleImageClickListener listener;
	private Context mContext;
	public SexangleImageViews(Context context) {
		super(context);

	}
	public SexangleImageViews(Context context,ViewBean bean) {
		super(context);
		mContext = context;
		setCustomAttributes(bean);
	}

	private void setCustomAttributes(ViewBean bean) {
		textsize =PixelUtil.sp2px(8);
		home = bean.getHome();
		texts = bean.getTexts();
		ways = bean.getWays();
		home_flight = bitmaps[0]; 
		color= getResources().getColor(UtilTools.getRandomColor());
		isKey = bean.isIs_key();
	}
	 
	@SuppressLint("Recycle")
	public SexangleImageViews(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.sexangleImageView);
		color = typedArray.getInt(R.styleable.sexangleImageView_backcolor, 0);
		textsize = typedArray.getDimension(R.styleable.sexangleImageView_textSize, PixelUtil.sp2px(8));
		home = typedArray.getInt(R.styleable.sexangleImageView_home, 0);
		texts = typedArray.getString(R.styleable.sexangleImageView_texts);
		 
		home_flight = bitmaps[0];
	 
	}
	


	private Bitmap[] bitmaps = {
			BitmapFactory.decodeResource(getResources(),R.drawable.key_map) 

	};
	

	public int getmWidth() {
		return mWidth;
	}
	public void setmWidth(int mWidth) {
		this.mWidth = mWidth;
	}
	public int getmHeight() {
		return mHeight;
	}
	public void setmHeight(int mHeight) {
		this.mHeight = mHeight;
	}
	public int getmLenght() {
		return mLenght;
	}
	public void setmLenght(int mLenght) {
		this.mLenght = mLenght;
	}
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
		//double radian30 = centreX*3/6/2;
		float a = (float) (mLenght * Math.sin(radian30));
		//float b = (float) (mLenght * Math.cos(radian30));
		float b = (float) (mLenght / Math.cos(radian30));
		float c = (mHeight - 2 * b) / 2;
		paint = new Paint();
		// 去锯齿
		paint.setAntiAlias(true);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		//paint.setColor(Color.BLACK);
		paint.setColor(color);
		paint.setAlpha(200);
		Path path = new Path();
		path.moveTo(mWidth / 2,2*b);    //3
		path.lineTo( mWidth,3*b/2);  //2
		path.lineTo( mWidth,b/2);//1
		path.lineTo( mWidth / 2,0);//0 最上面那个点，顺时针开始数
		path.lineTo(0,b/2);//5
		path.lineTo( 0,3*b/2); //4
		path.close();
		canvas.drawPath(path, paint);

		//画编号
		paint.setColor(Color.WHITE);
		paint.setTextSize(textsize);
		canvas.drawText(texts,centreX-textsize/2,centreY+textsize/2, paint);

		//画路径
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(PixelUtil.dp2px(1));
		for(byte way:ways){
			switch (way) {
			case 0://左上边为第一条边   0点和5点的 中点
				canvas.drawLine(centreX, centreY,  (mWidth/2)/2,b/2/2, paint);
				break;
			case 1:  //0 1
				canvas.drawLine(centreX, centreY, (mWidth/2+mWidth)/2, b/2/2, paint);			
				break;
			case 2://1,2
				canvas.drawLine(centreX, centreY, mWidth, (3*b/2+b/2)/2, paint);
				break;
			case 3://2,3
				canvas.drawLine(centreX, centreY, (mWidth / 2+mWidth)/2, (3*b/2+2*b)/2, paint);
				break;
			case 4://3,4
				canvas.drawLine(centreX, centreY, mWidth/4, (3*b/2+2*b)/2, paint);
				break;
			case 5://4,5
				canvas.drawLine(centreX, centreY, 0, (3*b/2+b/2)/2, paint);
				break;

			default:
				break;
			}
		}

		if(isKey){
		   Matrix matrix = new Matrix();
		   matrix.postTranslate(centreX-home_flight.getWidth()/2,
				   centreY-home_flight.getHeight()/2);
			canvas.drawBitmap(home_flight, matrix, paint);
		}
		

	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(mWidth, mHeight);
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
			//paint.setAlpha(60);
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
	
	public interface  OnSexangleImageClickListener {
		public void onClick(View view);
	}

	public void setViewInLayout(ViewInLayout v_layout) {
		// TODO Auto-generated method stub
		this.v_layout = v_layout;
	}
	public ViewInLayout getViewInLayout(){
		return this.v_layout;
	}
	public byte[] getWays() {
		return ways;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColl() {
		return coll;
	}
	public void setColl(int coll) {
		this.coll = coll;
	}
	public boolean isKey() {
		return isKey;
	}  
	public void setKey(boolean key){
		this.isKey = key;
	}
}

