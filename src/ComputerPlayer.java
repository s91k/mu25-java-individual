public class ComputerPlayer extends Player{
    public ComputerPlayer(char mark, String name) {
        super(mark, name);
    }

    @Override
    public int getNextMove(Board b){
        var board = b.getBoard().clone();
        var scoreBoard = new int[board.length][board.length];

        for(int y = 0; y < board.length; y++){
            int rowScore = 0;

            for(int x = 0; x < board.length; x++){
                rowScore += getTileScore(board[y][x]);
            }

            for(int x = 0; x < board.length; x++) {
                scoreBoard[y][x] = rowScore;
            }
        }

        for(int x = 0; x < board.length; x++){
            int columnScore = 0;

            for(int y = 0; y < board.length; y++){
                columnScore += getTileScore(board[y][x]);
            }

            for(int y = 0; y < board.length; y++) {
                scoreBoard[y][x] += columnScore;
            }
        }

        for(int i = 0; i < board.length; i++){
            int diagonalScore = getTileScore(board[i][i]);

            for(int j = 0; j < board.length; j++){
                scoreBoard[j][j] += diagonalScore;
            }
        }

        for(int i = 0; i < board.length; i++){
            int diagonalScore = getTileScore(board[2 - i][i]);

            for(int j = 0; j < board.length; j++){
                scoreBoard[2 - j][j] += diagonalScore;
            }
        }

        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for(int y = 0; y < scoreBoard.length; y++) {
            for (int x = 0; x < scoreBoard.length; x++) {
                // The best move has a 50% change of being updated if the score is the same for both tiles in order to make the AI less predictable
                if(board[y][x] == 0 && (scoreBoard[y][x] > bestScore || (scoreBoard[y][x] == bestScore && Math.random() > 0.5))){
                    bestScore = scoreBoard[y][x];
                    bestMove = y * scoreBoard.length + x + 1;
                }
            }
        }

        return bestMove;
    }

    private int getTileScore(char mark){
        if(mark == getMark()){
            return 2;
        } else if(mark == 0){
            return 1;
        } else {
            return -1;
        }
    }
}
