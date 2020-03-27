package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameLoop;
import inf112.skeleton.app.cards.MoveForwardCard;
import inf112.skeleton.app.cards.TurnLeftCard;
import inf112.skeleton.app.cards.TurnRightCard;

public class HudManager extends InputAdapter {

    private Stage stage;
    private Actor moveForwardCard;
    private Actor turnLeftCard;
    private Actor turnRightCard;
    private GameLoop gameLoop;

    public HudManager(GameLoop gameLoop){
        stage = new Stage(new ScreenViewport());
        this.gameLoop = gameLoop;

        createMoveForwardCard();
        createTurnLeftCard();
        createTurnRightCard();

    }

    private void createMoveForwardCard(){
        Texture texture1 = new Texture(Gdx.files.internal("moveforwardcard.png"));
        Image moveForwardCard = new Image(texture1);
        moveForwardCard.setPosition(stage.getWidth()/2 - 50,0);
        moveForwardCard.setHeight(150);
        moveForwardCard.setWidth(100);
        stage.addActor(moveForwardCard);
        this.moveForwardCard = moveForwardCard;
    }

    private void createTurnLeftCard(){
        Texture texture2 = new Texture(Gdx.files.internal("turnleftcard.png"));
        Image turnLeftCard = new Image(texture2);
        turnLeftCard.setPosition(stage.getWidth()/2 - 170,0);
        turnLeftCard.setHeight(150);
        turnLeftCard.setWidth(100);
        stage.addActor(turnLeftCard);
        this.turnLeftCard = turnLeftCard;
    }

    private void createTurnRightCard(){
        Texture texture3 = new Texture(Gdx.files.internal("turnrightcard.png"));
        Image turnRightCard = new Image(texture3);
        turnRightCard.setPosition(stage.getWidth()/2 + 70, 0);
        turnRightCard.setHeight(150);
        turnRightCard.setWidth(100);
        stage.addActor(turnRightCard);
        this.turnRightCard = turnRightCard;
    }


    public Stage getStage(){
        return stage;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        if(screenX > stage.getWidth()/2 - 170 && screenX < stage.getWidth()/2 - 70 && screenY > stage.getHeight() - 150 && screenY < stage.getHeight()){
            gameLoop.doRobotTurn(new TurnLeftCard(1, gameLoop.getPlayers()[0]));
        }

        else if(screenX > stage.getWidth()/2 - 50 && screenX < stage.getWidth()/2 + 50 && screenY > stage.getHeight() - 150 && screenY < stage.getHeight()){
            gameLoop.doRobotTurn(new MoveForwardCard(1, gameLoop.getPlayers()[0], gameLoop.getBoard()));
        }

        else if(screenX > stage.getWidth()/2 + 70 && screenX < stage.getWidth()/2 + 170 && screenY > stage.getHeight()-150 && screenY < stage.getHeight()){
            gameLoop.doRobotTurn(new TurnRightCard(1, gameLoop.getPlayers()[0]));
        }

        return true;
    }

}
