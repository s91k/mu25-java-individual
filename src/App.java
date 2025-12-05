import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner s  = new Scanner(System.in);

        int nrOfPlayers = showMenu(s, "Välj antal spelare:\n1. En spelare\n2. Två spelare", new int[] {1, 2}, "Ogiltigt alternativ, giltiga alternativ: 1 och 2.");

        Player[] players = new Player[2];

        System.out.print("Ange namn på spelare 1: ");
        players[0] = new HumanPlayer('X', s.nextLine().trim());

        System.out.print("Ange namn på spelare 2: ");
        players[1] = nrOfPlayers == 1 ? new ComputerPlayer('◯', s.nextLine().trim()) : new HumanPlayer('◯', s.nextLine().trim());


        boolean continuePlaying = true;

        while(continuePlaying){
            Board b = new Board(3);
            int currentPlayer = Math.random() > 0.5 ? 1 : 0;

            while(true){
                System.out.println(b);

                int tile;

                if(players[currentPlayer] instanceof HumanPlayer){
                    tile = showMenu(s, players[currentPlayer].getName() + " (" + players[currentPlayer].getMark() + "), vilken ruta?",
                            b.getValidMoves(), "Ogiltigt drag, giltiga drag: " + Arrays.toString(b.getValidMoves()) + "\n" + b);
                } else {
                    tile = ((ComputerPlayer)players[currentPlayer]).getNextMove(b);
                    System.out.println(players[currentPlayer].getName() + " (" + players[currentPlayer].getMark() + ") väljer ruta " + tile);
                }

                if(!b.placeMark(players[currentPlayer].getMark(), tile)){
                    System.out.println("Error, ogiltigt drag");
                    return;
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

            continuePlaying = showMenu(s, "Vill ni spela igen? (y/n)", new String[]{"y", "n"}, null).equals("y");
        }
    }

    public static String showMenu(Scanner s, String instruction, String[] validOptions, String error){
        while(true){
            System.out.println(instruction);

            String option = s.nextLine();

            for(String validOption : validOptions){
                if(validOption.equals(option)){
                    return option;
                }
            }

            if(error != null){
                System.out.println(error);
            }
        }
    }

    public static int showMenu(Scanner s, String instruction, int[] validOptions, String error){
        String[] validMovesStr = new String[validOptions.length];

        for(int i = 0; i < validOptions.length; i++){
            validMovesStr[i] = String.valueOf(validOptions[i]);
        }

        return Integer.parseInt(showMenu(s, instruction, validMovesStr, error));
    }
}
