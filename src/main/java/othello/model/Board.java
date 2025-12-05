package othello.model;

public class Board {
    public static final int SIZE = 8; // 8x8 board, that's most othello boards
    private static final int[][] DIRECTIONS = { // where the pieces can go
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };
    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[SIZE][SIZE];
        initBoard();
    }

    public Board(Board other) {
        this.grid = new Piece[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(other.grid[i], 0, this.grid[i], 0, SIZE);
        }
    }

    private void initBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = Piece.EMPTY;
            }
        }
        grid[3][3] = Piece.WHITE;
        grid[3][4] = Piece.BLACK;
        grid[4][3] = Piece.BLACK;
        grid[4][4] = Piece.WHITE;


    }

    public int getSize() {
        return SIZE;
    }

    public Piece getPiece(Position pos) {
        if (!pos.isValid(SIZE)) {
            return Piece.EMPTY;
        }
        return grid[pos.getRow()][pos.getCol()];
    }

    public void setPiece(Position pos, Piece piece) {
        if (pos.isValid(SIZE)) {
            grid[pos.getRow()][pos.getCol()] = piece;
        }
    }

    public boolean isValidMove(Position pos, Piece piece) {
        if (!pos.isValid(SIZE) || getPiece(pos) != Piece.EMPTY) {
            return false;
        }

        for (int[] dir : DIRECTIONS) {
            if (wouldFlipInDirection(pos, piece, dir[0], dir[1])) {
                flipInDirection(pos, piece, dir[0], dir[1]);
            }
        }
        return true;
    }

    private boolean wouldFlipInDirection(Position pos, Piece piece, int rowOff, int colOff) {
        Position current = pos.offset(rowOff, colOff);
        boolean foundOpposite = false;

        while (current.isValid(SIZE)) {
            Piece currentPiece = getPiece(current);

            if (currentPiece == Piece.EMPTY) {
                return false;
            }
            if (currentPiece == piece.opponent()) {
                foundOpposite = true;
                current = current.offset(rowOff, colOff);
            } else if (currentPiece == piece) {
                return foundOpposite;
            }
        }
        return false;
    }

    private void flipInDirection(Position pos, Piece piece, int rowOff, int colOff) {
        Position current = pos.offset(rowOff, colOff);

        while (current.isValid(SIZE) && getPiece(current) == piece.opponent()) {
            setPiece(current, piece);
            current = current.offset(rowOff, colOff);
        }
    }

    public int countPieces(Piece piece) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == piece) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isFull() {
        return countPieces(Piece.EMPTY) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  0 1 2 3 4 5 6 7\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < SIZE; j++) {
                sb.append(grid[i][j].getSymbol()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
