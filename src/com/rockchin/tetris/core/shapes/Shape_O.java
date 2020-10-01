package com.rockchin.tetris.core.shapes;

public class Shape_O extends Shape{
    public Shape_O(){
        this.addShapeData(new boolean[][]{
                {T,T,F,F},
                {T,T,F,F},
                {F,F,F,F},
                {F,F,F,F}
        });
    }

    @Override
    public Shape cloneWithoutRotate() {
        return new Shape_O();
    }
}
