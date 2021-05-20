package com.cansu.kalahgame.model;


import org.junit.Assert;
import org.junit.Test;

public class PitTest {

    @Test
    public void testGetBigPit(){
        final Pit givenPit1 = new Pit(7,Player.PLAYER1,6 );
        final Pit givenPit2 = new Pit(14,Player.PLAYER2,8);
        final Pit givenPit3 = new Pit(3,Player.PLAYER1,3);
        final Pit givenPit4 = new Pit(9,Player.PLAYER2,4);

        Assert.assertTrue(givenPit1.isBigPit());
        Assert.assertTrue(givenPit2.isBigPit());
        Assert.assertFalse(givenPit3.isBigPit());
        Assert.assertFalse(givenPit4.isBigPit());
    }

    @Test
    public void testGetPlayer() {
        final Pit Pit1 = new Pit(7,Player.PLAYER1,6 );
        final Pit Pit2 = new Pit(14,Player.PLAYER2,8);
        final Pit Pit3 = new Pit(3,Player.PLAYER1,3);
        final Pit Pit4 = new Pit(9,Player.PLAYER2,4);

        Assert.assertEquals(Player.PLAYER1, Pit1.getPlayer());
        Assert.assertEquals(Player.PLAYER2, Pit2.getPlayer());
        Assert.assertEquals(Player.PLAYER1, Pit3.getPlayer());
        Assert.assertEquals(Player.PLAYER2, Pit4.getPlayer());

    }

    @Test
    public void testSetStoneCount() {
        final Pit pit = new Pit(7,Player.PLAYER1,6 );
        pit.setStones(8);

        Assert.assertEquals(8, pit.getStones());
    }

    @Test
    public void testInit() {
        final Pit Pit1 = new Pit(7,Player.PLAYER1,6 );
        final Pit Pit2 = new Pit(14,Player.PLAYER2,8);
        final Pit Pit3 = new Pit(3,Player.PLAYER1,3);

        Assert.assertEquals(7, Pit1.getId());
        Assert.assertEquals(14, Pit2.getId());
        Assert.assertEquals(3, Pit3.getId());
    }
}
