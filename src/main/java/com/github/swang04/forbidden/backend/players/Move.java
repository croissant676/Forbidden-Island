/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.board.Tile;
import dev.kason.forbidden.logging.Log;

import java.util.logging.Logger;

public interface Move {


    boolean getApplied();

    void apply();

    class Movement implements Move {

        private final Pawn pawn;
        private final Tile tile;
        Logger logger = Log.logger();
        private boolean applied = false;

        public Movement(Pawn pawn, Tile tile) {
            this.pawn = pawn;
            this.tile = tile;
        }

        public Tile getTile() {
            return tile;
        }

        public Pawn getPawn() {
            return pawn;
        }

        @Override
        public boolean getApplied() {
            return applied;
        }

        @Override
        public void apply() {
            if (applied) return;
            applied = true;
            pawn.setTile(tile);
            logger.info("Moving " + pawn + " to [" + tile.getX() + ", " + tile.getY() + "].");
        }
    }

    class Trade implements Move {

        private final Player sender;
        private final Player receiving;
        private final Card card;
        private boolean applied = false;

        public Trade(Player sender, Player receiving, Card card) {
            this.sender = sender;
            this.receiving = receiving;
            this.card = card;
        }

        public Card getCard() {
            return card;
        }

        public Player getReceiving() {
            return receiving;
        }

        public Player getSender() {
            return sender;
        }

        @Override
        public boolean getApplied() {
            return false;
        }

        @Override
        public void apply() {
            if (applied) return;
            applied = true;

        }
    }

}