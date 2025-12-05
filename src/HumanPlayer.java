import java.util.Arrays;

public class HumanPlayer extends Player{
    public HumanPlayer(char mark, String name) {
        super(mark, name);
    }

    @Override
    public int getNextMove(Board b) {
        return InputUtils.showMenu(this + ", vilken ruta?",
                b.getValidMoves(), "Ogiltigt drag, giltiga drag: " + Arrays.toString(b.getValidMoves()) + "\n" + b);
    }
}
