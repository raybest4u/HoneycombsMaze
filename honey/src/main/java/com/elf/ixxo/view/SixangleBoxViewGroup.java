package com.elf.ixxo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class SixangleBoxViewGroup extends ViewGroup {

	private static int SPACE;// view与view上下间隔

	private float Vheight;
	private int bottomSpace;// 六边形离底部的间隔

	double leftSpace;

	public SixangleBoxViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int row_count = 1;
		// View temView = getChildAt(0);
		int lenght = (int) (getWidth() / row_count);// 每个子VIEW的长度

		double radian30 = 30 * Math.PI / 180;// 角度转弧度
		Vheight = (float) (lenght / 2 * Math.cos(radian30));// 正六边形中连接对角线形成的正三角形的垂直线长度
		//bottomSpace = (int) (lenght / 2 - Vheight);

		//SPACE = bottomSpace * 0;

		/*
		 * double radian60 = 60 * Math.PI / 180; leftSpace=(float) (lenght/2 *
		 * Math.cos(radian60)); int offsetX = (int) (lenght-leftSpace+SPACE) ;//
		 * X轴每次偏移的长度
		 */

		//int offsetX = lenght;// X轴每次偏移的长度

		int rowIndex = 0;// 行下标
		//int tempCount = 1;// 行个数
		int startL = 0;
		int startT = 0;
		//int coll_count = 1;
		int index = 0;
		//int tempLent = 0;
		View child = getChildAt(index);
		//startL = tempLent ;
		child.layout(startL, rowIndex * lenght, startL + lenght, startT
				+ lenght + rowIndex * lenght);
//		for (int i = 0; i < row_count; i++) {
//			rowIndex++;
//			startT = rowIndex;
//			coll_count = rowIndex < 8 ? coll_count + 1 : coll_count - 1;
//			tempCount = rowIndex < 8 ? tempCount - 1 : tempCount + 1;
//			tempLent = tempCount * offsetX / 2;
//			for (int j = 0; j < coll_count; j++) {
//				
//			}
//		}
	}

	/*@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, height);

		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure((int) (getWidth() / 2.5), (int) (getWidth() / 2.5));
		}
	}*/

	public View getFirstChild(){
		return this.getChildAt(0);
	}
}
