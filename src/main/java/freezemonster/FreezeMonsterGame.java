package freezemonster;

import java.awt.EventQueue;

import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

public class FreezeMonsterGame extends MainFrame {

    public static void main(String[] args) {

        EventQueue.invokeLater(FreezeMonsterGame::new);
    }

    public FreezeMonsterGame() {
        super("Freeze Monsters");
    }

    @Override
    protected AbstractBoard createBoard() {
        return new FreezeMonsterBoard();
    }

}