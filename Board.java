public class Board {

    private char[][] grid;
    public  final int SIZE = 3;
    public StateOfGame state;
    private char currentPlayer;

    public Board(String initialState) {
        int index = 0;
        grid = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = initialState.charAt(index);
                index++;
            }

        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.grid[i][j] == '_') {
                    this.grid[i][j] = ' ';
                }
            }
        }
    }
    
    public char[][] getGrid() {
        return this.grid;
    }

    public void markBoard(char player, int row, int col) {
        if (this.grid[row][col] == ' ' && (player == 'X' || player == 'O')) {
            this.grid[row][col] = player;
        }
        nextPlayer();  //next player's turn after move has been made
    }

    public boolean hasEmptyCells() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cellOpen(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cellOpen(int row, int column) {
        return this.grid[row][column] == ' ';
    }

    private boolean checkForWin(char player) {
        if (this.grid[0][0] == player && this.grid[0][1] == player && this.grid[0][2] == player) {return true;}  //check rows
        if (this.grid[1][0] == player && this.grid[1][1] == player && this.grid[1][2] == player) {return true;}
        if (this.grid[2][0] == player && this.grid[2][1] == player && this.grid[2][2] == player) {return true;}
        if (this.grid[0][0] == player && this.grid[1][0] == player && this.grid[2][0] == player) {return true;}  //check columns
        if (this.grid[0][1] == player && this.grid[1][1] == player && this.grid[2][1] == player) {return true;}
        if (this.grid[0][2] == player && this.grid[1][2] == player && this.grid[2][2] == player) {return true;}
        if (this.grid[0][0] == player && this.grid[1][1] == player && this.grid[2][2] == player) {return true;}  //check diagonals
        if (this.grid[0][2] == player && this.grid[1][1] == player && this.grid[2][0] == player) {return true;}
        return false;
    }

    public void updateGame() {
        if (checkForWin('O')) {
            state = StateOfGame.O_WINS;
            return;
        }

        if (checkForWin('X')) {
            state = StateOfGame.X_WINS;
            return;
        }

        if (!hasEmptyCells() && !checkForWin('X') && !checkForWin('O')) {
            state = StateOfGame.DRAW;
            return;
        }
        state = StateOfGame.NOT_FINISHED;
    }

    public StateOfGame getGameStatus() {
        return state;
    }

    public char getCurrentPlayer() {
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'X') {
                    xCount++;
                }

                if (grid[i][j] == 'O') {
                    oCount++;
                }
            }
        }

        if (xCount == oCount) {
            this.currentPlayer = 'X';
        }

        if (xCount > oCount) {
            this.currentPlayer = 'O';
        }
        return this.currentPlayer;
    }
    
    public char getOpposingPlayer() {
        if (getCurrentPlayer() == 'X') {
            return 'O';
        }
        return 'X';
    }
    private void nextPlayer() {
        if (this.currentPlayer == 'X') {
            this.currentPlayer = 'O';
            return;
        }
        this.currentPlayer = 'X';
    }

    public void printBoard() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("---------\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append("| ");
            for (int j = 0; j < SIZE; j++) {
                sb.append(this.grid[i][j]);
                sb.append(" ");
            }
            sb.append("|");
            sb.append("\n");
        }
        sb.append("---------");
        return sb.toString();
    }

    enum StateOfGame {
        NOT_FINISHED("Game not finished"),
        X_WINS("X wins"),
        O_WINS("O wins"),
        DRAW("Draw");

        private String msg;

        StateOfGame(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }
}
