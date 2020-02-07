package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
public class HelloWorld implements ApplicationListener  {
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
        position = new Vector2(4, 0);
        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        playerCell = new Cell();
        playerDead = new Cell();
        playerWon  = new Cell();


        map = new TmxMapLoader().load("assets/testMap.tmx");
        camera = new OrthographicCamera();
        renderer = new OrthogonalTiledMapRenderer(map, (float)(1/300));
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
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        camera.update();
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

        playerLayer.setCell(0, 0, playerCell);
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

    @Override
    public boolean keyUp(int keycode){
        Vector2 newPos = generateNewPlayerPosition(keycode);
        if(validPlayerPosition(newPos)){
            playerLayer.setCell((int)position.x, (int)position.y, null);
            position = newPos;
            playerLayer.setCell((int)position.x, (int)position.y, playerCell);
            return true;
        }
        return false;
    }

    /**
     * @param keycode key that was pressed
     * @return a new position if player has pressed a key indicating a move, old position otherwise.
     */
    public Vector2 generateNewPlayerPosition(int keycode) {
        if(keycode == Input.Keys.UP) return new Vector2().set(position.x, position.y+1);
        else if(keycode == Input.Keys.DOWN) return new Vector2().set(position.x, position.y-1);
        else if(keycode == Input.Keys.LEFT) return new Vector2().set(position.x-1, position.y);
        else if(keycode == Input.Keys.RIGHT) return new Vector2().set(position.x+1, position.y);
        else return position;

    }

    /**
     * Checks whether suggested player position is valid.
     * @param pos new position to try.
     * @return true if given position is a valid plauyer position, false otherwise.
     */
    public boolean validPlayerPosition(Vector2 pos){
        if(wall.getCell((int)pos.x,(int)pos.y) != null || pos.x < 0 || pos.x >= camera.viewportWidth || pos.y < 0 || pos.y >= camera.viewportHeight){
            return false;
        }
        return true;
    }


}
