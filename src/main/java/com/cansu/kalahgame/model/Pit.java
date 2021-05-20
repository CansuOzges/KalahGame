package com.cansu.kalahgame.model;

import com.cansu.kalahgame.model.Board;
import com.cansu.kalahgame.model.Player;

public class Pit {
    private final int id;
    private int stones;
    private final Player player;

    public Pit(int id, Player player, int stones) {
        this.id = id;
        this.player = player;
        this.stones = stones;
    }

    public int getId() {
        return this.id;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getStones() {
        return this.stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public Boolean isBigPit(){
        return this.id == Board.PLAYER1_PIT || this.id == Board.PLAYER2_PIT;
    }

    public boolean isProperPit(Player player) {
        return (!player.equals(Player.PLAYER1)
                || (this.getId() != Player.PLAYER1.getBigPitIndex())
                && !player.equals(Player.PLAYER2)
                || (this.getId() != Player.PLAYER2.getBigPitIndex()));
    }
}
