package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import java.util.ArrayList;
import java.util.HashMap;

public class Renderer  implements ApplicationListener {

    private Board board;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    @Override
    public void create() {
        board = new Board();
        Gdx.input.setInputProcessor(board);
        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(board.getMap(), 1/300f);
        camera.setToOrtho(false, 5, 5);
        camera.position.set((float) camera.viewportWidth/2, (float) camera.viewportHeight/2, 0);
        renderer = new OrthogonalTiledMapRenderer(board.getMap(), 1/300f);
        camera.setToOrtho(false, 5, 5);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        board.getMap();
        renderer.render();
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


}
