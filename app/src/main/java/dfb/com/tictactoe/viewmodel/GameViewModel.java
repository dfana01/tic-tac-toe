package dfb.com.tictactoe.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import dfb.com.tictactoe.model.Game;
import dfb.com.tictactoe.model.Move;
import dfb.com.tictactoe.model.Player;


/**
 * Created by Dante on 5/19/2017.
 */

public class GameViewModel extends BaseObservable {

    private Game game;

    public GameViewModel(int gameModality){
        game = Game.getInstance(gameModality);
    }

    @Bindable
    public Player getPlayer1(){
        return game.getPlayer1();
    }

    @Bindable
    public Player getPlayer2(){
        return game.getPlayer2();
    }

    @Bindable
    public Move[] getPlays(){
        return game.getPlays();
    }

    @Bindable
    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        if(game.hasWinner()){
            Player winner = game.getWinner();
            sb.append(winner.getMark()).append(" Win!");
        } else if(game.isDraw()){
            sb.append("Draw!");
        } else{
            sb.append(game.getCurrentPlayer().getMark()).append(" Turn ");
        }
        return sb.toString();
    }

    public boolean playerMove(int position){
        if(game.hasWinner() || game.isDraw()) {
            notifyChange();
            game.startNewGame();
        }else if (game.play(position)) {
            notifyChange();
            game.playUi();
            notifyChange();
            return true;
        }
        return false;
    }

    public void reset(){
        game.reset();
        notifyChange();
    }
}
