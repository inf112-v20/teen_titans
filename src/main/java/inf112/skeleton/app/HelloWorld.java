package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    TiledMap map;
    TiledMapTileLayer ground;
    TiledMapTileLayer hole;
    TiledMapTileLayer wall;
    TiledMapTileLayer flag;
    OrthoCachedTiledMapRenderer render;
    OrthographicCamera camera;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        map = new TmxMapLoader().load("assets/testMap.tmx");
        ground = (TiledMapTileLayer) new TiledMap().getLayers().get("Ground");
        hole = (TiledMapTileLayer) new TiledMap().getLayers().get("Holes");
        wall = (TiledMapTileLayer) new TiledMap().getLayers().get("Walls");
        flag = (TiledMapTileLayer) new TiledMap().getLayers().get("Flags");

        camera.setToOrtho(false,3.0f,5);
        camera.update();

        render.setView(camera);

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
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
