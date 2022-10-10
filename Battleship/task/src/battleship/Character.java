package battleship;

public enum Character {

    FREE('~'), OCCUPIED('O'), HIT('X'), MISS('M');

    private final char symbol;

    Character(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
