/**
*author:Ray
*date:2015-1-12
**/

package com.elf.ixxo.bean;

public class ViewInLayout {
	private int left;
	private int top;
	private int right;
	private int bottom;
	
	public ViewInLayout(int left, int top, int right, int bottom) {
		super();
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	public ViewInLayout() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the left
	 */
	public int getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(int left) {
		this.left = left;
	}
	/**
	 * @return the top
	 */
	public int getTop() {
		return top;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(int top) {
		this.top = top;
	}
	/**
	 * @return the ringht
	 */
	public int getRight() {
		return right;
	}
	/**
	 * @param ringht the ringht to set
	 */
	public void setRight(int ringht) {
		this.right = ringht;
	}
	/**
	 * @return the bottom
	 */
	public int getBottom() {
		return bottom;
	}
	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	public String toString(){
		return "left:"+this.left+"\ntop:"+this.top+"\nright"+this.right+"\nbottom"+this.bottom;
	}
}
