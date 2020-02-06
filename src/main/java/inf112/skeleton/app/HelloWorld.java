package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class HelloWorld extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    TiledMap map;
    TiledMapTileLayer ground;
    TiledMapTileLayer hole;
    TiledMapTileLayer wall;
    TiledMapTileLayer flag;
    TiledMapTileLayer playerLayer;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Vector2 position;
    Cell playerCell;
    Cell playerDead;
    Cell playerWon;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        playerCell = new Cell();
        playerDead = new Cell();
        playerWon  = new Cell();


        map = new TmxMapLoader().load("assets/testMap.tmx");
        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(map, 1/90000);
        ground =      (TiledMapTileLayer) map.getLayers().get("Ground");
        hole =        (TiledMapTileLayer) map.getLayers().get("Holes");
        wall =        (TiledMapTileLayer) map.getLayers().get("Walls");
        flag =        (TiledMapTileLayer) map.getLayers().get("Flags");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        Texture player = new Texture(Gdx.files.internal("player.png"));
        TextureRegion[][] frank = new TextureRegion(new Texture("player.png")).split(300,300);
        playerCell.setTile(new StaticTiledMapTile(frank[0][0]));
        playerDead.setTile(new StaticTiledMapTile(frank[0][1]));
        playerWon.setTile(new StaticTiledMapTile(frank[0][2]));

        camera.setToOrtho(false, 8, 8);
        camera.viewportWidth = 8;
        camera.viewportHeight = 8;
        camera.position.set(8/2f, 8/2f, 0);
        renderer.setView(camera);

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

        renderer.render();


        playerLayer.setCell(0, 2400, playerCell);

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
    public boolean keyUp(int keycode){
        switch(keycode){
            case(Input.Keys.UP):
                playerLayer.setCell((int) position.x, (int) position.y, null);
                position.y -= 300;
                playerLayer.setCell((int) position.x, (int) position.y, playerCell);
                return true;
            case(Input.Keys.DOWN):
                playerLayer.setCell((int) position.x, (int) position.y, null);
                position.y += 300;
                playerLayer.setCell((int) position.x, (int) position.y, playerCell);
                return true;
            case(Input.Keys.RIGHT):
                playerLayer.setCell((int) position.x, (int) position.y, null);
                position.x += 300;
                playerLayer.setCell((int) position.x, (int) position.y, playerCell);
                return true;
            case(Input.Keys.LEFT):
                playerLayer.setCell((int) position.x, (int) position.y, null);
                position.x -= 300;
                playerLayer.setCell((int) position.x, (int) position.y, playerCell);
                return true;
        }

        return false;
    }

}
