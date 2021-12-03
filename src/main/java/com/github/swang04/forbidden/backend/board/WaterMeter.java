/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

public class WaterMeter {

    public static final int NOVICE = 0;
    public static final int NORMAL = 1;
    public static final int ELITE = 2;
    public static final int LEGENDARY = 3;

    public static int getStateBasedOnName(String str) {
        return switch (str.toLowerCase()) {
            case "novice" -> 0;
            case "normal" -> 1;
            case "elite" -> 2;
            case "legendary" -> 3;
            default -> -1;
        };
    }

    private int state;

    public WaterMeter(int state) {
        this.state = state;
    }

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
