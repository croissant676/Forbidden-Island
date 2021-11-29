/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */
package com.github.swang04.forbidden.backend.board;

// Author: Kason
public enum TileState {
    DRY,
    FLOODED,
    SUNK;

    @Override
    public String toString() {
        return switch (this) {
            case DRY -> "Dry";
            case FLOODED -> "Flooded";
            case SUNK -> "Sunk";
        };
    }
}