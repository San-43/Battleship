package battleship;

class Coordinate {
    final String coordinate;
    final int row;
    final int column;

    Coordinate(String inputString) {
        this.coordinate = inputString;
        row = coordinate.charAt(0) - 65;
        column = Integer.parseInt(coordinate.substring(1)) - 1;
    }

    static Coordinate getFirstCoordinate(String input) {
        return new Coordinate(input.split(" ")[0]);
    }

    static Coordinate getSecondCoordinate(String input) {
        return new Coordinate(input.split(" ")[1]);
    }
}