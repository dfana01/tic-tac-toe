package dfb.com.tictactoe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Dante on 5/19/2017.
 */

public class Game {

    public static final int MULTI_PLAYER = 1;
    public static final int SINGLE_PLAYER = 2;

    private final String MARK_O = "O";
    private final String MARK_X = "X";

    private Player[] players;
    private Board board;

    private Player currentPlayer;
    private Player winner;

    private int playModality;

    private Map<Integer, Move> emptyPositions;

    private Move [][] winningCombinations;

    private static Game instance;

    private Game(int playModality){
        init(playModality);
    }

    public static Game getInstance(int playModality){
        if(!(instance instanceof Game)){
            instance = new Game(playModality);
        }else{
            instance.playModality = playModality;
        }
        return instance;
    }

    private void init(int playModality){
        this.playModality = playModality;
        players = new Player[2];
        players[0] = new Player(MARK_X);
        players[1] = new Player(MARK_O);
        board = new Board();
        currentPlayer = players[0];
        initPosition();
    }

    private void initPosition(){
        emptyPositions = new HashMap<Integer, Move>();
        for(int i = 0; i <= 8; i++){
            emptyPositions.put(i,new Move(i));
        }
        winningCombinations= new Move[][]{
                {emptyPositions.get(0),emptyPositions.get(1),emptyPositions.get(2)},
                {emptyPositions.get(3),emptyPositions.get(4),emptyPositions.get(5)},
                {emptyPositions.get(6),emptyPositions.get(7),emptyPositions.get(8)},
                {emptyPositions.get(0),emptyPositions.get(3),emptyPositions.get(6)},
                {emptyPositions.get(1),emptyPositions.get(4),emptyPositions.get(7)},
                {emptyPositions.get(2),emptyPositions.get(5),emptyPositions.get(8)},
                {emptyPositions.get(0),emptyPositions.get(4),emptyPositions.get(8)},
                {emptyPositions.get(2),emptyPositions.get(4),emptyPositions.get(6)}};
    }


    public boolean play(int position){
        Move move = emptyPositions.containsKey(position)? emptyPositions.get(position): null;
        if ( move == null) return false;

        move.setMark(currentPlayer.getMark());
        if (!board.move(move) || winner != null ) return false;

        currentPlayer.move(move);
        emptyPositions.remove(position);
        checkWinner();
        switchPlayer();

        return true;
    }

    public boolean playUi(){
        if(playModality == SINGLE_PLAYER && currentPlayer == players[1] && !emptyPositions.isEmpty()) {
            LinkedList keySet = new LinkedList(emptyPositions.keySet());
            Collections.shuffle(keySet);
            return play(emptyPositions.get(keySet.getFirst()).getPosition());
        }
        return false;
    }

    private void switchPlayer() {
        if (players[0] != currentPlayer)
            currentPlayer = players[0];
        else
            currentPlayer = players[1];
    }

    public boolean checkWinner(){
        for (Player player: players){
            for (Move[] combination: winningCombinations) {
                Set playerMoves = player.getMoves();
                if (playerMoves.contains(combination[0]) &&
                        playerMoves.contains(combination[1]) &&
                        playerMoves.contains(combination[2])) {
                    player.markPoint();
                    winner = player;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasWinner(){
        return winner != null;
    }

    public boolean isDraw(){
        return  board.isFull() && winner == null;
    }

    public void reset(){
        startNewGame();
        for(Player player: players){
            player.resetScore();
        }
    }

    public void startNewGame(){
        for (Move move: board.getPlays()) {
            if(move == null)
                continue;
            move.setMark("");
            emptyPositions.put(move.getPosition(),move);
        }
        board.reset();
        currentPlayer = players[0];
        winner = null;
        for(Player player: players){
            player.resetMoves();
        }
    }

    public Player getPlayer1(){
        return players[0];
    }

    public Player getPlayer2(){
        return players[1];
    }

    public Player getWinner(){
        return winner;
    }

    public Player getCurrentPlayer(){ return currentPlayer; }

    public Move[] getPlays(){
        return board.getPlays();
    }

}
