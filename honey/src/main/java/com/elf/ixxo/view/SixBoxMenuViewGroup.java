package com.elf.ixxo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SixBoxMenuViewGroup extends ViewGroup {

	private static  int SPACE ;// view与view上下间隔
	
	private float Vheight;
	private int bottomSpace;//六边形离底部的间隔
	
	double leftSpace;
	public SixBoxMenuViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int lenght = (int) (getWidth() / 4) ;// 每个子VIEW的长度
		
		double radian30 = 30 * Math.PI / 180;//角度转弧度
		Vheight = (float) (lenght/2 * Math.cos(radian30));//正六边形中连接对角线形成的正三角形的垂直线长度
		bottomSpace=(int) (lenght/2-Vheight);
		
		SPACE=bottomSpace*2;
	
		
/*		double radian60 = 60 * Math.PI / 180;
		leftSpace=(float) (lenght/2 * Math.cos(radian60));
		int offsetX = (int) (lenght-leftSpace+SPACE) ;// X轴每次偏移的长度
*/		
		
		
		int offsetX = lenght * 3 / 4 + SPACE;// X轴每次偏移的长度
		//int offsetX = lenght * 3 / 4 + bottomSpace;// X轴每次偏移的长度
		int offsetY = lenght / 2;// Y轴每次偏移的长度
		
		int rowIndex = 0;//行下标  
//		int childCount = 3;
//		int tempCount = 3;
		int childCount = 2;//总数
		int tempCount = 2;//行个数
		int startL = 0;
		int startT=0;
		
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			if(i == childCount){
				rowIndex++;
				if(tempCount==2){
					tempCount=3;	
				}else{
					tempCount=2;
				}
				childCount += tempCount;
			}
			
			if(tempCount==2){
				
				startL = i % 2 * offsetX;
				startT = i % 2 * offsetY;
				//i=5,10,15,20...
				//i=5,startL = offsetX*1;
				//i=10,startL = offsetX*2; 
				//...
				
				if(i>5&&i%5==0){
					int j=i/5;
					startL = offsetX*j;
					startT =offsetY*j; 	
				}
				// i=6,11,16,21...
				//i=6,startL = offsetX*2;
				//i=11,startL = offsetX*3; 
				//...
				if((i-6)%5==0 &&i>2){	
					int j=(i-1)/5;
					startL = offsetX*(j+1);
					startT =offsetY*(j+1); 	
				} 
				 
				
			}else if(tempCount==3){
				if(i<=2){
					startL = i % 2 * offsetX;
					startT = i % 2 * offsetY;	
				}else{
					startL = (i % 3+1) * offsetX;
					startT = (i % 3+1) * offsetY;
				}
				//i=7,12,17,22...
				//i=7,startL = offsetX*1;
				//i=12,startL = offsetX*2; 
				//...
				if((i-7)%5==0 &&i>=7){
					int j=(i-2)/5;
					startL = offsetX*j;
					startT =  offsetY*j;
				}
				
				//i=8,13,18,23...
				if((i-8)%5==0 &&i>=8){
					int j=(i-2)/5;
					startL = offsetX*(j+1);
					startT =  offsetY*(j+1);
				}
				
				//i=9,14,19,24...
				if((i-9)%5==0 &&i>=9){
					int j=(i-2)/5;
					startL = offsetX*(j+2);
					startT =  offsetY*(j+2);
				}
				
			}
	
			
			
			//child.layout(startL, startT + rowIndex * lenght-(rowIndex)*bottomSpace,startL + lenght, startT + lenght+ rowIndex * lenght-(rowIndex)*bottomSpace );
			child.layout(startL, startT + rowIndex * lenght,startL + lenght, startT + lenght+ rowIndex * lenght );
		}

	}

/*	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, height);

		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure((int) (getWidth() / 2.5), (int) (getWidth() / 2.5));
		}
	}*/

}