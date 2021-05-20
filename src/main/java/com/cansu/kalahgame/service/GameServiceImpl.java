package com.cansu.kalahgame.service;

import com.cansu.kalahgame.exception.GameException;
import com.cansu.kalahgame.gamerepository.GameRepository;
import com.cansu.kalahgame.model.Game;
import com.cansu.kalahgame.model.Pit;
import com.cansu.kalahgame.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository repository;

    @Autowired
    public GameServiceImpl(final GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game initGame() {
        return this.repository.save();
    }

    @Override
    public Game playGame(final Integer gameId, final Integer pitId) {
        final Game game = this.repository.find(gameId);
        checkFinish(game);
        distributeStones(game, pitId);
        return game;
    }

    public void checkFinish(Game game) {

        Integer player1StoneCount = game.getBoard().getStoneCount(game.getPlayer1());
        Integer player2StoneCount = game.getBoard().getStoneCount(game.getPlayer2());

        if( player1StoneCount == 0 || player2StoneCount == 0){

            game.setStatus("FINISHED");
            player1StoneCount = player1StoneCount + game.getBoard().getPit(game.getPlayer1().getBigPitIndex()).getStones();
            player2StoneCount = player2StoneCount + game.getBoard().getPit(game.getPlayer2().getBigPitIndex()).getStones();

            if (player1StoneCount > player2StoneCount){
                game.setWinner(game.getPlayer1());
            }
            else if(player2StoneCount>player1StoneCount){
                game.setWinner(game.getPlayer2());
            }
            resetBoard(game);
        }
    }

    public void resetBoard(Game game){
        game.getBoard().getPits().stream()
                .filter(pit -> !pit.isBigPit()).forEach(pit -> pit.setStones(0));
    }

    private void distributeStones(final Game game, int pitId) {
        final Pit firstPit = game.getBoard().getPit(pitId);
        decideMoveAndTurn(game, pitId);
        int stonesCount= firstPit.getStones();
        firstPit.setStones(0);
        while (stonesCount > 0) {
            final Pit currentPit = game.getBoard().getPit(++pitId);
            if (currentPit.isProperPit(game.getTurn())) {
                currentPit.setStones(currentPit.getStones() + 1);
                stonesCount--;
            }
        }
        lastPitCalculation(game, pitId);
        setPlayerTurn(game, pitId);
    }

    private void setPlayerTurn(final Game game, final int pitId) {
        final Pit pit = game.getBoard().getPit(pitId);
        if (pit.isBigPit() && Player.PLAYER1.equals(pit.getPlayer())
                && Player.PLAYER1.equals(game.getTurn())) {
            game.setTurn(Player.PLAYER1);
        } else if (pit.isBigPit() && Player.PLAYER2.equals(pit.getPlayer())
                && Player.PLAYER2.equals(game.getTurn())) {
            game.setTurn(Player.PLAYER2);
        } else {
            if (Player.PLAYER1.equals(game.getTurn())) {
               game.setTurn(Player.PLAYER2);
            } else {
                game.setTurn(Player.PLAYER1);
            }
        }
    }


    private void decideMoveAndTurn(final Game game, final int startPitId) {
        final Pit startPit = game.getBoard().getPit(startPitId);
        if (startPit.isBigPit()) {
            throw new GameException("Can not start from the big pit.");
        }
        if (Player.PLAYER1.equals(game.getTurn())
                && !Player.PLAYER1.equals(startPit.getPlayer())) {
            throw new GameException("It's Player1's turn.");
        }
        if (Player.PLAYER2.equals(game.getTurn())
                && !Player.PLAYER2.equals(startPit.getPlayer())) {
            throw new GameException("It's Player2's turn.");
        }
        if (startPit.getStones() == 0) {
            throw new GameException("Can not start from empty pit");
        }
        if (game.getTurn() ==null) {
            if (Player.PLAYER1.equals(startPit.getPlayer())) {
                game.setTurn(Player.PLAYER1);
            } else {
                game.setTurn(Player.PLAYER2);
            }
        }
    }

    private void lastPitCalculation(final Game game, final int endPitId) {
        final Pit endPit = game.getBoard().getPit(endPitId);
        if (!endPit.isBigPit() && endPit.getPlayer().equals(game.getTurn())
                && (endPit.getStones() == 1)) {
            final Pit oppositePit = game.getBoard().getOppositePit(endPit.getId());
            if (oppositePit.getStones() > 0) {
                final Pit bigPit = game.getBoard().getPit(endPit.getPlayer().getBigPitIndex());
                bigPit.setStones(
                        (bigPit.getStones() + oppositePit.getStones()) + endPit.getStones());
                oppositePit.setStones(0);
                endPit.setStones(0);
            }
        }
    }
}
