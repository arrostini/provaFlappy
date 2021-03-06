package elementi;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Bird implements DrawableElement, SolidElement {
    protected Animation ghostAnimation;
    protected SpriteSheet ghostSheet;
    private float x;
    private float y;
    private float speed;
    private float size;
    private GameContainer container;
    private static final float G_PROPORTION = 0.008f;
    private static final float MAX_SPEED_PROPORTION = 0.0019f;
    private static final float SIZE_PROPORTION= 0.15f;
    private Shape shape;

    public Bird(GameContainer container) throws SlickException {
        this.container= container;
        x = container.getWidth()/10f;
        y = container.getHeight()/2f;
        size= container.getWidth()*SIZE_PROPORTION/2;
        speed= 0;
        ghostSheet = new SpriteSheet("res/ghost.png",32,32);
        ghostAnimation = new Animation(ghostSheet,100);
//        shape= new Rectangle(x +0.05f*size, y+0.05f*size, size*0.9f, size*0.9f);
        shape= new Ellipse(x+size/2f, y+size/2f, size/2*0.9f, size/2*0.9f);
    }
    @Override
    public void update(GameContainer container, int delta) {

        speed += G_PROPORTION * delta;
        y += speed*delta;
        if (y>container.getHeight())
            jump();
//      shape.setX(x);
        shape.setY(y);
    }

    @Override
    public void render(GameContainer container, Graphics g) {



        ghostAnimation.getCurrentFrame().setRotation( (float) 90*( (float) Math.atan2(speed, MAX_SPEED_PROPORTION*container.getHeight()))/((float)Math.PI)) ;
        ghostAnimation.getCurrentFrame().setCenterOfRotation( size/2,  size/2);

        ghostAnimation.draw((int) x , (int) y, size, size);
//        g.drawRect(x, y, size, size);
//        g.drawOval(x+size*0.1f/2,y+size*0.1f/2, size*0.9f,size*0.9f);
//  g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
    public void jump(){
        speed= - MAX_SPEED_PROPORTION * container.getHeight();
    }

    @Override
    public boolean collides(Shape otherShape) {
        return shape.intersects(otherShape);
    }

    public Shape getShape() {
        return shape;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
