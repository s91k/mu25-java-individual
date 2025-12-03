import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Player[] players = new Player[2];

        Scanner s  = new Scanner(System.in);
        System.out.print("Ange namn på spelare 1: ");
        players[0] = new Player('X', s.nextLine().trim());

        System.out.print("Ange namn på spelare 2: ");
        players[1] = new Player('◯', s.nextLine().trim());

        boolean continuePlaying = true;

        while(continuePlaying){
            Board b = new Board(3);
            int currentPlayer = 0;

            while(true){
                boolean markPlaced = false;

                // Loop until the player makes a valid move
                while(!markPlaced){
                    System.out.println(b);
                    System.out.println(players[currentPlayer].getName() + " (" + players[currentPlayer].getMark() + "), vilken ruta (1-9)?");

                    try {
                        int nextMove = Integer.parseUnsignedInt(s.nextLine()) - 1;
                        markPlaced = b.placeMark(players[currentPlayer].getMark(), nextMove % 3, nextMove / 3);

                        if(!markPlaced){
                            System.out.println("Ogiltigt drag, rutan måste vara tom.");
                        }
                    } catch(NumberFormatException | IndexOutOfBoundsException _) {
                        System.out.println("Ogiltigt drag, ange en siffra mellan 1 och 9");
                    }
                }

                if(b.isWinner(players[currentPlayer].getMark())){
                    System.out.println(b);
                    System.out.println(players[currentPlayer].getName() + " (" + players[currentPlayer].getMark() + ") has vunnit!");

                    players[currentPlayer].setNrOfWins(players[currentPlayer].getNrOfWins() + 1);
                    break;
                } else if(!b.hasEmptySpots()){
                    System.out.println(b);
                    System.out.println("Oavgjort, det går inte att göra några fler drag.");
                    break;
                }

                currentPlayer = (currentPlayer + 1) % 2;
            }

            System.out.println(players[0] + " och " + players[1]);

            // Loop until valid response is given
            while(true){
                System.out.println("Vill ni spela igen? (y/n)");
                String line = s.nextLine();

                if(line.equals("y")){
                    break;
                } else if(line.equals("n")){
                    continuePlaying = false;
                    break;
                }
            }
        }


    }
}
