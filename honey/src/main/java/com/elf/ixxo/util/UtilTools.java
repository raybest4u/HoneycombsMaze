package com.elf.ixxo.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

import com.elf.ixxo.R;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class UtilTools {
	
   private static Random random = new Random();
   private static int current =0;
   private static int random_int =0;
   
   public static int getRandom(int length){
	   
		
		while(current==random_int){
			random_int =  random.nextInt(length);
		}
		current = random_int;
		return current;
	}
	//随机整数
	public static Integer[] randomArr(int max,int randomSize)
	{
		
		try {
			if(max<randomSize){
				randomSize=max;
			}
			Random random = new Random();
	       // Object[] values = new Object[randomSize];
	        HashSet<Integer> hashSet = new LinkedHashSet<Integer>();
	        
	        // 生成随机数字并存入HashSet
	        while(hashSet.size() < randomSize){
	            hashSet.add(random.nextInt(max) + 1);
	        }
	        Integer[] its=new Integer[randomSize];
	        Iterator<Integer> it=hashSet.iterator();
	        int i=0;
	        while(it.hasNext()){
	        	its[i]=it.next();
	        	i++;
	        }
	        return its;
	       
		} catch (Exception e) {
			 
		}
		return new Integer[]{} ;
	}
	
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.density;
	}
	
	/**
	 * pxתdp
	 * @param id
	 * @param context
	 * @return
	 */
	public static int getDimenT(int id,Context context) {
		try {
			return (int) (id * getScreenDensity(context));
		} catch (Exception e) {
			return 0;
		}
	}
	public static void checkUpdate(final Context activity,final boolean flag) {
		// TODO Auto-generated method stub
		UmengUpdateAgent.setUpdateCheckConfig(false);
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
		    @Override
		    public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
		        switch (updateStatus) {
		        case UpdateStatus.Yes: // has update
		            UmengUpdateAgent.showUpdateDialog(activity, updateInfo);
		            break;
		        case UpdateStatus.No: // has no update
		        	if(flag)
		        	Toast.makeText(activity, "没有更新", Toast.LENGTH_SHORT).show();
		            break;
		        case UpdateStatus.NoneWifi: // none wifi
		        	if(flag)
		            Toast.makeText(activity, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
		            break;
		        case UpdateStatus.Timeout: // time out
		        	if(flag)
		            Toast.makeText(activity, "超时", Toast.LENGTH_SHORT).show();
		            break;
		        }
		    } 
		});
		 UmengUpdateAgent.update(activity);
	}
	
	 private  static  int[] colors = { R.color.color_flight,
			 R.color.color_train,
			 R.color.color_setting,
			 R.color.color_sales,
			 R.color.color_hotel,
			 R.color.color_user,
			 R.color.color_1,
			 R.color.color_2,
			 R.color.color_3,
			 R.color.color_4,
			 R.color.color_5,
			 R.color.color_6,
			 R.color.color_7,
			 R.color.color_8,
			 R.color.color_9,
			 R.color.color_11,
			 R.color.color_12,
			 R.color.color_13,
			 R.color.color_14,
			 R.color.color_15,
			 R.color.color_16,
			 R.color.color_17,
			 R.color.color_18,
			 R.color.color_19,
			 R.color.color_remind};
	public static int getRandomColor(){
		return colors[UtilTools.getRandom(colors.length-1)];
	}

}
