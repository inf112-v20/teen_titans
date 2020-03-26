package inf112.skeleton.app.scenes;
import com.badlogic.gdx.Game;
import inf112.skeleton.app.scenes.MainMenuScreen;

public class Orchestrator extends Game {
    private MainMenuScreen menuScreen;

    public final static int MENU = 0;
    @Override
    public void create() {

    }
    public void changeScreens(int screen) {
        switch (screen) {
            case MENU:
                if(menuScreen == null) menuScreen = new MainMenuScreen();
                this.setScreen(menuScreen);
                break;
        }
    }
}
