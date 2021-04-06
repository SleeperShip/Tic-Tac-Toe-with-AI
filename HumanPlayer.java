package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public void makeMove(Board board) {
        char player = board.getCurrentPlayer();
        int[] coordinates = new int[2];
        coordinates = getCoordinates(board);
        board.markBoard(player, coordinates[0], coordinates[1]);
        board.printBoard();
        board.updateGame();
    }

    public int[] getCoordinates(Board board) {
        Scanner inputScanner = new Scanner(System.in);
        int row = 0;
        int column = 0;
        boolean invalid = true;
        int[] coordinates = new int[2];
        do {
            System.out.println("Enter the coordinates: >");
            if (inputScanner.hasNextInt()) {  //Checks that the input can be parsed as an int
                row = inputScanner.nextInt() - 1; //subtract 1 to account for zero-indexing in array
                column = inputScanner.nextInt() - 1;

                if (row > 2 || row < 0 || column < 0 || column > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (!board.cellOpen(row, column)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                inputScanner.nextLine();  // Advances the scanner to prevent input errors
                invalid = false;  // Sets the condition to false to break the loop
            } else {
                System.out.println("You should enter numbers!");
                inputScanner.nextLine(); // Advances the scanner to prevent input errors
            }
        } while (invalid); // The loop ends when the input is valid
        coordinates[0] = row;
        coordinates[1] = column;
        return coordinates;
    }
}
