package tetris;
import java.util.Random;

public class Piece_Factory {
	private Piece[] PIECE_LIST = {new T_Shape(), new r_Shape(), new L_Shape(), new I_Shape(), new O_Shape(), new Z_Shape(), new Reverse_Z_Shape()};
	Random rng = new Random();
	Board board;
	public Piece_Factory(Board board) {
		this.board = board;
		
		for(int i = 0; i < PIECE_LIST.length; i++) {
			PIECE_LIST[i].Set_Board(board);
		}
	}
	
	public Piece Gen_piece() {
		int number = rng.nextInt(PIECE_LIST.length - 1);
		//return PIECE_LIST[number];
		return PIECE_LIST[0];
	}
}
