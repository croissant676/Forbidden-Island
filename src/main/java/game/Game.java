package game;

import game.board.Board;
import game.board.WaterMeter;
import game.cards.FloodDeck;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/** */
public class Game {

    private final Board board;
    private List<Player> players;
    private boolean win;
    private final Random random;
    private WaterMeter meter;
    private final FloodDeck deck;

    public Game(long seed) {
        board = new Board(seed);
        random = new Random(seed);
        deck = new FloodDeck();
        Collections.shuffle(deck, random);
    }

    /**
     * @return board
     * */
    public Board getBoard() {
        return board;
    }

    /**
     * @return list of players*/
    public List<Player> getPlayers() {
        return players;
    }

    public void drawCard() {

    }

}
