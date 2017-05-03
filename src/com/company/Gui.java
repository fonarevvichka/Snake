package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

/**
 * Created by lhscompsci on 4/12/17.
 */
public class Gui extends JPanel {
    private int width, height;
    private int key;
    private char dir = 'R';
    private boolean start = false, pause = false;
    private Label label;
    private JFrame frame;
//    private Vector<Icon> snake;
//    private Icon snakeCell;
//    private Icon food;

    int x = 0, y = 0;

    public Gui(int width, int height) {
        this.width = width;
        this.height = height;


        // ----------- Label Setup ---------//
//        welcomeText = new Label("Press 'Space' to begin move around with the arrow keys");
        label = new Label("                                                                                              ");
        label.setAlignment(Label.CENTER);
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.WHITE);
        // ----------- Label Setup ---------//

        // ----------- Panel Setup ---------//
//        panel = new JPanel(new GridBagLayout()); //try spring
        this.add(label);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.setBackground(Color.darkGray);
        // ----------- Panel Setup ---------//

        //------------ Image Setup ---------//
//        snakeCell = new ImageIcon("snakeCell.png");
//        food = new ImageIcon("snakeFood.png");
        //------------ Image Setup ---------//

        //-------- Key Listener --------//
        this.addKeyListener(new KeyListener() {
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
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.getContentPane().add(this);
        frame.pack();
        frame.setSize(width, height);
        frame.setVisible(true);
        // ----------- Frame Setup ---------//

        //----------- Draw Snake -----------//
        //----------- Draw Snake -----------//
    }

//    public void draw(Graphics g) {
//        g.setColor(Color.yellow);
//        g.fillRect(40,40, 20,20);
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("safsdf");
        for(Cell cell : SnakeRunner.python.getSnakeVector()) {
            g.setColor(Color.yellow);
            g.fillRect(cell.getX()*15, cell.getY()*15, 15, 15);
        }
        g.setColor(Color.red);
        g.fillRect(SnakeRunner.python.food.getXCord() * 15, SnakeRunner.python.food.getYCord() * 15, 15, 15);
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
