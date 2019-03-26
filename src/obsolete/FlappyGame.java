/*import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.tests.PedigreeTest;

import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.opengl.Display.getWidth;

public class FlappyGame  extends BasicGame {
    private GameContainer container;
    private elementi.Bird bird;
    private elementi.EnemyBird enemyBird;
    private Image background;
    private float gameSpeed=0.7f;
    private ArrayList<elementi.Pipe> pipes;
    private float maxH=0;
    private float minH=8000;
    private boolean collisione=false;
    TrueTypeFont font;
    public FlappyGame(){
        super("Flappy elementi.Bird");
    }
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.container= gameContainer;
        pipes= new ArrayList<>();
        background= new Image("res/bg.jpeg");
        bird= new elementi.Bird(container);
        enemyBird= new elementi.EnemyBird(container);
        pipes.add(new elementi.Pipe(container, container.getWidth(), container.getHeight()/2f));
        pipes.add(new elementi.Pipe(container, container.getWidth()*3f/2 + elementi.Pipe.WIDTH_PROPORION*container.getWidth()/2, container.getHeight()/2f));
        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        collisione=false;
        i*=gameSpeed;
        bird.update(gameContainer, i);
        enemyBird. update(gameContainer, i);
        for(elementi.Pipe pipe: pipes){
            pipe.update(gameContainer, i);
            if (pipe.outOfBounds())
                pipe.regenerate(randomHeight());
            if (pipe.collides(bird.getShape())){
                collisione= true;
            }
        }

    }

    private float randomHeight(){
        return container.getHeight()/4f + (new Random()).nextFloat()*(container.getHeight())/2f;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        background.draw(0,0, container.getWidth(), container.getHeight());
        bird.render(gameContainer, graphics);
        enemyBird.render(gameContainer, graphics);
        for(elementi.Pipe pipe: pipes)
            pipe.render(gameContainer, graphics);
        if(collisione)
            font.drawString( container.getWidth()/2f- font.getWidth("COLLISIONE!")/2f, container.getHeight()/10f,"COLLISIONE!");
    }

    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new FlappyGame());
            container.setTargetFrameRate(250);



//            container.setDisplayMode(Display.getDisplayMode().getWidth(),Display.getDisplayMode().getHeight(),false);
            container.setDisplayMode(450,650,false);
            System.err.println(Display.getDisplayMode().getHeight());
            System.err.println(Display.getDisplayMode().getWidth());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}*/
