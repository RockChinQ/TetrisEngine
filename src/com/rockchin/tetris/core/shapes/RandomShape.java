package com.rockchin.tetris.core.shapes;

import com.rockchin.tetris.core.IRandomShape;

import java.util.ArrayList;

public class RandomShape implements IRandomShape {
    ArrayList<Shape> optionalShape=new ArrayList<>();
    public ArrayList<Shape> getOptionalShape(){
        return optionalShape;
    }
    public RandomShape(){
        registerShape(new Shape_I());
        registerShape(new Shape_J());
        registerShape(new Shape_L());
        registerShape(new Shape_O());
        registerShape(new Shape_S());
        registerShape(new Shape_T());
        registerShape(new Shape_Z());
    }
    @Override
    public Shape nextShape(){
        Shape r=optionalShape.get((int) (Math.random()*100%optionalShape.size())).cloneWithoutRotate();
        return r;
    }

    @Override
    public void registerShape(Shape shape) {
        this.optionalShape.add(shape);
    }
}
