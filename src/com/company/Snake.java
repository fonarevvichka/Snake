package com.company;

import java.util.Vector;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Snake {
    private int length = 0;
    private char dir;
    private Cell head;
    private Vector<Cell> snakeVector;
    Gameboard board = new Gameboard(20, 20);


    public static void main(String[] Args) {
        //------- Clean up ---------//
        char[][] gameboard = new char[22][22];

        for(int y = 0; y < 22; y++) {
            for (int x = 0; x < 22; x++) {
                if (y == 0 || y == 21) {
                    gameboard[y][x] = '-';
                } else if (x == 0 || x == 21) {
                    gameboard[y][x] = '|';
                } else {
                    gameboard[y][x] = ' ';
                }
            }
        }
        //------- Clean up ---------//

        Snake python = new Snake('R');
//        while(python.isAlive()) {
            python.updateSnake();
            python.eat();
            python.setBoard(gameboard);
            python.displayBoard(gameboard);
//        }
    }
    public Snake(char dir) {
        this.dir = dir;

        head = new Cell(1,1, dir);
        snakeVector = new Vector<Cell>();
        snakeVector.add(head);
    }
    public void updateSnake() {
        for(Cell cell : snakeVector) {
            cell.move();
        }
        updateDir();
    }
    public void updateDir() {
        moveHead();
        for(int i = 1; i < snakeVector.size(); i++) {
            snakeVector.get(i).setDir(snakeVector.get(i-1).getDir());
        }
    }
    public void moveHead() {
        head.setDir(dir);
        head.move();
    }
    public void changeDir(char dir) {
        this.dir = dir;
    }
    public void eat() {
        //compare location of food and head
    }
    public void grow() {
        Cell tail = snakeVector.get(snakeVector.size()-1);
        Cell newTail = new Cell(tail.getX(), tail.getY(), tail.getDir());

        snakeVector.add(newTail);
    //create new cell
    }
    public boolean isAlive() {
        if((head.getX() >= 0 && head.getX() <= board.getWidth()) && (head.getY() >= 0 && head.getY() <= board.getHeight())) {
            for (int i = 1; i < snakeVector.size(); i++) {
                if (head.getX() != snakeVector.get(i).getX() && head.getY() != snakeVector.get(i).getY()) {
                    return true;
                }
            }
        }
        return false;
    }
    public int getLength() {
        return this.length;
    }
    public char getDirection() {
        return this.dir;
    }
    public void setBoard(char[][] gameboard) {
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                for(int k = 0; k < snakeVector.size(); k++) {
                    if(snakeVector.get(k).getX() == x && snakeVector.get(k).getY() == y) {
                        gameboard[y][x] = '*';
                    } else {
                        gameboard[y][x] = ' ';
                    }
                }
            }
        }
    }
    public void displayBoard(char[][] gameboard) {
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 22; x++) {
                System.out.print(" " + gameboard[y][x] + " ");
            }
            System.out.print("\n");
        }
    }
}
