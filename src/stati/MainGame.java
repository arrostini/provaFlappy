package stati;

import elementi.Bird;
import elementi.Pipe;
import network.GameClient;
import network.GameServer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends BasicGameState  {
    public static final int ID = 1;
    private GameContainer container;
    private Bird bird;
    private Bird bird2;
    private Image background;
    private float gameSpeed;
    private ArrayList<Pipe> pipes;
    private ArrayList<Pipe> pipes2;
    private Pipe lastPipe2;
    private int score;
    private TrueTypeFont font;
    private double angle;
    private Pipe lastPipe;
    private Image prova;
    private int updates=0;
    private int renders=0;
    private boolean stopRotation=false;
    private long initTime=0;
    private int deltaTime;
    private boolean initialized=false;
    private GameClient socketClient;
    private GameServer socketServer;


    private Image altroImage;
    private Image altroImage2;
    @Override
    public int getID() {
        return MainGame.ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container= gameContainer;
        pipes= new ArrayList<>();
        pipes2= new ArrayList<>();
        background= new Image("res/cimitero.png");
        bird= new Bird(container);
        bird2= new Bird(container);
        //enemyBird= new EnemyBird(container);
        pipes.add(new Pipe(container, container.getWidth()/2, container.getHeight()/2f));
        pipes.add(new Pipe(container, container.getWidth()*3f/4f + Pipe.WIDTH_PROPORION*container.getWidth()/4, randomHeight()));
        pipes.add(new Pipe(container, container.getWidth()*4f/4f + Pipe.WIDTH_PROPORION*container.getWidth()/2, randomHeight()));
        pipes.add(new Pipe(container, container.getWidth()*5f/4f + Pipe.WIDTH_PROPORION*container.getWidth()*3f/4, randomHeight()));
        pipes.add(lastPipe= new Pipe(container, container.getWidth()*6f/4f + Pipe.WIDTH_PROPORION*container.getWidth(), randomHeight()));
        pipes2.add(new Pipe(container, container.getWidth()/2, container.getHeight()/2f));
        pipes2.add(new Pipe(container, container.getWidth()*3f/4f + Pipe.WIDTH_PROPORION*container.getWidth()/4, randomHeight()));
        pipes2.add(new Pipe(container, container.getWidth()*4f/4f + Pipe.WIDTH_PROPORION*container.getWidth()/2, randomHeight()));
        pipes2.add(new Pipe(container, container.getWidth()*5f/4f + Pipe.WIDTH_PROPORION*container.getWidth()*3f/4, randomHeight()));
        pipes2.add(lastPipe2= new Pipe(container, container.getWidth()*6f/4f + Pipe.WIDTH_PROPORION*container.getWidth(), randomHeight()));

        java.awt.Font font1= new java.awt.Font("Verdana", java.awt.Font.BOLD, 32);
        font= new TrueTypeFont(font1, true);
        gameSpeed=0.5f;
        score=0;
        angle=0;

        altroImage2= new Image("res/blank.png");
        altroImage= new Image(225, 325);

        socketServer = new GameServer(this);
        socketServer.start();

        socketClient = new GameClient(this,"localhost");
        socketClient.start();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        renders++;
        altroImage.draw(-500, -500);
        //graphics.rotate(gameContainer.getWidth()/2f, gameContainer.getHeight()/2f,  (float) angle);

        background.draw(0,0, 2.4f*container.getWidth()/2f,container.getHeight());
        bird.render(gameContainer, graphics);
        //enemyBird.render(gameContainer, graphics);
        for(Pipe pipe: pipes)
            pipe.render(gameContainer, graphics);
        font.drawString(200,200,String.valueOf(score));
        if (stopRotation){
            graphics.resetTransform();
            stopRotation=false;
        }
//        System.out.println("Altezza: " + altroImage.getWidth()+ " larghezza: "  + altroImage.getHeight());
        container.getGraphics().copyArea(altroImage2, 0, 0);
        altroImage=altroImage2.getScaledCopy(1.0f);
        background.draw(0,0, 2.4f*container.getWidth()/2f,container.getHeight());
        bird2.render(container, graphics);
        for(Pipe pipe: pipes2)
            pipe.render(gameContainer, graphics);
        altroImage.draw( container.getWidth()/2f , 0);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        updates++;
        if(initialized==false){
            initTime=System.currentTimeMillis();
            initialized=true;
        }
        deltaTime+=i;
        System.out.println("Elapsed: " + (System.currentTimeMillis()-initTime) + " Delta: " + deltaTime);

        System.out.println((System.currentTimeMillis()-initTime - deltaTime ));
        i*=gameSpeed;
        bird2.update(gameContainer, i);
        bird.update(gameContainer, i);
        //enemyBird. update(gameContainer, i);
        for(Pipe pipe: pipes){
            pipe.update(gameContainer, i);
            if(pipe.getX()<bird.getX()&&!pipe.isPassed()){
                score++;
                pipe.setPassed(true);

            }
            if (pipe.outOfBounds()){
                pipe.regenerate(randomHeight(), lastPipe.getX()+ container.getWidth()/4f + Pipe.WIDTH_PROPORION*container.getWidth()/2);
                lastPipe= pipe;
                pipe.setPassed(false);

            }
            if (pipe.collides(bird.getShape())){
                //stateBasedGame.enterState(2, new FadeOutTransition(), null);
            }
        }
        for(Pipe pipe: pipes2){
            pipe.update(gameContainer, i);
            if(pipe.getX()<bird2.getX()&&!pipe.isPassed()){
                score++;
                pipe.setPassed(true);

            }
            if (pipe.outOfBounds()){
                pipe.regenerate(randomHeight(), lastPipe2.getX()+ container.getWidth()/4f + Pipe.WIDTH_PROPORION*container.getWidth()/2);
                lastPipe2= pipe;
                pipe.setPassed(false);

            }
            if (pipe.collides(bird2 .getShape())){
                //stateBasedGame.enterState(2, new FadeOutTransition(), null);
            }
        }
        angle+=i*0.02;
//        System.out.println("Renders: " + renders + ", Updates: " + updates);
    }


    private float randomHeight(){
        return container.getHeight()/4f + (new Random()).nextFloat()*(container.getHeight())/2f;
    }
    public void enemyJump(){
        bird2.jump();
    }
    public void keyPressed(int key, char c){
        if (key== Input.KEY_SPACE){
            bird.jump();
        }
        /*if (key== Input.KEY_LCONTROL){
            enemyBird.jump();
        }*/
        if( key== Input.KEY_Z){
            stopRotation=true;
            System.err.println("PORCO");

        }
        if( key == Input.KEY_ESCAPE){
            System.exit(0);

        }
        if( key == Input.KEY_LCONTROL){
            socketClient.sendData("jump".getBytes());

        }
    }


}
