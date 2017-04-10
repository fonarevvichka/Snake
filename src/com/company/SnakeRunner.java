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
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new GridBagLayout());

        frame.setTitle("Snake");


        //--------------- Creating Welcome Text -----------------//
        Label welcomeText = new Label("Press 'Space' to begin move around with the arrow keys");
        welcomeText.setAlignment(Label.CENTER);
        welcomeText.setBackground(Color.DARK_GRAY);
        welcomeText.setForeground(Color.WHITE);
        panel.add(welcomeText);
        //--------------- Creating Welcome Text -----------------//

        //--------------- Borders RIP -------------------------//
        //--------------- Borders RIP -------------------------//

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        panel.setFocusable(true);
        panel.requestFocus();
        panel.setBackground(Color.darkGray);

        frame.setSize(1600,1600);
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
                    case KeyEvent.VK_SPACE:
                        start = true;
                        pause = false;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        pause = true;
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
        while(!start) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        snakeRunner.start();
    }
}
