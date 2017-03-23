package com.company;

/**
 * Created by lhscompsci on 3/23/17.
 */
public class Gameboard {
    private int height, width;

    public Gameboard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
