package com.cansu.kalahgame.gamerepository;
import com.cansu.kalahgame.model.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class GameRepositoryTest {

    @InjectMocks
    private GameRepository repository;

    @Test
    public void testSave() {
        final Game game = this.repository.save();
        Assert.assertNotNull(game);
    }
    @Test
    public void testFind() {
        final Game game = this.repository.save();
        final Game founded = this.repository.find(game.getId());

        Assert.assertNotNull(game);
        Assert.assertEquals(game, founded);
    }


}
