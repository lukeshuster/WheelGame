package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
    /* Attributes */
    private String playerId;
    private String playerName;
    private int points, bet;
    private BetType betType;

    /* Constructor */
    public SimplePlayer(String playerId, String playerName, int initialPoints){
        this.playerId = playerId;
        this.playerName = playerName;
        points = initialPoints;
    }

    public String getPlayerId(){
        return playerId;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String newPlayerName){
        playerName = newPlayerName;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int newPoints){
        points = newPoints;
    }

    public boolean setBet(int newBet){
        boolean betResult = false;
        if((newBet > 0) && (points >= newBet)){
            bet = newBet;
            betResult = true;
        }
        return betResult;
    }

    public int getBet(){
        return bet;
    }

    public void setBetType(BetType newBetType){
        betType = newBetType;
    }

    public BetType getBetType(){
        return betType;
    }

    public void resetBet(){
        bet = 0;
    }

    @Override
    public String toString(){
        return "Player ID: = '" + this.playerId + "', Player Name: = '" + this.playerName + "', Bet: = '" + this.bet + "', Bet Type: = '" + this.betType + "', Points: = '" + this.points + "'";
    }
}