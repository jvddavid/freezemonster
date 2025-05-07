package freezemonster;

import java.awt.EventQueue;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

/**
 * Hello world!
 */
public class FreezeMonsterGame extends MainFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            final FreezeMonsterGame game = new FreezeMonsterGame();
            System.err.println(game.getClass().getName());
        });
    }

    public FreezeMonsterGame() {
        super("Space Invaders");
    }

    @Override
    protected AbstractBoard createBoard() {
        return new FreezeMonsterBoard();
    }
}
