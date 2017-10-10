/**
 *author:Ray
 *date:2015-1-20
 *TODO
 **/

package com.elf.ixxo.maze;
 

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.elf.ixxo.R;

public class MusicService extends Service {
	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override  
    public void onCreate() {  

		if (mediaPlayer == null) {

			// R.raw.mmp是资源文件，MP3格式的
			
			try {
				mediaPlayer = MediaPlayer.create(this, R.raw.bee);
				mediaPlayer.setLooping(true);
				mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				    @Override
				    public void onPrepared(MediaPlayer arg0) {
				    	mediaPlayer.start();

				    }
				});
				//mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

		}
		 //super.onCreate();  
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mediaPlayer.start();  
	    return START_NOT_STICKY;
	} 

	@Override
	public void onDestroy() {
		mediaPlayer.stop();
		mediaPlayer.reset();
		mediaPlayer.release();
		super.onDestroy();
		
	}
}
