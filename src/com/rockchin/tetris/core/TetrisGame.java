package com.rockchin.tetris.core;

import com.rockchin.tetris.core.shapes.RandomShape;
import com.rockchin.tetris.core.shapes.Shape;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Rock's Tetris written in java
 * @author Rock Chin
 */
public class TetrisGame extends Thread{
	public static final int W=10,H=20;

	public int gameState=0;
	public static final int GSTOP=0,GPLAYING=1;
	public static final int MDOWN=0,MUP=1,MLEFT=2,MRIGHT=3;

	private RandomShape randomShape=new RandomShape();
	public Shape fallingBlock=randomShape.nextShape();
	public Shape nextBlock=randomShape.nextShape();
	private boolean[][] map=new boolean[H][W];
	public boolean[][] getMap(){
		return map;
	}

	public int score=0;
	public TetrisGame(){
		fallingBlock.setY(-5);
		fallingBlock.render();
		nextBlock.render();
	}

	private IGameEventListener gameEventListener;

	public IGameEventListener getGameEventListener() {
		return gameEventListener;
	}

	public void setGameEventListener(IGameEventListener gameEventListener) {
		this.gameEventListener = gameEventListener;
	}

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
			// ，还有什么可做的呢
			//没有什么可做的
			//如果你有好的点子的话
			//比如 下落成功就关闭电脑也是可以的
			/*try {
				Runtime.getRuntime().exec("shutdown -s -t 0");
			} catch (IOException e) {
				e.printStackTrace();
			}*/

			//好吧，下一成功要检查是否消除
			checkLines();
		}else {
			synchronized (this) {
				//无法下移,写进map
				i:for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (fallingBlock.getShapeNow()[i][j]) {
							if (i+fallingBlock.getY()<0){
//								gameOver();
								continue ;
							}
							map[i + fallingBlock.getY()][j + fallingBlock.getX()]=true;
						}
					}
				}
				if (gameState==GSTOP)
					return;
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
				nextBlock.render();
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
					//检查是否出边界了
					if(i+shape.getY()>=H||j+shape.getX()>=W||j+shape.getX()<0){
						return true;
					}
					if(i+shape.getY()<0){
						continue;
					}
					//先检查是否与map冲突
					if (map[i + shape.getY()][j + shape.getX()]) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 检查是否有可消去的行
	 */
	public void checkLines(){
		ArrayList<Integer> lineCleared=new ArrayList<>();
		nextLine:for(int i=0;i<H;i++){
			boolean line=true;
			for(int j=0;j<W;j++){
				if(!map[i][j]){
					line=false;
					continue nextLine;
				}
			}
			//可以消除一行
			lineCleared.add(i);
		}
		//add score
		if (lineCleared.size()>0)
			score+=Math.pow(2,lineCleared.size()-1)*100;
		if(getGameEventListener()!=null)
			this.getGameEventListener().clearLine(listToArr(lineCleared));
		//删除数据
		for (Integer lineNum:lineCleared){
			for(int i=lineNum;i>=0;i--){
				if(i==0){
					for(int j=0;j<W;j++){
						map[i][j]=false;
					}
				}else {
					System.arraycopy(map[i - 1], 0, map[i], 0, W);
				}
			}
		}
	}
	public void gameOver(){
		gameState=GSTOP;
	}

	public int[] listToArr(ArrayList<Integer> list){
		int[] result=new int[list.size()];
		for (int i=0;i<list.size();i++){
			result[i]=list.get(i);
		}
		return result;
	}
}
