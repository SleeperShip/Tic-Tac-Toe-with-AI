import java.util.Random;

public class AIPlayerHard extends Player {
    
@Override
public void makeMove(Board board) {
        
    int[] moveLoc = findBestMove(board);
    System.out.println("In HARD AI OBJECT " + moveLoc[0] + ", " + moveLoc[1]);
    
    board.markBoard(board.getCurrentPlayer(), moveLoc[0], moveLoc[1]);
    board.updateGame();
    board.printBoard();
}
   
public int[] findBestMove(Board board) {
   
    int bestVal = -1000;
    int[] bestMove = new int[2];
    bestMove[0] = -1;  //row
    bestMove[1] = -1;  //col
    char currentPlayer = board.getCurrentPlayer();
    char[][] gridCopy = board.getGrid();
 
    // Traverse all cells, evaluate minimax function
    // for all empty cells. And return the cell
    // with optimal value.
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            // Check if cell is empty
            if (gridCopy[i][j] == '_') {
                // Make the move
                gridCopy[i][j] = board.getCurrentPlayer();
 
                // compute evaluation function for this move
                int moveVal = miniMax(gridCopy, board, 0, false);
 
                // Undo the move
                gridCopy[i][j] = '_';
 
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
 
    //System.out.printf("The value of the best Move " +
                             //"is : %d\n\n", bestVal);
 
    return bestMove;
}

private int miniMax(char[][] gridCopy, Board board, int depth, Boolean isMax) {
                       
    int score = evaluate(board);
 
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
    if (board.hasEmptyCells() == false) {
        return 0;
    }
 
    // If this maximizer's move
    if (isMax) {
        int best = -1000;
 
        // Traverse all cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (gridCopy[i][j]=='_') {
                    // Make the move
                    gridCopy[i][j] = board.getCurrentPlayer();
 
                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.max(best, miniMax(gridCopy, board,
                                    depth + 1, !isMax));
 
                    // Undo the move
                    gridCopy[i][j] = '_';
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
                if (gridCopy[i][j] == '_') {
                    // Make the move
                    gridCopy[i][j] = board.getOpposingPlayer();
 
                    // Call minimax recursively and choose
                    // the minimum value
                    best = Math.min(best, miniMax(gridCopy, board,
                                    depth + 1, !isMax));
 
                    // Undo the move
                    gridCopy[i][j] = '_';
                }
            }
        }
        return best;
    }
}

private int evaluate(Board board) {
    // Check for X or O victory.
    if (board.checkForWin('X')) {
        return 10;
    } else if (board.checkForWin('O')) {
        return -10;
    } else {
        return 0;
    }
}
 

}
