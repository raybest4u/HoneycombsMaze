/**
 *author:Ray
 *date:2015-1-23
 *TODO
 **/

package com.elf.ixxo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.elf.ixxo.maze.MusicService;
import com.elf.ixxo.util.Constant;
import com.elf.ixxo.util.SharedpreferencesUtils;
import com.elf.ixxo.util.UtilTools;
import com.elf.ixxo.view.Switch;
import com.elf.ixxo.view.Switch.OnCheckedChangeListener;
import com.umeng.analytics.MobclickAgent;

public class SettingActivity extends Activity implements OnClickListener {
	private Switch turn_muisc;
	private Switch turn_song;
	private View ll_update;
	protected boolean serviceFlag;
	protected Intent AudioIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		turn_muisc = (Switch)findViewById(R.id.s_music_on);
		turn_song = (Switch)findViewById(R.id.s_song_on);
		ll_update = (View)findViewById(R.id.ll_update);
		String isOn = SharedpreferencesUtils.getPreValue(Constant.IS_TURN_ON_BACKGROUND_MUSIC, this);
		String isOn2 = SharedpreferencesUtils.getPreValue(Constant.IS_TURN_ON_CLICK_SONG, this);
		turn_muisc.setChecked(isOn == null || Constant.YES.equals(isOn)?true:false);
		turn_song.setChecked(isOn2 == null || Constant.YES.equals(isOn2)?true:false);
		ll_update.setOnClickListener(this);
		turn_muisc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(Switch switchView, boolean isChecked) {
				SharedpreferencesUtils.putSharedpre(Constant.IS_TURN_ON_BACKGROUND_MUSIC, isChecked?Constant.YES:Constant.NO, SettingActivity.this);
				AudioIntent = new Intent("com.ray.Android.MUSIC");
				AudioIntent.setClass(SettingActivity.this, MusicService.class);
				if(isChecked){ 
					if (serviceFlag == false) {
						startService(AudioIntent);
						serviceFlag = true;
					}
				}else{
					if (serviceFlag == true) {
						stopService(AudioIntent);  
						serviceFlag = false;
					}
				}
			}
		});
		turn_song.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(Switch switchView, boolean isChecked) {
				SharedpreferencesUtils.putSharedpre(Constant.IS_TURN_ON_CLICK_SONG, isChecked?Constant.YES:Constant.NO, SettingActivity.this);
			}
		});
	}

	@Override
	public void onClick(View arg0) {
        if(arg0 == ll_update){
        	UtilTools.checkUpdate(this, true);
        }
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		}
		public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		}

}
