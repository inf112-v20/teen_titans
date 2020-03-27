package inf112.skeleton.app.scenes;
import com.badlogic.gdx.Game;
import inf112.skeleton.app.Renderer;


public class Orchestrator extends Game {
    private LoadingScreen loadingScreen;
    private MainMenuScreen menuScreen;
    private PreferencesScreen preferencesScreen;
    private ApplicationScreen applicationScreen;
    private EndScreen endScreen;
    private Renderer renderer;


    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;

    @Override
    public void create() {
    loadingScreen = new LoadingScreen(this);
    setScreen(loadingScreen);
    }
    public void changeScreens(int screen) {
        switch (screen) {
            case MENU:
                if(menuScreen == null) menuScreen = new MainMenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if(renderer == null) renderer = new Renderer(this);
                renderer.render();
                this.setScreen(renderer);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
        }
    }
}
