package stati;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class StartMenu extends BasicGameState implements ComponentListener {
    public static final int ID = 0;
    private GameContainer container;
    private Image background;
    private Image play;
    private StateBasedGame stateBasedGame;
    private MouseOverArea playButton;
    private Music theme;

    @Override
    public int getID() {
        return StartMenu.ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        background= new Image("res/bg.jpeg");
        play = new Image("res/play.png").getScaledCopy(150, 70);
        playButton = new MouseOverArea(container, play, 150, 250, 150, 70, this);
        theme = new Music("res/menu.ogg");
        theme.loop(1.0f,0.3f);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0, container.getWidth(), container.getHeight());
        playButton.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }
    public void componentActivated(AbstractComponent source) {
        if (source == playButton ) {
            try {
                stateBasedGame.getState(1).init(container,stateBasedGame);
                stateBasedGame.getState(2).init(container,stateBasedGame);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            stateBasedGame.enterState(1);
        }
    }
}
