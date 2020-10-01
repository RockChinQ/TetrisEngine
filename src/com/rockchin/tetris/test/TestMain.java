package com.rockchin.tetris.test;

import com.rockchin.tetris.core.shapes.RandomShape;
import com.rockchin.tetris.core.shapes.Shape;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args)throws Exception{
        shapeTest();
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
