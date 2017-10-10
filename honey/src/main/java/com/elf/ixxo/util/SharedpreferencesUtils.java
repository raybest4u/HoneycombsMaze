/**
 * This is about SharedpreferencesUtils.java
 * 
 * @author liuray
 * @date 2015-1-10
 * @deprecated 
 */
package com.elf.ixxo.util;

 


import com.elf.ixxo.bean.Keyvalue;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedpreferencesUtils {
	private static int MODE_PRIVATE = 0;
	private static String PREF_NAME = "HONEYCOMB";

	public static void putSharedpre(Keyvalue keyvalue,Context context){
		    
		    SharedPreferences pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE); 
		    Editor editor = pref.edit();
		    editor.putString(keyvalue.getKey(), keyvalue.getValue());  // Saving string
		     
		    // Save the changes in SharedPreferences
		    editor.commit(); // commit changes
	}
	public static void putSharedpre(String key,String value,Context context){
	    
	    SharedPreferences pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE); 
	    Editor editor = pref.edit();
	    editor.putString(key,value);  // Saving string
	     
	    // Save the changes in SharedPreferences
	    editor.commit(); // commit changes
}

	public static String getPreValue(String key,Context context){
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE); 
		return pref.getString(key, null);
	}
	public static void delectPreValue(String key,Context context){
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE); 
		Editor editor = pref.edit();
		editor.remove(key);
		editor.commit();
	}
	public static void clearPref(Context context){
		SharedPreferences pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE); 
		Editor editor = pref.edit();
		editor.clear();
		editor.commit();

	}
}

