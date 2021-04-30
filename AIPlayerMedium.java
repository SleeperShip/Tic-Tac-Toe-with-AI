package tictactoe;
import java.util.Random;

public class AIPlayerMedium extends Player {
   
    private Random rand = new Random();
    
    @Override
    public void makeMove(Board board) {
        
        int[] moveLoc = new int[2];
        moveLoc = checkForWinningCell(board);
        System.out.println("In MEDIUM AI OBJECT " + moveLoc[0] + ", " + moveLoc[1]);
       
        if (moveLoc[0] == -1 || moveLoc[1] == -1) {
            makeRandomMove(board);
        } else {
            board.markBoard(board.getCurrentPlayer(), moveLoc[0], moveLoc[1]);
            board.updateGame();
            board.printBoard();
        }
   
     }

public void makeRandomMove(Board board) {
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
    System.out.println("Making move level " + '"' + "medium(random)" + '"');
    //board.updateGame();
}

public int[] checkForWinningCell(Board board) {
   char[][] grid = board.getGrid();
   char currentPlayer = board.getCurrentPlayer();
   char opposingPlayer = board.getOpposingPlayer();
   int[] winningLocation = new int[2];
   char[] tempRow = new char[3];
   winningLocation[0] = -1;
   winningLocation[1] = -1;
   
   for (int i = 0; i < grid[0].length; i++) {
       if (checkArrayRow(grid[i], currentPlayer, opposingPlayer)) {
           winningLocation = getBlankSpaceRow(grid[i], i);
       }
   }
 
   for (int i = 0; i < grid[0].length; i++) {
       if (checkArrayCol(grid, i, currentPlayer, opposingPlayer)) {
           winningLocation = getBlankSpaceCol(grid, i);
       }
   }
   
   if (checkArrayDiag1(grid, currentPlayer, opposingPlayer)) {
       winningLocation = getBlankSpaceDiag1(grid);
   }
   
   if (checkArrayDiag2(grid, currentPlayer, opposingPlayer)) {
       winningLocation = getBlankSpaceDiag2(grid);
   }
   
   return winningLocation;
}

/* the 4 blank space methods are only utilized 
when a win condition/loss prevention spot is found */

public int[] getBlankSpaceDiag1(char[][] grid) { 
   int[] coordinates = new int[2];
   
   for (int i = 0; i < grid[0].length; i++)  {
       if (grid[i][i] == ' ') {
           coordinates[0] = i;
           coordinates[1] = i;
       }
   }
   return coordinates;
}

public int[] getBlankSpaceDiag2(char[][] grid) {
   int[] coordinates = new int[2];
   
   for (int i = 0; i < grid[0].length; i++)  {
       if (grid[grid[0].length - i - 1][i] == ' ') {
           coordinates[0] = grid[0].length - i - 1;
           coordinates[1] = i;
       }
   }
   return coordinates;
}

public int[] getBlankSpaceCol(char[][] grid, int col) {
    int row = -1;
    int[] coordinates = new int[2];
   
    for (int i = 0; i < grid[0].length; i++) {
        if (grid[i][col] == ' ') {
            row = i;
        }
    }
    coordinates[0] = row;
    coordinates[1] = col;
    return coordinates;
}

public int[] getBlankSpaceRow(char[] set, int row) {  //row number is given, returned will be col for coordinates
   int col = -1;
   int[] coordinates = new int[2];
   
   for (int i = 0; i < set.length; i++)  {
       if (set[i] == ' ') {
           col = i;
       }
   }
   coordinates[0] = row;
   coordinates[1] = col;
   return coordinates;
}

public boolean checkArrayDiag1(char[][] grid, char currentPlayer, char opposingPlayer) { //DUN
   int currentPlayerCount = 0;
   int opposingPlayerCount = 0;
   int whiteSpaceCount = 0;
   
   for (int i = 0; i < grid[0].length; i++) {
      if (grid[i][i] == currentPlayer) {
           currentPlayerCount++;
      }
      
      if (grid[i][i] == opposingPlayer) {
          opposingPlayerCount++;
      }
           
      if (grid[i][i] == ' ') {
           whiteSpaceCount++;
      }
  }
   
   if ( (currentPlayerCount == 2 && whiteSpaceCount == 1) || (opposingPlayerCount == 2 && whiteSpaceCount == 1) ) {
       return true;
   }
   
   return false;
}

public boolean checkArrayDiag2(char[][] grid, char currentPlayer, char opposingPlayer) {
   int currentPlayerCount = 0;
   int whiteSpaceCount = 0;
   int opposingPlayerCount = 0;
   
   for (int i = 0; i < grid[0].length; i++) {
           if(grid[grid[0].length - i - 1][i] == currentPlayer) {
               currentPlayerCount++;
           }
           
           if (grid[grid[0].length - i - 1][i] == opposingPlayer) {
               opposingPlayerCount++;
           }
           
           if (grid[grid[0].length - i - 1][i] == ' ') {
               whiteSpaceCount++;
           }
       
   }
   
   if ( (currentPlayerCount == 2 && whiteSpaceCount == 1) || (opposingPlayerCount == 2 && whiteSpaceCount == 1) ) {
       return true;
   }
   
   return false;
}

public boolean checkArrayCol(char[][] grid, int colNumber, char currentPlayer, char opposingPlayer) {
   int currentPlayerCount = 0;
   int whiteSpaceCount = 0;
   int opposingPlayerCount = 0;
   
   for (int i = 0; i < grid[0].length; i++) {   
      if (grid[i][colNumber] == currentPlayer) {
          currentPlayerCount++;
      }
          
      if (grid[i][colNumber] == opposingPlayer) {
          opposingPlayerCount++;
      }  
           
      if (grid[i][colNumber] == ' ') {
          whiteSpaceCount++;
      }
   }
   
   if ( (currentPlayerCount == 2 && whiteSpaceCount == 1) || (opposingPlayerCount == 2 && whiteSpaceCount == 1) ) {
       return true;
   }
   
   return false;
}

public boolean checkArrayRow(char[] set, char currentPlayer, char opposingPlayer) {
   int currentPlayerCount = 0;
   int whiteSpaceCount = 0;
   int opposingPlayerCount = 0;
   
   for (int i = 0; i < set.length; i++) {  

       if (set[i] == opposingPlayer) {
           opposingPlayerCount++;
       }

       if (set[i] == currentPlayer) {
           currentPlayerCount++;
       }
       
       if(set[i] == ' ') {
           whiteSpaceCount++;
       }
   }
   
   if ( (currentPlayerCount == 2 && whiteSpaceCount == 1) || (opposingPlayerCount == 2 && whiteSpaceCount == 1) ) {
       return true;
   }
   
   return false;
}

}
