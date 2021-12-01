/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.PawnManager;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.backend.players.PlayerManager;

import java.util.Random;

public class Game {

    private static Game game = new Game();

    private Board board;
    private final Random random;
    private final PlayerManager playerManager;
    private final PawnManager pawnManager;

    public Game() {
        // Only for testing purposes
        this(0, WaterMeter.NOVICE, "Bob", "Ashhsley", "Joncadence");
    }

    public Game(int seed, int waterSettings, String... names) {
        board = Board.create(seed, waterSettings);
        playerManager = new PlayerManager(names);
        random = new Random(seed);
        pawnManager = new PawnManager(this);
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

    @Override
    public String toString() {
        return "Board\n" + board + "\nPlayers\n" + playerManager + "\nPawns\n" + pawnManager;
    }
}
