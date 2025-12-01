import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Board b = new Board(3);
        Player[] players = new Player[] { new Player('o'), new Player('x')};
        int currentPlayer = 0;
        Scanner s  = new Scanner(System.in);

        while(true){
            boolean markPlaced = false;

            while(!markPlaced){
                System.out.println(b);
                System.out.println("Spelare " + players[currentPlayer].getMark() + ", vilken ruta (1-9)?");

                try {
                    int nextMove = Integer.parseUnsignedInt(s.nextLine()) - 1;
                    markPlaced = b.placeMark(players[currentPlayer].getMark(), nextMove % 3, nextMove / 3);
                } catch(NumberFormatException _) { }

                if(!markPlaced){
                    System.out.println("Ogiltigt drag, välj en annan ruta.");
                }
            }

            if(b.isWinner(players[currentPlayer].getMark())){
                System.out.println("Spelare " + players[currentPlayer].getMark() + " has vunnit!");
                break;
            } else if(!b.hasEmptySpots()){
                System.out.println("Det går inte att göra några fler drag.");
                break;
            }

            currentPlayer = (currentPlayer + 1) % 2;
        }
    }
}
