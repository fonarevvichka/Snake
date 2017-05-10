package com.company;

import java.util.Random;
import java.util.Vector;

/**
 * Created by lhscompsci on 3/22/17.
 */
public class Snake {
    private int length = 0;
    public char dir;
    private Cell head;
    private Vector<Cell> snakeVector;
    private int width = 38, height = 38;
    private Gameboard board;
    Food food = null;


    public static void main(String[] Args) {
//        Snake python = new Snake('R');
//        python.updateSnake();
//        python.changeDir('D');
//        while(python.isAlive()) {
//            python.updateSnake();
//        }
    }
    public Snake(char dir, Gameboard board) {
        this.dir = dir;

        this.board = board;
        head = new Cell(0,0, dir);
        snakeVector = new Vector<Cell>();
        snakeVector.add(head);
        food = generateFood();
    }
    public Food generateFood() {
        Random rand = new Random();

        int foodX = rand.nextInt(board.getWidth());
        int foodY = rand.nextInt(board.getHeight());
        for (int i = 0; i < snakeVector.size(); i++) {
            if (foodX == snakeVector.get(i).getX() || foodY == snakeVector.get(i).getY()) {
                foodX = rand.nextInt(board.getWidth());
                foodY = rand.nextInt(board.getHeight());
            } else {
                break;
            }
        }
        return new Food(foodX, foodY);
    }
    public void updateSnake() {
        updateDir();
        for(Cell cell : snakeVector) {
            cell.move();
        }
        if(isHeadAtFood()) {
            eat();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public void updateDir() {
        for(int i = snakeVector.size()-1; i > 0; i--) {
            snakeVector.get(i).setDir(snakeVector.get(i-1).getDir());
        }
        head.setDir(this.dir);

    }
    public void changeDir(char dir) {
        this.dir = dir;
    }
    public void eat() {

        for(int i = 0; i < 2; i++) {
            char dir = snakeVector.get(snakeVector.size() - 1).getDir();
            int lastX = snakeVector.get(snakeVector.size() - 1).getX();
            int lastY = snakeVector.get(snakeVector.size() - 1).getY();

            int changeInY = 0;
            int changeInX = 0;
            switch (dir) {
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
        }
        food = generateFood();
    }
    public boolean isHeadAtFood() {
        if(head.getX() == food.getXCord() && head.getY() == food.getYCord())
            return true;

        return false;

    }
    public boolean isAlive() {
        int headX = head.getX();
        int headY = head.getY();
        if(headX < 0  || headX >= board.getWidth() || headY < 0 || headY >= board.getHeight()) {
            return false;
        } else {
            for (int i = 1; i < snakeVector.size(); i++) {
                if (headX == snakeVector.get(i).getX() && headY == snakeVector.get(i).getY()) {
                    return false;
                }
            }
            return true;
        }
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
                            gameboard[y][x] = '*';
                        } else {
                            gameboard[y][x] = '.';
                        }
//                        System.out.println(" x - " + x + " y - " + y + " * ");
                        break;
                    } else {
                        gameboard[y][x] = ' ';
//                        System.out.println(" x - " + x + " y - " + y + " space  ");
                    }
                }
                if(food.getXCord() == x && food.getYCord() == y) {
                    gameboard[y][x] = 'o';
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
    public Vector<Cell> getSnakeVector() {
        return snakeVector;
    }
    public Gameboard getBoard() {
        return board;
    }
}
