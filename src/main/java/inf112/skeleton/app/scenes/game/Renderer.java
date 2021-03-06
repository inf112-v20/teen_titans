package inf112.skeleton.app.scenes.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.scenes.Orchestrator;

@SuppressWarnings("FieldCanBeLocal")
public class Renderer {

    @SuppressWarnings("FieldCanBeLocal")
    private final Orchestrator parent;
    private final GameLoop gameLoop;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private final int BOARDHEIGHT = 16;
    private final int BOARDWIDTH = 16;

    private HudManager hudManager;



   public Renderer(Orchestrator orchestrator, int playerNumber, boolean host) {
       parent = orchestrator;
       gameLoop = new GameLoop(this, playerNumber, host);
   }

    public void create(int playersAmount) {
        gameLoop.create(playersAmount);
        hudManager = gameLoop.getHudManager();
        setupTextures();
        Gdx.input.setInputProcessor(gameLoop.getMyPlayer());
        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(gameLoop.getBoard().getMap(), 1/300f);
        camera.setToOrtho(false, BOARDWIDTH, BOARDHEIGHT);
        camera.position.set(camera.viewportWidth/2 - 2, camera.viewportHeight/2 - 4, 0);
        camera.update();
        renderer.setView(camera);
        gameLoop.getGameLoopThread().start();
    }

    public void dispose() {
        renderer.dispose();
        hudManager.getStage().dispose();
        gameLoop.getGameLoopThread().interrupt();
    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderer.render();
        hudManager.getStage().draw();
        hudManager.getStage().act();

    }

    private void setupTextures(){
        gameLoop.getBoard().createTextures();
    }

    public GameLoop getGameLoop(){
       return gameLoop;
    }


}
