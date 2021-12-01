/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import dev.kason.forbidden.Log;

import java.util.Objects;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Movement movement)) return false;
            return Objects.equals(player, movement.player) &&
                    Objects.equals(location, movement.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(player, location);
        }

        @Override
        public String toString() {
            return "Movement{" +
                    "player=" + player +
                    ", location=" + location +
                    '}';
        }
    }

    class Trade extends AbstractMoveClass {

        private Player giver;
        private Player receiver;

        private TreasureDeckCard TreasureDeckCard;

        public Trade(Player giver, Player receiver, TreasureDeckCard TreasureDeckCard) {
            this.giver = giver;
            this.receiver = receiver;
            this.TreasureDeckCard = TreasureDeckCard;
        }

        public Player getGiver() {
            return giver;
        }

        public void setGiver(Player giver) {
            this.giver = giver;
        }

        public Player getReceiver() {
            return receiver;
        }

        public void setReceiver(Player receiver) {
            this.receiver = receiver;
        }

        public TreasureDeckCard getTreasureDeckCard() {
            return TreasureDeckCard;
        }

        public void setTreasureDeckCard(com.github.swang04.forbidden.backend.treasure.TreasureDeckCard TreasureDeckCard) {
            this.TreasureDeckCard = TreasureDeckCard;
        }

        @Override
        public void apply() {
            super.apply();
            giver.getInventoryItems().remove(TreasureDeckCard);
            receiver.receiveCard(TreasureDeckCard);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Trade trade)) return false;
            return Objects.equals(giver, trade.giver) && Objects.equals(receiver, trade.receiver) && Objects.equals(TreasureDeckCard, trade.TreasureDeckCard);
        }

        @Override
        public int hashCode() {
            return Objects.hash(giver, receiver, TreasureDeckCard);
        }

        @Override
        public String toString() {
            return "Trade{" +
                    "giver=" + giver +
                    ", receiver=" + receiver +
                    ", TreasureDeckCard=" + TreasureDeckCard +
                    '}';
        }
    }

}