package tetris;
import java.util.Timer;
import java.util.TimerTask;


public class GameManager  extends Thread {	
    TimerTask gameTask;
	boolean isRunning = false;
	public void run() {
		Board board = new Board();
		gameTask = new Helper(board);
        KeypressSimulation test = new KeypressSimulation(board);
        test.run();
        Timer timer = new Timer();
        isRunning = true;
        timer.schedule(gameTask, 250, 1000);
	}
	public boolean stopGameLoop() {
		if(isRunning)
		{
			gameTask.cancel();
			return true;
		}
		return false;
	}
}
