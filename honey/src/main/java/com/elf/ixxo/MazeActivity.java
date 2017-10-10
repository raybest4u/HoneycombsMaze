package com.elf.ixxo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elf.ixxo.bean.Level;
import com.elf.ixxo.bean.ViewBean;
import com.elf.ixxo.maze.MusicService;
import com.elf.ixxo.util.Constant;
import com.elf.ixxo.util.GDTAdUtil;
import com.elf.ixxo.util.MazeAnimationUtils;
import com.elf.ixxo.util.PixelUtil;
import com.elf.ixxo.util.SharedpreferencesUtils;
import com.elf.ixxo.util.T;
import com.elf.ixxo.util.UtilTools;
import com.elf.ixxo.view.CircleFlashView;
import com.elf.ixxo.view.MyView;
import com.elf.ixxo.view.SexangleImageViews;
import com.elf.ixxo.view.SexangleImageViews.OnSexangleImageClickListener;
import com.elf.ixxo.view.SexangleViewGroup;
import com.elf.ixxo.view.SixBoxMenuViewGroup;
import com.elf.ixxo.view.SixBoxMenuViews;
import com.elf.ixxo.view.SixangleBoxViewGroup;
import com.elf.ixxo.view.SixangleBoxViews;
import com.elf.ixxo.view.SixangleBoxViews.OnSexangleBoxClickListener;
import com.elf.ixxo.view.shimmer.Shimmer;
import com.elf.ixxo.view.shimmer.ShimmerTextView;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.comm.util.AdError;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeActivity extends Activity implements OnClickListener {

    static boolean isBehind = false;

    private SexangleViewGroup sexangleViewGroup;
    private SixangleBoxViewGroup roomGroup1;
    private SixangleBoxViewGroup roomGroup2;
    private SixangleBoxViewGroup currentRoom;
    private SixangleBoxViewGroup nextRoom;
    private SixBoxMenuViewGroup menus;
    private FrameLayout fl_success;
    private ImageButton showMap;
    private View showMenus,btn_back;
    private ViewBean viewBean;
    //private ShimmerTextView tv;
    private ShimmerTextView shimmer_team;

    //private View mSplashView;
    private TextView tv_step;
    private TextView tv_keys;
    // private SexangleImageViews imageViews;
    private static final int ID = 0x10000;
    private View locationView;
    private FrameLayout fl_main;
    //private int screenH = 0;
    //private int screenW = 0;
    private boolean isShow = false;
    private SexangleImageViews currentView = null;
    private List<SexangleImageViews> mazeMap = new ArrayList<SexangleImageViews>();
    private List<SixBoxMenuViews> menuMap = new ArrayList<SixBoxMenuViews>();
    private int counter = 1;

    private int l_w = 0;
    private int l_h = 0;
    private int veidoo = 4;
    private Level level;
    private int loc_w = 0;
    private int colect_keys = 0;


    protected static final int SETTING = 0;

    private boolean is_turn_on_background_music = false;
    private boolean is_turn_on_click_song = false;
    private SoundPool mSound;
    private Map<Integer, Integer> soundPoolMap;
    private boolean serviceFlag = false;
    private Intent AudioIntent;
    protected boolean menuShowing = true;

    private boolean hadInitSounds = false;

    private int play_level = 4;
    private int see_map_times = 4;
    protected int _level = 4;
    protected String step_str = "";
    private String noWay = "";
    protected int step = 0;
    private MyView fireworkView;

    private Shimmer shimmer2;
    private ImageView iv_key;
    private String need_collect_key = "";




    private  GDTAdUtil adUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();

        setContentView(R.layout.activity_maze);

        fl_main =  findViewById(R.id.fl_main);
        fl_success = findViewById(R.id.fl_success);
        btn_back = findViewById(R.id.btn_back);
        showMap = findViewById(R.id.btn_show);
        showMenus = findViewById(R.id.btn_menu);
        //tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        shimmer_team =  findViewById(R.id.shimmer_team);
        //mSplashView = findViewById(R.id.splash_view);
        sexangleViewGroup =  findViewById(R.id.sexangleView);
        menus = findViewById(R.id.g_menus);
        roomGroup1 = findViewById(R.id.g_room1);
        roomGroup2 = findViewById(R.id.g_room2);
        tv_step =   findViewById(R.id.tv_step);
        iv_key =  findViewById(R.id.iv_key);
        tv_keys =   findViewById(R.id.tv_keys);
        showMap.setOnClickListener(this);
        showMenus.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        shimmer2 = new Shimmer();
        shimmer2.start(shimmer_team);

        initView();
        new MazeTask().execute("");



        adUtil = new  GDTAdUtil(this);
        adUtil.initBanner((ViewGroup) findViewById(R.id.bannerContainer));
        adUtil.addListener(findViewById(R.id.showIAD));
        adUtil.addListener(findViewById(R.id.showIADAsPPW));
        adUtil.addListener(findViewById(R.id.closePPWIAD));
        adUtil.doRefreshBanner();
    }

    private void playMusic() {
        if (is_turn_on_background_music && !isBehind) {
            AudioIntent = new Intent("com.ray.Android.MUSIC");
            AudioIntent.setClass(this, MusicService.class);
            if (serviceFlag == false) {
                startService(AudioIntent);
                serviceFlag = true;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SETTING:
                setSong();
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initDatas() {
        step_str = getResources().getString(R.string.step);
        noWay = getResources().getString(R.string.noway);
        need_collect_key = getResources().getString(R.string.need_collect_key);
        loc_w = getResources().getDimensionPixelSize(R.dimen.cicle_loc);
    }

    private void setSong() {

        //setSong();
        String isOn = SharedpreferencesUtils.getPreValue(Constant.IS_TURN_ON_BACKGROUND_MUSIC, this);
        String isOn2 = SharedpreferencesUtils.getPreValue(Constant.IS_TURN_ON_CLICK_SONG, this);
        if (isOn == null || Constant.YES.equals(isOn)) {
            is_turn_on_background_music = true;

        } else {
            is_turn_on_background_music = false;
        }
        if (isOn2 == null || Constant.YES.equals(isOn2)) {
            is_turn_on_click_song = true;
            InitSounds();
        } else {
            is_turn_on_click_song = false;
        }
    }

    private void showKey() {
        PlaySound(6, 0);
        iv_key.setVisibility(View.VISIBLE);
        //iv_key.startAnimation(MazeAnimationUtils.getShowA());
        iv_key.startAnimation(MazeAnimationUtils.getZoomRA());
        //iv_key.setVisibility(View.GONE);
        tv_keys.setVisibility(View.VISIBLE);
        tv_keys.setText("" + (++colect_keys));
        iv_key.getAnimation().setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_key.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 初始化声音
     */
    @SuppressLint("UseSparseArrays")
    private void InitSounds() {
        if (hadInitSounds) {
            return;
        }
        hadInitSounds = true;
        // 设置播放音效
        // mPlayer = MediaPlayer.create(this, R.raw.chang);
        // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        mSound = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(1, mSound.load(this, R.raw.h_ah, 1));
        soundPoolMap.put(2, mSound.load(this, R.raw.h_baboy, 1));
        soundPoolMap.put(3, mSound.load(this, R.raw.h_haha, 1));
        soundPoolMap.put(4, mSound.load(this, R.raw.h_nono, 1));
        soundPoolMap.put(5, mSound.load(this, R.raw.h_bal_baboy, 1));
        soundPoolMap.put(6, mSound.load(this, R.raw.h_baboy2, 1));
        // 可以在后面继续put音效文件
    }

    /**
     * soundPool播放
     *
     * @param sound 播放第一个
     * @param loop  是否循环
     */
    private void PlaySound(int sound, int loop) {
        if (!is_turn_on_click_song) {
            return;
        }
        AudioManager mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        // 获取系统声音的当前音量
        float currentVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统声音的最大音量
        float maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取当前音量的百分比
        float volume = currentVolume / maxVolume;

        // 第一个参数是声效ID,第二个是左声道音量，第三个是右声道音量，第四个是流的优先级，最低为0，第五个是是否循环播放，第六个播放速度(1.0
        // =正常播放,范围0.5 - 2.0)
        mSound.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                if (see_map_times < 0) {
                    PlaySound(4, 0);
                    return;
                }
                PlaySound(1, 0);
                if (locationView == null) {
                    locationView = CircleFlashView.createCircleFlashView(MazeActivity.this);
                    fl_main.addView(locationView);

                }
                isShow = !isShow;
                translateMap();
                roomGroup2.setVisibility(View.GONE);
                if (!menuShowing) {
                    roomGroup1.startAnimation(!isShow ? MazeAnimationUtils.getBoxBA() : MazeAnimationUtils.getBoxZBA());
                    //roomGroup2.startAnimation(!isShow?MazeAnimationUtils.getBoxBA():MazeAnimationUtils.getBoxZBA());
                    roomGroup1.setVisibility(!isShow ? View.VISIBLE : View.GONE);
                }
                if (!isShow)
                    see_map_times--;
                break;
            case R.id.btn_menu:

                PlaySound(2, 0);
                menuShowing = true;
                menus.setVisibility(View.VISIBLE);
                menus.startAnimation(MazeAnimationUtils.getLeftTopToZeroAnimationSet());
                showMenus.setVisibility(View.GONE);
                showMap.setVisibility(View.GONE);
                tv_step.setVisibility(View.GONE);
                tv_keys.setVisibility(View.GONE);
                if (isShow) {
                    isShow = false;

                    translateMap();

                }
                if (roomGroup1.getVisibility() == View.VISIBLE || roomGroup2.getVisibility() == View.VISIBLE) {
                    roomGroup1.startAnimation(MazeAnimationUtils.getBoxZRBA());
                    //roomGroup2.startAnimation(MazeAnimationUtils.getBoxZRBA());
                    roomGroup2.setVisibility(View.GONE);
                    roomGroup1.setVisibility(View.GONE);
                }

                break;
            case R.id.btn_back:
                fl_success.startAnimation(MazeAnimationUtils.getToLeftTopAnimationSet());
                fl_success.setVisibility(View.GONE);
                if (fireworkView != null && fireworkView.isRunning()) {
                    fireworkView.setRunning(false);
                }
                if (fireworkView != null) {
                    fl_success.removeView(fireworkView);
                }
                showMenus.performClick();
                if (_level == play_level) {
                    SharedpreferencesUtils.putSharedpre(Constant.USER_PLAY_LEVEL, "" + (_level + 1), this);
                    play_level++;
                    openNextLevel();
                }
                adUtil.showAD();
                break;

            default:
                break;
        }

    }

    public void translateMap() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, isShow ? 0.0f : 0f, Animation.RELATIVE_TO_SELF, isShow ? 0.0f : 0f, Animation.RELATIVE_TO_SELF,
                isShow ? -1f : 0f, Animation.RELATIVE_TO_SELF, isShow ? 0f : -1f);
        translateAnimation.setDuration(600);
        animationSet.addAnimation(translateAnimation);
        animationSet.setAnimationListener(new ShowOrHideAnimationListener());

        sexangleViewGroup.startAnimation(animationSet);
        fl_main.startAnimation(animationSet);
        sexangleViewGroup.setVisibility(isShow ? View.VISIBLE : View.GONE);
        fl_main.setVisibility(isShow ? View.VISIBLE : View.GONE);
        int res = R.drawable.btn_0;
        switch (see_map_times) {
            case 6:
                res = R.drawable.btn_6;
                break;
            case 5:
                res = R.drawable.btn_5;
                break;
            case 4:
                res = R.drawable.btn_4;
                break;
            case 3:
                res = R.drawable.btn_3;
                break;
            case 2:
                res = R.drawable.btn_2;
                break;
            case 1:
                res = R.drawable.btn_1;
                break;
            case 0:
                res = R.drawable.btn_0;
                break;

            default:
                break;
        }


        showMap.setImageResource(res);
        // if(currentRoom!=null)


    }

    private void translateBoxes(int way) {
        // SixangleBoxViewGroup temRoom = currentRoom;
        nextRoom.setVisibility(View.VISIBLE);

        switch (way) {
            case 0:
                nextRoom.startAnimation(MazeAnimationUtils.getBoxLTA());
                currentRoom.startAnimation(MazeAnimationUtils.getBoxZRBA());
                //T.showLong(MazeActivity.this, "up left 0");
                break;
            case 1:
                nextRoom.startAnimation(MazeAnimationUtils.getBoxRTA());

                currentRoom.startAnimation(MazeAnimationUtils.getBoxZLBA());
                //T.showLong(MazeActivity.this, "up right 1");
                break;
            case 2:
                nextRoom.startAnimation(MazeAnimationUtils.getBoxRA());
                currentRoom.startAnimation(MazeAnimationUtils.getBoxZLA());
                //T.showLong(MazeActivity.this, "right 2");
                break;
            case 3:
                nextRoom.startAnimation(MazeAnimationUtils.getBoxRBA());
                currentRoom.startAnimation(MazeAnimationUtils.getBoxZLTA());
                //T.showLong(MazeActivity.this, "down right 3");
                break;
            case 4:
                nextRoom.startAnimation(MazeAnimationUtils.getBoxLBA());
                currentRoom.startAnimation(MazeAnimationUtils.getBoxZRTA());
                //T.showLong(MazeActivity.this, "down left 4");
                break;
            case 5:
                nextRoom.startAnimation(MazeAnimationUtils.getBoxLA());
                currentRoom.startAnimation(MazeAnimationUtils.getBoxZRA());
                //T.showLong(MazeActivity.this, "left 5");
                break;

            default:
                break;
        }
        currentRoom.getAnimation().setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                View tmpView = currentRoom.getFirstChild();
                if (tmpView instanceof SixangleBoxViews) {
                    ((SixangleBoxViews) tmpView).invalidate();
                }
            }
        });
        //nextRoom.setVisibility(View.GONE);

        tv_step.setText(step_str + (++step));
        // nextRoom = temRoom;
        // currentRoom = nextRoom;

    }

    public void initView() {
        SixBoxMenuViews menu;
        String _play_level = SharedpreferencesUtils.getPreValue(Constant.USER_PLAY_LEVEL, this);
        if (_play_level == null || "".equals(_play_level)) {
            play_level = 4;
        } else {
            play_level = Integer.parseInt(_play_level);
        }
        int isOpen = getOpen(play_level);
        for (int i = 0; i < 9; i++) {
            viewBean = new ViewBean();
            viewBean.setHome(i);
            viewBean.setColor(i);
            viewBean.setIs_open(i <= isOpen || i == 8);
            viewBean.setTextsize(20);
            viewBean.setTexts(setName(i));
            menu = new SixBoxMenuViews(this, viewBean);
            menu.setId(ID + i);
            menu.setOnSexangleImageClick(listener);
            menus.addView(menu);
            menuMap.add(menu);
        }
        viewBean = new ViewBean();
        viewBean.setHome(0);
        viewBean.setColor(0);
        viewBean.setTextsize(20);
        viewBean.setTexts("");
        SixangleBoxViews room1 = new SixangleBoxViews(this, viewBean);
        room1.setOnSexangleImageClick(boxListener);
        roomGroup1.addView(room1);
        viewBean = new ViewBean();
        viewBean.setHome(0);
        viewBean.setColor(0);
        viewBean.setTextsize(20);
        viewBean.setTexts("");
        SixangleBoxViews room2 = new SixangleBoxViews(this, viewBean);
        room2.setOnSexangleImageClick(boxListener);
        roomGroup2.addView(room2);

        currentRoom = roomGroup1;
        nextRoom = roomGroup2;


    }

    private int getOpen(int __level) {
        int isOpen = 0;
        switch (__level) {
            case 4:
                isOpen = 0;
                break;
            case 5:
                isOpen = 1;
                break;
            case 6:
                isOpen = 2;
                break;
            case 7:
                isOpen = 3;
                break;
            case 8:
                isOpen = 4;
                break;
            case 9:
                isOpen = 5;
                break;
            case 10:
                isOpen = 6;
                break;
            case 11:
                isOpen = 7;
                break;
            default:
                isOpen = 0;
                break;
        }
        return isOpen;
    }

    private void isSuccess() {
        if (colect_keys < veidoo) {
            T.showLong(this, need_collect_key);
            return;
        }
        PlaySound(3, 0);


        fireworkView = new MyView(this);
        if (!fireworkView.isRunning()) {
            fireworkView.setRunning(true);
        }
        fl_success.addView(fireworkView, 0);
        fl_success.setVisibility(View.VISIBLE);
        fl_success.startAnimation(MazeAnimationUtils.getLeftTopToZeroAnimationSet());
        //T.showShort(MazeActivity.this, "你好厉害。：］");
    }

    private void openNextLevel() {
        int isOpen = getOpen(play_level);
        menuMap.get(isOpen).setIs_open(true);
        menuMap.get(isOpen).invalidate();
    }

    public String setName(int i) {
        String name = "";
        switch (i) {
            case 0:
                name = getResources().getString(R.string.menu_1);
                break;
            case 1:
                name = getResources().getString(R.string.menu_2);

                break;

            case 2:
                name = getResources().getString(R.string.menu_3);
                break;
            case 3:
                name = getResources().getString(R.string.menu_4);
                break;
            case 4:
                name = getResources().getString(R.string.menu_5);
                break;
            case 5:
                name = getResources().getString(R.string.menu_6);
                break;
            case 6:
                name = getResources().getString(R.string.menu_7);
                break;
            case 7:
                name = getResources().getString(R.string.menu_8);
                break;
            case 8:
                name = getResources().getString(R.string.menu_9);
                break;

            default:
                break;
        }
        return name;
    }

    @Override
    protected void onPause() {
        isBehind = true;
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void findNextBox(int way) {

    }

    OnSexangleBoxClickListener boxListener = new OnSexangleBoxClickListener() {

        @Override
        public void onBoxClick(View view, int way) {
            byte[] c_ways = currentView.getWays();
            boolean isWay = false;
            for (int i = 0, j = c_ways.length; i < j; i++) {
                if (c_ways[i] == way) {
                    isWay = true;
                    int _row = currentView.getRow();
                    int _coll = currentView.getColl();
                    int _index = level.getMapIndex(way, _row, _coll, veidoo);
                    if (_index < 0) {
                        isSuccess();
                    } else {
                        // forwardView = currentView;
                        currentView = mazeMap.get(_index);
                        View tmpView = currentRoom.getFirstChild();
                        if (tmpView instanceof SixangleBoxViews) {
                            ((SixangleBoxViews) tmpView).setCaseAndPosition(1, way);
                            ((SixangleBoxViews) tmpView).invalidate();
                        }
                        if (currentView.isKey()) {
                            showKey();
                            currentView.setKey(false);
                            currentView.invalidate();
                        }
                        translateBoxes(way);
                        PlaySound(5, 0);
                    }
                    break;
                    // findNextBox(way);
                }

            }
            if (!isWay) {
                nextRoom.setVisibility(View.GONE);
                View tmpView = currentRoom.getFirstChild();
                if (tmpView instanceof SixangleBoxViews) {
                    ((SixangleBoxViews) tmpView).setCaseAndPosition(2, way);
                    ((SixangleBoxViews) tmpView).invalidate();
                }
                if (way != -1) {
                    PlaySound(4, 0);

                    T.showShort(MazeActivity.this, noWay);
                }

            }

        }

    };


    OnSexangleImageClickListener listener = new OnSexangleImageClickListener() {


        @Override
        public void onClick(View view) {
            // Toast.makeText(MazeActivity.this, view.getId() + "--",
            // Toast.LENGTH_LONG).show();
            //play_level = 11;

            if (view.getId() >= ID) {

                switch (view.getId()) {
                    case ID:
                        veidoo = 4;
                        _level = 4;
                        see_map_times = 4;
                        break;
                    case ID + 1:
                        if (play_level < 5) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 5;
                        veidoo = 5;
                        see_map_times = 5;
                        break;
                    case ID + 2:
                        if (play_level < 6) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 6;
                        see_map_times = 6;
                        veidoo = 6;
                        break;
                    case ID + 3:

                        if (play_level < 7) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 7;
                        see_map_times = 6;
                        veidoo = 7;
                        break;
                    case ID + 4:
                        if (play_level < 8) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 8;
                        see_map_times = 0;
                        veidoo = 4;
                        break;
                    case ID + 5:
                        if (play_level < 9) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 9;
                        see_map_times = 0;
                        veidoo = 5;
                        break;
                    case ID + 6:
                        if (play_level < 10) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 10;
                        see_map_times = 0;
                        veidoo = 6;
                        break;
                    case ID + 7:
                        if (play_level < 11) {
                            PlaySound(4, 0);
                            return;
                        }
                        _level = 11;
                        see_map_times = 0;
                        veidoo = 7;
                        break;
                    case ID + 8:
                        // setting
                        startActivityForResult(new Intent(MazeActivity.this, SettingActivity.class), SETTING);
                        return;

                    default:
                        break;
                }
                //see_map_times = 20;
                colect_keys = 0;
                level = new Level(veidoo, _level > 7, MazeActivity.this);
                menus.startAnimation(MazeAnimationUtils.getToLeftTopAnimationSet());
                menus.setVisibility(View.GONE);
                showMenus.setVisibility(View.VISIBLE);
                mazeMap = level.getSixbosex();
                sexangleViewGroup.removeAllViews();
                for (SexangleImageViews v : mazeMap) {
                    // v.setOnSexangleImageClick(listener);
                    sexangleViewGroup.addView(v);

                }
                currentView = mazeMap.get(level.getCenterBox());
                showMap.setVisibility(View.VISIBLE);

                showMap.performClick();
                menuShowing = false;
                showMap.setImageResource(R.drawable.btn_begin);
                tv_step.setVisibility(View.VISIBLE);
                step = 0;
                tv_step.setText(step_str + step);
                // onClick(showMap);
            }

        }
    };

    private class ShowOrHideAnimationListener implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @SuppressLint("NewApi")
        @Override
        public void onAnimationEnd(Animation animation) {

            // isShow = !isShow;

            // sexangleViewGroup.setY(isShow?0:-screenH);
            if (isShow) {
                // locationView.setVisibility(View.VISIBLE);
                int[] loc = new int[2];
                currentView.getLocationOnScreen(loc);
                if (loc[0] > 0 || loc[1] > 0) {

                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                    layoutParams.gravity = Gravity.LEFT | Gravity.TOP;

                    layoutParams.leftMargin = loc[0] + currentView.getWidth() / 2 - loc_w / 2;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                        layoutParams.topMargin = loc[1] + currentView.getHeight() / 2 - loc_w / 2- PixelUtil.dp2px(24);
                    }else{
                        layoutParams.topMargin = loc[1] + currentView.getHeight() / 2 - loc_w / 2;
                    }



                    locationView.setLayoutParams(layoutParams);
                } else {
                    fl_main.setVisibility(View.GONE);
                }

            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    /**
     * 连续按两次返回键就退出
     */
    private long firstTime;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstTime < 3000) {
            finish();
        } else {
            firstTime = System.currentTimeMillis();
            T.showShort(this, R.string.press_again_exit);
        }
    }

    @Override
    protected void onStop() {
        stopMusicService();
        super.onStop();
    }

    private void stopMusicService() {
        if (is_turn_on_background_music && isBehind) {
            //AudioIntent = new Intent("com.ray.Android.MUSIC");
            if (serviceFlag == true) {
                stopService(AudioIntent);
                serviceFlag = false;
            }
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (AudioIntent != null) {
            stopService(AudioIntent);
            serviceFlag = false;
        }

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isBehind = false;
        playMusic();
        MobclickAgent.onResume(this);
        //sexangleViewGroup.invalidate();
    }

    public class MazeTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            setSong();
            UtilTools.checkUpdate(MazeActivity.this, false);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }

    }
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1002;
    private final static int REQUEST_CODE_ASK_PERMISSIONS2 = 1003;
    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int res = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            int res2 = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (res != PackageManager.PERMISSION_GRANTED||res2 != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults!=null&&grantResults.length>0&&grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MazeActivity.this, "READ_PHONE_STATE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case REQUEST_CODE_ASK_PERMISSIONS2:
                if (grantResults!=null&&grantResults.length>0&&grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MazeActivity.this, "WRITE_EXTERNAL_STORAGE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
