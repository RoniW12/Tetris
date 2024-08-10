package tetris;
import java.util.Random;
import java.lang.reflect.Constructor;

public class Piece_Factory {
	Piece_Generator reflector = new Piece_Generator();
	
	private String[] PIECE_LIST = {"T_Shape", "r_Shape", "L_Shape", "I_Shape",
			"O_Shape", "Z_Shape", "Reverse_Z_Shape"};
	Random rng = new Random();
	Board board;
	public Piece_Factory(Board board) {
		this.board = board;
		
	}
	
	public Piece Gen_piece() {
		int number = rng.nextInt(PIECE_LIST.length - 1);
		try {

			//return reflector.Generate(PIECE_LIST[number]);
			Piece temp = reflector.Generate(PIECE_LIST[number%3]);
			temp.Set_Board(board);
			return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
class Piece_Generator {
    public Piece Generate(String shape) throws Exception {
        // Assuming you have a class named T_Shape
        Class<?> shapeClass = Class.forName("tetris."+shape); // Replace with the actual package

        // Get a default constructor
        Constructor<?> constructor = shapeClass.getDeclaredConstructor();

        // Create a new instance
        return (Piece)constructor.newInstance();

    }
}