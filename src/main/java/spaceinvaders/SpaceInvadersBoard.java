package spaceinvaders;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;

import spaceinvaders.sprite.Bomb;
import spaceinvaders.sprite.BomberSprite;
import spaceinvaders.sprite.Shot;
import spriteframework.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

public class SpaceInvadersBoard extends AbstractBoard {
    // define sprites
    // private List<BadSprite> aliens;
    private Shot shot;

    // define global control vars
    private int direction = -1;
    private int deaths = 0;

    private final String explImg = "images/explosion.png";

    @Override
    protected void createBadSprites() { // create sprites
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                BomberSprite alien = new BomberSprite(Commons.ALIEN_INIT_X + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i);
                badSprites.add(alien);
            }
        }
    }

    @Override
    protected void createOtherSprites() {
        shot = new Shot();
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        int x = player.getX();
        int y = player.getY();

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            if (inGame) {

                if (!shot.isVisible()) {

                    shot = new Shot(x, y);
                }
            }
        }
    }

    @Override
    protected void update() {
        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        for (Player player : players)
            player.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (BadSprite alien : badSprites) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        ImageIcon ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens

        for (BadSprite alien : badSprites) {

            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {

                direction = -1;

                for (BadSprite a2 : badSprites) {
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {

                direction = 1;

                for (BadSprite a : badSprites) {
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        for (BadSprite alien : badSprites) {
            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                alien.moveX(direction);
            }
        }

        // bombs

        updateOtherSprites();
    }

    // private void gameOver(Graphics g) {
    //
    // g.setColor(Color.black);
    // g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
    //
    // g.setColor(new Color(0, 32, 48));
    // g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
    // g.setColor(Color.white);
    // g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
    //
    // Font small = new Font("Helvetica", Font.BOLD, 14);
    // FontMetrics fontMetrics = this.getFontMetrics(small);
    //
    // g.setColor(Color.white);
    // g.setFont(small);
    // g.drawString(message, (Commons.BOARD_WIDTH -
    // fontMetrics.stringWidth(message)) / 2,
    // Commons.BOARD_WIDTH / 2);
    // }

    protected void updateOtherSprites() {
        Random generator = new Random();

        for (BadSprite alien : badSprites) {

            int is_shot = generator.nextInt(15);
            Bomb bomb = ((BomberSprite) alien).getBomb();

            if (is_shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();

            if (players.get(0).isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }
}
