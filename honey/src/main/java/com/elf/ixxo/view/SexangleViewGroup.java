package com.elf.ixxo.view;


import com.elf.ixxo.bean.ViewInLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class SexangleViewGroup extends ViewGroup {

	private static int SPACE;// view与view上下间隔

	private float Vheight;
	private int bottomSpace;// 六边形离底部的间隔
    private ViewInLayout v_layout;
    //private ViewInLayout locationLayout;
	double leftSpace;
   // private View locactionView;
	public SexangleViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		v_layout = new ViewInLayout();
		//locationLayout = new ViewInLayout(40,50,60,70);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int childCount = getChildCount();
		int row_count = 13;
		int tempCount = 7;// 行个数
		int coll_count = 6;
		int veidoo = 7;
		switch (childCount) {
		case 127:
			row_count =13;
			tempCount =7;
			coll_count =6;
			veidoo = 7;
			break;
		case 91:
			row_count =11;
			tempCount =6;
			coll_count =5;
			veidoo = 6;
			break;

		case 61:
			row_count =9;
			tempCount =5;
			coll_count =4;
			veidoo = 5;
			break;

		case 37:
			row_count =7;
			tempCount =4;
			coll_count =3;
			veidoo = 4;
			break;


		default:
			break;
		}
		
		//SexangleImageViews temView = (SexangleImageViews) getChildAt(0);
		int lenght = (int) (getWidth() / row_count);// 每个子VIEW的长度

		double radian30 = 30 * Math.PI / 180;// 角度转弧度
//		Vheight = (float) (lenght / 2 * Math.cos(radian30));// 正六边形中连接对角线形成的正三角形的垂直线长度
//		bottomSpace = (int) (lenght / 2 - Vheight);
//
//		SPACE = bottomSpace * -2;
//
//		int offsetX = lenght+SPACE;// X轴每次偏移的长度
		
		Vheight = (float)( ((lenght/2)/Math.cos(radian30))*2);// 正六边形中连接对角线形成的正三角形的垂直线长度
//		bottomSpace = (int) (lenght / 2 - Vheight);
//
//		SPACE = bottomSpace * -2;

		int offsetX = lenght;// X轴每次偏移的长度
		int offsetY = (int)((Vheight*3)/4);

		int rowIndex = 0;// 行下标
		
		int startL = 0;
		int startT = 0;
		
		int index = 0;
		int tempLent = 0;
		for (int i = 0; i < row_count; i++) {
			rowIndex++;
			startT = rowIndex;
			coll_count = rowIndex < veidoo+1 ? coll_count + 1 : coll_count - 1;
			tempCount = rowIndex < veidoo+1 ? tempCount - 1 : tempCount + 1;
			tempLent = tempCount * offsetX / 2;
			for (int j = 0; j < coll_count; j++) {
				SexangleImageViews child = (SexangleImageViews) getChildAt(index++);
				startL = tempLent + j * offsetX;
				v_layout.setLeft(startL);
				v_layout.setTop((rowIndex-1)*offsetY);
				v_layout.setRight(startL + lenght);
				v_layout.setBottom( (rowIndex-1)*offsetY+(int)Vheight);
				//child.setViewInLayout(v_layout);  
				child.setRow(rowIndex);
				child.setColl(j);
				child.layout(v_layout.getLeft(),v_layout.getTop(),v_layout.getRight(),v_layout.getBottom());
			}
			
		}
		//locactionView = getChildAt(getChildCount()-1);
		//locactionView.layout(v_layout.getLeft(),v_layout.getTop(),v_layout.getRight(),v_layout.getBottom());
	}

//	public ViewInLayout getLocationLayout() {
//		//return locationLayout;
//	}
//
//	public void setLocationLayout(ViewInLayout locationLayout) {
//		this.locationLayout = locationLayout;
//	}
     
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

}
