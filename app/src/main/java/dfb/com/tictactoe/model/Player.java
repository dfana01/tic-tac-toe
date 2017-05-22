package dfb.com.tictactoe.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Dante on 5/19/2017.
 */

public class Player {

    private String mark;

    private Set<Move> moves;
    private int score;

    public Player(String mark) {
        this.mark = mark;
        score = 0;
        moves = new HashSet<Move>();
    }

    public Set<Move> getMoves() {
        return moves;
    }

    public void move(Move move){ moves.add(move); }

    public void resetMoves(){ moves.clear(); }

    public void resetScore(){ score = 0; }

    public void markPoint(){ score++; }

    public String getScore() { return String.valueOf(score); }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return mark.equals(player.mark);
    }

    @Override
    public int hashCode() {
        return mark.hashCode();
    }

    @Override
    public String toString() {
        return "Player{" +
                "mark='" + mark + '\'' +
                ", moves=" + moves +
                ", score=" + score +
                '}';
    }
}
