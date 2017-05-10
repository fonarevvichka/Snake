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
    private String topMessage, bottomMessage, scoreMessage;
    int offset;
    private char dir = SnakeRunner.python.dir;
    public boolean start = false, pause = false;
    private JFrame frame;
    private int text;
//    private Vector<Icon> snake;
//    private Icon snakeCell;
//    private Icon food;

    public Gui() {
        this.width = 15 * (SnakeRunner.python.getBoard().getWidth());
        this.height = 15 * (SnakeRunner.python.getBoard().getHeight()) + 22 + 30; //22 --> account for toolbar, 30 --> score bar

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
        frame.setVisible(true);
        // ----------- Frame Setup ---------//
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        //----------------- Score Bar -----------------//
        g.setColor(new Color(45, 45, 45));
        g.fillRect(0, height-39, width, 20);

        g.setColor(Color.WHITE);
        scoreMessage = "Score: " + SnakeRunner.python.getLength();
        g.drawString(scoreMessage, width - g.getFontMetrics().stringWidth(scoreMessage) - 5, height - 25);
        //----------------- Score Bar -----------------//

        g.setColor(Color.yellow);
        for (Cell cell : SnakeRunner.python.getSnakeVector()) {
            g.fillRect(cell.getX() * 15, cell.getY() * 15,13, 13);
        }
        g.setColor(Color.red);
        g.fillRect(SnakeRunner.python.food.getXCord() * 15, SnakeRunner.python.food.getYCord() * 15, 13, 13);

        switch(text) {
            case 0:
                topMessage = "Press 'Space' to begin, move around with the arrow keys";
                bottomMessage = "";
                offset = 0;
                break;
            case 1:
                topMessage = "Paused, press 'Space to resume";
                bottomMessage = "";
                offset = 0;

                break;
            case 2:
                topMessage = "";
                bottomMessage = "";
                offset = 0;
                break;
            case 3:
                offset = 10;
                topMessage = "You lost with a score of: " + SnakeRunner.python.getLength();
                bottomMessage = "Press space to try again";
                break;
        }
        if(text != 2) {
            g.setColor(new Color(140,140,140,200));
            g.fillRect(width/2 - (g.getFontMetrics().stringWidth(topMessage))/2 - 5, height/2 - offset - 25, g.getFontMetrics().stringWidth(topMessage) + 10, 20);
            if(text == 3) {
                g.fillRect(width / 2 - g.getFontMetrics().stringWidth(topMessage) / 2 - 5, height / 2 + offset - 25, g.getFontMetrics().stringWidth(topMessage) + 10, 20);
            }
        }
        g.setColor(Color.white);

        g.drawString(topMessage, width/2 - (g.getFontMetrics().stringWidth(topMessage))/2, height/2 - offset - 10);
        g.drawString(bottomMessage, width/2 - g.getFontMetrics().stringWidth(bottomMessage)/2, height/2 + offset - 10);


    }
    public char getDir() {
        return dir;
    }
    public void labelMessage(int text) {
        this.text = text;
    }
}
