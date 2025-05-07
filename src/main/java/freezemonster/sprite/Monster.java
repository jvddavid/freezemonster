package freezemonster.sprite;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

import spriteframework.Commons;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

public class Monster extends BadnessBoxSprite {

  private Slime gosma;
  private ImageIcon congelado;

  public Monster(int x, int y, String monstroImg, String congelado) {

    initMonster(x, y, monstroImg, congelado);
  }

  public ImageIcon getCongelado() {
    return congelado;
  }

  public Slime getSlime() {

    return gosma;
  }

  @Override
  public void die() {
    setImage(congelado.getImage());
    gosma.die();
    setDying(true);
    dx = 0;
    dy = 0;
  }

  @Override
  public LinkedList<BadSprite> getBadnesses() {
    LinkedList<BadSprite> umaGosma = new LinkedList<>();
    umaGosma.add(gosma);
    return umaGosma;
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

  private void initMonster(int x, int y, String monstroImgPath, String congeladoImgPath) {
    Random rand = new Random();
    this.x = x;
    this.y = y;
    int i = rand.nextInt(4);

    gosma = new Slime(x, y, i);

    // Redimensiona a imagem do monstro para 30x50
    ImageIcon monstroIcon = new ImageIcon(monstroImgPath);
    Image monstroImage = monstroIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    this.setImage(monstroImage);

    // Redimensiona a imagem congelada para 30x50
    ImageIcon congeladoIcon = new ImageIcon(congeladoImgPath);
    Image congeladoImage = congeladoIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    this.congelado = new ImageIcon(congeladoImage);
  }

}