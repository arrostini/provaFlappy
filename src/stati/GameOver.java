package stati;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState implements ComponentListener {
    private static final int ID = 2;
    private GameContainer container;
    private Image background;
    private Image gameOver;
    private MouseOverArea gameOverButton;
    private StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return GameOver.ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        this.stateBasedGame= stateBasedGame;
        background= new Image("res/bg.jpeg");
        gameOver = new Image("res/gameOver.png").getScaledCopy(250, 130);
        gameOverButton = new MouseOverArea(container, gameOver, 100, 250, 250, 130, this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0, container.getWidth(), container.getHeight());
        gameOverButton.render(gameContainer, graphics);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void componentActivated(AbstractComponent source)  {
        if (source == gameOverButton ) {
            try{
                stateBasedGame.getState(0).init(container,stateBasedGame);
            } catch (SlickException e){
                e.printStackTrace();
            }
            stateBasedGame.enterState(0);
        }
    }
}