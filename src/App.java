public class App {
    public static void main(String[] args){
        Player[] players = new Player[2];

        int nrOfPlayers = InputUtils.showMenu("Välj antal spelare:\n1. En spelare\n2. Två spelare", new int[] {1, 2}, "Ogiltigt alternativ, giltiga alternativ: 1 och 2.");

        System.out.print("Ange namn på spelare 1: ");
        players[0] = new HumanPlayer('X', InputUtils.nextLine().trim());

        System.out.print("Ange namn på spelare 2: ");
        players[1] = nrOfPlayers == 1 ? new ComputerPlayer('◯', InputUtils.nextLine().trim()) : new HumanPlayer('◯', InputUtils.nextLine().trim());

        boolean continuePlaying = true;

        while(continuePlaying){
            Board b = new Board(3);

            // Randomize which player starts
            int currentPlayer = Math.random() > 0.5 ? 1 : 0;

            while(true){
                System.out.println(b);

                int tile = players[currentPlayer].getNextMove(b);

                System.out.println(players[currentPlayer] + " väljer ruta " + tile);

                if(!b.placeMark(players[currentPlayer].getMark(), tile)){
                    System.out.println("Error, ogiltigt drag");
                    return;
                }

                if(b.isWinner(players[currentPlayer].getMark())){
                    System.out.println(b);
                    System.out.println(players[currentPlayer] + " har vunnit!");

                    players[currentPlayer].setNrOfWins(players[currentPlayer].getNrOfWins() + 1);
                    break;
                } else if(!b.hasEmptySpots()){
                    System.out.println(b);
                    System.out.println("Oavgjort, det går inte att göra några fler drag.");
                    break;
                }

                currentPlayer = (currentPlayer + 1) % 2;
            }

            System.out.println(players[0].getDescription() + " och " + players[1].getDescription());

            continuePlaying = InputUtils.showMenu("Vill ni spela igen? (y/n)", new String[]{"y", "n"}, null).equals("y");
        }
    }
}
