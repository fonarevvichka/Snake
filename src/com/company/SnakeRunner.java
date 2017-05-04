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
                    python.dir = 'R';
                    while (python.isAlive()) {
                        gui.restart = 0;
                        if (!gui.pause) {
                            gui.labelMessage(2);
                            python.dir = gui.getDir();
                            System.out.println(gui.restart);
                            python.changeDir(python.dir);
                            python.updateSnake();
                            gui.repaint();
                            if (!python.isAlive()) {
                                gui.start = false;
                            }
                        } else {
                            gui.labelMessage(1);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        //gui stuff
                    }

                    gui.labelMessage(3);

                    if (gui.restart == 1 && !newSnakeMade) {
                        python = new Snake('R');
                        python.dir = 'R';
                        newSnakeMade = true;
                    }else if (gui.restart == 2) {
                        gui.start = true;
                        gui.restart = 0;

                    }
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
