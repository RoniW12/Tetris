//package tetris;
//
//import java.util.TimerTask;
//
//import screens.GameScreen;
//
//public class Helper extends TimerTask
//{
//	Board _board;
//	private GameScreen _gameScreen;
//	public Helper(Board board, GameScreen gameScreen) {
//		_board = board;
//		_gameScreen = gameScreen;
//	}
//    public void run()
//    {
//		_board.move_down();
////		_board.print_board();
//		updateBoard();
//    }
//    
//    public void updateBoard() {
//		for(int i = 0; i < _board.BOARD_SIZE_Y; i++) {
//			for(int j = 0; j< _board.BOARD_SIZE_X; j++) {
//				_gameScreen.setGridSquareColor(i,j,_board.board[i][j]);
//			}
//		}
//		_gameScreen.updateScore(_board.score);
//	}
//}