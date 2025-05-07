package freezemonster.sprite;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import spriteframework.Commons;
import spriteframework.sprite.Player;

public class Woody extends Player {

  private int width;
  private int lastmoved = 2;

  public int getLastmoved() {
    return lastmoved;
  }

  public void setLastmoved(int lastmoved) {
    this.lastmoved = lastmoved;
  }

  @Override
  public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      lastmoved = 0;
      dx = -2;
    }

    if (key == KeyEvent.VK_RIGHT) {
      lastmoved = 1;
      dx = 2;
    }

    if (key == KeyEvent.VK_UP) {
      lastmoved = 2;
      dy = -2;
    }

    if (key == KeyEvent.VK_DOWN) {
      lastmoved = 3;
      dy = 2;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      dx = 0;
    }

    if (key == KeyEvent.VK_RIGHT) {
      dx = 0;
    }
    if (key == KeyEvent.VK_UP) {
      dy = 0;
    }

    if (key == KeyEvent.VK_DOWN) {
      dy = 0;
    }
  }

  @Override
  public void act() {
    x += dx;
    y += dy;

    // Corrigindo os limites horizontais
    if (x <= Commons.BORDER_LEFT) {
      x = Commons.BORDER_LEFT;
    } else if (x + getImageWidth() > Commons.BOARD_WIDTH - Commons.BORDER_RIGHT) {
      x = Commons.BOARD_WIDTH - Commons.BORDER_RIGHT - getImageWidth();
    }

    // Corrigindo os limites verticais
    if (y <= 0) {
      y = 0;
    } else if (y + getImageHeight() > Commons.BOARD_HEIGHT) {
      y = Commons.BOARD_HEIGHT - getImageHeight();
    }
  }

  @Override
  protected void loadImage() {
    ImageIcon ii = new ImageIcon("images/woody.png");

    // Redimensiona a imagem para 15x15
    Image woodyImage = ii.getImage().getScaledInstance(30, 60, Image.SCALE_DEFAULT);
    width = ii.getImage().getWidth(null); // Define a largura desejada
    setImage(woodyImage);
  }

  private void initRaio(int x, int y) {
    String raioImg = "images/ray.png";
    ImageIcon ii = new ImageIcon(raioImg);

    Image raioImage = ii.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    setImage(raioImage);

    int H_SPACE = 6;
    setX(x + H_SPACE);

    int V_SPACE = 1;
    setY(y - V_SPACE);
  }

}