package com.rockchin.tetris.core;

import com.rockchin.tetris.core.shapes.Shape;

public interface IRandomShape {
    Shape nextShape();
    void registerShape(Shape shape);
}
