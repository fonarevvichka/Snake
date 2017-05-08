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
    private String message;
    private char dir = SnakeRunner.python.dir;
    public boolean start = false, pause = false;
    private JFrame frame;
    private int text;
//    private Vector<Icon> snake;
//    private Icon snakeCell;
//    private Icon food;

    public Gui() {
        this.width = 15 * (SnakeRunner.python.getBoard().getWidth());
        this.height = 15 * (SnakeRunner.python.getBoard().getHeight()) + 22;


        // ----------- Panel Setup ---------//
        this.setSize(width, height);
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
                dir = SnakeRunner.python.dir;
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
//        frame.setSize(width, height)
        frame.setVisible(true);
        // ----------- Frame Setup ---------//

        //----------- Draw Snake -----------//
        //----------- Draw Snake -----------//
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Cell cell : SnakeRunner.python.getSnakeVector()) {
            g.setColor(Color.yellow);
            g.fillRect(cell.getX() * 15, cell.getY() * 15,15, 15);
        }
        g.setColor(Color.red);
        g.fillRect(SnakeRunner.python.food.getXCord() * 15, SnakeRunner.python.food.getYCord() * 15, 15, 15);

        switch(text) {
            case 0:
                message = "Press 'Space' to begin, move around with the arrow keys";
                break;
            case 1:
                message = "Paused, press 'Space to resume";
                break;
            case 2:
                message = "";
                break;
            case 3:
                message = "Press space to try again, you lost with a score of: " + SnakeRunner.python.getLength();
                break;
        }
//        System.out.println(message.length());
        g.drawString(message,width/2 - (int) (message.length()*3.2), height/2);


    }

    public char getDir() {
        return dir;
    }
    public void labelMessage(int text) {
        this.text = text;
    }
}
