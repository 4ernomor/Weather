package Chess;

public class Rook extends ChessPiece {
    public boolean hadMoving = false;

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line < 0 || line > 7 || column < 0 || column > 7 || toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            boolean check = false;
            return false;
        }
        int positionX = Math.abs(toLine - line);
        int positionY = Math.abs(toColumn - column);
        if (positionX == 0 && positionY > 0) {
            hadMoving = true;
            return true;
        }
        if (positionX > 0 && positionY == 0) {
            hadMoving = true;
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

