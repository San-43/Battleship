package battleship;

import java.util.Scanner;

public class Player {
    private final Scanner scanner = new Scanner(System.in);
    private final String name;
    private final GameBoard gameBoard;
    private final Ship[] ships;

    Player(String name) {
        this.name = name;
        gameBoard = new GameBoard();
        ships = new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)
        };
    }

    void drawGameBoard(boolean isFogOfWar) {
        gameBoard.draw(isFogOfWar);
    }

    public void requestCannonShot(Player opponent) {
        boolean approvedShot = false;
        System.out.println(opponent.name + ", it's your turn:");
        do {
            CannonShot cannonShot = opponent.getCannonShot();
            if (gameBoard.isValidCoordinate(cannonShot.getCoordinate())) {
                approvedShot = true;
                gameBoard.markCannonShot(cannonShot);
            }
        } while (!approvedShot);
    }

    public void setUpGame() {
        System.out.println(this.name + ", place your ships on the game field");
        gameBoard.draw(false);
        for (Ship ship : ships) {
            requestCellsForShip(ship);
            gameBoard.draw(false);
        }
    }

    private void requestCellsForShip(Ship ship) {
        boolean approvedPlacement = false;
        System.out.println("Enter the coordinates of the " + ship.getType() + " (" + ship.getLength() + ") cells: ");
        do {
            PlacementOrder placementOrder = getPlacementOrder(gameBoard, ship);
            if (placementOrder.isValid() && gameBoard.isValidPlacementOrder(placementOrder)) {
                gameBoard.placeShip(placementOrder);
                approvedPlacement = true;
            }
        } while (!approvedPlacement);
    }

    private PlacementOrder getPlacementOrder(GameBoard gameBoard, Ship ship) {
        return new PlacementOrder(gameBoard, ship, scanner.nextLine());
    }

    public CannonShot getCannonShot() {
        return new CannonShot(scanner.nextLine());
    }

    public boolean hasShips() {
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    public void turnOverKeyboard() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }
}