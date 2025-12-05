public abstract class Player {
    private final char mark;
    private final String name;
    private int nrOfWins;

    public Player(char mark, String name){
        this.mark = mark;
        this.name = name;
        this.nrOfWins = 0;
    }

    public char getMark(){
        return mark;
    }

    public String getName(){
        return name;
    }

    public int getNrOfWins(){
        return nrOfWins;
    }

    public void setNrOfWins(int nrOfWins){
        this.nrOfWins = nrOfWins;
    }

    public String toString(){
        return name + " (" + mark + ") har vunnit " + nrOfWins + " " + (nrOfWins == 1 ? "gång" : "gånger");
    }
}
