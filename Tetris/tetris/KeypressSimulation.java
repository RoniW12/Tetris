package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeypressSimulation {
	Board board;
	int BOARD_X, BOARD_Y;
	public KeypressSimulation(Board board) {
		this.board = board;
		BOARD_X = board.BOARD_SIZE_X;
		BOARD_Y = board.BOARD_SIZE_Y;
		
	}
    public void run() {
        JFrame frame = new JFrame("Press the button");
        JPanel panel = new JPanel();
        JButton button = new JButton("Left");
        JButton button_2 = new JButton("Right");
        JButton button_3 = new JButton("Down");

        JButton button_R = new JButton("Rotate");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(board.isMoveable) {
	            	boolean collision = false;
	            	int [][] temp = new int[BOARD_Y][BOARD_X];
	            	board.board_copy(temp);

            		board.current_piece.Get_Piece_Bound();
            		if(board.current_piece.pos.get(1) == 0) return;
            		
	            	for(int j = 1; j < BOARD_X; j++) {
	            		
	            		if(collision) {
	            			break;
	            		}
	    				for(int i = BOARD_Y-1; i >=0; i--) {
	/*    					if(board.board[i][0] == 1 || board.board[i][0] == -1) {
								System.out.println("*********WALL***********");
	    						board.board = temp;
	    						collision = true;
	    						break;
	    					}*/
	    					//System.out.println(" "+i + " " + j);
	    					if(board.board[i][j] == 1|| board.board[i][j] == -1) {
	    							if(board.board[i][j-1] != 2) {
		    						//System.out.println("Found");
	    	    					board.board[i][j-1] = board.board[i][j];
		    						board.board[i][j] += -1;
	    							}
	    							else{
		        						collision = true;
		        						board.board = temp;
		        						break;
	        					}
	    					}
	    					
	    				}
	    			}
	            	board.print_board();
            	}
            }
        });
        
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(board.isMoveable) {
	            	board.move_down();
	            	board.print_board();
            	}
            }
        });
        
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(board.isMoveable) {
	            	int [][] temp = new int[BOARD_Y][BOARD_X];
	            	board.board_copy(temp);
	            	boolean collision = false;
	            	
	            	board.current_piece.Get_Piece_Bound();
	            	System.out.println(board.current_piece.pos.get(1) +"   "+ (BOARD_X-board.current_piece.size[1]));
            		if(board.current_piece.pos.get(1) == BOARD_X-board.current_piece.size[1]) return;
            		
					for(int j = BOARD_X-2; j >= 0; j--) {
	
	            		if(collision) {
	            			break;
	            		}
						for(int i = 0; i < BOARD_Y; i++) {
							/*if(board.board[i][BOARD_X-1] == 1 || board.board[i][BOARD_X-1] == -1) {
								System.out.println("*********WALL***********");
	    						board.board = temp;
	    						collision = true;
	    						break;
	    					}*/
	    					//System.out.println(" "+i + " " + j);
	    					if(board.board[i][j] == 1 || board.board[i][j] == -1) {
	    						if(board.board[i][j+1] != 2) {
	    						//System.out.println("Found");
	        						board.board[i][j+1] = board.board[i][j];
	        						board.board[i][j] += -1;
	    						}
	    						else{
	        						System.out.println("\nCollision*******");
	        						collision = true;
	        						board.board = temp;
	        						break;
	        					}
	    					}
	
	    					
	    				}
	    			}
	            	board.print_board();
            	}
            }
        });
        
        button_R.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		board.current_piece.Rotate();
        		board.print_board();
        	}
        });
        
        panel.add(button);
        panel.add(button_2);
        panel.add(button_3);
        panel.add(button_R);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
