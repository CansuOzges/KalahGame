package com.cansu.kalahgame.controller;

import com.cansu.kalahgame.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping(value = "/games")
    public ResponseEntity initGame(){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.initGame());
    }

    @PutMapping("/games/{gameId}/pits/{pitIndex}")
    public ResponseEntity playGame(@PathVariable Integer gameId, @PathVariable Integer pitIndex){
        return ResponseEntity.ok().body(gameService.playGame(gameId, pitIndex));
    }

}
