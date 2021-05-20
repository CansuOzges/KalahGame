package com.cansu.kalahgame.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final Integer PIT_START = 1;
    public static final Integer PIT_END = 14;
    public static final Integer PLAYER1_PIT = 7;
    public static final Integer PLAYER2_PIT = 14;
    public static final Integer STONE_COUNT = 6;

    private final List<Pit> pits;

    public Board() {

        this.pits = new ArrayList<>();
        for (int i = Board.PIT_START; i < Board.PLAYER1_PIT; i++) {
            this.pits.add(new Pit(i, Player.PLAYER1,STONE_COUNT));
        }
        this.pits.add(new Pit(7, Player.PLAYER1,0));
        for (int i = Board.PLAYER1_PIT+1; i < Board.PIT_END; i++) {
            this.pits.add(new Pit(i, Player.PLAYER2,STONE_COUNT));
        }
        this.pits.add(new Pit(14, Player.PLAYER2,0));
    }

    public Pit getPit(final int id) {
        return this.pits.get((id - 1) % Board.PIT_END);
    }

    public List<Pit> getPits() {
        return this.pits;
    }

    public int getPlayerStones(final Player player) {
        return this.getPits().stream()
                .filter(pit -> (pit.getPlayer().equals(player) &&!pit.isBigPit()))
                .mapToInt(Pit::getStones).sum();
    }

    public Integer getStoneCount(Player player){
        return this.getPits().stream()
                .filter(pit -> (pit.getPlayer().equals(player) && !pit.isBigPit())).mapToInt(Pit::getStones).sum();
    }

    public Pit getOppositePit(Integer pitIndex){
        return getPit(Board.PIT_END - pitIndex);
    }

}
