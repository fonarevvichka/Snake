package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by lhscompsci on 4/3/17.
 */

public class SnakeRunner{
    static Snake python;
    static char dir = 'R', prevDir = 'R';
    static int key;
    public static void main(String Args[]) {
        python = new Snake('R');
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.getContentPane().add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.setBackground(Color.red);
        panel.setSize(400, 400);
        panel.setVisible(true);
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch(key) {
                    case KeyEvent.VK_LEFT:
                        dir = 'L';
                        break;
                    case KeyEvent.VK_RIGHT:
                        dir = 'R';
                        break;
                    case KeyEvent.VK_DOWN:
                        dir = 'D';
                        System.out.println("dir");
                        break;
                    case KeyEvent.VK_UP:
                        dir = 'U';
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
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

//                        System.out.println(dir);
                        python.changeDir(dir);
//                        python.updateSnake();
                        if (!python.isAlive()) {
                            stopInTheNameOfLove();
                        }
                        //gui stuff
                    }
                    //more gui stuff
                }
            }
        }, "snakeRunner");
        snakeRunner.start();
    }

}
