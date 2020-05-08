package inf112.skeleton.app.scenes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class WinnerDisplay {


    private Stage stage;
    public WinnerDisplay(){
        stage = new Stage(new ScreenViewport());

    }

    public void createImages(String[] playerModesls){
        for(String model : playerModesls){
            if(model != null){
                Image img = new Image(new Texture(Gdx.files.internal(model)));
                img.setVisible(false);
                img.setName(model);
                img.setPosition(stage.getWidth()/2-150, stage.getHeight()/2+150);
                stage.addActor(img);
            }
        }
    }

    public void setWinner(String name){
        //TODO: lagre modell i serverlistener v/ start, match med connection v/ slutt.
        System.out.println("setwinner called with value " + name);
        for(Actor actor : stage.getActors()){
            if(actor.getName() == name){
                actor.setVisible(true);
            }
        }
    }

}
