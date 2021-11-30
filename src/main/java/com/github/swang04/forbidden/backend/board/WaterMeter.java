/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

public class WaterMeter {

    private int state;

    public void raiseWaterLevel() {
        state++;
    }

    public int getNumberOfCards() {
        return switch (state) {
            case 0, 1 -> 2;
            case 2, 3, 4 -> 3;
            case 5, 6 -> 4;
            case 7, 8 -> 5;
            default -> Integer.MAX_VALUE;
        };
    }

    public int getState() {
        return state;
    }
}
