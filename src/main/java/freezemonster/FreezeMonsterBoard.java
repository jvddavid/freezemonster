package freezemonster;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import freezemonster.sprite.FreezeBeam;
import freezemonster.sprite.Monster;
import freezemonster.sprite.Slime;
import freezemonster.sprite.Woody;
import spriteframework.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;
import spriteframework.sprite.Sprite;

public class FreezeMonsterBoard extends AbstractBoard {
    private FreezeBeam raio;
    long currentTime;
    long lastTime[];
    private int deaths = 0;

    public void movimenta(Sprite raio) {
        if (raio.getDx() == 0) {
            int x = raio.getX();
            x -= 4;

            if (x < 0) {
                raio.die();
            } else {
                raio.setX(x);
            }
        }
        if (raio.getDx() == 1) {
            int x = raio.getX();
            x += 4;

            if (x > Commons.BOARD_WIDTH) {
                raio.die();
            } else {
                raio.setX(x);
            }
        }
        if (raio.getDx() == 2) {
            int y = raio.getY();
            y -= 4;

            if (y < 0) {
                raio.die();
            } else {
                raio.setY(y);
            }
        }
        if (raio.getDx() == 3) {
            int y = raio.getY();
            y += 4;

            if (y > Commons.GROUND) {
                raio.die();
            } else {
                raio.setY(y);
            }
        }
    }

    @Override
    protected void createPlayers() {
        players = new LinkedList<>();
        players.add(createPlayer());
    }

    @Override
    protected Player createPlayer() {
        return new Woody();
    }

    @Override
    protected void createBadSprites() {
        int numMonstros = 9;
        int espacoEntreMonstros = 30;
        lastTime = new long[9];
        for (int k = 0; k < numMonstros; k++) {
            String path = "images/monster" + (k + 1) + ".png";
            String path2 = "images/monster" + (k + 1) + "bg.png";
            lastTime[k] = 0;
            // Calcule a posição X baseada no índice do monstro e no espaçamento
            int monstroX = Commons.MONSTRO_INIT_X + k * (Commons.MONSTRO_WIDTH + espacoEntreMonstros);
            int monstroY = Commons.MONSTRO_INIT_Y;

            Monster monstro = new Monster(monstroX, monstroY, path, path2);
            badSprites.add(monstro);
        }
    }

    @Override
    protected void createOtherSprites() {
        raio = new FreezeBeam();
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        drawRaio(g);
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        int x = player.getX();
        int y = player.getY();

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            if (inGame) {

                if (!raio.isVisible()) {
                    Woody woodyPlayer = (Woody) player;
                    // Chama os métodos específicos da classe Woody
                    raio = new FreezeBeam(x, y, woodyPlayer.getLastmoved());
                }
            }
        }
    }

    @Override
    protected void update() {
        if (deaths == Commons.NUMBER_OF_MONSTERS_TO_DESTROY) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        for (Player player : players)
            player.act();

        if (raio.isVisible()) {

            int raioX = raio.getX();
            int raioY = raio.getY();

            for (BadSprite monstro : badSprites) {
                int monstroX = monstro.getX();
                int monstroY = monstro.getY();

                if (monstro.isVisible() && raio.isVisible() && !monstro.isDying()) {
                    if (raioX >= (monstroX)
                            && raioX <= (monstroX + Commons.MONSTRO_WIDTH)
                            && raioY >= (monstroY)
                            && raioY <= (monstroY + Commons.MONSTRO_HEIGHT)) {

                        // Realiza o casting diretamente
                        Monster monstroCast = (Monster) monstro;
                        monstro.setImage(monstroCast.getCongelado().getImage());

                        monstro.setDying(true);
                        deaths++;
                        raio.die();
                    }
                }
            }
            movimenta(raio);
        }

        currentTime = System.currentTimeMillis();
        int i = 0;
        for (BadSprite monster : badSprites) {
            if (!(monster instanceof Monster) || !monster.isDying()) {
                Random rand = new Random();
                int randomDx = 2 * (rand.nextInt(3) - 1); // Gera aleatoriamente 2, 0 ou -2
                int randomDy = 2 * (rand.nextInt(3) - 1); // Gera aleatoriamente 2, 0 ou -2

                if (currentTime - lastTime[i] >= 1000) {
                    monster.setDx(randomDx);
                    monster.setDy(randomDy);
                    lastTime[i] = System.currentTimeMillis();
                }
                i++;
                monster.act();
            }
        }

        updateOtherSprites();

    }

    protected void updateOtherSprites() {
        Random generator = new Random();

        for (BadSprite monstro : badSprites) {
            int shot = generator.nextInt(15);
            Slime gosma = ((Monster) monstro).getSlime();

            if (shot == Commons.CHANCE && monstro.isVisible() && gosma.isDestroyed() && !monstro.isDying()) {
                gosma.setDestroyed(false);
                gosma.setX(monstro.getX());
                gosma.setY(monstro.getY());
            }

            int gosmaX = gosma.getX();
            int gosmaY = gosma.getY();
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();
            int raioX = raio.getX();
            int raioY = raio.getY();

            if (players.get(0).isVisible() && !gosma.isDestroyed() && !monstro.isDying()) {
                if (gosmaX >= (playerX)
                        && gosmaX <= (playerX + Commons.PLAYER_WIDTH)
                        && gosmaY >= (playerY)
                        && gosmaY <= (playerY + Commons.PLAYER_HEIGHT)) {

                    // ImageIcon ii = new ImageIcon(explImg);
                    // players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    gosma.setDestroyed(true);
                }
            }
            if (raio.isVisible() && !gosma.isDestroyed() && !monstro.isDying()) {
                if (gosmaX >= (raioX)
                        && gosmaX <= (raioX + Commons.TIRO_HEIGHT)
                        && gosmaY >= (raioY)
                        && gosmaY <= (raioY + Commons.TIRO_HEIGHT)
                        || (raioX >= (gosmaX)
                                && raioX <= (gosmaX + Commons.TIRO_HEIGHT)
                                && raioY >= (gosmaY)
                                && raioY <= (gosmaY + Commons.TIRO_HEIGHT))) {

                    raio.die();
                    gosma.setDestroyed(true);
                }
            }
            if (!gosma.isDestroyed()) {
                movimenta(gosma);
                if (gosma.getY() >= Commons.GROUND - Commons.TIRO_HEIGHT || gosma.getY() <= 5
                        || gosma.getX() > Commons.BOARD_WIDTH - 50 || gosma.getX() <= 5) {
                    gosma.setDestroyed(true);
                    Random rand = new Random();
                    gosma.setDx(rand.nextInt(4));
                }
            }
        }
    }

    private void drawRaio(Graphics g) {
        if (raio.isVisible()) {
            g.drawImage(raio.getImage(), raio.getX(), raio.getY(), this);
        }
    }
}
