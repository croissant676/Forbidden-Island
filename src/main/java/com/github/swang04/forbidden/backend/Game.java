/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.PawnManager;
import com.github.swang04.forbidden.backend.players.PlayerManager;

import java.util.Random;

public class Game {

    private static Game game = new Game();

    private Board board;
    private Random random;
    private PlayerManager playerManager;
    private PawnManager pawnManager;

    public Game() {
        // Only for testing purposes
        this(0, "Bob", "Alice", "Joncadence");
    }

    public Game(int seed, String... names) {
        board = Board.create(seed);

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Random getRandom() {
        return random;
    }

    public PawnManager getPawnManager() {
        return pawnManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

}
