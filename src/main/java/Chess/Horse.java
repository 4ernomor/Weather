package Chess;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line < 0 || line > 7 || column < 0 || column > 7 || toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7)
            return false;
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        int positionX = Math.abs(toLine - line);
        int positionY = Math.abs(toColumn - column);

        return (positionX == 1 && positionY == 2) || (positionY == 1 && positionX == 2);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

}
//ЕСТ своих