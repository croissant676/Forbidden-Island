package game.board;

import com.formdev.flatlaf.ui.FlatBorder;
import game.Visualizable;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Tile implements Visualizable {

    private final byte row;
    private final byte col;
    private final TileType tileType;
    private TileState state;

    public Tile(byte row, byte col, TileType tileType) {
        this.row = row;
        this.col = col;
        this.tileType = tileType;
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

    public TileType getTileType() {
        return tileType;
    }

    @Override
    public JButton getVisual() {
        JButton button = new JButton();
        if(state == TileState.DRY) {
            button.setIcon(new ImageIcon(tileType.getRegularImage()));
        } else if(state == TileState.FLOODED) {
            button.setIcon(new ImageIcon(tileType.getRegularImage()));
        } else {
            // Just to be safe
            button.setIcon(new ImageIcon(tileType.getRegularImage()));
            button.setOpaque(false);
        }
        button.setBorder(new FlatBorder());
        return button;
    }
}
