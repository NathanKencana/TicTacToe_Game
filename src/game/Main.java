package game;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static String[] board;
    static String turn;

    public static void main(String[] args)
    {
        System.out.println("Welcome to Than's Tic Tac Toe.");

        Scanner in = new Scanner(System.in);
        int boardSize = inputBoardSize();

        board = new String[boardSize +1];
        turn = "X";
        String winner = null;

        for (int a = 0; a < boardSize; a++) {
            board[a] = String.valueOf(a + 1);
        }

        printBoard(boardSize);

        System.out.println("X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int numInput;

            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= boardSize)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                in = new Scanner(System.in);
                continue;
            }

            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;

                if (turn.equals("X")) {
                    turn = "O";
                }
                else {
                    turn = "X";
                }

                printBoard(boardSize);
                winner = checkWinner(boardSize);
            }
            else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
    }

    static void printBoard(int boardSize)
    {
        String pipe = "|";
        String line = "-----";
        String dividingLine = "";
        int baseNumber = (int) Math.sqrt(boardSize);

        for(int i = 0; i < baseNumber; i++) {
            dividingLine = dividingLine + line;
        }
        System.out.println("");
        dividingLine = "|".concat(dividingLine.substring(1)).concat("|");
        System.out.println(dividingLine);

        int counter = 0;

        for (int i = 1; i <= baseNumber * 2; i++) {
            if(i % 2 == 0) {
                System.out.println(dividingLine);
            } else {
                int j = 0;
                do {
                    String boardNumber = board[counter];
                    if(boardNumber.length() < 2) {
                        boardNumber = " ".concat(boardNumber);
                    }
                    System.out.print("| " + boardNumber + " ");
                    j++;
                    counter++;
                }
                while(j < baseNumber);
                System.out.print("|\n");
            }
        }

    }

    private static int inputBoardSize() {
        Scanner inBoardSize = new Scanner(System.in);
        boolean boardChosen = false;

        while (!boardChosen) {
            System.out.println("Please input your desired board size : \n a. 3x3 \n b. 5x5 \n c. 7x7 \n d. 9x9");
            String inputBoard = inBoardSize.next();
            if ("a".equalsIgnoreCase(inputBoard)) {
                System.out.println("You've chosen 3x3 board");
                return 9;
            } else if ("b".equalsIgnoreCase(inputBoard)) {
                System.out.println("You've chosen 5x5 board");
                return 25;
            } else if ("c".equalsIgnoreCase(inputBoard)) {
                System.out.println("You've chosen 7x7 board");
                return 49;
            } else if ("d".equalsIgnoreCase(inputBoard)){
                System.out.println("You've chosen 9x9 board");
                return 81;
            } else {
                System.out.println("Invalid input, please input the specified option.");
            }
        }
        return 0;
    }

    static String checkWinner(int boardSize)
    {
        int baseNumber = (int) Math.sqrt(boardSize);
        int counterLoop = baseNumber-2;

        int counterRow = 0;
        for (int i = 0; i < baseNumber; i++) {
            for (int j = 0; j < counterLoop; j++) {
                String line = board[counterRow] + board[counterRow+1] + board[counterRow+2];
                if(line.equalsIgnoreCase("xxx")) {
                    return "X";
                } else if(line.equalsIgnoreCase("ooo")) {
                    return "O";
                }
                counterRow++;
            }
            counterRow+=2;
        }

        int counterColumn = 0;
        for (int i = 0; i < counterLoop; i++) {
            for (int j = 0; j < baseNumber; j++) {
                String line = board[counterColumn] + board[counterColumn+baseNumber] + board[counterColumn+(baseNumber*2)];
                if(line.equalsIgnoreCase("xxx")) {
                    return "X";
                } else if(line.equalsIgnoreCase("ooo")) {
                    return "O";
                }
                counterColumn++;
            }
        }

        int counterDiagonalRight = 0;
        for (int i = 0; i < counterLoop; i++) {
            for (int j = 0; j < counterLoop; j++) {
                String line = board[counterDiagonalRight] + board[counterDiagonalRight+baseNumber+1] + board[counterDiagonalRight+((baseNumber + 1) * 2)];
                if (line.equalsIgnoreCase("xxx")) {
                    return "x";
                } else if (line.equalsIgnoreCase("ooo")) {
                    return "o";
                }
                counterDiagonalRight++;
            }
            counterDiagonalRight+=2;
        }

        int counterDiagonalLeft = baseNumber-1;
        int counter = 1;
        for (int i = counterLoop; i > 0; i--) {
            for (int j = counterLoop; j > 0; j--) {
                String line = board[counterDiagonalLeft] + board[counterDiagonalLeft + baseNumber - 1]
                        + board[counterDiagonalLeft
                        + ((baseNumber - 1)*2)];
                if (line.equalsIgnoreCase("xxx")) {
                    return "x";
                } else if (line.equalsIgnoreCase("ooo")) {
                    return "o";
                }
                counterDiagonalLeft--;
            }
            counter++;
            counterDiagonalLeft = (baseNumber * counter) - 1;
        }

        for (int a = 0; a < boardSize; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            }
            else if (a == boardSize -1) {
                return "draw";
            }
        }

        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

}
