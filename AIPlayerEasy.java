package tictactoe;
import java.util.Random;

public class AIPlayerEasy extends Player{

    protected Random rand = new Random();

    @Override
    public void makeMove(Board board) {
        rand = new Random(System.currentTimeMillis());
        int row;
        int col;

        while (true) {
            row = rand.nextInt(3) + 1 - 1;
            col = rand.nextInt(3) + 1 - 1;

            if (board.cellOpen(row, col)) {
                break;
            } else {
                continue;
            }
        }
        board.markBoard(board.getCurrentPlayer(), row, col);
        board.updateGame();
        board.printBoard();
        System.out.println("Making move level " + '"' + "easy" + '"');
        board.updateGame();
    }
}
