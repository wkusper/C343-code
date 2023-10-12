public class Car {
    private final Cell position;
    private final int deltax;
    private final int deltay;

    Car (Cell position, int deltax, int deltay) {
        this.position = position;
        this.deltax = deltax;
        this.deltay = deltay;
    }

    Cell getPosition() {
        return position;
    }

    int getDeltax() {
        return deltax;
    }

    int getDeltay() {
        return deltay;
    }

    public String toString() {
        return String.format("Car at [%d,%d] moving at (%d,%d)",
                position.x(), position.y(), deltax, deltay);
    }

    public boolean equals(Object o) {
        if (o instanceof Car c) {
            return position.equals(c.position) &&
                    deltax == c.deltax &&
                    deltay == c.deltay;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return position.hashCode() + deltax + deltay;
    }
}
