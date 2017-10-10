package com.elf.ixxo.maze;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;


/**
 * Created by  ray on 2016/3/23.
 */
public class App extends Application {
    public static App mInstance;
    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static App getInstance() {
        return mInstance;
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

 
  public static int getVerCode() {
      int verCode = -1;
      try {
          verCode = mInstance.getPackageManager().getPackageInfo(
                  "com.ray.honeycombsmaze", 0).versionCode;
      } catch (PackageManager.NameNotFoundException e) {
          e.printStackTrace();
      }
      return  verCode;
  }

    public static String getVerName() {
        String verName = "";
        try {
            verName = mInstance.getPackageManager().getPackageInfo(
                    "com.ray.honeycombsmaze", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
