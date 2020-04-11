package inf112.skeleton.app.scenes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.GameLoop;
import inf112.skeleton.app.scenes.Orchestrator;
import inf112.skeleton.app.scenes.HudManager;

public class Renderer {

    private Orchestrator parent;
    private GameLoop gameLoop;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public final int BOARDHEIGHT = 12;
    public final int BOARDWIDTH = 12;

    private HudManager hudManager;



   public Renderer(Orchestrator orchestrator) {
       parent = orchestrator;
       create();
   }

    public void create() {
        hudManager = new HudManager();
        gameLoop = new GameLoop(hudManager);
        gameLoop.loop.start();
        setupTextures();

        Gdx.input.setInputProcessor(gameLoop.getPlayers()[0]);

        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(gameLoop.getBoard().getMap(), 1/300f);
        camera.setToOrtho(false, BOARDWIDTH, BOARDHEIGHT);
        camera.position.set(camera.viewportWidth/2 - 1, camera.viewportHeight/2 - 2, 0);
        camera.update();
        renderer.setView(camera);

    }

    public void dispose() {
        renderer.dispose();
        hudManager.getStage().dispose();
        gameLoop.loop.interrupt();
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


}
