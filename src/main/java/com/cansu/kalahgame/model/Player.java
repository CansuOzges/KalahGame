package com.cansu.kalahgame.model;

public enum Player {

    PLAYER1(Board.PLAYER1_PIT,false),
    PLAYER2(Board.PLAYER2_PIT,false);
    private boolean turn;

    private int bigPitIndex;

    Player(final int bigPitIndex, boolean turn) {
        this.bigPitIndex = bigPitIndex;
        this.turn = turn;
    }

    public int getBigPitIndex() {
        return this.bigPitIndex;
    }
}