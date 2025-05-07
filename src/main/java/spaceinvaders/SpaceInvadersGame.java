package spaceinvaders;

import java.awt.EventQueue;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

public class SpaceInvadersGame extends MainFrame {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			final SpaceInvadersGame game = new SpaceInvadersGame();
			System.err.println(game.getClass().getName());
		});
	}

	public SpaceInvadersGame() {
		super("Space Invaders");
	}

	@Override
	protected AbstractBoard createBoard() {
		return new SpaceInvadersBoard();
	}

}
