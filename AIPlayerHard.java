package tictactoe;
public class AIPlayerHard extends Player {

    @Override
    public void makeMove(Board board) {

        int[] moveLoc = findBestMove(board);
        //System.out.println("In HARD AI OBJECT " + moveLoc[0] + ", " + moveLoc[1]);

        board.markBoard(this.playerMark, moveLoc[0], moveLoc[1]);
        board.updateGame();
        board.printBoard();
    }

    private int[] findBestMove(Board board) {
        int bestVal = -1000;
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;
        char[][] grid = board.getGrid();

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (grid[i][j] == ' ') {
                    // Make the move
                    grid[i][j] = this.playerMark;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, grid, 0, false);

                    // Undo the move
                    grid[i][j] = ' ';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return bestMove;
    }

    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
    private int minimax(Board board, char grid[][], int depth, Boolean isMax) {
        int score = evaluate(board, grid);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10) {
            return score;
        }

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10) {
            return score;
        }

        // If there are no more moves and
        // no winner then it is a tie
        if (hasMovesLeft(grid) == false) {
            return 0;
        }

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (grid[i][j] == ' ') {
                        // Make the move
                        grid[i][j] = this.playerMark;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board, grid, depth + 1, false));

                        // Undo the move
                        grid[i][j] = ' ';
                    }
                }
            }
            return best;
        }
        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (grid[i][j] == ' ') {
                        // Make the move
                        grid[i][j] = board.getOpposingPlayer(this.playerMark);

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board, grid, depth + 1, true));

                        // Undo the move
                        grid[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(Board board, char[][] grid) {
        // Check for X or O victory.
        if (checkForWin(this.playerMark, grid)) {
            return 10;
        } else if (checkForWin(board.getOpposingPlayer(this.playerMark) ,grid)) {
            return -10;
        } else {
            return 0;
        }
    }

    public boolean checkForWin(char player, char[][] grid) {
        if (grid[0][0] == player && grid[0][1] == player && grid[0][2] == player) {return true;}  //check rows
        if (grid[1][0] == player && grid[1][1] == player && grid[1][2] == player) {return true;}
        if (grid[2][0] == player && grid[2][1] == player && grid[2][2] == player) {return true;}
        if (grid[0][0] == player && grid[1][0] == player && grid[2][0] == player) {return true;}  //check columns
        if (grid[0][1] == player && grid[1][1] == player && grid[2][1] == player) {return true;}
        if (grid[0][2] == player && grid[1][2] == player && grid[2][2] == player) {return true;}
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {return true;}  //check diagonals
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {return true;}
        return false;
    }

    static Boolean hasMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

}
