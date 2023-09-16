package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;
import chess.chess.pieces.*;

public class ChessMatch {
    private Board board;
    public ChessMatch(){
        board = new Board(8,8);
        initialSetup();
    }
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i=0; i< board.getRows(); i++){
            for(int j=0; j< board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(board, Color.WHITE)); //ROOK
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE)); //ROOKS
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        placeNewPiece('e', 1, new King(board, Color.WHITE)); //KING
        placeNewPiece('e', 8, new King(board, Color.BLACK));

        placeNewPiece('d', 1, new Queen(board, Color.WHITE)); //QUEEN
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));

        placeNewPiece('c', 1, new Bishop(board, Color.WHITE)); //BISHOPS
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));

        placeNewPiece('b', 1, new Knight(board, Color.WHITE)); //KNIGHTS
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
    }

    private void validateSourcePosition(Position position){
        if(board.piece(position)==null) {
            throw new BoardException("There is no piece on source position");
        }
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece)capturedPiece;
    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
        }
