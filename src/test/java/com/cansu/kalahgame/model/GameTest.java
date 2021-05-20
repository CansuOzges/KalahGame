package com.cansu.kalahgame.model;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void testInit(){
        Game game = new Game();
        Assert.assertNotNull(game.getBoard());
        Assert.assertNull(game.getWinner());
    }

    @Test
    public void testGetPlayer(){
        Game game = new Game();
        Assert.assertEquals(Player.PLAYER1, game.getPlayer1());
        Assert.assertEquals(Player.PLAYER2, game.getPlayer2());
    }

    @Test
    public void testWinner(){
        Game game = new Game();
        game.setWinner(Player.PLAYER1);
        Assert.assertEquals(Player.PLAYER1, game.getWinner());
    }

    @Test
    public void testStatus(){
        Game game = new Game();
        game.setStatus("FINISHED");
        Assert.assertEquals("FINISHED", game.getStatus());
    }
}
