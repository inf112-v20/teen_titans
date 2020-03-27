package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.scenes.HudManager;

public class Renderer implements ApplicationListener {

    private GameLoop gameLoop;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public final int BOARDHEIGHT = 12;
    public final int BOARDWIDTH = 12;

    private HudManager hudManager;


    @Override
    public void create() {
        gameLoop = new GameLoop();
        hudManager = new HudManager(gameLoop);
        gameLoop.loop.start();
        setupTextures();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gameLoop.getBoard());
        multiplexer.addProcessor(hudManager);
        Gdx.input.setInputProcessor(multiplexer);

        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(gameLoop.getBoard().getMap(), 1/300f);
        camera.setToOrtho(false, BOARDWIDTH, BOARDHEIGHT);
        camera.position.set(camera.viewportWidth/2 - 1, camera.viewportHeight/2 - 2, 0);
        camera.update();
        renderer.setView(camera);

    }

    @Override
    public void dispose() {
        renderer.dispose();
        hudManager.getStage().dispose();
        gameLoop.loop.interrupt();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderer.render();
        hudManager.getStage().draw();
        hudManager.getStage().act();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    private void setupTextures(){
        gameLoop.getBoard().createTextures();
    }


}
