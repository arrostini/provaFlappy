package stati;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartMenu extends BasicGameState {
    public static final int ID = 0;
    private GameContainer container;
    private Image background;
    private Image play;


    @Override
    public int getID() {
        return StartMenu.ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        background= new Image("res/bg.jpeg");
        play = new Image("res/play.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0, container.getWidth(), container.getHeight());
        play.draw(150,250,150,70);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if((posX>150&&posX<300)&&(posY>330&&posY<400)){
            if(Mouse.isButtonDown(0)){
                stateBasedGame.enterState(1);
            }
        }
    }
}
