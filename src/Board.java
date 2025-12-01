public class Board {
    private final char[][] board;

    public Board(int size){
        board = new char[size][size];
    }

    public boolean placeMark(char mark, int x, int y){
        if(board[y][x] == 0){
            board[y][x] = mark;
            return true;
        } else {
            return false;
        }
    }

    public boolean hasEmptySpots(){
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isWinner(char mark){
        // Check horizontal
        for(int y = 0; y < board.length; y++){
            boolean hasWon = true;

            for(int x = 0; x < board[y].length && hasWon; x++){
                hasWon = board[y][x] == mark;
            }

            if(hasWon){
                return true;
            }
        }

        // Check vertical
        for(int x = 0; x < board[0].length; x++){
            boolean hasWon = true;

            for(int y = 0; y < board.length && hasWon; y++){
                hasWon = board[y][x] == mark;
            }

            if(hasWon){
                return true;
            }
        }

        // Check diagonals
        boolean hasWon = true;

        for(int i = 0; i < board.length && hasWon; i++){
            hasWon = board[i][i] == mark;
        }

        if(hasWon){
            return true;
        }

        hasWon = true;

        for(int i = 0; i < board.length && hasWon; i++){
            hasWon = board[board.length - i - 1][i] == mark;
        }

        return hasWon;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length - 1; x++){
                s.append(' ').append((board[y][x] == 0 ? ' ' : board[y][x])).append(" |");
            }

            s.append(' ').append(board[y][board[y].length - 1] == 0 ? ' ' : board[y][board[y].length - 1]);

            if(y < board.length - 1){
                s.append("\n---").append("+---".repeat(Math.max(0, board[y].length - 1))).append('\n');
            }
        }

        return s.toString();
    }
}
