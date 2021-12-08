/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.WaterMeter;
import com.github.swang04.forbidden.backend.players.Player;
import dev.kason.forbidden.ui.GameVisualizer;

public class WatersRiseCard implements TreasureDeckCard {
    @Override
    public Player getHolder() {
        return null;
    }

    @Override
    public void setHolder(Player player) {
    }

    @Override
    public void onDraw(Player player) {
    }

    public void apply() {
        WaterMeter meter = Board.getInstance().getWaterMeter();
        meter.raiseWaterLevel();
        FloodDeck deck = Board.getInstance().getFloodDeck();
        deck.moveDiscardedBack();
        GameVisualizer.getInstance().getWaterMeterVisualizer().updatePanel();
        GameVisualizer.getInstance().repaintPanels();
    }

    @Override
    public String toString() {
        return "Waters Rise";
    }
}
