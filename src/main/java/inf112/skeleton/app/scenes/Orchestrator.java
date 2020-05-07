package inf112.skeleton.app.scenes;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.scenes.game.Renderer;

public class Orchestrator extends Game {

    private Screens currentScreen;
    private MainMenuScreen menuScreen;
    private HostGameScreen hostGameScreen;
    private JoinGameScreen joinGameScreen;
    private Renderer renderer;



    @Override
    public void create() {
        boolean skipmenu = false;
        if(skipmenu){
            createGame();
        }
        else{
            menuScreen = new MainMenuScreen(this);
            Gdx.input.setInputProcessor(menuScreen);
            currentScreen = Screens.MAINMENU;
        }
    }

    @Override
    public void render(){
        switch(currentScreen){
            case RENDERER:
                renderer.render();
                break;
            case MAINMENU:
                menuScreen.render();
                break;
            case HOSTGAME:
                hostGameScreen.render();
                break;
            case JOINGAME:
                joinGameScreen.render();
        }
    }

    public void hostGame(){
        renderer = new Renderer(this, 0, true);
        hostGameScreen = new HostGameScreen(this, renderer.getGameLoop().getGameClient(), renderer.getGameLoop().getGameServer());
        currentScreen = Screens.HOSTGAME;
    }

    public void createGame(){
        renderer = new Renderer(this,0, true);
        currentScreen = Screens.RENDERER;
    }

    public void startGame(int playersAmount){
        renderer.create(playersAmount);
        currentScreen = Screens.RENDERER;
    }

    public void joinGame(){
        renderer = new Renderer(this, 1, false);
        joinGameScreen = new JoinGameScreen(this, renderer.getGameLoop().getGameClient());
        currentScreen = Screens.JOINGAME;
    }

}
