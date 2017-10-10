/**
 * This is about Level.java
 * 
 * @author liuray
 * @date 2015-1-13
 * @deprecated 
 */
package com.elf.ixxo.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.elf.ixxo.util.UtilTools;
import com.elf.ixxo.view.SexangleImageViews;

public class Level {
	private static int ID=10000;
	private int veidoo=7;
	private int boxesCount = 127;
	private IWays ways;
	
	private List<SexangleImageViews> sixbosex= new ArrayList<SexangleImageViews>(); 
	
	private int centerBox=63;
	private Context context;
	private boolean isSuper=false;
	
	
	public Level(int veidoo,boolean isSuper,Context context){
		/**
		 * Only Level 4 had set the ways
		 */
		this.veidoo = veidoo; 
		this.context = context;
		this.isSuper = isSuper;
		init();
	}
	private void init(){
		switch (this.veidoo) {
		case 4:
			this.ways =isSuper?new Ways_4s(): new Ways_4();
			break;
        case 5:
        	this.ways = isSuper?new Ways_5s():new Ways_5();
			break;
        case 6:
        	this.ways =isSuper?new Ways_6s(): new Ways_6();
	        break;
	    case 7:
	    	this.ways =isSuper?new Ways_7s(): new Ways_7();
		break;
		default:
			break;
		}
		boxesCount = (2*veidoo*veidoo)+(veidoo-2)*(veidoo-1)-1;
		Integer[] keys = UtilTools.randomArr(boxesCount-1, veidoo);
		centerBox = (boxesCount-1)/2;
		ViewBean viewBean;
		for (int i = 0; i < boxesCount; i++) {
			
			viewBean = new ViewBean();
			viewBean.setIs_key(false);
			for(Integer k:keys){
				if(i==k){
					viewBean.setIs_key(true);
				}
			}
			viewBean.setWays(this.ways.getWays()[i]);
			viewBean.setHome(i);
			viewBean.setColor(i);
			viewBean.setTextsize(20);
			viewBean.setTexts(""+i);
			SexangleImageViews imageViews = new SexangleImageViews(context, viewBean);
			imageViews.setId(ID + i);
			//imageViews.setOnSexangleImageClick(listener);
			//sexangleViewGroup.addView(imageViews);
			sixbosex.add(imageViews);
		} 
	}
	
	public int getMapIndex(int way,int _row,int _coll,int _veidoo){
		//int _veidoo = veidoo;
		int _index = 0;
		switch (way) {
		case 0: 
			if(_row-1<=0){
				//is way out
				//isSuccess();
				return -1;
			}
			
			if(_row<=_veidoo){
				if(_coll==0){
					//is way out
					return -1;
				}
				//int _coll_count = veidoo*_row
				for(int x=0;x<_row-2;x++){
					_index += _veidoo++;
					
				}
				_index = _index+_coll-1;
			}else{
				
				int x=0,_tem = _veidoo;
				for( x=0;x<_veidoo-1;x++){
					_index += _tem++; 
				}
				for(;x<=_row-3;x++){
					_index +=_tem--;
				}
				_index = _index+_coll;

			}
			return _index;
			//T.showLong(MazeActivity.this, "up left 0");
			//break;
        case 1:
        	if(_row-1<=0){
				//is way out
				//isSuccess();
				return -1;
			}
			//int _veidoo = veidoo;
			//int _index = 0;
			if(_row<=_veidoo){
				if((_veidoo+_row)-2==_coll){
					//is way out
					return -1;
				}
				//int _coll_count = veidoo*_row
				for(int x=0;x<_row-2;x++){
					_index += _veidoo++; 
				}
				_index = _index+_coll;
			}else{
				int x=0,_tem = _veidoo;
				for( x=0;x<_veidoo-1;x++){
					_index += _tem++; 
				}
				for(;x<=_row-3;x++){
					_index +=_tem--;
				}
				_index = _index+_coll+1;

			}
			return _index;
        	//T.showLong(MazeActivity.this, "up right 1");
			//break;
		case 2:
			if(_row<=_veidoo){
				if((_veidoo+_row)-2==_coll){
					//is way out
					return -1;
				}
				//int _coll_count = veidoo*_row
				for(int x=0;x<=_row-2;x++){
					_index += _veidoo++; 
				}
				_index = _index+_coll+1;
			}else{
				if(2*_veidoo-(_row-_veidoo)-2==_coll){
					//way out
					return -1;
				}
				 

				int x=0,_tem = _veidoo;
				for( x=0;x<_veidoo-1;x++){
					_index += _tem++; 
				}
				for(;x<=_row-2;x++){
					_index +=_tem--;
				}

				_index = _index+_coll+1;

			}
			return _index;
			//T.showLong(MazeActivity.this, "right 2");
			//break;
		case 3:
			if(_row == 2*_veidoo-1){
				//is way out
				//isSuccess();
				return -1;
			} 
			 
			if(_row<=_veidoo){
				if((_veidoo+_row)-1==_coll){
					//is way out  
					return -1;
				}
				int _col_count =_veidoo;
				for(int x=0;x<=_row-1;x++){
					_index += _col_count++; 
				}
				_index = _row==_veidoo?_index+_coll:_index+_coll+1;
			}else{
				if(2*_veidoo-(_row-_veidoo)-2==_coll){
					//way out
					return -1;
				}
				int x=0,_tem = _veidoo;
				for( x=0;x<_veidoo-1;x++){
					_index += _tem++; 
				}
				for(;x<=_row-1;x++){
					_index +=_tem--;
				}
				_index = _index+_coll;

			}
			return _index;

		case 4:
			if(_row == 2*_veidoo-1){
				//is way out
				//isSuccess();
				return -1;
			} 
			if(_row<=_veidoo){
				if(_row==_veidoo&&_coll==0){
					//is way out
					return -1;
				}
				int _col_count =_veidoo;
				for(int x=0;x<=_row-1;x++){
					_index += _col_count++; 
				}
				_index = _row==_veidoo?_index+_coll-1:_index+_coll;
			}else{
				if(_coll==0){
					//way out
					return -1;
				}
				int x=0,_tem = _veidoo;
				for( x=0;x<_veidoo-1;x++){
					_index += _tem++; 
				}
				for(;x<=_row-1;x++){
					_index +=_tem--;
				}
				_index = _index+_coll-1;

			}
			return _index;

		case 5:
			if(_coll==0){
				//is way out
				//isSuccess();
				return -1;
			} 
			if(_row<=_veidoo){
				 
				 
				for(int x=0;x<=_row-2;x++){
					_index += _veidoo++; 
				}
				_index = _index+_coll-1;
			}else{
				 
				int x=0,_tem = _veidoo;
				for( x=0;x<_veidoo-1;x++){
					_index += _tem++; 
				}
				for(;x<=_row-2;x++){
					_index +=_tem--;
				}
				_index = _index+_coll-1;

			}
			return _index;
		
		default:
			break;
		}
		return -1;
	}
	
	
	public int getVeidoo() {
		return veidoo;
	}
	public void setVeidoo(int veidoo) {
		this.veidoo = veidoo;
	}
	public int getBoxesCount() {
		return boxesCount;
	}
	public void setBoxesCount(int boxesCount) {
		this.boxesCount = boxesCount;
	}
	public List<SexangleImageViews> getSixbosex() {
		return sixbosex;
	}
	public void setSixbosex(List<SexangleImageViews> sixbosex) {
		this.sixbosex = sixbosex;
	}
	public int getCenterBox() {
		return centerBox;
	}
	public void setCenterBox(int centerBox) {
		this.centerBox = centerBox;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	

}

