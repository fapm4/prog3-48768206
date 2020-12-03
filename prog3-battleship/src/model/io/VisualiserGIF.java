package model.io;

import java.awt.Color;
import java.io.File;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;
import model.ship.Board2D;

public class VisualiserGIF implements IVisualiser{

	private Game game;
	private AnimatedGIF agif;

	
	public VisualiserGIF(Game game) {
		
		if(game == null) {
			throw new NullPointerException();
		}
		
		this.game = game;
		agif = new AnimatedGIF();
	}
	
	
	private void addFranjas(FrameGIF frame) {
		Board b = game.getBoard1();
		int tam = b.getSize();
		
		
		for(int i = 0;i < tam;i++) {
			try {
				frame.printSquare(i, tam, Color.DARK_GRAY);
			} catch (BattleshipIOException e) {
				throw new RuntimeException();
			}
		}
		
	}
	
	
	private void auxAddFrame(int i, int j, char c, FrameGIF frame) {
		
		try {
			if(c == Board.NOTSEEN_SYMBOL) {
				frame.printSquare(j, i, Color.LIGHT_GRAY);
			}
			
			if(c == Board.WATER_SYMBOL) {
				frame.printSquare(j, i, Color.BLUE);
			}
			
			if(c == Board.HIT_SYMBOL) {
				frame.printSquare(j, i, Color.RED);
			}
			
			if(c == Board.Board_SEPARATOR) {
				frame.printSquare(j, i, Color.ORANGE);
			}
		}
		catch (BattleshipIOException e) {
			throw new RuntimeException();
		}
	}
	
	private void printBoard(Board b, FrameGIF frame, boolean typeBoard) {
		int tamBoard = b.getSize();
		String cadBoard = b.show(false);
		
		
		// Board 1
		if(typeBoard) {
			for(int i = 0; i < tamBoard;i++) {
				for(int j = 0;j < tamBoard;j++) {
					//System.out.println(i + " " + j);
					auxAddFrame(j, i, cadBoard.charAt(j), frame);
				}
			}
		}
		
		// Board 2
		else {
			for(int i = 0;i < tamBoard;i++) {
				for(int j = tamBoard + 1;j <= tamBoard * 2;j++) {
					auxAddFrame(j, i, cadBoard.charAt(i), frame);
				}
			}
		}
	}
	
	
	public void show() {
		Board b1 = game.getBoard1();
		Board b2 = game.getBoard2();
		int tamFrame = b1.getSize();
		FrameGIF frame = new FrameGIF(tamFrame, tamFrame*2+1);
		
		if(b1 instanceof Board2D) {
			printBoard(b1, frame, true);
			addFranjas(frame);
			printBoard(b2, frame, false);
		}
		
		else {
			
		}
		
		try {
			agif.addFrame(frame);
		}catch(BattleshipIOException e) {
			throw new RuntimeException();
		}
		
	}
	

	public void close() {
		try {
			agif.saveFile(new File("/home/alu/git/prog3-48768206/prog3-battleship/files/output.gif"));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
