package elementi;

import org.newdawn.slick.geom.Shape;

public interface  SolidElement {
    public boolean collides(Shape otherShape);
}
