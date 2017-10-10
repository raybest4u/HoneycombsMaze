package com.elf.ixxo.view;

import com.elf.ixxo.R;
import com.elf.ixxo.bean.ViewBean;
import com.elf.ixxo.util.PixelUtil;
import com.elf.ixxo.util.UtilTools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray; 
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;  
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation; 

 

public class SixangleBoxViews extends View {

	private int mWidth;
	private int mHeight;
	private int centreX;
	private int centreY;
	private int mLenght;
	private Paint paint;
	
	
	 private float mPosX, mPosY, mCurPosX, mCurPosY;
	 private static final int FLING_MIN_DISTANCE = 50;// 移动最小距离
	// private static final int FLING_MIN_VELOCITY = 200;// 移动最大速度
	
	private int color;
	private float textsize=24;
    private byte[] ways;
    private int waygo=-1;
	private int home;
	private String texts;
	
	private int ccase=0;
	private int position=0;
	//private Bitmap home_flight;
	private int state = 1; // 按下
	
    //private ViewInLayout v_layout;

	private OnSexangleBoxClickListener listener;
	private Context mContext;
	public SixangleBoxViews(Context context) {
		super(context);

	}
	public SixangleBoxViews(Context context,ViewBean bean) {
		super(context);
		mContext = context;
		setCustomAttributes(bean);
	}

	private void setCustomAttributes(ViewBean bean) {
		//color = bean.getColor();
		textsize =PixelUtil.sp2px(24);
		home = bean.getHome();
		texts = bean.getTexts();
		//home_flight = bitmaps[0]; 
		color=colors[UtilTools.getRandom(7)];
	}
	 
	@SuppressLint("Recycle")
	public SixangleBoxViews(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.sexangleImageView);
		color = typedArray.getInt(R.styleable.sexangleImageView_backcolor, 0);
		textsize = typedArray.getDimension(R.styleable.sexangleImageView_textSize, PixelUtil.sp2px(24));
		home = typedArray.getInt(R.styleable.sexangleImageView_home, 0);
		texts = typedArray.getString(R.styleable.sexangleImageView_texts);
		 
		//home_flight = bitmaps[0];
	 
	}
	
	private int[] colors = { getResources().getColor(R.color.color_flight),
			getResources().getColor(R.color.color_train),
			getResources().getColor(R.color.color_setting),
			getResources().getColor(R.color.color_sales),
			getResources().getColor(R.color.color_hotel),
			getResources().getColor(R.color.color_user),
			getResources().getColor(R.color.color_remind)};

//	private Bitmap[] bitmaps = {
//			BitmapFactory.decodeResource(getResources(),R.drawable.icon_user) 
//
//	};
	

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
		float b = (float) (mLenght * Math.cos(radian30));
		float c = (mHeight - 2 * b) / 2;
 	
		//int color=Color.parseColor("#FFD700");//十六进制颜色代码,转为int类型
		if (null == paint) {
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setStyle(Style.FILL);
			//paint.setColor(Color.BLACK);
			paint.setColor(color);
			paint.setAlpha(180);
		}
		//画六边形
		Path path = new Path();
		path.moveTo(mHeight / 2,mWidth);    //3
		path.lineTo( mHeight - c,mWidth - a);  //2
		path.lineTo( mHeight - c,mWidth - a - mLenght);//1
		path.lineTo( mHeight / 2,0);//0 最上面那个点，顺时针开始数
		path.lineTo(c,a);//5
		path.lineTo( c,mWidth - a); //4
		path.close();	 
		canvas.drawPath(path, paint); 
		
		
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		paint.setTextSize(textsize);
		// 去锯齿
		paint.setAntiAlias(true);
		paint.setStrokeWidth(10);
		canvas.drawLine( mHeight / 2,0, mHeight - c,mWidth - a - mLenght, paint);//01
		canvas.drawLine( mHeight - c,mWidth - a - mLenght, mHeight - c,mWidth - a, paint);//12
		canvas.drawLine( mHeight - c,mWidth - a-5, mHeight / 2,mWidth-5, paint);//23
		canvas.drawLine(mHeight / 2,mWidth-5,  c,mWidth - a-5, paint);//34
		canvas.drawLine(  c,mWidth - a, c,a, paint);//45
		canvas.drawLine( mHeight / 2,0, c,a, paint);//50

		
		reCall(canvas);
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
				mPosX = event.getX();
				mPosY = event.getY();
				paint.setColor(Color.BLUE);
				paint.setAlpha(50);
				invalidate();
			}

			break;
		case MotionEvent.ACTION_MOVE:
           

            break;

		case MotionEvent.ACTION_UP:
			 mCurPosX = event.getX();
	            mCurPosY = event.getY();
			this.startAnimation(endAnimation);
			//paint.setColor(Color.BLACK);
			paint.setColor(color);
			paint.setAlpha(180);
			if(mCurPosY-mPosY>0){
				//is down gesture 
				if(mCurPosX-mPosX>0&&Math.abs(mCurPosY-mPosY)>mLenght/2&&(Math.abs(mCurPosX-mPosX+mCurPosY-mPosY)>FLING_MIN_DISTANCE)){
					//Right and down swipe 
					this.waygo =3; 
				}else if(mCurPosX-mPosX<0&&Math.abs(mCurPosY-mPosY)>mLenght/2&&(Math.abs(mCurPosX-mPosX+mCurPosY-mPosY)>FLING_MIN_DISTANCE)){
					//Left and down swipe 
					this.waygo = 4;
				}else if(mCurPosX-mPosX>0&&Math.abs(mCurPosY-mPosY)<mLenght/2&&(Math.abs(mCurPosX-mPosX)>FLING_MIN_DISTANCE)){
					//Right swipe
					this.waygo = 2;
				}else if(mCurPosX-mPosX<0&&Math.abs(mCurPosY-mPosY)<mLenght/2&&(Math.abs(mCurPosX-mPosX)>FLING_MIN_DISTANCE)){
					//Left swipe
					this.waygo = 5;
				}else{
					this.waygo =-1;
				}
				
			}else if(mCurPosY - mPosY <= 0){
				//is up gesture
				if(mCurPosX-mPosX>0&&Math.abs(mCurPosY-mPosY)>mLenght/2&&(Math.abs(mCurPosX-mPosX+mCurPosY-mPosY)>FLING_MIN_DISTANCE)){
					//Right and up swipe 
					this.waygo = 1;  
				}else if(mCurPosX-mPosX<0&&Math.abs(mCurPosY-mPosY)>mLenght/2&&(Math.abs(mCurPosX-mPosX+mCurPosY-mPosY)>FLING_MIN_DISTANCE)){
					//Left and up swipe 
					this.waygo = 0;  
				}else if(mCurPosX-mPosX>0&&Math.abs(mCurPosY-mPosY)<mLenght/2&&(Math.abs(mCurPosX-mPosX)>FLING_MIN_DISTANCE)){
					//Right swipe
					this.waygo = 2; 
				}else if(mCurPosX-mPosX<0&&Math.abs(mCurPosY-mPosY)<mLenght/2&&(Math.abs(mCurPosX-mPosX)>FLING_MIN_DISTANCE)){
					//Left swipe
					this.waygo = 5; 
			    }else{
					this.waygo =-1;
				}

			}else{
				this.waygo =-1; 
			}
			if(listener!=null){
				listener.onBoxClick(this,this.waygo);
			}

			invalidate();
			performClick();
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
	 @Override
	 public boolean performClick() {
	  // Calls the super implementation, which generates an AccessibilityEvent
	        // and calls the onClick() listener on the view, if any
	        super.performClick();

	        // Handle the action for the custom click here

	        return true;
	 }

	public void setOnSexangleImageClick(OnSexangleBoxClickListener listener ){
		this.listener=listener;
	}
	public byte[] getWays() {
		return ways;
	}
	public void setWays(byte[] ways) {
		this.ways = ways;
	} 
	public interface  OnSexangleBoxClickListener {
		public void onBoxClick(View view,int way);
	}
	 
	protected void reCall(Canvas canvas) {
		if(ccase==0){
			return;
		}

		mWidth = getWidth();
		mHeight = getHeight();

		//中心点
		centreX = mWidth / 2;
		centreY = mHeight / 2;
		mLenght = mWidth / 2;

		double radian30 = 30 * Math.PI / 180;
		//double radian30 = centreX*3/6/2;
		float a = (float) (mLenght * Math.sin(radian30));
		float b = (float) (mLenght * Math.cos(radian30));
		float c = (mHeight - 2 * b) / 2;
 	
		//int color=Color.parseColor("#FFD700");//十六进制颜色代码,转为int类型
		
		
		Paint paint = new Paint();
		if(ccase==1){//green
			paint.setColor(Color.GREEN);
		}else if(ccase==2){//red
			paint.setColor(Color.RED);
		}
		
		paint.setTextSize(textsize);
		// 去锯齿
		paint.setAntiAlias(true);
		paint.setStrokeWidth(30);
		switch(position){
		case 1://左上
			canvas.drawLine( mHeight / 2,+15, mHeight - c,mWidth - a - mLenght+15, paint);//01
			break;
		case 2://右上
			canvas.drawLine( mHeight - c,mWidth - a - mLenght, mHeight - c,mWidth - a, paint);//12
			break;
		case 3://右
			canvas.drawLine( mHeight - c,mWidth - a-15, mHeight / 2,mWidth-15, paint);//23
			break;
		case 4://右下
			canvas.drawLine(mHeight / 2,mWidth-15,  c,mWidth - a-15, paint);//34
			break;
		case 5://左下
			canvas.drawLine(  c,mWidth - a, c,a, paint);//45
			break;
		case 0://左
			canvas.drawLine( mHeight / 2,+15, c,a+15, paint);//50
			break;
		default:
		}
		ccase=0;
	}
	public void setCaseAndPosition(int ccase,int position){
		this.ccase=ccase;
		this.position=position;
	}
}

