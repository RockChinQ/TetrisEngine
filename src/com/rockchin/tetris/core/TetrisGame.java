package com.rockchin.tetris.core;

import com.rockchin.tetris.core.shapes.RandomShape;
import com.rockchin.tetris.core.shapes.Shape;

/**
 * Rock's Tetris written in java
 * @author Rock Chin
 */
public class TetrisGame extends Thread{
	public static final int W=10,H=20;

	private int state=0;
	public static final int READY=0,PLAYING=1,OVER=2;
	private int gameState=0;
	public static final int GSTOP=0,GFALLING=1,GROTATE=2,GNEXT=3;
	public static final int MDOWN=0,MUP=1,MLEFT=2,MRIGHT=3;

	private Shape fallingBlock=null;
	private Shape nextBlock=null;
	private RandomShape randomShape=new RandomShape();
	private boolean[][] map=new boolean[H][W];
	/**
	 * 进入下一个循环
	 */
	public void cycle(){
		/*可能需要做的事
		验证是否可以
				可以，并掉落
				不可以，存到map
		无掉落方块
				出现新方块，并随机下一个
		*/
		boolean isDowned=operate(MDOWN);
		if (isDowned){
			//成功向下移
		}else {
			synchronized (this) {
				//无法下移,写进map
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (fallingBlock.getShapeNow()[i][j]) {
							if (map[i + fallingBlock.getY()][j + fallingBlock.getX()]){
								System.out.println("##程序出现无法解释异常##");
							}
							map[i + fallingBlock.getY()][j + fallingBlock.getX()]=true;
						}
					}
				}
				//下一个
				fallingBlock = nextBlock.cloneWithoutRotate();
				int rotateTimes = (int) (Math.random() * 100 % 4 + 1);
				for (int i = 0; i < rotateTimes; i++) {
					fallingBlock.rotate();
				}
				fallingBlock.setX(4);
				fallingBlock.setY(-2);
				//如果未下落就已堆满
				if (conflict(fallingBlock)) {
					gameOver();
				}
				//获取下一个
				nextBlock = randomShape.nextShape();
				nextBlock.rotate();
			}
		}
	}
	public synchronized boolean operate(int type){
		switch (type){
			case MDOWN:{
				fallingBlock.setY(fallingBlock.getY()+1);
				if (conflict(fallingBlock)){
					fallingBlock.setY(fallingBlock.getY()-1);
					return false;
				}
				return true;
			}
			case MUP:{
				fallingBlock.rotate();
				if(conflict(fallingBlock)){
					fallingBlock.back();
					return false;
				}
				return true;
			}
			case MLEFT:{
				fallingBlock.setX(fallingBlock.getX()-1);
				if(conflict(fallingBlock)){
					fallingBlock.setX(fallingBlock.getX()+1);
					return false;
				}
				return true;
			}
			case MRIGHT:{
				fallingBlock.setX(fallingBlock.getX()+1);
				if(conflict(fallingBlock)){
					fallingBlock.setX(fallingBlock.getX()-1);
					return false;
				}
				return true;
			}
		}
		return false;
	}
	public boolean conflict(Shape shape){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(shape.getShapeNow()[i][j]) {
					//先检查是否与map冲突
					if (map[i + shape.getY()][j + shape.getX()]) {
						return true;
					}
					//检查是否出边界了
					if(i+shape.getY()>=H||j+shape.getX()>=W||j+shape.getX()<0){
						return true;
					}
				}
			}
		}
		return false;
	}
	public void gameOver(){

	}
}
