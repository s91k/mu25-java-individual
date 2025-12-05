import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final String ANSI_GRAY = "\u001B[90m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final char[][] board;

    public Board(int size){
        board = new char[size][size];
    }

    public boolean placeMark(char mark, int tile){
        tile--;

        if(board[tile / 3][tile % 3] == 0){
            board[tile / 3][tile % 3] = mark;
            return true;
        } else {
            return false;
        }
    }

    public void removeMark(int tile){
        board[(tile - 1) / 3][(tile - 1) % 3] = 0;
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

    public char[][] getBoard(){
        return board;
    }

    public int[] getValidMoves(){
        List<Integer> list = new ArrayList<>();

        for(int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if(board[y][x] == 0){
                    list.add(y * 3 + x + 1);
                }
            }
        }

        int[] arr = new int[list.size()];

        for(int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }

        return arr;
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

        s.append("┌").append("╴╴╴┬".repeat(board.length - 1)).append("╴╴╴┐\n");

        for(int y = 0; y < board.length; y++){
            s.append('╎');

            for(int x = 0; x < board[y].length; x++){
                s.append(' ').append(tileToString(x, y)).append(" ╎");
            }

            if(y < board.length - 1){
                s.append("\n├╴╴╴").append("┼╴╴╴".repeat(Math.max(0, board[y].length - 1))).append("┤\n");
            }
        }

        s.append("\n└").append("╴╴╴┴".repeat(board.length - 1)).append("╴╴╴┘");

        return s.toString();
    }

    private String tileToString(int x, int y){
        return (board[y][x] == 0 ? (ANSI_GRAY + (y * 3 + x + 1) + ANSI_RESET) : String.valueOf(board[y][x]));
    }
}
