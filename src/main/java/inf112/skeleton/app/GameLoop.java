package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class GameLoop {
    Renderer renderer;


    public GameLoop(Renderer renderer){
        this.renderer = renderer;
    }


    private void gameLoop(){
        while(true){

        }
    }


    private void roundLoop(){

    }

    public boolean testPosition(Vector2 vector) {
        return renderer.validPlayerPosition(vector);
    }



}
