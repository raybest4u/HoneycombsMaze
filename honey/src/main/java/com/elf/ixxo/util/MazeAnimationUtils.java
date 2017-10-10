/**
 * This is about AnimationUtils.java
 * 
 * @author liuray
 * @date 2015-1-14
 * @deprecated 
 */
package com.elf.ixxo.util;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class MazeAnimationUtils {
	private static AnimationSet lb_anima;
	private static AnimationSet lt_anima;
	private static AnimationSet lt2_anima;
	public static AnimationSet getToLeftBottomAnimationSet() {
		if (lb_anima != null) {
			return lb_anima;
		}
		lb_anima = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,-1f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 2f);
		translateAnimation.setDuration(600);
		ScaleAnimation animation = new ScaleAnimation(1f, 0.3f, 1f, 0.3f,
		        Animation.RELATIVE_TO_SELF, 0.5f, 
		        Animation.RELATIVE_TO_SELF,0.5f);

		animation.setDuration(600);

		lb_anima.addAnimation(translateAnimation);
		lb_anima.addAnimation(animation);
		return lb_anima;
	}
	public static AnimationSet getToLeftTopAnimationSet() {
		if (lt_anima != null) {
			return lt_anima;
		}
		lt_anima = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,-1f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, -2f);
		translateAnimation.setDuration(600);
		ScaleAnimation animation = new ScaleAnimation(1f, 0.3f, 1f, 0.3f,
		        Animation.RELATIVE_TO_SELF, 0.5f, 
		        Animation.RELATIVE_TO_SELF,0.5f);

		animation.setDuration(600);

		lt_anima.addAnimation(translateAnimation);
		lt_anima.addAnimation(animation);
		return lt_anima;
	}
	public static AnimationSet getLeftTopToZeroAnimationSet(){
		if (lt2_anima != null) {
			return lt2_anima;
		}
		lt2_anima = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -2f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, -2f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(600);
		ScaleAnimation animation = new ScaleAnimation(0.3f, 1f, 0.3f, 1f,
		        Animation.RELATIVE_TO_SELF, 1f, 
		        Animation.RELATIVE_TO_SELF,1f);

		animation.setDuration(600);

		lt2_anima.addAnimation(translateAnimation);
		lt2_anima.addAnimation(animation);
		return lt2_anima;

	}
	private static AnimationSet b_lta;
	private static AnimationSet b_rta;
	private static AnimationSet b_ra;
	private static AnimationSet b_rba;
	private static AnimationSet b_la;
	private static AnimationSet b_lba;
	private static AnimationSet b_zba;
	private static AnimationSet b_ba;
	public static AnimationSet getBoxLTA(){
		if (b_lta != null) {
			return b_lta;
		}
		b_lta = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -0.5f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, -0.7f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_lta.addAnimation(translateAnimation); 
		return b_lta;

	}
	public static AnimationSet getBoxRTA(){
		if (b_rta != null) {
			return b_rta;
		}
		b_rta = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, -0.7f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_rta.addAnimation(translateAnimation); 
		return b_rta;

	}

	public static AnimationSet getBoxRA(){
		if (b_ra != null) {
			return b_ra;
		}
		b_ra = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 1f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_ra.addAnimation(translateAnimation); 
		return b_ra;

	}

	public static AnimationSet getBoxRBA(){
		if (b_rba != null) {
			return b_rba;
		}
		b_rba = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, 0.7f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_rba.addAnimation(translateAnimation); 
		return b_rba;

	}
	public static AnimationSet getBoxBA(){
		if (b_ba != null) {
			return b_ba;
		}
		b_ba = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, 1f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(600);
		 

		b_ba.addAnimation(translateAnimation); 
		return b_ba;

	}
	public static AnimationSet getBoxZBA(){
		if (b_zba != null) {
			return b_zba;
		}
		b_zba = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 1f);
		translateAnimation.setDuration(600);
		 

		b_zba.addAnimation(translateAnimation); 
		return b_zba;

	}

	public static AnimationSet getBoxLBA(){
		if (b_lba != null) {
			return b_lba;
		}
		b_lba = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -0.5f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, 0.7f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_lba.addAnimation(translateAnimation); 
		return b_lba;

	}

	public static AnimationSet getBoxLA(){
		if (b_la != null) {
			return b_la;
		}
		b_la = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -1f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_la.addAnimation(translateAnimation); 
		return b_la;

	}
	private static AnimationSet b_zlta;
	private static AnimationSet b_zrta;
	private static AnimationSet b_zra;
	private static AnimationSet b_zrba;
	private static AnimationSet b_zla;
	private static AnimationSet b_zlba;
	private static AnimationSet zra;
	private static AnimationSet sa;
	public static AnimationSet getBoxZLTA(){
		if (b_zlta != null) {
			return b_zlta;
		}
		b_zlta = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,-0.8f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, -0.8f);
		translateAnimation.setDuration(1000);
		 

		b_zlta.addAnimation(translateAnimation); 
		return b_zlta;

	}
	public static AnimationSet getBoxZRTA(){
		if (b_zrta != null) {
			return b_zrta;
		}
		b_zrta = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,0.8f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, -0.8f);
		translateAnimation.setDuration(1000);
		 

		b_zrta.addAnimation(translateAnimation); 
		return b_zrta;

	}

	public static AnimationSet getBoxZRA(){
		if (b_zra != null) {
			return b_zra;
		}
		b_zra = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,1f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_zra.addAnimation(translateAnimation); 
		return b_zra;

	}

	public static AnimationSet getBoxZRBA(){
		if (b_zrba != null) {
			return b_zrba;
		}
		b_zrba = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,0.8f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0.8f);
		translateAnimation.setDuration(1000);
		 

		b_zrba.addAnimation(translateAnimation); 
		return b_zrba;

	}

	public static AnimationSet getBoxZLBA(){
		if (b_zlba != null) {
			return b_zlba;
		}
		b_zlba = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,-0.8f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0.8f);
		translateAnimation.setDuration(1000);
		 

		b_zlba.addAnimation(translateAnimation); 
		return b_zlba;

	}

	public static AnimationSet getBoxZLA(){
		if (b_zla != null) {
			return b_zla;
		}
		b_zla = new AnimationSet(true);
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF,-1f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f);
		translateAnimation.setDuration(1000);
		 

		b_zla.addAnimation(translateAnimation); 
		return b_zla;

	}
	public static AnimationSet getZoomRA(){ 
		if (zra != null) {
			return zra;
		}
		zra = new AnimationSet(true);
//		ScaleAnimation animation1 = new ScaleAnimation(0f,0f, 0f, 0f,
//		        Animation.RELATIVE_TO_SELF, 1f, 
//		        Animation.RELATIVE_TO_SELF,1f);
//
//		animation1.setDuration(1000);
		
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, -0.46f,
				Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, -2.5f);
		translateAnimation.setDuration(2000);
		ScaleAnimation animation = new ScaleAnimation(1f, 0.1f, 1f, 0.1f,
		        Animation.RELATIVE_TO_SELF, 1f, 
		        Animation.RELATIVE_TO_SELF,1f);

		animation.setDuration(2000);
		RotateAnimation rotateAnimation = new RotateAnimation(0, -720);
		rotateAnimation.setDuration(1000 * 2); 
		rotateAnimation.setFillAfter(true); 
		//zra.addAnimation(animation1);
		zra.addAnimation(rotateAnimation);
		zra.addAnimation(animation);
		zra.addAnimation(translateAnimation); 
		
		
		return zra;
	}
	public static AnimationSet getShowA(){
		if (sa != null) {
			return sa;
		}
		sa = new AnimationSet(true);
		ScaleAnimation animation = new ScaleAnimation(0.1f, 1f, 0.1f, 1f,
		        Animation.RELATIVE_TO_SELF, 1f, 
		        Animation.RELATIVE_TO_SELF,1f);

		animation.setDuration(2000);
		sa.addAnimation(animation);
		return sa;
	}


}
