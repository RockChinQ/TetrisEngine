package com.rockchin.tetris.core.shapes;

public class Shape_T extends Shape{
    public Shape_T(){
        this.addShapeData(new boolean[][]{
                {T,T,T,F},
                {F,T,F,F},
                {F,F,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {F,F,T,F},
                {F,T,T,F},
                {F,F,T,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {F,F,F,F},
                {F,T,F,F},
                {T,T,T,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {T,F,F,F},
                {T,T,F,F},
                {T,F,F,F},
                {F,F,F,F}
        });
    }

    @Override
    public Shape cloneWithoutRotate() {
        return new Shape_T();
    }
}
