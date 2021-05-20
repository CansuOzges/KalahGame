package com.cansu.kalahgame.model;


public class Game {
    private Integer id;
    private Board board;
    private Player winner;
    private Player player1;
    private Player player2;
    private Player turn;
    private String status;

    public Game() {
        this.board = new Board();
        this.status = "STARTED";
        this.player1 = Player.PLAYER1;
        this.player2 =Player.PLAYER2;
    }

    public Player getPlayer1(){
        return this.player1;
    }
    public Player getPlayer2(){
        return this.player2;
    }
    public Board getBoard() {
        return this.board;
    }

    public int getId() {
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Player getWinner() {
        return this.winner;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }

    public String getStatus (){
        return this.status;
    }

    public void setStatus (String status){
        this.status = status;
    }

    public Player getTurn() {
        return this.turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }


}
