package tetris;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
	
	public static void main(String[] args) {
		Board board = new Board();
        KeypressSimulation test = new KeypressSimulation(board);
        test.run();
		Timer timer = new Timer();
        TimerTask task = new Helper(board);
        timer.schedule(task, 250, 250);
	}
	
	
}
