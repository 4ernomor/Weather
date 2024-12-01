package Chess;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line < 0 || line > 7 || column < 0 || column > 7 || toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }
            int positionX = Math.abs(toLine - line);
            int positionY = Math.abs(toColumn - column);

            if (positionX != positionY) {
                return false;
            }

        return chessBoard.isPathClear(line, column, toLine, toColumn);
    }


    @Override
    public String getSymbol() {
        return "B";
    }
}
//ЕСТ своих