package battleship;

public class BoardCell {
    private final int row, column;
    private Character character;
    private Ship ship;

    BoardCell(int row, int column, Character character) {
        this.row = row;
        this.column = column;
        this.character = character;
    }

    Character getMark(boolean isFogOfWar) {
        if (isFogOfWar && character == Character.OCCUPIED) {
            return Character.FREE;
        } else {
            return character;
        }
    }

    void setMark(Character character) {
        this.character = character;
    }

    String setCannonShot() {
        String message;
        if (isOccupied()) {
            if (this.character == Character.OCCUPIED) {
                this.ship.incrementDamage();
                setMark(Character.HIT);
            }
            if (this.getShip().isDestroyed()) {
                message = "You sank a ship! Specify a new target:";
            } else {
                message = "You hit a ship!";
            }
        } else {
            setMark(Character.MISS);
            message = "You missed!";
        }
        return message;
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    boolean isANeighbour(BoardCell boardCell) {
        return row == boardCell.row && Math.abs(column - boardCell.getColumn()) == 1 ||
                Math.abs(row - boardCell.row) == 1 && column == boardCell.column;
    }

    int getLength(BoardCell toBoardCell) {
        return 1 + Math.max(Math.abs(this.getRow() - toBoardCell.getRow()),
                Math.abs(this.getColumn() - toBoardCell.getColumn()));
    }

    public boolean isOccupied() {
        return ship != null;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
