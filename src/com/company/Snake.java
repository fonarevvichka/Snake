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
        python.updateSnake();
        python.updateSnake();
        python.eat();
        python.eat();
        python.eat();
        python.changeDir('D');
        python.updateSnake();
        python.updateSnake();

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
        updateDir();
        for(Cell cell : snakeVector) {
            cell.move();
        }
    }
    public void updateDir() {
//        char prevDir = snakeVector.get(0).getDir();
//        char nextDir;
//        for(int i = 1; i < snakeVector.size()-1; i++) {
//            nextDir = snakeVector.get(i).getDir();
//            snakeVector.get(i).setDir(prevDir);
//            prevDir = nextDir;
//        }
        for(int i = snakeVector.size()-1; i > 0; i--) {
            snakeVector.get(i).setDir(snakeVector.get(i-1).getDir());
        }
        head.setDir(this.dir);

    }
    public void changeDir(char dir) {
        this.dir = dir;
    }
    public void eat() {
        char dir = snakeVector.get(snakeVector.size()-1).getDir();
        int lastX = snakeVector.get(snakeVector.size()-1).getX();
        int lastY = snakeVector.get(snakeVector.size()-1).getY();

        int changeInY = 0;
        int changeInX = 0;
        switch(dir) {
            case 'U':
                changeInY += 1;
                break;
            case 'D':
                changeInY += -1;
                break;
            case 'L':
                changeInX += 1;
                break;
            case 'R':
                changeInX += -1;
                break;
        }
        snakeVector.add(snakeVector.size(), new Cell(lastX + changeInX, lastY + changeInY, dir));

//        for(Cell cell : snakeVector) {
//            System.out.println(cell.toString());
//        }
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
        return snakeVector.size();
    }
    public char getDirection() {
        return this.dir;
    }
    public void setBoard(char[][] gameboard) {
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                for(int k = 0; k < snakeVector.size(); k++) {
                    if(snakeVector.get(k).getX() == x && snakeVector.get(k).getY() == y) {
                        if(k== 0) {
                            gameboard[y][x] = '0';
                        } else if (k == 1) {
                            gameboard[y][x] = '1';
                        } else {
                            gameboard[y][x] = '2';
                        }
//                        System.out.println(" x - " + x + " y - " + y + " * ");
                        break;
                    } else {
                        gameboard[y][x] = ' ';
//                        System.out.println(" x - " + x + " y - " + y + " space  ");
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
