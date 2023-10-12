package exceptions;

public class OutsideGridException extends Exception {
    final int x;
    final int y;
    public OutsideGridException (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("exceptions.OutsideGridException: (x,y) = (%d,%d)", x, y);
    }
}
