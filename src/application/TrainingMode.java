package application;

import java.security.InvalidParameterException;
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
		
		Color pov;
		System.out.print("From which point of view would you like to train Black or White (b/w)? ");
		char option = sc.next().charAt(0);
		sc.nextLine();
		if (option == 'b')
			pov = Color.BLACK;
		else
			pov = Color.WHITE;
		UI.clearScreen();
		

		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, pov);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves, pov);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
	
				chessMatch.peformChessMove(source, target);
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Enter the piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
				
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InvalidParameterException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch);
		sc.close();
	

	}

}
