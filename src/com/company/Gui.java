package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.Scanner;

/**
 * Created by lhscompsci on 4/12/17.
 */
public class Gui extends JPanel {
    private int pixelWidth, pixelHeight;
    private int cleanWidth, cleanHeight;
    private int key;
    public String topMessage, bottomMessage, scoreMessage;
    int offset;
    private char dir = SnakeRunner.python.dir;
    public boolean start = false, pause = false;
    public char speed = 'M';
    private JFrame frame;
    private int text;

    public Gui() {
        this.pixelWidth = 15 * (SnakeRunner.board.getWidth());
        this.pixelHeight = 15 * (SnakeRunner.board.getHeight()) + 22; //22 --> account for toolbar, 20 --> score bar

        // ----------- Panel Setup ---------//
        this.setSize(pixelWidth, pixelHeight);
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.setBackground(new Color(45, 45, 45));
        // ----------- Panel Setup ---------//

        //-------- Key Listener --------//
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                key = e.getKeyCode();
                dir = SnakeRunner.python.dir;
                switch(key) {
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        if(dir != 'R') {
                            dir = 'L';
                        }
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        if(dir != 'L') {
                            dir = 'R';
                        }
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        if(dir != 'U') {
                            dir = 'D';
                        }
                        break;
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        if(dir != 'D') {
                            dir = 'U';
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        start = true;
                        pause = !pause;

                        break;
                    case KeyEvent.VK_1:
                        speed = 'S';
                        pause = false;
                        start = true;
                        break;
                    case KeyEvent.VK_2:
                        speed = 'M';
                        pause = false;
                        start = true;
                        break;
                    case KeyEvent.VK_3:
                        speed = 'F';
                        pause = false;
                        start = true;
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        //-------- Key Listener --------//

        // ----------- Frame Setup ---------//
        frame = new JFrame();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.getContentPane().add(this);
        frame.pack();
        frame.setSize(pixelWidth, pixelHeight);
        frame.setVisible(true);
        // ----------- Frame Setup ---------//
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pixelWidth = getWidth();
        pixelHeight = getHeight();
        cleanWidth = (pixelWidth /15)*15;
        cleanHeight = (pixelHeight /15)*15;
        //----------------- Paint Board ---------------//
        g.setColor(Color.darkGray);
        g.fillRect(0,0, cleanWidth, cleanHeight); //gets rid of overflow pixels
        //----------------- Paint Board ---------------//


        //----------------- Score Bar -----------------//
//        g.setColor(new Color(45, 45, 45));
//        g.fillRect(0, pixelHeight - 30, pixelWidth, 30);

        g.setColor(Color.WHITE);
        scoreMessage = "Score: " + SnakeRunner.python.getLength();
        g.drawString(scoreMessage, cleanWidth - g.getFontMetrics().stringWidth(scoreMessage) - 10, cleanHeight - 10);
        //----------------- Score Bar -----------------//

        //----------------- Snake ---------------------//
        g.setColor(Color.yellow);
        for (Cell cell : SnakeRunner.python.getSnakeVector()) {
            g.fillRect(cell.getX() * 15, cell.getY() * 15,13, 13);
        }
        //----------------- Snake ---------------------//

        //----------------- Food ---------------------//
        g.setColor(Color.red);
        g.fillRect(SnakeRunner.python.food.getXCord() * 15, SnakeRunner.python.food.getYCord() * 15, 13, 13);
        //----------------- Food ---------------------//

        //----------------- Set Text -----------------//

        switch(text) {
            case 0:
                topMessage = "Press '1', '2', or '3' for speed and to begin, move around with the arrow keys, or WASD";
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
            case 4:
                offset = 0;
                topMessage = "You won, please go outside now";
                break;
            case 5:
                topMessage = "Great job! you beat " + SnakeRunner.topPlayers[topScoreIndex] + ". Input your name:";


        }
        //----------------- Set Text ------------------------//

        //----------------- Message background ---------------------//
        if(text != 2) {
            g.setColor(new Color(45,45,45,200));
            g.fillRect(pixelWidth /2 - (g.getFontMetrics().stringWidth(topMessage))/2 - 5, pixelHeight /2 - offset - 25, g.getFontMetrics().stringWidth(topMessage) + 10, 20);
            if(text == 3) {
                g.fillRect(pixelWidth / 2 - g.getFontMetrics().stringWidth(topMessage) / 2 - 5, pixelHeight / 2 + offset - 25, g.getFontMetrics().stringWidth(topMessage) + 10, 20);
            }
        }
        //----------------- Message background ---------------------//

        //----------------- Paint Text -----------------------------//
        g.setColor(Color.white);
        g.drawString(topMessage, pixelWidth /2 - (g.getFontMetrics().stringWidth(topMessage))/2, pixelHeight /2 - offset - 10);
        g.drawString(bottomMessage, pixelWidth /2 - g.getFontMetrics().stringWidth(bottomMessage)/2, pixelHeight /2 + offset - 10);
        //----------------- Paint Text -----------------------------//


    }
    public char getDir() {
        return dir;
    }
    public void labelMessage(int text) {
        this.text = text;
    }
//    @Override
//    public int getHeight() {
//        return super.getHeight()-20;
//    }
}
