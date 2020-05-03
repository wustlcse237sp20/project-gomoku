package com.homework.gomoku.game;

public class Tutorial {

    String tutName;
    Player plyr1;
    Player plyr2;
    String startMessage;
    String[] gameMessage;
    public Tutorial(String tutName) {
        this.tutName = tutName;
    }

    public void setPlayers(Player plyr1, Player plyr2){
        this.plyr1 = plyr1;
        this.plyr2 = plyr2;
    }

    public void setGameMessage(String[] gms) {
        gameMessage = gms;
    }

    public void setStartMessage(String startMessage){
        this.startMessage = startMessage;
    }

    public String getTutName(){
        return tutName;
    }

    public Player getPlyr1(){
        return plyr1;
    }

    public Player getPlyr2(){
        return plyr2;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public String getInGameMessage(int turn) {
        return gameMessage[turn];
    }
}
