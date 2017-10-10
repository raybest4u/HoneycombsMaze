/**
 * This is about Array3D.java
 * 
 * @author liuray
 * @date 2015-1-14
 * @deprecated 
 */
package com.elf.ixxo.bean;

import java.util.List;

public class Array3D<T> {
	private int columns;
	private int rows;
	private int[] six_gate;
	private List<T> array;
	
	/**
	 * @param columns
	 */
	public Array3D(int columns) {
		super();
		this.columns = columns;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int[] getSix_gate() {
		return six_gate;
	}
	public void setSix_gate(int[] six_gate) {
		this.six_gate = six_gate;
	}
	public List<T> getArray() {
		return array;
	}
	public void setArray(List<T> array) {
		this.array = array;
	}
	

}

