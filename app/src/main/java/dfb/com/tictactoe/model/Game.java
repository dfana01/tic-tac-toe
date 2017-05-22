package dfb.com.tictactoe.model;

import java.util.Set;

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

    private final Move [][] winningCombinations = {
            {new Move(0),new Move(1),new Move(2)},
            {new Move(3),new Move(4),new Move(5)},
            {new Move(6),new Move(7),new Move(8)},
            {new Move(0),new Move(3),new Move(6)},
            {new Move(1),new Move(4),new Move(7)},
            {new Move(2),new Move(5),new Move(8)},
            {new Move(0),new Move(4),new Move(8)},
            {new Move(2),new Move(4),new Move(6)}};

    public Game(){
        init();
    }

    private void init(){
        players = new Player[2];
        players[0] = new Player(MARK_X);
        players[1] = new Player(MARK_O);
        board = new Board();
        currentPlayer = players[0];
    }

    public boolean play(int position){
        Move move = new Move(position, currentPlayer.getMark());
        if ((!board.move(move)) || winner != null ) return false;
        currentPlayer.move(move);
        switchPlayer();
        checkWinner();
        return true;
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

    public Move[] getPlays(){
        return board.getPlays();
    }

}
