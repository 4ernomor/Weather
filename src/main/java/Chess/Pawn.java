package Chess;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line < 0 || line > 7 || column < 0 || column > 7 || toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7)
            return false;

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor()))
            return false;
        int direction = this.getColor().equals("White") ? 1 : - 1;
        if (toLine == line + direction && (toColumn == column + 1 || toColumn == column - 1)) {
            return targetPiece != null && !targetPiece.getColor().equals(this.getColor()); // взятие фигуры
        }


        if (color.equals("White")) {
            if (line == 1 && toLine == 3 && column == toColumn && chessBoard.board[2][column] == null)
                return true;
            return toLine == line + 1 && column == toColumn && chessBoard.board[toLine][toColumn] == null;
        } else {
            if (line == 6 && toLine == 4 && column == toColumn && chessBoard.board[5][column] == null)
                return true;
            return toLine == line - 1 && column == toColumn && chessBoard.board[toLine][toColumn] == null;
        }

    }
        @Override
        public String getSymbol () {
            return "P";
        }
    }
