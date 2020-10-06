package com.rockchin.tetris.core.shapes;

import java.util.ArrayList;

/**
 *  #
 *  #
 * ##
 *
 * #
 * ###
 *
 * ##
 * #
 * #
 *
 * ###
 *   #
 */
public class Shape_J extends Shape{
    public Shape_J(){
        this.addShapeData(new boolean[][]{
                {F,T,F,F},
                {F,T,F,F},
                {T,T,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {T,F,F,F},
                {T,T,T,F},
                {F,F,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {T,T,F,F},
                {T,F,F,F},
                {T,F,F,F},
                {F,F,F,F}
        });
        this.addShapeData(new boolean[][]{
                {T,T,T,F},
                {F,F,T,F},
                {F,F,F,F},
                {F,F,F,F}
        });
    }

    @Override
    public Shape cloneWithoutRotate() {
        return new Shape_J();
    }
}
