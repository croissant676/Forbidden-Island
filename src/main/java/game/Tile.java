package game;

public class Tile {

    private State currentState;
    private final Type type;

    private final byte row;
    private final byte column;

    public Tile(int row, int col, Type type) {
        this.row = (byte) row;
        this.column = (byte) col;
        this.type = type;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State state) {
        currentState = state;
    }

    public Type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public enum State {
        DRY,
        FLOODED,
        SUBMERGED;
    }

    public enum Type {
        HOWLING_GARDEN("Howling Garden"),
        OBSERVATORY("Observatory"),
        SILVER_GATE("Silver Gate"),
        IRON_GATE("Iron Gate"),
        PHANTOM_ROCK("Phantom Rock"),
        SUN_TEMPLE("Temple of the Sun"),
        WATCHTOWER("Watchtower"),
        CORAL_PALACE("Coral Palace"),
        WHISPERING_GARDEN("Whispering Garden"),
        CRIMSON_FOREST("Crimson Forest"),
        LANDING("Fool's Landing"),
        DUNES_OF_DECEPTION("Dunes of Deception"),
        GOLD_GATE("Gold Gate"),
        COPPER_GATE("Copper Gate"),
        LOST_LAGOON("Lost Lagoon"),
        CAVE_OF_SHADOW("Cave of Shadow"),
        TIDAL_PALACE("Tidal Palace"),
        MISTY_MARSH("Misty Marsh"),
        TWILIGHT_HOLLOW("Twilight Hollow"),
        MOON_TEMPLE("Temple of the Moon"),
        CAVE_OF_EMBERS("Cave of Embers"),
        BRONZE_GATE("Bronze Gate"),
        BREAKERS_BRIDGE("Breakers Bridge"),
        CLIFFS_OF_ABANDON("Cliffs of Abandon");

        private final String formalName;

        Type(String formalName) {
            this.formalName = formalName;
        }

        public String getFormalName() {
            return formalName;
        }
    }
}
