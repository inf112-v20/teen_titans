package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gamemap.GameMap;
import gamemap.tiles.BaseTile;
import gamemap.tiles.ITile;


public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "hello-world";
        cfg.width = 1500;
        cfg.height = 1500;


        new LwjglApplication(new HelloWorld(), cfg);
    }
}