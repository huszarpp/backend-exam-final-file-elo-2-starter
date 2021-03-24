package com.codecool.chessopen;

public class ChessPlayer {

    private String name;
    private int openPoints;

    public ChessPlayer(String name) {
        this.name = name;
    }

    public void addPoints(int points) {
        if (isValidPoints(points)) {
            openPoints += points;
        }
    }

    private boolean isValidPoints(int points) {
        return (0 <= points) && (points <= 2);
    }

    public String getName() {
        return name;
    }

    public int getOpenPoints() {
        return openPoints;
    }
}
