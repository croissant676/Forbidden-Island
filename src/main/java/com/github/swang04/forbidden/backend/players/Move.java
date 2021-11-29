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

    class AbstractMoveClass implements Move {

        private static final Logger logger = Log.logger();

        private boolean applied = false;

        @Override
        public boolean getApplied() {
            return applied;
        }

        @Override
        public void apply() {
            if (applied) {
                logger.info("Error: Move already applied");
                throw new IllegalStateException("Move " + getClass().getName() + " has already been applied.");
            }
            applied = true;
        }

        public Logger getLogger() {
            return logger;
        }
    }

    class Movement extends AbstractMoveClass {

        private Pawn player;
        private Tile location;

        public Movement(Pawn player, Tile location) {
            this.player = player;
            this.location = location;
        }

        public Pawn getPlayer() {
            return player;
        }

        public void setPlayer(Pawn player) {
            this.player = player;
        }

        public Tile getLocation() {
            return location;
        }

        public void setLocation(Tile location) {
            this.location = location;
        }

        @Override
        public void apply() {
            super.apply();
            player.setTile(location);
        }
    }

    class Trade extends AbstractMoveClass {

    }

}