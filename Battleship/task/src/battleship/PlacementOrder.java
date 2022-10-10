package battleship;

public class PlacementOrder {
    private final int length;
    private final BoardCell fromBoardCell, toBoardCell;
    private final BoardCell[] occupiedBoardCells;
    private final Ship ship;

    PlacementOrder(GameBoard gameBoard, Ship ship, String placement) {
        this.ship = ship;
        BoardCell firstBoardCell = gameBoard.getBoardCell(Coordinate.getFirstCoordinate(placement));
        BoardCell secondBoardCell = gameBoard.getBoardCell(Coordinate.getSecondCoordinate(placement));
        if (secondBoardCell.getRow() > firstBoardCell.getRow() ||
                secondBoardCell.getColumn() > firstBoardCell.getColumn()) {
            this.fromBoardCell = firstBoardCell;
            this.toBoardCell = secondBoardCell;
        } else {
            this.fromBoardCell = secondBoardCell;
            this.toBoardCell = firstBoardCell;
        }
        occupiedBoardCells = gameBoard.getBoardCells(fromBoardCell, toBoardCell);

        this.length = occupiedBoardCells.length;
    }

    private boolean isValidShipLength() {
        if (length == ship.getLength()) {
            return true;
        } else {
            System.out.println("Error! Wrong length of the " + ship.getType() + "! Try again:");
            return false;
        }
    }

    private boolean isShipPlacementStraight() {
        if (fromBoardCell.getRow() == toBoardCell.getRow() ||
                fromBoardCell.getColumn() == toBoardCell.getColumn()) {
            return true;
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
    }

    public boolean isValid() {
        return isValidShipLength() && isShipPlacementStraight();
    }

    public BoardCell[] getOccupiedBoardCells() {
        return occupiedBoardCells;
    }

    public Ship getShip() {
        return ship;
    }

}