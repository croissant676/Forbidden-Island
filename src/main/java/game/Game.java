package game;

import game.board.Board;

public class Game {
    private final Board board;
    private Player[] players;
    private boolean win;

    public Game() {
        board = new Board(0);
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

}
