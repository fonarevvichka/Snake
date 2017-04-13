package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by lhscompsci on 4/3/17.
 */

public class SnakeRunner extends Frame {
    static Snake python;
    static char dir = 'R', prevDir = 'R';
    static int key;
    static boolean start = false, pause = false;
    public static void main(String Args[]) {
        python = new Snake('R');

        Gui gui = new Gui(1920*2/3,1080*2/3);
        //make a gameboard
        Thread snakeRunner = new Thread(new Runnable() {
            private boolean running = true;
            public void stopInTheNameOfLove() {
                running = false;
            }
            @Override
            public void run() {
                while(running) {
                    while (python.isAlive()) {
                        if(!gui.isPaused()) {
                            gui.labelMessage(2);
                            dir = gui.getDir();
                            System.out.println(dir);
                            python.changeDir(dir);

                            python.updateSnake();
                            if (!python.isAlive()) {
                                stopInTheNameOfLove();
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
                    //more gui stuff
                }
            }
        }, "snakeRunner");
        gui.labelMessage(0);
        while(!gui.isRunning()) {
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
