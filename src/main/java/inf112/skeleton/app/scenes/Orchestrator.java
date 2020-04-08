package inf112.skeleton.app.scenes;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Orchestrator extends Game {

    private Screens currentScreen;
    private MainMenuScreen menuScreen;
    private Renderer renderer;


    @Override
    public void create() {
        menuScreen = new MainMenuScreen(this);
        Gdx.input.setInputProcessor(menuScreen);
        currentScreen = Screens.MAINMENU;
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

        }

    }

    public void createGame(){
        System.out.println("1");
        renderer = new Renderer(this);
        currentScreen = Screens.RENDERER;
    }

}
