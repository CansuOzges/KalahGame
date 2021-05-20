package com.cansu.kalahgame.gamerepository;

import com.cansu.kalahgame.exception.GameException;
import com.cansu.kalahgame.model.Game;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameRepository {

    private static final Map<Integer, Game> gameMap = new ConcurrentHashMap<>();

    public Game save(){
        Random rand = new Random();
        Integer id =  rand.nextInt();
        Game game = new Game();
        game.setId(id);
        gameMap.put(id, game);
        return gameMap.get(id);
    }

    public Game find(Integer id){
        Game game = gameMap.get(id);
        if(game == null){
            throw new GameException("Game is not found for the id: "+id);
        }
        return game;
    }
}
