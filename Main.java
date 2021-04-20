package tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] fieldMatrix = new char[3][3];
        initFieldMatrix(fieldMatrix);
        printFieldMatrix(fieldMatrix);
        char input = 'O';
        do {
            if (input == 'O') {
                input = 'X';
            } else {
                input = 'O';
            }
            changeFieldMatrix(fieldMatrix, input);
            analyseField(fieldMatrix);
        } while (analyseField(fieldMatrix));
    }

    public static void changeFieldMatrix (char[][] array, char xO) {
        boolean b = true;
        while (b) {
            System.out.print("Enter the coordinates: ");
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (array[x - 1][y - 1] == ' ') {
                    array[x - 1][y - 1] = xO;
                    printFieldMatrix(array);
                    b = false;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
    }
    public static void initFieldMatrix (char[][] array) {
        for (int i = 0; i < array.length; i++) {
            Arrays.fill(array[i], ' ');
        }
    }

    public static void printFieldMatrix (char[][] array) {
        System.out.println("---------");
        for (int i = 0; i < array.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%c ", array[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean analyseField(char[][] array) {
        int numberOfCells = 0;
        int numberOfX = 0;
        int numberOfO = 0;
        int threeO = 0;
        int threeX = 0;
        String output = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < (array[i].length); j++) {
                switch (array[i][j]) {
                    case ' ': {
                        numberOfCells += 1;
                        break;
                    }
                    case 'X': {
                        numberOfX += 1;
                        break;
                    }
                    case 'O': {
                        numberOfO += 1;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (array[i][0] + array[i][1] + array[i][2] == 264) {
                threeX += 1;
            }
            if (array[0][i] + array[1][i] + array[2][i] == 264) {
                threeX += 1;
            }

            if (array[i][0] + array[i][1] + array[i][2] == 237) {
                threeO += 1;
            }
            if (array[0][i] + array[1][i] + array[2][i] == 237) {
                threeO += 1;
            }
        }

        if (array[0][0] + array[1][1] + array[2][2] == 264) {
            threeX += 1;
        }
        if (array[0][2] + array[1][1] + array[2][0] == 264) {
            threeX += 1;
        }
        if (array[0][0] + array[1][1] + array[2][2] == 237) {
            threeO += 1;
        }
        if (array[0][2] + array[1][1] + array[2][0] == 237) {
            threeO += 1;
        }

        if (Math.abs(numberOfO - numberOfX) > 1 || threeO > 0 && threeX > 0) {
            System.out.println("Impossible");
        }
        else {
            if (threeO == 0 && threeX == 0 && numberOfCells > 0) {
                output = "Game not finished";
            }
            if (threeO == 0 && threeX == 0 && numberOfCells == 0) {
                System.out.println("Draw");
            }
            if (threeO > 0 && threeX == 0) {
                System.out.println("O wins");
            }
            if (threeO == 0 && threeX > 0) {
                System.out.println("X wins");
            }
        }
        if (output.equals("Game not finished")) {
            return true;
        }
        else return false;
    }
}
