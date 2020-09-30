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
}
