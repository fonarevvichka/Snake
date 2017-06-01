package com.company;

import javax.swing.*;

/**
 * Created by lhscompsci on 4/3/17.
 */

public class DoubleSnakeRunner extends JPanel {
    static Snake python, blackMamba;
    static Gameboard board;
    //    static Food food = null; //MOVE FOOD TO RUNNER AND OUT OF SNAKE? would have to be accesible from snake somehow
    static DoubleGui gui;
    private static boolean won = false;
    static int boardWidth, boardHeight;

    public static void main(String Args[]) {
        board = new Gameboard(38, 38);
        python = new Snake('D', board, 'M');
        blackMamba = new Snake('U', board, 'M', 37, 37);
        gui = new DoubleGui();
        blackMamba = new Snake('U', board, 'M', (gui.getWidth() / 15) - 1, (gui.getHeight() / 15) - 1);
        Thread snakeOneRunner = new Thread(new Runnable() {
            private boolean running = true;

            @Override
            public void run() {
                while (running) {
                    gui.start = false;
                    while (python.isAlive() && blackMamba.isAlive()) {
                        boardWidth = gui.getWidth() / 15;
                        boardHeight = gui.getHeight() / 15;

                        board.setWidth(boardWidth);
                        board.setHeight(boardHeight);

                        if (boardWidth * boardHeight < python.getLength()) {
                            gui.labelMessage(4);
                            won = true;
                            break;
                        }
                        if (!gui.pause) {
                            gui.labelMessage(2); // NO TEXT

                            python.dir = gui.getLeftDir(); // GET NEW DIR
//                            blackMamba.dir = gui.getRightDir();

                            python.changeDir(python.dir); // UPDATE SNAKE DIR
//                            blackMamba.changeDir(blackMamba.dir);

                            python.updateSnake(); // UPDATE SNAKE

//                            blackMamba.setFood(python.getFood());
//                            blackMamba.updateSnake();

                            if (python.eaten) {
                                blackMamba.setFood(python.getFood());
                            } else if (blackMamba.eaten) {
                                python.setFood(blackMamba.getFood());
                            }


                            gui.repaint(); // REDRAW GUI
                            if (!python.isAlive() && blackMamba.isAlive()) {
                                gui.start = false;
                            }
                        } else {
                            gui.labelMessage(1); // PAUSE MESSAGE
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (!won) {
                        if (python.getLength() > blackMamba.getLength()) { // P1 WINS
                            gui.labelMessage(3);
                        } else if (python.getLength() == blackMamba.getLength()) { // TIE
                            gui.labelMessage(4);
                        } else { // P2 WINS
                            gui.labelMessage(5);
                        }

                        while (!gui.start) { // WAIT FOR RESTART
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                        python = new Snake('D', board, 'M'); // NEW SNAKE;
                        blackMamba = new Snake('U', board, 'M', boardWidth - 1, boardHeight - 1);
                        gui.labelMessage(0); // PRESS PLAY TO START MESSAGE

                        gui.repaint(); // DRAW NEW SNAKE

                        gui.start = false;

                        while (!gui.start) { // WAIT FOR START
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        python.setSpeed(gui.speed);
                        blackMamba.setSpeed(gui.speed);
                        gui.labelMessage(2); // NO TEXT
                    } else { // GAME WON
                        won = false;

                        gui.start = false;
                        while (!gui.start) { // WAIT FOR RESTART
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        gui.labelMessage(0); // PRESS PLAY TO START MESSAGE
                        gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                        python = new Snake('D', board, 'M'); // NEW SNAKE
                        blackMamba = new Snake('U', board, 'M', boardWidth - 1, boardHeight - 1);

                        gui.repaint(); // DRAW NEW SNAKE

                        gui.start = false;

                        while (!gui.start) { // WAIT FOR START
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        python.setSpeed(gui.speed);
                        gui.labelMessage(2); // NO TEXT
                    }
                }

                //display lost message + score + hit space to try again and to reset snake /field
                //more gui stuff
//                }
            }
        }, "snakeRunner");
        Thread snakeTwoRunner = new Thread(new Runnable() {
            private boolean running = true;

            @Override
            public void run() {
                while (running) {
                    gui.start = false;
                    while (python.isAlive() && blackMamba.isAlive()) {
                        boardWidth = gui.getWidth() / 15;
                        boardHeight = gui.getHeight() / 15;

                        board.setWidth(boardWidth);
                        board.setHeight(boardHeight);

                        if (boardWidth * boardHeight < python.getLength()) {
                            gui.labelMessage(4);
                            won = true;
                            break;
                        }
                        if (!gui.pause) {
                            gui.labelMessage(2); // NO TEXT

                            python.dir = gui.getLeftDir(); // GET NEW DIR
                            blackMamba.dir = gui.getRightDir();

                            python.changeDir(python.dir); // UPDATE SNAKE DIR
                            blackMamba.changeDir(blackMamba.dir);

                            python.updateSnake(); // UPDATE SNAKE

                            blackMamba.setFood(python.getFood());
                            blackMamba.updateSnake();

                            if (python.eaten) {
                                blackMamba.setFood(python.getFood());
                            } else if (blackMamba.eaten) {
                                python.setFood(blackMamba.getFood());
                            }


                            gui.repaint(); // REDRAW GUI
                            if (!python.isAlive() && blackMamba.isAlive()) {
                                gui.start = false;
                            }
                        } else {
                            gui.labelMessage(1); // PAUSE MESSAGE
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (!won) {
                        if (python.getLength() > blackMamba.getLength()) { // P1 WINS
                            gui.labelMessage(3);
                        } else if (python.getLength() == blackMamba.getLength()) { // TIE
                            gui.labelMessage(4);
                        } else { // P2 WINS
                            gui.labelMessage(5);
                        }

                        while (!gui.start) { // WAIT FOR RESTART
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                        python = new Snake('D', board, 'M'); // NEW SNAKE;
                        blackMamba = new Snake('U', board, 'M', boardWidth - 1, boardHeight - 1);
                        gui.labelMessage(0); // PRESS PLAY TO START MESSAGE

                        gui.repaint(); // DRAW NEW SNAKE

                        gui.start = false;

                        while (!gui.start) { // WAIT FOR START
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        python.setSpeed(gui.speed);
                        blackMamba.setSpeed(gui.speed);
                        gui.labelMessage(2); // NO TEXT
                    } else { // GAME WON
                        won = false;

                        gui.start = false;
                        while (!gui.start) { // WAIT FOR RESTART
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        gui.labelMessage(0); // PRESS PLAY TO START MESSAGE
                        gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                        python = new Snake('D', board, 'M'); // NEW SNAKE
                        blackMamba = new Snake('U', board, 'M', boardWidth - 1, boardHeight - 1);

                        gui.repaint(); // DRAW NEW SNAKE

                        gui.start = false;

                        while (!gui.start) { // WAIT FOR START
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        python.setSpeed(gui.speed);
                        gui.labelMessage(2); // NO TEXT
                    }
                }
            }
        }, "snakeRunnerTwo");

        gui.labelMessage(0);
        while (!gui.start) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        python.setSpeed(gui.speed);
        blackMamba.setSpeed(gui.speed);
        gui.labelMessage(2);
        snakeOneRunner.start();
        snakeTwoRunner.start();
    }
}
