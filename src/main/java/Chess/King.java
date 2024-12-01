package Chess;

public class King extends ChessPiece {
    public boolean hadMoving = false;

    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line < 0 || line > 7 || column < 0 || column > 7 || toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            hadMoving = true;
            return false;
        }
        if (line == toLine && column == toColumn) return false;

        int positionX = Math.abs(toLine - line);
        int positionY = Math.abs(toColumn - column);
        if (positionX <= 1 || positionY <= 1) {
            hadMoving = true;
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        if (line < 0 || line > 7 || column < 0 || column > 7) {
            return false;
        }

            for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece != null && !piece.getColor().equals(this.getColor())) {
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


}
