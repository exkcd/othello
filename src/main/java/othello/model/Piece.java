package othello.model;

public enum Piece {
    BLACK('B'),
    WHITE('W'),
    EMPTY(' ');

    private final char symbol;

    Piece(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Piece opponent() {
        return this == BLACK ? WHITE : this == WHITE ? BLACK : EMPTY;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
