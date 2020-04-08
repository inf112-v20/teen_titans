package inf112.skeleton.app;

import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.cards.MoveForwardCard;
import inf112.skeleton.app.cards.TurnLeftCard;
import inf112.skeleton.app.cards.TurnRightCard;
import inf112.skeleton.app.scenes.HudManager;
import java.util.ArrayList;

public class Player extends InputAdapter {

    private Board board;
    private HudManager hud;
    private Robot robot;
    private ArrayList<ICard> cardStorage = new ArrayList<>();

    public ICard[] getCardStorage(){
        ICard[] cardStorage = new ICard[9];
        for(int i = 0; i < this.cardStorage.size(); i++){
            cardStorage[i] = this.cardStorage.get(i);
        }
        return cardStorage;
    }

    public ICard[] getSortedCards(){
        ICard[] sortedCards = new ICard[5];
        for(int i = 0; i < sortedCards.length; i++){
            sortedCards[i] = this.cardStorage.get(i);
        }
        return sortedCards;
    }

    public Robot getRobot(){
        return robot;
    }

    public Player(Robot robot, HudManager hud, Board board){
        this.board = board;
        this.robot = robot;
        this.hud = hud;
    }

    public void recieveCards(ArrayList<ICard> cards){
        cardStorage = cards;
    }







    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){

        if(screenX > hud.getStage().getWidth()/2 - 170 && screenX < hud.getStage().getWidth()/2 - 70 && screenY > hud.getStage().getHeight() - 150 && screenY < hud.getStage().getHeight()){
            board.doRobotTurn(new TurnLeftCard(1, this));
        }

        else if(screenX > hud.getStage().getWidth()/2 - 50 && screenX < hud.getStage().getWidth()/2 + 50 && screenY > hud.getStage().getHeight() - 150 && screenY < hud.getStage().getHeight()){
            board.doRobotTurn(new MoveForwardCard(1, this, board));
        }

        else if(screenX > hud.getStage().getWidth()/2 + 70 && screenX < hud.getStage().getWidth()/2 + 170 && screenY > hud.getStage().getHeight()-150 && screenY < hud.getStage().getHeight()){
            board.doRobotTurn(new TurnRightCard(1, this));
        }

        return true;
    }




}
