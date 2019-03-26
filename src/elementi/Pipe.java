package elementi;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Pipe implements DrawableElement, SolidElement {
    private Image upperImage;
    private Image lowerImage;
    private Shape upperShape;
    private Shape lowerShape;
    private float x;
    private float y;

    private float speed;
    private GameContainer container;
    private static final float MAX_SPEED_PROPORTION = 0.0005f;
    private static final float FREE_SPACE_PROPORTION= 0.35f;
    private float width;
    private float height;
    public static final float WIDTH_PROPORION = 1f/6;
    private static final float WIDTH_HEIGHT_PROPORTION= 320f/63f;

    public Pipe(GameContainer container, float sfasamento, float center) throws SlickException {
        this.container= container;
        lowerImage= new Image("res/pipe.png");
        upperImage= lowerImage.getFlippedCopy(false, true);
        width= WIDTH_PROPORION * container.getWidth();
        height= width* WIDTH_HEIGHT_PROPORTION;
        x=sfasamento;
        y=center;
        upperShape= new Rectangle(x, y-height-0.5f*FREE_SPACE_PROPORTION*container.getHeight(), width, height);
        lowerShape= new Rectangle(x, y+0.5f*FREE_SPACE_PROPORTION*container.getHeight(), width, height);

        speed = MAX_SPEED_PROPORTION*container.getWidth();


    }
    @Override
    public void update(GameContainer container, int delta) {
        x-=speed*delta;
        lowerShape.setX(x);
        upperShape.setX(x);
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        upperImage.draw(x, y-height-0.5f*FREE_SPACE_PROPORTION*container.getHeight(), width, height);
        lowerImage.draw(x, y+0.5f*FREE_SPACE_PROPORTION*container.getHeight(), width, height);
//        g.drawRect(upperShape.getX(), upperShape.getY(), upperShape.getWidth(), upperShape.getHeight());
//        g.drawRect(lowerShape.getX(), lowerShape.getY(), lowerShape.getWidth(), lowerShape.getHeight());


    }

    @Override
    public boolean collides(Shape otherShape) {
        return upperShape.intersects(otherShape)||lowerShape.intersects(otherShape);
    }
    public void regenerate(float y){
        x= container.getWidth();
        this.y=y;
        lowerShape.setY(y+0.5f*FREE_SPACE_PROPORTION*container.getHeight());
        upperShape.setY(y-height-0.5f*FREE_SPACE_PROPORTION*container.getHeight());
    }
    public boolean outOfBounds(){
        return x+width<0;
    }

    public float getX() {
        return x;
    }
}
