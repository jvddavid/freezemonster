package spriteframework.sprite;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import spriteframework.Commons;

public class Player extends Sprite {

    private int width;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player() {
        loadImage();
        getImageDimensions();
        resetState();
    }

    public void act() {

        x += dx;

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {

            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/player.png"));
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
    }

    private void resetState() {

        setX(Commons.INIT_PLAYER_X);
        setY(Commons.INIT_PLAYER_Y);
    }
}
