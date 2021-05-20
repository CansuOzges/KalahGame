package com.cansu.kalahgame.model;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BoardTest {

    @Test
    public void testGetPit() {
        final Board board = new Board();
        final Pit pit = board.getPit(4);

        Assert.assertNotNull(pit);
        Assert.assertEquals(4, pit.getId());
    }

    @Test
    public void testGetStoneCount() {
        final Board board = new Board();
        board.getPit(1).setStones(0);
        Assert.assertEquals(30, board.getPlayerStones(Player.PLAYER1));
        Assert.assertEquals(36, board.getPlayerStones(Player.PLAYER2));
    }

    @Test
    public void testGetPits() {
        final Board board = new Board();
        final List<Pit> pits = board.getPits();
        Assert.assertNotNull(pits);
    }

    @Test
    public void testInit() {
        final Board board = new Board();
        Assert.assertNotNull(board.getPits());
        Assert.assertEquals(Board.PIT_END, Integer.valueOf(board.getPits().size()));
    }
}
