package battleship;

public class Ship {
    private final String type;
    private final int length;
    private int damage;

    Ship(String type, int length) {
        this.type = type;
        this.length = length;
        damage = 0;
    }

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    void incrementDamage() {
        damage++;
    }

    public boolean isDestroyed() {
        return damage == length;
    }
}
