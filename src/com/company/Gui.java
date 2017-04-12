package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by lhscompsci on 4/12/17.
 */
public class Gui extends Frame {
    private int width, height;
    private int key;
    private char dir = 'R';

    private Label label;
    private boolean start = false, pause = false;
    public Gui(int width, int height) {
        this.width = width;
        this.height = height;


        // ----------- Label Setup ---------//
//        welcomeText = new Label("Press 'Space' to begin move around with the arrow keys");
        label = new Label("");
        label.setAlignment(Label.CENTER);
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.WHITE);

        // ----------- Label Setup ---------//
        // ----------- Panel Setup ---------//
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(label);
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.setBackground(Color.darkGray);

        // ----------- Panel Setup ---------//


        //-------- Key Listener --------//
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
        //-------- Key Listener --------//
        // ----------- Frame Setup ---------//
        JFrame frame = new JFrame();
        frame.setTitle("Snake");
//        frame.setSize(width, height);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.pack();
        // ----------- Frame Setup ---------//

    }
    public char getDir() {
        return dir;
    }
    public boolean isRunning() {
        return start;
    }
    public boolean isPaused() {
        return pause;
    }
    public void labelMessage(int text) {
        if(text == 0) {
            label.setText("Press 'Space' to begin move around with the arrow keys");
        } else if (text == 1) {
            label.setText("Paused, press 'Space' to resume");
        } else if (text == 2) {
            label.setText("");
        }
    }
}
