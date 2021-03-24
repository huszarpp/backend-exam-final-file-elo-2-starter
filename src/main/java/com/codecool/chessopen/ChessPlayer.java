package com.codecool.chessopen;

class ChessPlayer {

    private final String name;
    private int openPoints;

    ChessPlayer(String name) {
        this.name = name;
    }

    void addPoints(int points) {
        if (isValidPoints(points)) {
            openPoints += points;
        }
    }

    private boolean isValidPoints(int points) {
        return (0 <= points) && (points <= 2);
    }

    String getName() {
        return name;
    }

    int getOpenPoints() {
        return openPoints;
    }
}
