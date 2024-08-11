package tetris;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import screens.GameScreen;
import screens.TetrisOpeningScreen;

public class GameManager{	
    Helper gameTask;
	boolean isRunning = false;
	private Board board;
	private GameScreen gameScreen;
    Double multiplier = 1.0;
    private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
	private ScheduledFuture<?> scheduledFuture;

	
	public GameManager() {
		
    	gameScreen = new GameScreen();
		this.board = new Board();
    	gameScreen.addWindowListener(new WindowAdapter() {
      	  public void windowClosing(WindowEvent e) {
      		  stopGame();
    		  TetrisOpeningScreen.openScreen();
    	  }
    	});

        timer = Executors.newSingleThreadScheduledExecutor();
        gameTask = new Helper(multiplier, timer);
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
        isRunning = true;
        scheduledFuture = timer.scheduleAtFixedRate(gameTask, 250, 250, TimeUnit.MILLISECONDS); // Use scheduleAtFixedRate for fixed interval
	}
	public boolean stopGame() {
		if (isRunning) {
	        scheduledFuture.cancel(false); // Cancel without interrupting current task
	        isRunning = false;
	        return true;
	    }
	    return false;
	}
	
	public class Helper implements Runnable {
		ScheduledExecutorService _timer;
		int counter;
		double multiplier;
		public Helper(Double multiplier, ScheduledExecutorService timer) {
			_timer = timer;
			counter = 0;
			multiplier = 1.0;
		}
	    public void run()
	    {
	    	if(counter > 20) {
	    		counter = 0;
	    		multiplier += 0.01;
	    		System.out.println("********Speedup: Multiplier = "+multiplier+"**********");
	            scheduledFuture = timer.schedule(this, (int) (250 / multiplier), TimeUnit.MILLISECONDS);
	    	}
	    	
			board.move_down();
//			_board.print_board();
			updateBoard();
			counter += 1;
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




