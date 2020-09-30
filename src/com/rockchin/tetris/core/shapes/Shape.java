package com.rockchin.tetris.core.shapes;

import java.util.ArrayList;

/**
 * 描述一个形状
 * @author Rock Chin
 */
public abstract class Shape {
	protected int rotate=0;
	protected boolean[][] shapeNow=new boolean[4][4];
	private ArrayList<boolean[][]> dataLs=new ArrayList<>();
	protected int addShapeData(boolean[][] d){
		if (d.length!=4||d[0].length!=4){
			throw new IllegalArgumentException("形状数据应为[4][4]的二维数组");
		}
		dataLs.add(d);
		return dataLs.size()-1;
	}
	protected ArrayList<boolean[][]> getShapeDataList(){
		return dataLs;
	}

	public void rotate(){
		shapeNow=dataLs.get((++rotate)%dataLs.size()).clone();
	}
	public boolean[][] getShapeNow(){
		return shapeNow;
	}
}
