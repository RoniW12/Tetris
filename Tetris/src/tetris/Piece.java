package tetris;

import java.util.ArrayList;
import java.util.Vector;

public abstract class Piece {
	int[][] shape;
	int[] size = new int[2];
	Board board;

	protected Vector<Integer> pos;
	ArrayList<Vector<Integer>> location = new ArrayList<Vector<Integer>>();
	
	
	private final int X  = 1;
	private final int Y = 0;
	
	public void Piece() {
		
	}

	public void Piece(Board board) {
		this.board = board;
	}
	
	public void Get_Piece_Bound() {

		pos = new Vector<Integer>();
		pos.add(board.BOARD_SIZE_Y);
		pos.add(board.BOARD_SIZE_X-1);
		
		for(int i = 0; i < board.BOARD_SIZE_Y; i++) {
			for(int j = 1; j<board.BOARD_SIZE_X-1; j++) {
				if(board.board[i][j] == 1 || board.board[i][j] ==-1) {
					location.add(new Vector(i, j));
					if(i < pos.get(0)) pos.set(0, i);
					if(j < pos.get(1)) pos.set(1, j);
				}
			}
		}

	}
	
	public void Set_Shape(int[][] new_shape) {
		shape = new_shape;
		size[Y] = new_shape.length;
		size[X] = new_shape[0].length;
	}
	
	public void Rotate() {
		Get_Piece_Bound();
	}
	
	public void Set_Board(Board board)
	{
		this.board = board;
	}
}
