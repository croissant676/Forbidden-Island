/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.treasure;

public enum Treasure {
    EARTH_STONE,
    CRYSTAL_OF_FIRE,
    STATUE_OF_WIND,
    OCEAN_CHALICE;

    public String getFileName() {
        return switch (this) {
            case EARTH_STONE -> "treasure_earth";
            case CRYSTAL_OF_FIRE -> "treasure_fire";
            case OCEAN_CHALICE -> "treasure_ocean";
            case STATUE_OF_WIND -> "treasure_wind";
        };
    }

    public String getFormalName() {
        return switch (this) {
            case STATUE_OF_WIND -> "Statue of Wind";
            case EARTH_STONE -> "Earth Stone";
            case OCEAN_CHALICE -> "Ocean Chalice";
            case CRYSTAL_OF_FIRE -> "Crystal Of File";
        };
    }

    @Override
    public String toString() {
        return name();
    }
}