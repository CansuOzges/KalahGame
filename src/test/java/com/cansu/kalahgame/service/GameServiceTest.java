package com.cansu.kalahgame.service;
import com.cansu.kalahgame.model.Game;
import com.cansu.kalahgame.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService service;

    @Test
    @DirtiesContext
    public void testCreateGame() {
        final Game game = this.service.initGame();

        Assert.assertNotNull(game);
    }

    @Test
    @DirtiesContext
    public void testPlay() {
        final Game game = this.service.initGame();
        this.service.playGame(game.getId(), 6);

        Assert.assertEquals(Player.PLAYER2, game.getTurn());
        Assert.assertNull(game.getWinner());
        Assert.assertEquals(31, game.getBoard().getStoneCount(Player.PLAYER1).intValue()+game.getBoard().getPit(Player.PLAYER1.getBigPitIndex()).getStones());
    }
}
