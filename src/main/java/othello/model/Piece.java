package main.java.othello.model;

public enum Piece {
    BLACK,
    WHITE,
    EMPTY;

    public Piece opponent() {
        return this == BLACK ? WHITE : this == WHITE ? BLACK : EMPTY;
    }
}
