package com.cansu.kalahgame.service;

import com.cansu.kalahgame.model.Game;

public interface GameService {

    Game initGame();

    Game playGame(Integer gameId, Integer pitId);

}
