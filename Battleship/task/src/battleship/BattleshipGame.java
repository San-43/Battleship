package battleship;

public class BattleshipGame {
    BattleshipGame() {
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        players[0].setUpGame();
        players[0].turnOverKeyboard();
        players[1].setUpGame();
        players[1].turnOverKeyboard();
        play(players);
    }

    public void play(Player[] players) {
        boolean doContinue = true;
        do {
            players[1].drawGameBoard(true);
            GameBoard.drawSeparator();
            players[0].drawGameBoard(false);
            players[1].requestCannonShot(players[0]);
            players[1].drawGameBoard(true);
            if (!players[1].hasShips()) {
                doContinue = false;
                System.out.println("You sank the last ship. You won. Congratulations!");
            } else {
                players[0].turnOverKeyboard();
                switchPlayers(players);
            }
        } while (doContinue);
    }

    private void switchPlayers(Player[] players) {
        Player intermediate = players[0];
        players[0] = players[1];
        players[1] = intermediate;
    }
}