package com.rockchin.tetris.swing;

import com.rockchin.tetris.core.TetrisGame;
import com.rockchin.tetris.core.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GUI {
    public JFrame mainwd=new JFrame("Tetris");
    public JPanel bgp=new JPanel();

    static class GamePanel extends JPanel{
        public static final Color BACKGROUND=new Color(255, 235, 197);
        public static final Color GRIDLINE=new Color(165, 165, 165,100);

        public TetrisGame tetrisGame=new TetrisGame();
        int gridSize=20;
        public GamePanel(){

        }

        public void setGridSize(int gridSize) {
            this.gridSize = gridSize;
        }

        @Override
        public void paint(Graphics g) {
            this.setSize(TetrisGame.W*gridSize,TetrisGame.H*gridSize);
            //bg
            g.setColor(BACKGROUND);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            //blocks
            boolean[][] map=tetrisGame.getMap();
            g.setColor(Color.ORANGE);
            for(int i=0;i<map.length;i++){
                for (int j=0;j<map[i].length;j++){
                    if (map[i][j]){
                        g.fillRect(j*gridSize,i*gridSize,gridSize,gridSize);
                    }
                }
            }
            //falling shape
            Shape fallings=tetrisGame.fallingBlock;
            boolean[][] falling=tetrisGame.fallingBlock.getShapeNow();
            g.setColor(Color.yellow);
            for(int i=0;i<falling.length;i++){
                for(int j=0;j<falling[i].length;j++){
                    if(falling[i][j]){
                        g.fillRect((fallings.getX()+j)*gridSize,(fallings.getY()+i)*gridSize,gridSize,gridSize);
                    }
                }
            }

            if (tetrisGame.gameState== TetrisGame.GSTOP){
                g.setColor(Color.black);
                g.drawString("GAME OVER",20,20);
            }
            //lines
            g.setColor(GRIDLINE);
            for(int i=0;i<TetrisGame.W;i++){
                g.drawLine(i*gridSize,0,i*gridSize,this.getHeight());
            }
            for(int i=0;i<TetrisGame.H;i++){
                g.drawLine(0,i*gridSize,this.getWidth(),i*gridSize);
            }
        }
    }
    class KeyChecker extends TimerTask{
        public boolean U=false,D=false,L=false,R=false;
        @Override
        public void run() {
            int oper=-1;
            if(D){
                single.tetrisGame.operate(TetrisGame.MDOWN);
                oper=0;
            }
            if(L){
                single.tetrisGame.operate(TetrisGame.MLEFT);
                oper=0;
            }
            if(R){
                single.tetrisGame.operate(TetrisGame.MRIGHT);
                oper=0;
            }
            if(oper!=-1){
                single.repaint();
            }
        }
    }
    KeyChecker keyChecker=new KeyChecker();
    GamePanel single=new GamePanel();
    public GUI(){
        this.mainwd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainwd.setSize(400,600);
        this.mainwd.setLocation(200,200);
        this.mainwd.setLayout(null);

        bgp.setLayout(null);
        bgp.setSize(mainwd.getSize());
        bgp.setLocation(0,0);

        bgp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()){
                    case 87:
                    case 38:{
                        single.tetrisGame.operate(TetrisGame.MUP);
                        single.repaint();
                        keyChecker.U=true;
                        break;
                    }
                    case 83:
                    case 40:{
                        keyChecker.D=true;
                        break;
                    }
                    case 37:
                    case 65:{
                        keyChecker.L=true;
                        break;
                    }
                    case 39:
                    case 68:{
                        keyChecker.R=true;
                        break;
                    }
                    default:{
                        System.out.println(e.getKeyCode());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()){
                    case 87:
                    case 38:{
                        keyChecker.U=false;
                        break;
                    }
                    case 83:
                    case 40:{
                        keyChecker.D=false;
                        break;
                    }
                    case 37:
                    case 65:{
                        keyChecker.L=false;
                        break;
                    }
                    case 39:
                    case 68:{
                        keyChecker.R=false;
                        break;
                    }
                    default:{
                        System.out.println(e.getKeyCode());
                    }
                }
            }
        });
        bgp.requestFocus();
        mainwd.add(bgp);

        single.setLocation(10,30);
        single.setSize(20*TetrisGame.W,20*TetrisGame.H);
        single.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bgp.requestFocus();
                if(single.tetrisGame.gameState==TetrisGame.GPLAYING){
                    return;
                }
                new Thread(()->{
                    try {
                        Timer keyTimer=new Timer();
                        keyChecker=new KeyChecker();
                        keyTimer.schedule(keyChecker,new Date(),110);
                        single.tetrisGame=new TetrisGame();
                        single.tetrisGame.gameState=1;
                        for (;true;) {
                            Thread.sleep(150);
                            single.tetrisGame.cycle();

                            single.repaint();
                            if (single.tetrisGame.gameState== TetrisGame.GSTOP){
                                break;
                            }
                           /* char[][] panel=new char[TetrisGame.H][TetrisGame.W];
                            for(int j=0;j<TetrisGame.H;j++){
                                for(int k=0;k<TetrisGame.W;k++){
                                    panel[j][k]=single.tetrisGame.getMap()[j][k]?'#':'_';
                                }
                            }
                            for(int j=0;j<4;j++){
                                for(int k=0;k<4;k++){
                                    if(single.tetrisGame.fallingBlock.getShapeNow()[j][k]) {
                                        try {
                                            panel[j + single.tetrisGame.fallingBlock.getY()][k + single.tetrisGame.fallingBlock.getX()] = '*';
                                        }catch (Exception e1){}
                                    }
                                }
                            }
                            //输出
                            for(int j=0;j<panel.length;j++){
                                for(int k=0;k<panel[j].length;k++){
                                    System.out.print(panel[j][k]);
                                }
                            }*/
                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }).start();
            }
        });
        bgp.add(single);

        mainwd.setVisible(true);
    }
}
