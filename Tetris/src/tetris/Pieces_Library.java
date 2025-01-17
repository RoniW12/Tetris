package tetris;


class T_Shape extends Piece{
	int orientation = 0;
	private static final int[][][] SHAPES = {
			{{-2,-2,-2},{-2,-1,-2},{-1,-1,-1}},
			{{-1,-2,-2},{-1,-1,-2},{-1,-2,-2}},
			{{-1,-1,-1},{-2,-1,-2},{-2,-2,-2}},
			{{-2,-2,-1},{-2,-1,-1},{-2,-2,-1}},
	};
	
	
	private static final int[][] INITIAL_SHAPE = SHAPES[0];
	
	public T_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		super.Rotate();
		switch(orientation) {
		case 0:
			pos.set(0, pos.get(0)-1);
			
			if(pos.get(0) < 0) {
				return;
			}
			break;
		case 3:
			pos.set(1, pos.get(1)-1);
			break;
		}
		
		orientation = (orientation + 1)%4;

		if(pos.get(0) >= 50) {
			return;
		}
		board.thread_lock.writeLock().lock();
		
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.println("(" + i + ", " + j + ")" + " + " + pos.toString());
				if(board.board[i+pos.get(0)][j+pos.get(1)] >= 0) {
					board.board[i+pos.get(0)][j+pos.get(1)] = SHAPES[orientation][i][j]+2;
				}
				else {
					board.board[i+pos.get(0)][j+pos.get(1)] = SHAPES[orientation][i][j];
				}
			}
		}
		board.thread_lock.writeLock().unlock();

	}
}

class r_Shape extends Piece{
	private static final int[][] INITIAL_SHAPE = {
	        {-1, -1, -2}, {-1, -2, -2}, {-1, -2, -2}
	    };
	public r_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		super.Rotate();
	}
}

class L_Shape extends Piece{
	private static final int[][] INITIAL_SHAPE = {
	        {-1, -2, -2}, {-1, -2, -2}, {-1, -1, -2}
	    };
	public L_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		super.Rotate();
	}
}

class I_Shape extends Piece{
	private static final int[][][] SHAPES = {
        {{-2,-2,-2,-2},
         {-1, -1, -1, -1},
         {-2,-2,-2,-2},
         {-2,-2,-2,-2}},
        
        {{-1,-2,-2,-2},
         {-1,-2,-2,-2},
         {-1,-2,-2,-2},
         {-1,-2,-2,-2}}
	};
	private static final int[][] INITIAL_SHAPE = SHAPES[0];
	public I_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		super.Rotate();
		
		for(int i = 0; i < 4; i++) {
			
		}
	}
}

class O_Shape extends Piece{
	private static final int[][] INITIAL_SHAPE = {
	        {-1, -1},{ -1, -1}
	    };
	public O_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		return;
	}
}

class Z_Shape extends Piece{
	private static final int[][] INITIAL_SHAPE = {
	        {-1, -1, -2},{-2, -1, -1}
	    };
	public Z_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		super.Rotate();
	}
}

class Reverse_Z_Shape extends Piece{
	private static final int[][] INITIAL_SHAPE = {
	        {-1, -1, -2},{-2, -1, -1}
	    };
	public Reverse_Z_Shape() {
		
		super.Set_Shape(INITIAL_SHAPE);
	}
	
	@Override
	public void Rotate() {
		
	}
}