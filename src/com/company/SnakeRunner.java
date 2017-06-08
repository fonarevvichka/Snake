package com.company;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by lhscompsci on 4/3/17.
 */

public class SnakeRunner extends JPanel {
    static Snake python;
    static Gameboard board;
    static Gui gui;
    private static boolean won = false;
    static int boardWidth, boardHeight;
    public static String[] topPlayers = {"john", "john", "john", "john", "john"};
    public static int[] topScores = {0,0,0,0,0};

    public static void main(String Args[]) {
        board = new Gameboard(38, 38);
        python = new Snake('S', board, 'M');
        gui = new Gui();
        Thread snakeRunner = new Thread(new Runnable() {
            private boolean running = true;

            @Override
            public void run() {
                while (running) {
                    updateBoardDimensions();
                    gui.start = false;

                    while (python.isAlive()) {
                        if (boardWidth * boardHeight < python.getLength()) { // CHECK IF THE GAME IS WON
                            gui.labelMessage(4); // WON MESSAGE
                            won = true;
                            break;
                        }

                        if (!gui.pause) {
                            gui.labelMessage(2); // NO TEXT
                            python.dir = gui.getDir(); // GET NEW DIR
                            python.changeDir(python.dir); // UPDATE SNAKE DIR
                            python.updateSnake(); // UPDATE SNAKE
                            gui.repaint(); // REDRAW GUI
                            if (!python.isAlive()) {
                                gui.start = false;
                            }
                        } else {
                            gui.labelMessage(1); // PAUSE MESSAGE
                            sleep();
                        }
                    }

                    //------------ HIGH SCORE CALCULATION -------------//
                    readTopScores(); // populates arrays
                    int topScoreIndex = 6;
                    Scanner cin = new Scanner(System.in);
                    String name = "";
                    for (int i = 0; i < 5; i++) {
                        if (SnakeRunner.python.getLength() > SnakeRunner.topScores[i]) {
                            topScoreIndex = i;
                            gui.setScoreBeat(topScoreIndex);
                            break;
                        }
                    }
                    if (topScoreIndex < 6) {
                        gui.labelMessage(5);
                        gui.repaint();

                        System.out.print("Enter Name:");
                        name = cin.next();
                        System.out.println("Thank you");
                        int tempScore = python.getLength();
                        String tempName = name;
                        gui.repaint();

                        for(int i = 4; i > topScoreIndex; i--) {
                            topPlayers[i] = topPlayers[i-1];
                            topScores[i] = topScores[i-1];

                        }
                        SnakeRunner.topPlayers[topScoreIndex] = name;
                        SnakeRunner.topScores[topScoreIndex] = SnakeRunner.python.getLength();
                    } else {
                        gui.labelMessage(3);
                        gui.repaint();
                    }
                    writeScoresToFile();
                    //------------ HIGH SCORE CALCULATION -------------//



                    if (!won) {
                        gui.labelMessage(3); //SCORE MESSAGE
                        while (!gui.start) { // WAIT FOR RESTART
                            sleep();
                        }
                        gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                        python = new Snake('S', board, 'M'); // NEW SNAKE;
                        gui.labelMessage(0); // PRESS PLAY TO START MESSAGE

                        gui.repaint(); // DRAW NEW SNAKE

                        gui.start = false;

                        while (!gui.start) { // WAIT FOR START
                            sleep();
                        }
                        python.setSpeed(gui.speed);
                        gui.labelMessage(2); // NO TEXT
                    }
                    else { // GAME WON
                        won = false;

                        gui.start = false;
                        while (!gui.start) { // WAIT FOR RESTART
                            sleep();
                        }
                        gui.labelMessage(0); // PRESS PLAY TO START MESSAGE
                        gui.resetKeyboardActions(); // RESET KEYBOARD INPUT
                        python = new Snake('S', board, 'M'); // NEW SNAKE;

                        gui.repaint(); // DRAW NEW SNAKE

                        gui.start = false;

                        while (!gui.start) { // WAIT FOR START
                            sleep();
                        }
                        python.setSpeed(gui.speed);
                        gui.labelMessage(2); // NO TEXT
                    }
                }

                //display lost message + score + hit space to try again and to reset snake /field
                //more gui stuff
//                }

            }
        }, "snakeRunner");
        gui.labelMessage(0);
        while (!gui.start) {
            sleep();
        }
        python.setSpeed(gui.speed);
        gui.labelMessage(2);
        snakeRunner.start();

    }

    public static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateBoardDimensions() {
        boardWidth = gui.getWidth() / 15;
        boardHeight = (gui.getHeight() / 15);

        board.setWidth(boardWidth);
        board.setHeight(boardHeight);
    }

    public static void readTopScores() {
        Scanner reader = null;
        String tempName = "";
        int count = 0;
        try {
            File file = new File("topScores.txt");
            reader = new Scanner(file);
        } catch (Exception e) {
            System.out.println ("Wrong file name");
        }
        while (reader.hasNext()) {
            topScores[count] = reader.nextInt();
            topPlayers[count] = reader.next();
            count ++;
        }
        reader.close();
    }

    public static void writeScoresToFile() {
        PrintWriter writer = null;
        String tempName = "";
        try {
            File file = new File("topScores.txt");
            writer = new PrintWriter(file);
        } catch (Exception e) {
            System.out.println ("Wrong file name");
        }
        for (int i = 0; i < topScores.length; i ++) {
            writer.println(topScores[i] + " " + topPlayers[i]);
        }
        writer.close();
        writer.flush();
    }


}
