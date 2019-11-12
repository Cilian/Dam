/*
import java.util.ArrayList;

public class GitCheckers {

    */
/**
     * contains the board with the ability to modify it through a few moves.
     *//*

    public class CheckersBoard {

        private String movesMade = "";

        private boolean player1 = true;

        private CheckersPiece[][] checkersBoard;

        private ArrayList<CheckersPiece> player1Pieces, player2Pieces;


        private int winner;



        */
/**
         * gets moves available at that location given a color NOT including the board.
         *
         * @param row
         *         row to look at
         * @param col
         *         column to look at
         * @param player1
         *         color of the piece there
         * @return a list of moves a piece of that color and that place could go
         *//*

        // Udvid med ikke at tilføje umulige moves, og tilføj jumps
        public MovesList getMoves(int row, int col, boolean player1, boolean isKing) {
            MovesList nextMoves = new MovesList();
            if (player1 || isKing) {
                int moveX = row+1;
                int moveY = col-1;
                if(moveX < 8 && moveX >= 0 &&)
                nextMoves.add(row + 1, col - 1);
                nextMoves.add(row + 1, col + 1);
            }
            if (!player1 || isKing) {
                nextMoves.add(row - 1, col + 1);
                nextMoves.add(row - 1, col - 1);
            }
            return nextMoves;
        }


        public final ArrayList<CheckersPiece> getPlayer1Pieces() {
            return player1Pieces;
        }

        public ArrayList<CheckersPiece> getPlayer2Pieces() {
            return player2Pieces;
        }



        public final ArrayList<Coordinate> getOneJumps(final CheckersPiece piece, final MovesList availableMoves) {
            MovesList newList = new MovesList();
            for (Coordinate coordinate : availableMoves) {
                CheckersPiece tempPiece = getPiece(coordinate);
                if (tempPiece != null && tempPiece.getIsPlayer1() != piece.getIsPlayer1()) {
                    final int newCol = piece.getCol() + (tempPiece.getCol() - piece.getCol()) * 2;
                    final int newRow = piece.getRow() + (tempPiece.getRow() - piece.getRow()) * 2;
                    final CheckersPiece newPiece;
                    try {
                        newPiece = getPiece(newRow, newCol);
                        if (Config.DEBUG) {
                            System.out.println(newRow + ", " + newCol);
                        }
                        if (newPiece == null) {
                            newList.add(newRow, newCol);
                        } else {
                            if (Config.DEBUG) {
                                System.out.println("THERES SOMETHING THERE: " + newPiece);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        if (Config.DEBUG) {
                            System.err.println(newRow + ", " + newCol + " tried to jump there");
                        }
                    }
                }
            }
            return newList.list;
        }


        */
/**
         * checks to see if anyone won
         *
         * @return 0  if the game is still going on. 1 if player 1 won, -1 if player 2 won
         *//*

        public final int checkWin() {
            boolean player1 = false, player2 = false;
            if (winner == 0) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (checkersBoard[i][j] != null) {
                            if (checkersBoard[i][j].getIsPlayer1()) {
                                player1 = true;
                            } else {
                                player2 = true;
                            }
                            if (player1 && player2) {
                                return 0;
                            }
                        }
                    }
                }
                winner = player1 ? 1 : -1;
            }
            return winner;
        }

        */
/**
         * a modified arraylist that only accepts coordinates that are in the boards range.
         *//*

        public static class MovesList implements Iterable<Coordinate> {

            private ArrayList<Coordinate> list = new ArrayList<>();

            */
/**
             * creates new coordiante at x,y and adds it
             *
             * @param x
             *         x of the location to add
             * @param y
             *         y of the location to add
             *//*

            public final void add(int x, int y) {
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    list.add(new Coordinate(x, y));
                }
            }
        }

        */
/**
         * a move contains a piece to be moved and a cell to move the piece to
         *//*

        public static final class Move {

            private CheckersPiece piece;

            private Coordinate move;

            */
/**
             * @param piece
             *         piece to move
             * @param move
             *         cell to move it to
             *//*

            public Move(final CheckersPiece piece, final Coordinate move) {

                this.piece = piece;
                this.move = move;
            }

            public CheckersPiece getPiece() {
                return piece;
            }

            public Coordinate getEndCell() {
                return move;
            }

            @Override
            public boolean equals(final Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Move)) {
                    return false;
                }

                final Move move1 = (Move) o;

                if (getPiece() != null ? !getPiece().equals(move1.getPiece()) : move1.getPiece() != null) {
                    return false;
                }
                return !(move != null ? !move.equals(move1.move) : move1.move != null);

            }

            public final String toString() {
                return piece + " to " + getEndCell();

            }
        }
    }


}
*/
