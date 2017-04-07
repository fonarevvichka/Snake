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
        JPanel panel = new JPanel(new GridBagLayout());

        panel.add(new JTextField(20));
        frame.getContentPane().add(panel);
        frame.setVisible(true);

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
                key = e.getKeyCode();
//                System.out.println(e.getKeyCode());
                switch(key) {
                    case KeyEvent.VK_LEFT:
                        if(dir != 'R') {
                            dir = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(dir != 'L') {
                            dir = 'R';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(dir != 'U') {
                            dir = 'D';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(dir != 'D') {
                            dir = 'U';
                        }
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        frame.pack();
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

                        System.out.println(dir);
                        python.changeDir(dir);
                        python.updateSnake();
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
