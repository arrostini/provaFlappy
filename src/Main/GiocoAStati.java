package Main;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import stati.GameOver;
import stati.MainGame;
import stati.StartMenu;

import java.io.File;

public class GiocoAStati extends StateBasedGame {

    private static final int STARTMENU = 0;
    private static final int MAINGAME = 1;
    private static final int GAMEOVER = 2;

    public GiocoAStati() {
        super("FLAPPY BIRD");
    }

    @Override
    public void initStatesList(GameContainer gameContainer)  {
        this.addState(new StartMenu());
        this.addState(new MainGame());
        this.addState(new GameOver());
    }

    public static void main(String[] argv) {

        try {
            AppGameContainer container = new AppGameContainer(new GiocoAStati());
            container.setTargetFrameRate(250);
            //              container.setDisplayMode(Display.getDisplayMode().getWidth(),Display.getDisplayMode().getHeight(),false);
            container.setDisplayMode(450,650,false);
            System.err.println(Display.getDisplayMode().getHeight());
            System.err.println(Display.getDisplayMode().getWidth());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
