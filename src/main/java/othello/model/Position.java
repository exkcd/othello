package othello.model;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Can't be in Narnia
    public boolean isValid(int boardSize) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    // Diagonal, adjacent, etc.
    public Position offset(int rowOff, int colOff) {
        return new Position(row + rowOff, col + colOff);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

}
