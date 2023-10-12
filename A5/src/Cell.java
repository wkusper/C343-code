public record Cell(int x, int y) {
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
