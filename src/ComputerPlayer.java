public class ComputerPlayer extends Player{
    private final int difficultyLevel;

    public ComputerPlayer(char mark, int difficultyLevel) {
        String[] firstName = new String[] {
                "Lars", "Nils", "Sven", "Bert"
        };

        String[] secondName = new String[] {
                "Göran", "Erik", "Olof", "Gustav"
        };

        super(mark, firstName[(int)(Math.random() * firstName.length)] + '-' + secondName[(int)(Math.random() * secondName.length)]);

        this.difficultyLevel = difficultyLevel;
    }

    private double evaluateMove(Board b, int move, int maxSearchDepth){
        // Generate a score based on how close to winning the given move is
        // Note that this method does not take into account the opponents moves
        double score = 0.0;
        b.placeMark(getMark(), move);

        if(b.isWinner(getMark())){
            score = 1.0;
        } else if(maxSearchDepth > 0){
            var validMoves = b.getValidMoves();

            for(int nextMove : validMoves){
                score += evaluateMove(b, nextMove, maxSearchDepth - 1) / validMoves.length;
            }
        }

        b.removeMark(move);

        return score * ((double) maxSearchDepth / difficultyLevel);
    }

    @Override
    public int getNextMove(Board b){
        int bestMove = -1;
        double bestScore = Double.MIN_VALUE;
        var validMoves = b.getValidMoves();

        // Evaluate every possible move and pick the one with the best score
        for(int move : validMoves){
            double score = evaluateMove(b, move, difficultyLevel);

            if(score > bestScore){
                bestMove = move;
                bestScore = score;
            }
        }

        // No path to victory is found, then pick a random move
        if(bestMove == -1){
            bestMove = validMoves[(int)(Math.random() * validMoves.length)];
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
