package com.company;

import javax.swing.*;

/**
 * Created by lhscompsci on 4/3/17.
 */

public class SnakeRunner extends JPanel {
    static Snake python;
    static Gui gui;
    static boolean newSnakeMade = false;

    public static void main(String Args[]) {
        python = new Snake('R');
        gui = new Gui();
        //make a gameboard
        Thread snakeRunner = new Thread(new Runnable() {
            private boolean running = true;

            public void stopInTheNameOfLove() {
                running = false;
            }

            @Override
            public void run() {
                while (running) {
                    gui.start = false;
                    while (python.isAlive()) {
                        if (!gui.pause) {
                            gui.labelMessage(2); // NO TEXT
                            python.dir = gui.getDir(); // GET NEW DIR
                            python.changeDir(python.dir); // UPDATE SNAKE DIR
                            python.updateSnake(); // UPDATE SNAKE
                            gui.repaint(); // REDRAW GUI
                            if (!python.isAlive()) {
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
                    gui.labelMessage(3); //SCORE MESSAGE
                    //DEAD
                    while(!gui.start) { // WAIT FOR RESTART
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                    python = new Snake('R'); // NEW SNAKE
                    gui.labelMessage(0); // PRESS PLAY TO START MESSAGE
                    gui.repaint();


                    gui.start = false;


                    while (!gui.start) { // WAIT FOR START
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    gui.labelMessage(2); // NO TEXT

                }

                //display lost message + score + hit space to try again and to reset snake /field
                //more gui stuff
//                }
            }
        }, "snakeRunner");
        gui.labelMessage(0);
        while (!gui.start) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        gui.labelMessage(2);
        snakeRunner.start();
    }
}
