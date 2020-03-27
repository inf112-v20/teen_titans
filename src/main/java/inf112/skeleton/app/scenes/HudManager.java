package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HudManager extends InputAdapter {

    private Stage stage;
    private Actor moveForwardCard;
    private Actor turnLeftCard;
    private Actor turnRightCard;

    public HudManager(){
        stage = new Stage(new ScreenViewport());

        createMoveForwardCard();
        createTurnLeftCard();
        createTurnRightCard();

    }

    private void createMoveForwardCard(){
        Texture texture1 = new Texture(Gdx.files.internal("moveforwardcard.png"));
        Image moveForwardCard = new Image(texture1);
        moveForwardCard.setPosition(0,0);
        moveForwardCard.setHeight(150);
        moveForwardCard.setWidth(100);
        moveForwardCard.addListener(createClickListener());
        stage.addActor(moveForwardCard);
        this.moveForwardCard = moveForwardCard;
    }

    private void createTurnLeftCard(){
        Texture texture2 = new Texture(Gdx.files.internal("turnleftcard.png"));
        Image turnLeftCard = new Image(texture2);
        turnLeftCard.setPosition(400,0);
        turnLeftCard.setHeight(150);
        turnLeftCard.setWidth(100);
        stage.addActor(turnLeftCard);
        this.turnLeftCard = turnLeftCard;
    }

    private void createTurnRightCard(){
        Texture texture3 = new Texture(Gdx.files.internal("turnrightcard.png"));
        Image turnRightCard = new Image(texture3);
        turnRightCard.setPosition(800, 0);
        turnRightCard.setHeight(150);
        turnRightCard.setWidth(100);
        stage.addActor(turnRightCard);
        this.turnRightCard = turnRightCard;
    }

    private ClickListener createClickListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                event.getListenerActor().setWidth(400);
            }
        };
    }

    public Stage getStage(){
        return stage;
    }

}
