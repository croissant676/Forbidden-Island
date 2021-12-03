/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.PawnManager;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.TreasureDeck;
import com.github.swang04.forbidden.ui.LossView;
import dev.kason.forbidden.ui.ViewManager;

import java.util.Random;

public class Game {

    private static Game game;

    private Board board;
    private final Random random;
    private final PlayerManager playerManager;
    private final PawnManager pawnManager;

    private final TreasureDeck treasureDeck;

    public Game() {
        // Only for testing purposes
        this(0, WaterMeter.NOVICE, "Bob", "Ashhsley", "Joncadence", "Yayeet");
    }

    public Game(int seed, int waterSettings, String... names) {
        Game.game = this;
        board = Board.create(seed, waterSettings);
        playerManager = new PlayerManager(names);
        random = new Random(seed);
        pawnManager = new PawnManager(this);
        this.treasureDeck = new TreasureDeck();
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

    public void gameLost() {
        ViewManager.display(LossView.getInstance());
    }

    @Override
    public String toString() {
        return "Board\n" + board + "\nPlayers\n" + playerManager + "\nPawns\n" + pawnManager + "\nCards\n" + treasureDeck + "\nFlood Deck\n" + board.getFloodDeck();
    }
}
