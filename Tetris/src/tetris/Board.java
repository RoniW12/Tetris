package tetris;

import java.util.Vector;

public class Board{
	public int[][] board;
	private Piece_Factory piece_gen;
	public final int BOARD_SIZE_X = 10;
	public final int BOARD_SIZE_Y = 50;
	public Boolean isMoveable = true;
	
	public Board(){
		board = new int[BOARD_SIZE_Y][BOARD_SIZE_X];
		

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j< BOARD_SIZE_X; j++) {
				board[i][j] = -2;
			}
		}
			
		board[0][4] = -1;
		board[1][3] = -1;
		board[1][4] = -1;
		board[1][5] = -1;
		piece_gen = new Piece_Factory();
	}
	
	public void board_copy(int[][] temp) {
		for(int i = 0; i < BOARD_SIZE_Y; i++) {
			for(int j = 0; j< BOARD_SIZE_X; j++) {
				temp[i][j] = board[i][j];
			}
		}
	}
	
	public void print_board() {
		for(int i = 0; i < BOARD_SIZE_Y; i++) {
			for(int j = 0; j< BOARD_SIZE_X; j++) {
				if(board[i][j] == -1) {
					System.out.print(" * ");
				}
				else if (board[i][j] == -2) {
					System.out.print("   ");
				}
				else if(board[i][j] == 0) {
					System.out.print("[ ]");
				}
				else if(board[i][j] == 1) {
					System.out.print("[*]");
				}
				else if(board[i][j] == 2) {
					System.out.print("[X]");
				}
			}
			System.out.println();
		}
		System.out.println("_________________________________");

	}
	
	public void piece_has_fallen() {
		for (int i = 0; i < BOARD_SIZE_Y; i++) {
			for(int k = 0; k < BOARD_SIZE_X; k++) {
				if(board[i][k] == 1) { 
					board[i][k] = 2;
					isMoveable = true;
				}
				
			}
		}
	}
	
	public boolean full_row(int i) {
		for(int k = 1; k < BOARD_SIZE_X-1; k++) {
			if(board[i][k] != 2) {
				return false;
			}
		}
		return true;
	}
	
	private void clear_row(int i) {
		for(int j = 0; j < BOARD_SIZE_X; j++) {
			board[i][j] = 0;
		}

	}
	
	public void move_down() {
		boolean piece_alive = false;
		boolean drop_flag = true;
		int[][] temp = new int[BOARD_SIZE_Y][BOARD_SIZE_X];
		
		for (int j = 0; j < BOARD_SIZE_X; j++) {
			if(board[BOARD_SIZE_Y-1][j] == 1) {
				drop_flag = false;
				this.piece_has_fallen();
				break;
			}
		}
		
		
		if(drop_flag) {
			this.board_copy(temp);
			for(int i = BOARD_SIZE_Y-2; i >= 0; i--) {
				for(int j = 0; j< BOARD_SIZE_X; j++) {
					//System.out.println(" "+i + " " + j);
					if(board[i][j] == 1 || board[i][j] == -1) {
						if(board[i+1][j] == 2) {
							board = temp;
							this.piece_has_fallen();
							drop_flag = false;
							break;
						}
						piece_alive = true;
						//System.out.println("Found");
						if(board[i][j] == 1) {
							board[i][j] = 0;
							board[i+1][j] = 1;
						}
						else if(board[i][j] == -1) {
							board[i][j] = -2;
							if(board[i+1][j] == 0) {
								board[i+1][j] = 1;
							}
							else {
								board[i+1][j] = -1;
								
							}
						}
						
					}
					
				}
				if(!drop_flag) break;
			}
		}
		
		for(int i = BOARD_SIZE_Y-1; i >= 0; i--) {
			if(full_row(i)) {
				System.out.println("**********Clear row************");
				piece_alive = true;
				drop_flag = true;
				isMoveable = false;
				clear_row(i);
				for(int k = 0; k <= i; k++) {
					for(int j = 0; j < BOARD_SIZE_X; j++) {
						if(board[k][j] == 2) board[k][j] = 1;
					}
							
				}
				break;
			}
		}
		
		if(!piece_alive) {
			int[][] piece = piece_gen.Gen_piece();

			for(int i = 0; i < 3; i++) {
				for(int j = 0; j< 3; j++) {
					board[i][j+2] = piece[i][j];
				}
			}
		}
	}
}