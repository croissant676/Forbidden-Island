/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.PawnManager;
import com.github.swang04.forbidden.backend.board.TileState;
import com.github.swang04.forbidden.backend.board.TileType;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.treasure.Treasure;
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

    public static final int LOSS = 42069;
    public static final int WIN = 6942069;
    public static final int NONE = 69420;
    private static String lossReason;

    public static String getLossReason() {
        return lossReason;
    }

    public int checkForWinLossConditions() {

        if (Board.getInstance().getWaterMeter().getState() > 8) {
            lossReason = "Water Meter has reached its death state.";
        }
        if (TileType.LANDING.getTile().getTileState() == TileState.SUNK) {
            lossReason = "Fool's Landing has sunk!";
            return LOSS;
        }
        for (Player player : PlayerManager.getInstance().getPlayers()) {
            if (player.getPawn().getTile().getTileState() == TileState.SUNK && player.getPlayerType().getMovements().isEmpty()) {
                lossReason = player.getPlayerType().getName() + " does not have anywhere to go.";
                return LOSS;
            }
        }
        for (Treasure treasure : Treasure.values()) {
            if (treasure.isUnreachable()) {
                lossReason = "Cannot access treasure " + treasure.getFormalName() + ".";
                return LOSS;
            }
        }
        if (PlayerManager.getInstance().getPlayers().stream().allMatch((player) -> player.getPawn().getTile().getTileType() == TileType.LANDING) && PlayerManager.getInstance().getWonTreasures().size() == 4) {
            return WIN;
        }
        return NONE;
    }

    @Override
    public String toString() {
        return "Board\n" + board + "\nPlayers\n" + playerManager + "\nPawns\n" + pawnManager + "\nCards\n" + treasureDeck + "\nFlood Deck\n" + board.getFloodDeck();
    }
}
