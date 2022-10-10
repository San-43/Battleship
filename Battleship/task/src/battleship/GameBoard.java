package battleship;

public class GameBoard {
    private final BoardCell[][] gameBoard;
    private String message = "";

    GameBoard() {
        gameBoard = new BoardCell[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                gameBoard[row][column] = new BoardCell(row, column, Character.FREE);
            }
        }
    }

    public static void drawSeparator() {
        System.out.println("---------------------");
    }

    void draw(boolean isFogOfWar) {
        final String[] rowNotation = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String header = "  1 2 3 4 5 6 7 8 9 10";
        System.out.println(header);
        for (int row = 0; row < 10; row++) {
            System.out.print(rowNotation[row]);
            for (int column = 0; column < 10; column++) {
                System.out.print(" " + gameBoard[row][column].getMark(isFogOfWar));
            }
            System.out.println();
        }
        if (message.length() > 0) {
            System.out.println(message);
            message = "";
        }
    }

    public BoardCell getBoardCell(Coordinate coordinate) {
        return this.gameBoard[coordinate.row][coordinate.column];
    }

    BoardCell[] getBoardCells() {
        BoardCell[] cells = new BoardCell[100];
        int i = 0;
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                cells[i++] = gameBoard[row][column];
            }
        }
        return cells;
    }

    boolean isValidCoordinate(Coordinate coordinate) {
        return 0 <= coordinate.row && coordinate.row <= 9
                && 0 <= coordinate.column && coordinate.column <= 9;
    }

    boolean isOccupiedBoardCell(BoardCell cell) {
        return cell.isOccupied();
    }

    private boolean isNeighbourOccupied(BoardCell cell) {
        for (BoardCell boardCell : this.getBoardCells()) {
            if (boardCell.isANeighbour(cell) && boardCell.isOccupied()) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            }
        }
        return false;
    }

    boolean isValidPlacementOrder(PlacementOrder placementOrder) {
        // Check for valid, occupied and neighbouring cells
        for (BoardCell cell : placementOrder.getOccupiedBoardCells()) {
            if (this.isOccupiedBoardCell(cell) || this.isNeighbourOccupied(cell)) {
                return false;
            }
        }
        return true;
    }

    public void placeShip(PlacementOrder placementOrder) {
        for (BoardCell boardCell : placementOrder.getOccupiedBoardCells()) {
            boardCell.setShip(placementOrder.getShip());
            boardCell.setMark(Character.OCCUPIED);
        }
    }

    public void markCannonShot(CannonShot cannonShot) {
        BoardCell boardCell = this.getBoardCell(cannonShot.getCoordinate());
        message = boardCell.setCannonShot();

    }

    public BoardCell[] getBoardCells(BoardCell fromBoardCell, BoardCell toBoardCell) {
        int size = fromBoardCell.getLength(toBoardCell);
        BoardCell[] boardCells = new BoardCell[size];
        int index = 0;
        if (fromBoardCell.getRow() == toBoardCell.getRow()) {
            // All cells on same row horizontally
            for (int column = fromBoardCell.getColumn(); column <= toBoardCell.getColumn(); column++) {
                boardCells[index++] = gameBoard[fromBoardCell.getRow()][column];
            }
        } else {
            // All cells on same column vertically
            for (int row = fromBoardCell.getRow(); row <= toBoardCell.getRow(); row++) {
                boardCells[index++] = gameBoard[row][fromBoardCell.getColumn()];
            }
        }
        return boardCells;
    }
}
