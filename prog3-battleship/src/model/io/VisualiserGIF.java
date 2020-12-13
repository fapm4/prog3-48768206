package model.io;

import java.awt.Color;
import java.io.File;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;
import model.ship.Board2D;
import model.aircraft.Board3D;

// TODO: Auto-generated Javadoc
/**
 * The Class VisualiserGIF.
 * @author Francisco Alejandro Pérez Meneses - 48768206H
 */
public class VisualiserGIF implements IVisualiser{

	/** The game. */
	private Game game;
	
	/** The agif. */
	private AnimatedGIF agif;

	
	/**
	 * Instantiates a new visualiser GIF.
	 *
	 * @param game the game
	 */
	public VisualiserGIF(Game game) {
		
		if(game == null) {
			throw new NullPointerException();
		}
		
		this.game = game;
		agif = new AnimatedGIF();
	}
	
	
	/**
	 * Adds the franjas 2 D.
	 *
	 * @param frame the frame
	 */
	private void addFranjas2D(FrameGIF frame) {
		int tamFrame = game.getBoard1().getSize();
		
		for(int i = 0;i < tamFrame;i++) {
			try {
				frame.printSquare(i, tamFrame, Color.DARK_GRAY);
			} catch (BattleshipIOException e) {
				throw new RuntimeException();
			}
		}
		
	}
	
	
	/**
	 * Adds the franjas 3 D.
	 *
	 * @param frame the frame
	 */
	private void addFranjas3D(FrameGIF frame) {
		int tamFrame = game.getBoard1().getSize();
		
		for(int i = 0;i < (tamFrame * tamFrame) + (tamFrame - 1);i++) {
			try {
				frame.printSquare(i, tamFrame, Color.DARK_GRAY);
			} catch (BattleshipIOException e) {
				throw new RuntimeException();
			}
		}
		
	}
	
	
	/**
	 * Aux add frame.
	 *
	 * @param j the j
	 * @param i the i
	 * @param c the c
	 * @param frame the frame
	 */
	private void auxAddFrame(int j, int i, char c, FrameGIF frame) {
		
		try {
			if(c == Board.WATER_SYMBOL) {
				frame.printSquare(j, i, Color.BLUE);
			}
			
			if(c == Board.HIT_SYMBOL) {
				frame.printSquare(j, i, Color.RED);
			}
			
			if(c == Board.Board_SEPARATOR) {
				frame.printSquare(j, i, Color.ORANGE);
			}
			
			if(c == Board.NOTSEEN_SYMBOL) {
				frame.printSquare(j, i, Color.LIGHT_GRAY);
			}
			
			else if(c != Board.Board_SEPARATOR && c != Board.HIT_SYMBOL && c != Board.NOTSEEN_SYMBOL && c != Board.WATER_SYMBOL) {
				frame.printSquare(j, i, Color.RED);
			}
			
		}
		catch (BattleshipIOException e) {
			throw new RuntimeException();
		}
	}
	
	
	/**
	 * Prints the board 3 D.
	 *
	 * @param frame the frame
	 * @param b the b
	 * @param typeBoard the type board
	 */
	private void printBoard3D(FrameGIF frame, Board b, boolean typeBoard) {
		String cadBoard = b.show(false);
		int tamFrame = game.getBoard1().getSize();
		int pos = 0, contS = 0;
		int tamFrameW = (tamFrame * tamFrame) + (tamFrame - 1);
		
		if(typeBoard) {
			for(int i = 0; i < tamFrame; i++) {
				for(int j = 0; j < tamFrameW; j ++) {
						pos = (tamFrameW * i) + j + contS;
						auxAddFrame(j, i, cadBoard.charAt(pos), frame);
				}
				contS++;
			}
		}
		
		else {
			for(int i = tamFrame + 1; i <= tamFrame * 2; i++) {
				for(int j = 0; j < tamFrameW; j ++) {
					 pos = (tamFrameW * (i - (tamFrame + 1))) + j + contS;
					auxAddFrame(j, i, cadBoard.charAt(pos), frame);
				}
				contS++;
			}
		}
	}
	
	
	/**
	 * Prints the board 2 D.
	 *
	 * @param frame the frame
	 * @param b the b
	 * @param typeBoard the type board
	 */
	private void printBoard2D(FrameGIF frame, Board b, boolean typeBoard) {
		String cadBoard = b.show(false);
		int tamFrame = game.getBoard1().getSize();
		int contS = 0, pos = 0;
		
		if(typeBoard) {
			if(typeBoard) {
				for(int i = 0; i < tamFrame; i++) {
					for(int j = 0; j < tamFrame; j ++) {
							pos = (tamFrame * i) + j + contS;
							auxAddFrame(j, i, cadBoard.charAt(pos), frame);
					}
					contS++;
				}
			}
		}
		else {
			for(int i = tamFrame + 1; i <= tamFrame * 2; i++) {
				for(int j = 0; j < tamFrame; j ++) {
					 pos = (tamFrame * (i - (tamFrame + 1))) + j + contS;
					auxAddFrame(j, i, cadBoard.charAt(pos), frame);
				}
				contS++;
			}
		}
	}
	
	
	/**
	 * Show.
	 */
	public void show() {
		Board b1 = game.getBoard1();
		Board b2 = game.getBoard2();
		FrameGIF frame = null;
		int tamFrame = b1.getSize();
		int tamFrameH = tamFrame * 2 + 1;
		int tamFrameW = (tamFrame * tamFrame) + (tamFrame - 1);
	
		
		if(b1 instanceof Board3D) {
			frame = new FrameGIF(tamFrameW, tamFrameH);
			printBoard3D(frame, b1, true);
			addFranjas3D(frame);
			printBoard3D(frame, b2, false);
		}
		else if(b1 instanceof Board2D) {
			frame = new FrameGIF(tamFrame, tamFrameH);
			printBoard2D(frame, b1, true);
			addFranjas2D(frame);
			printBoard2D(frame, b2, false);
		}
		
		try {
			agif.addFrame(frame);
		}catch(BattleshipIOException e) {
			throw new RuntimeException();
		}
		
	}
	

	/**
	 * Close.
	 */
	public void close() {
		try {
			agif.saveFile(new File("files/output.gif"));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
