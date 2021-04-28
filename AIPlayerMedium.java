public class AIPlayerMedium extends Player {
   
    protected Random rand = new Random();
   
    @Override
    public void makeMove(Board board) {
        int[] moveLoc = checkForWinningCell(board.getGrid());
        //System.out.print(moveLoc[0] + ", " + movLoc[1]);
       
        if (moveLoc[0] == -1 || moveLoc[1] == -1) {
            makeRandomMove(board);
        } else {
            markBoard(board.getCurrentPlayer(), moveLoc[0], moveLoc[1]);
        }
   
     }

private void makeRandomMove(Board board) {
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

private int[] checkForWinningCell(Board board) {
   char[][] grid = board.getGrid();
   char currentPlayer = board.getCurrentPlayer();
   int[] winningLocation = new int[2];
   char[] tempRow = new char[3];
   winningLocation[0] = -1;
   winningLocation[1] = -1;
   
   for (int i = 0; i < grid[0].length; i++) {
       if (checkArrayRow(grid[i], currentPlayer)) {
           winningLocation = getBlankSpaceRow(grid[i], i);
       }
   }
 
   for (int i = 0; i < grid[0].length; i++) {
       if (checkArrayCol(grid, i, currentPlayer)) {
           winningLocation = getBlankSpaceCol(grid, i);
       }
   }
   
   if (checkArrayDiag1(grid, currentPlayer)) {
       winningLocation = getBlankSpaceDiag1(grid);
   }
   
   if (checkArrayDiag2(grid, currentPlayer)) {
       winningLocation = getBlankSpaceDiag2(grid);
   }
   
   return winningLocation;
}

private int[] getBlankSpaceDiag1(char[][] grid) {
   int[] coordinates = new int[2];
   
   for (int i = 0; i < grid[0].length; i++)  {
       if (grid[i][i] == ' ') {
           coordinates[0] = i;
           coordinates[1] = i;
       }
   }
   return coordinates;
}

private int[] getBlankSpaceDiag2(char[][] grid) {
   int[] coordinates = new int[2];
   
   for (int i = 0; i < grid[0].length; i++)  {
       if (grid[grid[0].length - i - 1][i] == ' ') {
           coordinates[0] = grid[0].length - i - 1;
           coordinates[1] = i;
       }
   }
   return coordinates;
}

private int[] getBlankSpaceCol(char[][] grid, int col) {
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

private int[] getBlankSpaceRow(char[] set, int row) {  //row number is given, returned will be col for coordinates
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

private boolean checkArrayDiag1(char[][] grid, char currentPlayer) {
   int playerCount = 0;
   int whiteSpaceCount = 0;
   
   for (int i = 0; i < grid[0].length; i++) {
      if(grid[i][i] == currentPlayer) {
           playerCount++;
      }
           
      if (grid[i][i] == ' ') {
           whiteSpaceCount++;
      }
  }
   
   if (playerCount == 2 && whiteSpaceCount == 1) {
       return true;
   }
   
   return false;
}

private boolean checkArrayDiag2(char[][] grid, char currentPlayer) {
   int playerCount = 0;
   int whiteSpaceCount = 0;
   
   for (int i = 0; i < grid[0].length; i++) {
           if(grid[grid[0].length - i - 1][i] == currentPlayer) {
               playerCount++;
           }
           
           if (grid[grid[0].length - i - 1][i] == ' ') {
               whiteSpaceCount++;
           }
       
   }
   
   if (playerCount == 2 && whiteSpaceCount == 1) {
       return true;
   }
   
   return false;
}

private boolean checkArrayCol(char[][] grid, int colNumber, char currentPlayer) {
   int playerCount = 0;
   int whiteSpaceCount = 0;
   boolean winningColumn = false;
   
   for (int i = 0; i < grid[0].length; i++) {    //use Board.SIZE
      if (grid[i][colNumber] == currentPlayer) {
          playerCount++;
      }
           
      if (grid[i][colNumber] == ' ') {
          whiteSpaceCount++;
      }
   }
   
   if (playerCount == 2 && whiteSpaceCount == 1) {
       winningColumn = true;
       return winningColumn;
   }
   
   return winningColumn;
}

private boolean checkArrayRow(char[] set, char currentPlayer) {
   int playerCount = 0;
   int whiteSpaceCount = 0;
   
   for (int i = 0; i < set.length; i++) {  


       if (set[i] == currentPlayer) {
           playerCount++;
       }
       
       if(set[i] == ' ') {
           whiteSpaceCount++;
       }
   }
   
   if (playerCount == 2 && whiteSpaceCount == 1) {
       return true;
   }
   
   return false;
}

}
