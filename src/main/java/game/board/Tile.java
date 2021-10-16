package game.board;

import com.formdev.flatlaf.ui.FlatBorder;
import game.Visualizable;

import javax.swing.*;
import java.util.Objects;

public class Tile implements Visualizable {

    private final byte row;
    private final byte col;
    private final TileType tileType;
    private TileState state;

    public static Tile getSample() {
        return new Tile((byte) 0, (byte) 0, TileType.LANDING);
    }

    public Tile(byte row, byte col, TileType tileType) {
        this.row = row;
        this.col = col;
        this.tileType = tileType;
        this.state = TileState.DRY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public TileType getTileType() {
        return tileType;
    }

    public String shortened() {
        return "T:" + tileType.ordinal() + "@[" + row + ":" + col + "]S=" + state.ordinal() + "";
    }

    @Override
    public JButton getVisual() {
        JButton button = new JButton();
        if (state == TileState.DRY) {
            button.setIcon(new ImageIcon(tileType.getRegularImage()));
        } else if (state == TileState.FLOODED) {
            button.setIcon(new ImageIcon(tileType.getFloodedImage()));
        } else {
            // Just to be safe
            button.setIcon(new ImageIcon(tileType.getFloodedImage()));
            button.setOpaque(false);
        }
        button.setBorder(new FlatBorder());
        return button;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return row == tile.row &&
                col == tile.col &&
                tileType == tile.tileType &&
                state == tile.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, tileType, state);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "row=" + row +
                ", col=" + col +
                ", tileType=" + tileType +
                ", state=" + state +
                '}';
    }

}
