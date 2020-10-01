package com.rockchin.tetris.core.shapes;

public class Shape_S extends Shape{
    public Shape_S(){
        this.addShapeData(new boolean[][]{
                {F,T,T,F},
                {T,T,F,F},
                {F,F,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {T,F,F,F},
                {T,T,F,F},
                {F,T,F,F},
                {F,F,F,F}
        });
    }

    @Override
    public Shape cloneWithoutRotate() {
        return new Shape_S();
    }
}
