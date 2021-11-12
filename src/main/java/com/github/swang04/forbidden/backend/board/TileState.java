package com.github.swang04.forbidden.backend.board;

// Author Kason
public enum TileState {
    DRY,
    FLOODED,
    SUNK;

    @Override
    public String toString() {
        return name();
    }
}
