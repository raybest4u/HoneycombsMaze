/**
 * This is about ViewBea.java
 * 
 * @author liuray
 * @date 2015-1-11
 * @deprecated 
 */
package com.elf.ixxo.bean;

public class ViewBean{
	private int color;
	private float textsize;
	private int home;
	private boolean is_key;
	private String texts;
	private byte[] ways;
	private boolean is_open;
	
	
	public boolean isIs_open() {
		return is_open;
	}


	public void setIs_open(boolean is_open) {
		this.is_open = is_open;
	}


	public byte[] getWays() {
		return ways;
	}
	
	
	public void setWays(byte[] ways) {
		this.ways = ways;
	}
	
	
	public boolean isIs_key() {
		return is_key;
	}


	public void setIs_key(boolean is_key) {
		this.is_key = is_key;
	}


	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public float getTextsize() {
		return textsize;
	}
	public void setTextsize(float textsize) {
		this.textsize = textsize;
	}
	public int getHome() {
		return home;
	}
	public void setHome(int home) {
		this.home = home;
	}
	public String getTexts() {
		return texts;
	}
	public void setTexts(String texts) {
		this.texts = texts;
	}
}

