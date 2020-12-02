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
	
	
	private void addFrameAux(int fila, int col, char c, FrameGIF frame) {
		try {
			if(c == Board.NOTSEEN_SYMBOL) {
				frame.printSquare(col, fila, Color.LIGHT_GRAY);
			}
			
			if(c == Board.WATER_SYMBOL) {
				frame.printSquare(col, fila, Color.BLUE);
			}
			
			if(c == Board.HIT_SYMBOL) {
				frame.printSquare(col, fila, Color.RED);
			}
		}
		catch(BattleshipIOException e) {
			throw new RuntimeException();
		}
	}	
	
	
	private void printBoard(Board b, FrameGIF frame, boolean bType) {
		String cad = b.show(false);
		int tamBoard = b.show(false).length();
		
		if(!bType) {
			for(int i = 0;i < tamBoard; i++) {
				for(int j = 0;j < tamBoard; j++) {
					addFrameAux(i, j, cad.charAt(j), frame);
				}
			}
		}
		else {
			for(int i = 0;i < tamBoard; i++) {
				for(int j = tamBoard + 1;j <= tamBoard * 2;j++) {
					addFrameAux(j, i, cad.charAt(i), frame);
				}
			}
		}
	}
	
	
	private void añadeFranjas(FrameGIF frame) {
		Board b = game.getBoard1();
		int tam = b.show(false).length();
		
		
		for(int i = 0;i < tam;i++) {
			try {
				frame.printSquare(i, tam, Color.DARK_GRAY);
			} catch (BattleshipIOException e) {
				throw new RuntimeException();
			}
		}
		
	}
	
	
	public void show() {
		Board b1 = game.getBoard1();
		Board b2 = game.getBoard2();
		int tamFrame = b1.show(false).length();
		FrameGIF frame = new FrameGIF(tamFrame, tamFrame*2+1);
		
		if(b1 instanceof Board2D) {
			printBoard(b1, frame, false);
			añadeFranjas(frame);
			printBoard(b2, frame, true);
			
			try {
				agif.addFrame(frame);
			}
			catch(BattleshipIOException e) {
				throw new RuntimeException();
			}
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
