package model.io;

import java.awt.Color;
import java.io.File;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

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
			
			agif.addFrame(frame);
			

		}
		catch(BattleshipIOException e) {
			throw new RuntimeException();
		}
		
	}	
	
	
	private void printBoard(Board b, FrameGIF frame) {
		String cadB1 = b.show(false);
		
		for(int i = 0;i < cadB1.length(); i++) {
			for(int j = 0;j < b.getSize(); j++) {
				addFrameAux(i, j, cadB1.charAt(i), frame);
			}
		}
	}
	

	
	
	public void show() {
		FrameGIF frame = new FrameGIF(5, 5*2+1);
		Board b1 = game.getBoard1();
		Board b2 = game.getBoard2();
		
		printBoard(b1, frame);
		printBoard(b2, frame);
		
		
	}
	

	public void close() {
		try {
			agif.saveFile(new File("/home/alu/git/prog3-48768206/prog3-battleship/files/output.gif"));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
