package dfb.com.tictactoe.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dante on 5/19/2017.
 */

public class Board {

    private Move []plays = {null,null,null,
                            null,null,null,
                            null,null,null};

    public Board(){
    }

    public boolean move(Move move){
        if (plays[move.getPosition()] != null) return false;
        plays[move.getPosition()] = move;
        return true;
    }

    public Move[] getPlays(){
        return plays;
    }

    public void reset (){
        int size = plays.length;
        for(int i = 0; i < size; i++){
            plays[i] = null;
        }
    }

    public boolean isFull(){
        for (Move play : plays) {
            if (play == null) return false;
        }
        return true;
    }
}
