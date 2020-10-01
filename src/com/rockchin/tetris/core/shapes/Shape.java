package com.rockchin.tetris.core.shapes;

import java.awt.*;
import java.util.ArrayList;

/**
 * 描述一个形状
 * @author Rock Chin
 */
public abstract class Shape {
	protected int rotate=0;
	protected Point position=new Point(4,0);
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

	/**
	 * 直接旋转，是否成功需要调用者验证并回溯（如果需要）
	 */
	public void rotate(){
		shapeNow=dataLs.get((++rotate)%dataLs.size()).clone();
	}

	/**
	 * 回溯
	 */
	public void back(){
		shapeNow=dataLs.get((--rotate)%dataLs.size());
	}
	public boolean[][] getShapeNow(){
		return shapeNow;
	}
}
