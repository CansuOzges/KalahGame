package com.cansu.kalahgame.model;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void getBigPitIndex(){
        final Player player1 = Player.PLAYER1;
        final Player player2 = Player.PLAYER2;

        Assert.assertEquals(7, player1.getBigPitIndex());
        Assert.assertEquals(14, player2.getBigPitIndex());

    }
}
