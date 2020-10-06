package com.rockchin.tetris.core.shapes;

import java.util.ArrayList;

/**
 * #
 * #
 * #
 * #
 * ####
 */
public class Shape_I extends Shape{
    public Shape_I(){
        this.addShapeData(new boolean[][]{
                {T,T,T,T},
                {F,F,F,F},
                {F,F,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {T,F,F,F},
                {T,F,F,F},
                {T,F,F,F},
                {T,F,F,F}
        });
    }

    @Override
    public Shape cloneWithoutRotate() {
        return new Shape_I();
    }
}
