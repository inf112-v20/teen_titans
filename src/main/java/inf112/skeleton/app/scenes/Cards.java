package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Cards {

    private Stage stage;

    public Cards(){
        stage = new Stage(new ScreenViewport());
        Texture texture1 = new Texture(Gdx.files.internal("moveforwardcard.png"));
        Image moveForwardCard = new Image(texture1);
        moveForwardCard.setPosition(0,0);
        moveForwardCard.setHeight(150);
        moveForwardCard.setWidth(100);
        stage.addActor(moveForwardCard);

        Texture texture2 = new Texture(Gdx.files.internal("turnleftcard.png"));
        Image turnLeftCard = new Image(texture2);
        turnLeftCard.setPosition(400,0);
        turnLeftCard.setHeight(150);
        turnLeftCard.setWidth(100);
        stage.addActor(turnLeftCard);

        Texture texture3 = new Texture(Gdx.files.internal("turnrightcard.png"));
        Image turnRightCard = new Image(texture3);
        turnRightCard.setPosition(800, 0);
        turnRightCard.setHeight(150);
        turnRightCard.setWidth(100);
        stage.addActor(turnRightCard);

    }

    public Stage getStage(){
        return stage;
    }

}
