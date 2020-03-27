package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import inf112.skeleton.app.scenes.Cards;
import inf112.skeleton.app.scenes.Orchestrator;

public class Renderer implements ApplicationListener, Screen {
    private Orchestrator parent;
    private GameLoop gameLoop;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public final int BOARDHEIGHT = 12;
    public final int BOARDWIDTH = 12;

    private Cards cards;

    public Renderer(Orchestrator orchestrator){
        parent = orchestrator;
        create();
    }
    @Override
    public void create() {

        cards = new Cards();
        gameLoop = new GameLoop();
        gameLoop.loop.start();
        setupTextures();
        Gdx.input.setInputProcessor(gameLoop.getBoard());
        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(gameLoop.getBoard().getMap(), 1/300f);
        camera.setToOrtho(false, BOARDWIDTH, BOARDHEIGHT);
        camera.position.set(camera.viewportWidth/2 - 1, camera.viewportHeight/2 -2, 0);
        camera.update();
        renderer.setView(camera);


    }

    @Override
    public void dispose() {
        renderer.dispose();
        cards.getStage().dispose();
        gameLoop.loop.interrupt();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        renderer.render();
        cards.getStage().draw();
        cards.getStage().act();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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

    @Override
    public void hide() {

    }

    private void setupTextures(){
        gameLoop.getBoard().createTextures();
    }


}
