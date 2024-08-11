package tetris;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import screens.GameScreen;
import screens.TetrisOpeningScreen;


public class GameManager{	
    TimerTask gameTask;
	boolean isRunning = false;
	private Board board;
	private GameScreen gameScreen;

	
	public GameManager() {
    	gameScreen = new GameScreen();
		this.board = new Board();
    	gameScreen.addWindowListener(new WindowAdapter() {
      	  public void windowClosing(WindowEvent e) {
      		  stopGame();
    		  TetrisOpeningScreen.openScreen();
    	  }
    	});
		gameTask = new Helper();
		KeyPressHandler handler = new KeyPressHandler(board, gameScreen); 
    	
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        gameScreen.add(panel);
        // Bind actions to key strokes
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();
        

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "spaceAction");
        

        actionMap.put("moveLeft", handler.left);
        actionMap.put("moveRight", handler.right);
        actionMap.put("moveDown", handler.down);
        actionMap.put("spaceAction", handler.rotate);
    	
    	
//        KeypressSimulation test = new KeypressSimulation(board);
//        test.run();
        Timer timer = new Timer();
        isRunning = true;
        timer.schedule(gameTask, 250, 1000);
	}
	public boolean stopGame() {
		if(isRunning)
		{
			gameTask.cancel();
			isRunning = false;
			return true;
		}
		return false;
	}
	
	public class Helper extends TimerTask {
	    public void run()
	    {
			board.move_down();
//			_board.print_board();
			updateBoard();
	    }
	    
	    public void updateBoard() {
			for(int i = 0; i < board.BOARD_SIZE_Y; i++) {
				for(int j = 0; j< board.BOARD_SIZE_X; j++) {
					gameScreen.setGridSquareColor(i,j,board.board[i][j]);
				}
			}
			gameScreen.updateScore(board.score);
		}
	}	
}




