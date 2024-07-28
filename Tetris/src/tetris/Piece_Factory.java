package tetris;
import java.util.Random;

public class Piece_Factory {
	private int[][][] pieces = {
			{{-2,-2,-2}, {-2,-1,-2}, {-1,-1,-1}},
			{{-2,-1,-1}, {-2,-2,-1}, {-2,-2,-1}},
			{{-1,-1,-2}, {-1,-2,-2}, {-1,-2,-2}},
			{{-2,-2,-2}, {-2,-1,-1}, {-2,-1,-1}},
			{{-2,-2,-2}, {-2,-1,-1}, {-1,-1,-2}},
			{{-2,-2,-2}, {-1,-1,-2}, {-2,-1,-1}}
	};
	
	Random rng = new Random();
	
	public int[][] Gen_piece() {
		int number = rng.nextInt(6);
		return pieces[number];
	}
}
