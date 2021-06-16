package com.creator;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static int turn = 0;
    static JPanel[][] panelArray = new JPanel[6][7];

    public static void main(String[] args) {
        ConnectFour c4 = new ConnectFour();
        JFrame jframe = new JFrame();
        jframe.getContentPane().add(c4.board);
        jframe.pack();
        jframe.setSize(550,430);
        jframe.setVisible(true);
        String[][] board = new String[6][7];
        panelArray = c4.getArray();
        Scanner scanner = new Scanner(System.in);
        checks check = new checks();
        JPanel winner = c4.getPrinter();

        //red = player 1
        String red = "R";

        //yellow = player 2
        String yellow = "Y";

        //whitespace
        String empty = " ";
        for (String[] strings : board) {
            Arrays.fill(strings, empty);
        }

        while (true) {
            checks.display(board);

            if (turn == 42){
                System.out.println("Game is a draw!");
                System.exit(0);
            }

            c4.getOne().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 0);
                if (turn % 2 != 0) {
                    board[rowCheck][0] = red;
                    panelArray[rowCheck][0].setBackground(Color.red);
                } else {
                    board[rowCheck][0] = yellow;
                    panelArray[rowCheck][0].setBackground(Color.yellow);
                }
                redundant(board, check, winner, red, yellow);
            });

            c4.getTwo().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 1);
                if (turn % 2 != 0) {
                    board[rowCheck][1] = red;
                    panelArray[rowCheck][1].setBackground(Color.red);
                } else {
                    board[rowCheck][1] = yellow;
                    panelArray[rowCheck][1].setBackground(Color.yellow);
                }
                redundant(board, check, winner, red, yellow);
            });

            c4.getThree().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 2);
                if (turn % 2 != 0) {
                    board[rowCheck][2] = red;
                    panelArray[rowCheck][2].setBackground(Color.red);
                } else {
                    board[rowCheck][2] = yellow;
                    panelArray[rowCheck][2].setBackground(Color.yellow);
                }
                redundant(board, check, winner, red, yellow);
            });

            c4.getFour().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 3);
                if (turn % 2 != 0) {
                    board[rowCheck][3] = red;
                    panelArray[rowCheck][3].setBackground(Color.red);
                } else {
                    board[rowCheck][3] = yellow;
                    panelArray[rowCheck][3].setBackground(Color.yellow);
                }
                redundant(board, check, winner, red, yellow);
            });

            c4.getFive().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 4);
                if (turn % 2 != 0) {
                    board[rowCheck][4] = red;
                    panelArray[rowCheck][4].setBackground(Color.red);
                } else {
                    board[rowCheck][4] = yellow;
                    panelArray[rowCheck][4].setBackground(Color.yellow);
                }
                redundant(board, check, winner, red, yellow);
            });

            c4.getSix().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 5);
                if (turn % 2 != 0) {
                    board[rowCheck][5] = red;
                    panelArray[rowCheck][5].setBackground(Color.red);
                } else {
                    board[rowCheck][5] = yellow;
                    panelArray[rowCheck][5].setBackground(Color.yellow);
                }
                redundant(board, check, winner, red, yellow);
            });

            c4.getSeven().addActionListener(e -> {
                int rowCheck;
                rowCheck = check.emptyCheck(board, empty, 6);
                if (turn % 2 != 0) {
                    board[rowCheck][6] = red;
                    panelArray[rowCheck][6].setBackground(Color.red);
                } else {
                    board[rowCheck][6] = yellow;
                    panelArray[rowCheck][6].setBackground(Color.yellow);
                }
                checks.display(board);
                turn++;
                if (check.verticalCheck(board, red, yellow)) {
                        winner.setBackground(Color.red);
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
//                    System.exit(0);
                }
                duplicate(board, check, winner, red, yellow);
            });

            //Ask where to place piece
            boolean piece = false;
            turn++;
            while (!piece) {
                System.out.println("Enter the column (0 to 6) you want to put your piece in: ");
                int location = scanner.nextInt();
                int rowCheck = -10;
                if (location >= 0 && location <= 6) rowCheck = check.emptyCheck(board, empty, location);
                if (rowCheck == -1) {
                    System.out.println("The column is full, enter another column: ");
                    location = scanner.nextInt();
                } else if (rowCheck != -10){
                    if (turn % 2 != 0) {
                        board[rowCheck][location] = red;
                        panelArray[rowCheck][location].setBackground(Color.red);
                    }
                    else {
                        board[rowCheck][location] = yellow;
                        panelArray[rowCheck][location].setBackground(Color.yellow);
                    }
                    piece = true;
                }
            }
        }
    }

    private static void duplicate(String[][] board, checks check, JPanel winner, String red, String yellow) {
        if (!check.horizontalCheck(board, red, yellow)) {
            duplicated(winner);
        }
        if (!check.downDiagonalCheck(board, red, yellow)) {
            duplicated(winner);
        }
        if (!check.upDiagonalCheck(board, red, yellow)) {
            duplicated(winner);
        }
    }

    private static void duplicated(JPanel winner) {
        if (turn % 2 != 0) {
            winner.setBackground(Color.yellow);
        } else {
            winner.setBackground(Color.red);
        }
    }

    private static void redundant(String[][] board, checks check, JPanel winner, String red, String yellow) {
        checks.display(board);
        turn++;
        if (check.verticalCheck(board, red, yellow)) {
            duplicated(winner);
        }
        duplicate(board, check, winner, red, yellow);
    }
}