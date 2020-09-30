package com.rockchin.tetris.core;

/**
 * Rock's Tetris written in java
 * @author Rock Chin
 */
public class TetrisMain extends Thread{
	private int state=0;
	public static final int READY=0,PLAYING=1,OVER=2;
	private int gameState=0;
	public static final int GSTOP=0,GFALLING=1,GROTATE=2,GNEXT=3;

	/**
	 * game panel data arr
	 */
	private char[][] panel=new byte[20][10];

	private boolean[][] next=new boolean[4][4];
	private boolean[][] falling=new boolean[4][4];
	private char rotate=0;//顺时针旋转的角度0:0 1:90 2:180 3:270
	private int y=0;
	private int x=0;
	//ILJSZKO
	/**
	 * model
	 * ####
	 * ####
	 * ####
	 */
	private static final boolean[][] SHAPE_I=new boolean[][]{{true,true,true,true},{false,false,false,false},{false,false,false,false}};
	private static final boolean[][] SHAPE_L=new boolean[][]{{true,true,false,false},{false,true,false,false},{false,true,false,false}};
	private static final boolean[][] SHAPE_J=new boolean[][]{{true,true,false,true},{true,false,false,false},{true,false,false,false}};
	private static final boolean[][] SHAPE_S=new boolean[][]{{true,false,false,false},{true,true,false,false},{false,true,false,false}};
	private static final boolean[][] SHAPE_Z=new boolean[][]{{true,true,false,false},{false,true,true,false},{false,false,false,false}};
}
