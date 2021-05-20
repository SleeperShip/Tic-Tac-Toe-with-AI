package tictactoe;
import java.util.Scanner;

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

    public void match(Player p1, Player p2) {
        printBoard();
        p1.makeMove(board);
        while (board.getGameStatus() == Board.StateOfGame.NOT_FINISHED) {
            p2.makeMove(board);
            if (board.getGameStatus() != Board.StateOfGame.NOT_FINISHED) {
                System.out.println(board.getGameStatus());
                return;
            }
            p1.makeMove(board);
        }
        System.out.println(board.getGameStatus());
    }

    private void getGameMode(String[] commands) {
        Player player1;
        Player player2;

        switch(commands[1]) {
            case "user" :
                player1 = new HumanPlayer();
                player1.playerMark = 'X';
                break;
            case "easy" :
                player1 = new AIPlayerEasy();
                player1.playerMark = 'X';
                break;
            case "medium" :
                player1 = new AIPlayerMedium();
                player1.playerMark = 'X';
                break;
            case "hard" :
                player1 = new AIPlayerHard();
                player1.playerMark = 'X';
                break;
            default :
                return;
        }

        switch(commands[2]) {
            case "user" :
                player2 = new HumanPlayer();
                player2.playerMark = 'O';
                break;
            case "easy" :
                player2 = new AIPlayerEasy();
                player2.playerMark = 'O';
                break;
            case "medium" :
                player2 = new AIPlayerMedium();
                player2.playerMark = 'O';
                break;
            case "hard" :
                player2 = new AIPlayerHard();
                player2.playerMark = 'O';
                break;
            default :
                return;
        }

        match(player1, player2);
    }

    public void menu() {
        String[] commands = getCommands();
        getGameMode(commands);
    }

    private boolean checkPlayerOption(String command) {
        boolean valid = command.equals("user") || command.equals("easy") || command.equals("medium") || command.equals("hard");
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

    private void printBoard() {
        System.out.println(board);
    }

    private void printGameState() {
        System.out.println(board.getGameStatus());
    }

}
