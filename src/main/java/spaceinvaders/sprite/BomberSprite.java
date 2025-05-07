package spaceinvaders.sprite;

import java.util.LinkedList;

import javax.swing.ImageIcon;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

public class BomberSprite extends BadnessBoxSprite {

    private Bomb bomb;

    public BomberSprite(int x, int y) {

        initBomber(x, y);
    }

    public Bomb getBomb() {

        return bomb;
    }

    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> aBomb = new LinkedList<>();
        aBomb.add(bomb);
        return aBomb;
    }

    private void initBomber(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        String alienImg = "images/alien.png";
        ImageIcon ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }
}
