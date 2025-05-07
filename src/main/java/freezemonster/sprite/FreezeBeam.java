package freezemonster.sprite;

import java.awt.Image;

import javax.swing.ImageIcon;

import spriteframework.sprite.BadSprite;

public class FreezeBeam extends BadSprite {

  public FreezeBeam() {
  }

  public FreezeBeam(int x, int y, int a) {

    initRaio(x, y, a);
  }

  private void initRaio(int x, int y, int lm) {
    String raioImg = "images/ray.png";
    ImageIcon ii = new ImageIcon(raioImg);

    // Redimensiona a imagem para 30x50
    Image raioImage = ii.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT);
    setImage(raioImage);

    int H_SPACE = 6;
    setX(x + H_SPACE);

    int V_SPACE = 1;
    setY(y - V_SPACE);

    this.dx = lm;
  }

}