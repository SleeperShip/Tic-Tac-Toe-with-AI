package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class Game {

    public Board board;
    private Scanner inputScanner = new Scanner(System.in);

    public Game() {
        String state = "_________";
        this.board = new Board(state);
    }

    public Game(String initialState) {
        this.board = new Board(initialState);
    }

    public void createGame() {
        System.out.println("Enter the cells: >");
        String state = inputScanner.nextLine();
        board = new Board(state);
        printBoard();
    }

    public void humanVsHuman () {
        HumanPlayer player1 = new HumanPlayer();
        HumanPlayer player2 = new HumanPlayer();
        printBoard();
        player1.makeMove(board);
        while (board.getGameStatus() == Board.StateOfGame.NOT_FINISHED) {
            player2.makeMove(board);
            if(board.getGameStatus() != Board.StateOfGame.NOT_FINISHED) {
                System.out.println(board.getGameStatus());
                return;
            }
            player1.makeMove(board);
        }

        System.out.println(board.getGameStatus());
    }

    /*
    public void aiVsHuman() {
        printBoard();
        makeMove();
        while (board.getGameStatus() == Board.StateOfGame.NOT_FINISHED) {
            makeComputerMove();
            if(board.getGameStatus() != Board.StateOfGame.NOT_FINISHED) {
                System.out.println(board.getGameStatus());
                return;
            }
            makeMove();
        }

        System.out.println(board.getGameStatus());
    }
     */

    /*
    public void aiVsAi () {
        makeComputerMove();
        while (board.getGameStatus() == Board.StateOfGame.NOT_FINISHED) {
            makeComputerMove();
        }

        System.out.println(board.getGameStatus());
    }
    */

    private void getGameMode(String[] commands) {
        Player player1 = null;
        Player player2 = null;

        switch(commands[1]) {
            case "user" :
                player1 = new HumanPlayer();
                break;
            case "easy" :
                player1 = new AIPlayerEasy();
                break;
            default :
                return;
        }

        switch(commands[2]) {
            case "user" :
                player2 = new HumanPlayer();
                break;
            case "easy" :
                player2 = new AIPlayerEasy();
                break;
            default :
                return;
        }
    }

    public void menu() {
        String[] commands = getCommands();
        getGameMode(commands);
    }

    private boolean checkPlayerOption(String command) {
        boolean valid = command.equals("user") || command.equals("easy");
        if (!valid) {
            System.out.println("Bad parameters!");
        }
        return valid;
    }

    private boolean startGame(String option) {
        boolean valid = option.equals("exit") || option.equals("start");
        if (option.equals("exit")) {
            System.exit(0);
        }

        if (!valid) {
            System.out.println("Bad parameters!");
        }
        return valid;
    }

    public String[] getCommands() {
        String[] commands;
        String userInput;
        boolean invalid = true;

        do {
            System.out.println("Input Command: >");
            userInput = inputScanner.nextLine();
            commands = userInput.trim().split("\\s+");

            if (commands[0].equals("exit")) {
                System.exit(0);
            }

            if (commands.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (!startGame(commands[0])) {
                continue;
            }

            if (!(checkPlayerOption(commands[1]) && checkPlayerOption(commands[2]))) {
                continue;
            }

            //inputScanner.nextLine();  // Advances the scanner to prevent input errors
            invalid = false;  // Sets the condition to false to break the loop

        } while(invalid);
        return commands;
    }

    /*
    public void makeMove() {
        int[] coordinates = getCoordinates();
        board.markBoard(board.getCurrentPlayer(), coordinates[0], coordinates[1]);
        printBoard();
        //System.out.println("Making move level " + '"' + "easy" + '"');
        board.updateGame();
        //printGameState();
    }
    */

    public void makeComputerMove() {
        Random rand = new Random(System.currentTimeMillis());
        int row;
        int col;

        while (true) {
            row = rand.nextInt(3) + 1 - 1;
            col = rand.nextInt(3) + 1 - 1;

            if (board.cellOpen(row, col)) {
                break;
            }
        }
        board.markBoard(board.getCurrentPlayer(), row, col);
        board.updateGame();
        printBoard();
        System.out.println("Making move level " + '"' + "easy" + '"');
        board.updateGame();
    }

    private void printBoard() {
        System.out.println(board);
    }

    private void printGameState() {
        System.out.println(board.getGameStatus());
    }

    /*
    private int[] getCoordinates() {
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
    */
}
