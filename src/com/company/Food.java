package com.company;

/**
 * Created by lhscompsci on 4/3/17.
 */

public class Food {
    private int xCord;
    private int yCord;

    public Food(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }
    public int getXCord() {
        return xCord;
    }
    public int getYCord() {
        return yCord;
    }
    public void setxCord(int xCord) {
        this.xCord = xCord;
    }
    public void setyCord(int yCord) {
        this.yCord = yCord;
    }
    @Override
    public String toString() {
        return "Food{" +
                "xCord=" + xCord +
                ", yCord=" + yCord +
                '}';
    }
}
