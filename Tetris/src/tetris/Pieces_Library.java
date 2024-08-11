package tetris;


class T_Shape extends Piece{
	
	public T_Shape() {

		int[][][] _shapes = {
				{{-2,-1,-2},{-1,-1,-1},{-2,-2,-2}},
				{{-1,-2,-2},{-1,-1,-2},{-1,-2,-2}},
				{{-2,-2,-2},{-1,-1,-1},{-2,-1,-2}},
				{{-2,-2,-1},{-2,-1,-1},{-2,-2,-1}},
		};
		super.SHAPES = _shapes;
		super.Set_Shape(SHAPES[0]);
	}
	
	@Override
	public void Rotate() {
		int[][] temp = new int[board.BOARD_SIZE_Y][board.BOARD_SIZE_X];
		int curr_orientation = orientation;
		board.board_copy(temp);
		try {
			super.Rotate();
			switch(orientation) {
			case 2:
				pos.set(0, pos.get(0)-1);
				
				if(pos.get(0) < 0) {
					board.thread_lock.writeLock().unlock();
					return;
				}
				break;
			case 3:
				pos.set(1, pos.get(1)-1);
				break;
			}
	
			if(pos.get(0)+3 > 49 || super.Check_Collision()) {
				return;
			}
			
			//board.thread_lock.writeLock().lock();

			orientation = (orientation + 1)%4;
			super.Implement_Rotation();
			//board.thread_lock.writeLock().unlock();
	
		}
		catch(Exception e) {
			System.out.println("******Could Not Rotate********");
			board.board = temp;
			orientation = curr_orientation;
		}
	}
}

class r_Shape extends Piece{
	
	public r_Shape() {
		int[][][] _shapes = {
				{{-2, -1, -1}, {-2, -1, -2}, {-2, -1, -2}},
				{{-2, -2, -2}, {-1, -1, -1}, {-2, -2, -1}},
				{{-2, -1, -2}, {-2, -1, -2}, {-1, -1, -2}},
				{{-1, -2, -2}, {-1, -1, -1}, {-2, -2, -2}}
				
		};
		
		super.SHAPES = _shapes;
		super.Set_Shape(SHAPES[0]);
	}
	
	@Override
	public void Rotate() {
		int[][] temp = new int[board.BOARD_SIZE_Y][board.BOARD_SIZE_X];
		int curr_orientation = orientation;
		board.board_copy(temp);
		try {
			super.Rotate();
			
			switch(orientation) {
	
			case 0:
				pos.set(1, pos.get(1)-1);
				break;
			
			case 1:
				pos.set(0, pos.get(0)-1);
				
				if(pos.get(0) < 0) {
					return;
				}
				break;
			}
			
			
			if(pos.get(0)+3 > 49 || super.Check_Collision()) {
				return;
			}
			orientation = (orientation + 1)%4;

			//board.thread_lock.writeLock().lock();
			
			super.Implement_Rotation();
			//board.thread_lock.writeLock().unlock();
	
		}
	
		catch(Exception e) {
			System.out.println("******Could Not Rotate********");
			board.board = temp;
			orientation = curr_orientation;
		}
	}
}

class L_Shape extends Piece{
	public L_Shape() {
		
		int[][][] _shapes = {
				{{-1, -1, -2}, {-2, -1, -2}, {-2, -1, -2}},
				{{-2, -2, -1}, {-1, -1, -1}, {-2, -2, -2}},
				{{-2, -1, -2}, {-2, -1, -2}, {-2, -1, -1}},
				{{-2, -2, -2}, {-1, -1, -1}, {-1, -2, -2}}
				
		};
		super.SHAPES = _shapes;
		super.Set_Shape(SHAPES[0]);
	}
	
	@Override
	public void Rotate() {
		int[][] temp = new int[board.BOARD_SIZE_Y][board.BOARD_SIZE_X];
		int curr_orientation = orientation;
		board.board_copy(temp);
		try {
			super.Rotate();
			
			switch(orientation) {
	
			case 2:
				pos.set(1, pos.get(1)-1);
				break;
			
			case 3:
				pos.set(0, pos.get(0)-1);
				
				if(pos.get(0) < 0) {
					return;
				}
				break;
			}
			
			orientation = (orientation + 1)%4;
			
			if(pos.get(0)+3 > 49 || super.Check_Collision()) {
				return;
			}
			//board.thread_lock.writeLock().lock();
			
			super.Implement_Rotation();
			//	board.thread_lock.writeLock().unlock();
	
		}

		catch(Exception e) {
			System.out.println("******Could Not Rotate********");
			board.board = temp;
			orientation = curr_orientation;
		}
	}
}


class I_Shape extends Piece{
	public I_Shape() {
		int[][][]  _shapes = {
	        {{-2,-2,-2,-2},
	         {-1, -1, -1, -1},
	         {-2,-2,-2,-2},
	         {-2,-2,-2,-2}},
	        
	        {{-2,-1,-2,-2},
	         {-2,-1,-2,-2},
	         {-2,-1,-2,-2},
	         {-2,-1,-2,-2}}
		};
		
		super.SHAPES = _shapes;
		super.orientation = 0;
		super.Set_Shape(SHAPES[0]);
	}
	
	@Override
	public void Rotate() {
		int[][] temp = new int[board.BOARD_SIZE_Y][board.BOARD_SIZE_X];
		int curr_orientation = orientation;
		board.board_copy(temp);
		try {
			super.Rotate();
			pos.set(orientation, pos.get(orientation)-1);
			orientation = (orientation + 1)%2;
			
			super.Implement_Rotation();
			
		}
		catch(Exception e) {
			System.out.println("******Could Not Rotate********");
			board.board = temp;
			orientation = curr_orientation;
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
	public Z_Shape() {
		int[][][] _shapes = {
		        {{-2,-2,-2},{-1, -1, -2},{-2, -1, -1}},
		        {{-1,-2,-2},{-1, -1, -2},{-2, -1, -2}}
		    };
		super.SHAPES = _shapes;
		super.Set_Shape(SHAPES[0]);
	}
	
	@Override
	public void Rotate() {
		int[][] temp = new int[board.BOARD_SIZE_Y][board.BOARD_SIZE_X];
		int curr_orientation = orientation;
		board.board_copy(temp);
		try {
			super.Rotate();
			orientation = (orientation + 1)%2;
			pos.set(0, pos.get(0)-orientation);
			
			super.Implement_Rotation();
			
		}
		catch(Exception e) {
			System.out.println("******Could Not Rotate********");
			board.board = temp;
			orientation = curr_orientation;
		}
	}
}

class Reverse_Z_Shape extends Piece{
	public Reverse_Z_Shape() {
		int[][][] _shapes = {
		        {{-2,-2,-2},{-2, -1, -1},{-1, -1, -2}},
		        {{-2,-2,-1},{-2, -1, -1},{-2, -1, -2}}
		    };
		super.SHAPES = _shapes;
		super.Set_Shape(SHAPES[0]);
	}
	
	@Override
	public void Rotate() {
		int[][] temp = new int[board.BOARD_SIZE_Y][board.BOARD_SIZE_X];
		int curr_orientation = orientation;
		board.board_copy(temp);
		try {
			super.Rotate();
			pos.set(orientation, pos.get(orientation)-1);
			orientation = (orientation + 1)%2;
			
			super.Implement_Rotation();
			
		}
		catch(Exception e) {
			System.out.println("******Could Not Rotate********");
			board.board = temp;
			orientation = curr_orientation;
		}
	}
}