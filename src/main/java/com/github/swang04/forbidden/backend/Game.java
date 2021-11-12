package com.github.swang04.forbidden.backend;

public class Game {

    private static Game game = new Game();

    public Game() {

    }

    public Game(int seed) {

    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

}
