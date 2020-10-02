package com.rockchin.tetris.core.shapes;

import java.awt.*;
import java.util.ArrayList;

/**
 * 描述一个形状
 * @author Rock Chin
 */
public abstract class Shape {
	protected static final boolean T=true,F=false;
	protected int rotate=0;
	protected Point position=new Point(4,-4);
	public int getY(){
		return (int)position.y;
	}
	public int getX(){
		return (int)position.x;
	}
	public void setX(int x){
		this.position.x=x;
	}
	public void setY(int y){
		this.position.y=y;
	}
	protected boolean[][] shapeNow=new boolean[4][4];
	protected ArrayList<boolean[][]> dataLs=new ArrayList<>();

	public ArrayList<boolean[][]> getDataLs() {
		return dataLs;
	}

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
	public void render(){
		shapeNow=dataLs.get((rotate)%dataLs.size()).clone();
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

	public abstract Shape cloneWithoutRotate();
}
