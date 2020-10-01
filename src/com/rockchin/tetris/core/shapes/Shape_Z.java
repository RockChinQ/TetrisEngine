package com.rockchin.tetris.core.shapes;

public class Shape_Z extends Shape{
    public Shape_Z(){
        this.addShapeData(new boolean[][]{
                {T,T,F,F},
                {F,T,T,F},
                {F,F,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {F,T,F,F},
                {T,T,F,F},
                {T,F,F,F},
                {F,F,F,F}
        });
    }

    @Override
    public Shape cloneWithoutRotate() {
        return new Shape_Z();
    }
}
