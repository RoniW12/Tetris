package tetris;

import java.util.TimerTask;

public class Helper extends TimerTask
{
	Board _board;
	public Helper(Board board) {
		_board = board;
	}
    public void run()
    {
		_board.print_board();
		_board.move_down();

    }
}