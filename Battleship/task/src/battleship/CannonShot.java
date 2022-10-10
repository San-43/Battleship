package battleship;

public class CannonShot {
    final Coordinate coordinate;

    CannonShot(String coordinate) {
        this.coordinate = new Coordinate(coordinate);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}