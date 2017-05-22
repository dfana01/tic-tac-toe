package dfb.com.tictactoe.model;


/**
 * Created by Dante on 5/19/2017.
 */

public class Move {

    private String mark;
    private int position;

    public Move(int position, String mark) {
        this.position = position;
        this.mark = mark;
    }

    public Move(int position) {
        this.position = position;
        this.mark = "";
    }

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }

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

        Move move = (Move) o;

        return position == move.position;
    }

    @Override
    public int hashCode() {
        return 31 * position;
    }

    @Override
    public String toString() {
        return "Move{" +
                "position=" + position +
                ", mark=" + mark +
                '}';
    }

}
