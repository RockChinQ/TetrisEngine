package com.rockchin.tetris.test;

import com.rockchin.tetris.core.TetrisGame;
import com.rockchin.tetris.core.shapes.RandomShape;
import com.rockchin.tetris.core.shapes.Shape;

import java.util.ArrayList;

public class TestMain {
    static TetrisGame tetrisGame=new TetrisGame();
    public static void main(String[] args)throws Exception{
        cycleTest();
    }
    public static void cycleTest(){
        new Thread(()->{
            try {
                Thread.sleep(1000);
                tetrisGame.gameState=1;
                for (int i = 0; i < 250; i++) {
                    Thread.sleep(100);
                    if (tetrisGame.gameState== TetrisGame.GSTOP){
                        System.out.println("game over");
                        System.exit(0);
                    }
                    tetrisGame.cycle();
                    char[][] panel=new char[TetrisGame.H][TetrisGame.W];
                    for(int j=0;j<TetrisGame.H;j++){
                        for(int k=0;k<TetrisGame.W;k++){
                            panel[j][k]=tetrisGame.getMap()[j][k]?'#':'_';
                        }
                    }
                    for(int j=0;j<4;j++){
                        for(int k=0;k<4;k++){
                            if(tetrisGame.fallingBlock.getShapeNow()[j][k]) {
                                try {
                                    panel[j + tetrisGame.fallingBlock.getY()][k + tetrisGame.fallingBlock.getX()] = '*';
                                }catch (Exception e){}
                            }
                        }
                    }
                    //输出
                    for(int j=0;j<panel.length;j++){
                        for(int k=0;k<panel[j].length;k++){
                            System.out.print(panel[j][k]);
                        }
                        System.out.print(j+"\n");
                    }
                    System.out.println("\n");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
    public static void shapeTest()throws Exception{
        RandomShape randomShape=new RandomShape();
        ArrayList<Shape> shapes=randomShape.getOptionalShape();
        for(Shape shape:shapes){
            ArrayList<boolean[][]> data=shape.getDataLs();
            for(boolean[][] d:data){
                for (int i=0;i<d.length;i++){
                    for(int j=0;j<d[i].length;j++){
                        System.out.print(d[i][j]?"#":"_");
                    }
                    System.out.println();
                }
                System.out.println(">>>>>>>>>>>>>>>");
            }
            System.out.println("================");
        }
        Thread.sleep(2000);
        System.out.println("Random test:");
        for(int i=0;i<20;i++){
            System.out.println("get random:"+i);
            Shape shape=randomShape.nextShape();
            //设置旋转
            int times= (int) (Math.random()*100%4)+1;
            for (int j=0;j<times;j++){
                shape.rotate();
            }
            for(int j=0;j<shape.getShapeNow().length;j++){
                for(int k=0;k<shape.getShapeNow()[j].length;k++){
                    System.out.print(shape.getShapeNow()[j][k]?"#":"_");
                }
                System.out.println();
            }
            Thread.sleep(500);
        }
    }
}
