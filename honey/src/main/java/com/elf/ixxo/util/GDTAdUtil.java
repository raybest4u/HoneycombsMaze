package com.elf.ixxo.util;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.elf.ixxo.R;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.qq.e.comm.util.AdError;

/**
 * Created by Ray on 2017/9/20.
 */

public class GDTAdUtil {
    Activity context;

    public GDTAdUtil(Activity context) {
        this.context = context;
    }
    public void addListener(View v){
        v.setOnClickListener(listener);
    }
    InterstitialAD iad;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.showIAD:
                    showAD();
                    break;
                case R.id.showIADAsPPW:
                    showAsPopup();
                    break;
                case R.id.closePPWIAD:
                    closeAsPopup();
                    break;
                default:
                    break;
            }
        }
    };

    private InterstitialAD getIAD() {
        if (iad == null) {
            iad = new InterstitialAD(context, Constant.AD_GDT_APP_ID, Constant.AD_GDT_InterteristalPosID);
        }
        return iad;
    }

    public void showAD() {
        try {
            getIAD().setADListener(new AbstractInterstitialADListener() {

                @Override
                public void onNoAD(AdError error) {
                    Log.d(
                            "AD_DEMO",
                            String.format("LoadInterstitialAd Fail, error code: %d, error msg: %s",
                                    error.getErrorCode(), error.getErrorMsg()));
                }

                @Override
                public void onADReceive() {
                    Log.i("AD_DEMO", "onADReceive");
                    iad.show();
                }
            });
            iad.loadAD();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAsPopup() {
        getIAD().setADListener(new AbstractInterstitialADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i(
                        "AD_DEMO",
                        String.format("LoadInterstitialAd Fail, error code: %d, error msg: %s",
                                error.getErrorCode(), error.getErrorMsg()));
            }

            @Override
            public void onADReceive() {
                iad.showAsPopupWindow();
            }
        });
        iad.loadAD();
    }

    public void closeAsPopup() {
        if (iad != null) {
            iad.closePopupWindow();
        }
    }


    ViewGroup bannerContainer;
    BannerView bv;
    public void initBanner(ViewGroup container) {
        bannerContainer = container;
        this.bv = new BannerView(context, ADSize.BANNER, Constant.AD_GDT_APP_ID, Constant.AD_GDT_BANNER_ID);
        // 注意：如果开发者的banner不是始终展示在屏幕中的话，请关闭自动刷新，否则将导致曝光率过低。
        // 并且应该自行处理：当banner广告区域出现在屏幕后，再手动loadAD。
        bv.setRefresh(30);
        bv.setADListener(new AbstractBannerADListener() {

            @Override
            public void onNoAD(AdError error) {
                Log.i(  "AD_DEMO",
                        String.format("Banner onNoAD，eCode = %d, eMsg = %s", error.getErrorCode(),
                                error.getErrorMsg()));
            }

            @Override
            public void onADReceiv() {
                Log.i("AD_DEMO", "ONBannerReceive");
            }
        });
        bannerContainer.addView(bv);
    }
    public void doRefreshBanner() {
        if (bv == null) {
           return;
        }
        bv.loadAD();
        //mHandler.postDelayed(refreshAd,60000);
    }

    public void doCloseBanner() {
        if(bannerContainer==null){
            return;
        }

        bannerContainer.removeAllViews();
        if (bv != null) {
            bv.destroy();
            bv = null;
        }
    }
}
