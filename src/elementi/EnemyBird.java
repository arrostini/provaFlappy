package elementi;

import elementi.Bird;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class EnemyBird extends Bird {
    public EnemyBird(GameContainer container) throws SlickException {
                super(container);

                image.setAlpha(0.5f);

    }

}
