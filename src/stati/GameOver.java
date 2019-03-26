package stati;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import sun.applet.Main;

public class GameOver extends BasicGameState {
    private static final int ID = 2;
    private GameContainer container;
    private Image background;
    private Image gameOver;

    @Override
    public int getID() {
        return GameOver.ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        background= new Image("res/bg.jpeg");
        gameOver = new Image("res/gameOver.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0, container.getWidth(), container.getHeight());
        gameOver.draw(100,250,250,130);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if((posX>100&&posX<350)&&(posY>270&&posY<400)){
            if(Mouse.isButtonDown(0)){
                stateBasedGame.getState(1).init(container,stateBasedGame);
                stateBasedGame.enterState(0);
            }
        }
    }
}
