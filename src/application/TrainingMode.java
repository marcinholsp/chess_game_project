package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;
import chess.Color;

public class TrainingMode {

	public static void main(String[] args) {


		ChessMatch chessMatch = new ChessMatch();
		Scanner sc = new Scanner(System.in);
		

		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, Color.WHITE);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves, Color.WHITE);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
	
				chessMatch.peformChessMove(source, target);
				
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch);
		sc.close();
	

	}

}