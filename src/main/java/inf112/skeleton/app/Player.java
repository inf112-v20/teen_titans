package inf112.skeleton.app;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.cards.MoveForwardCard;
import inf112.skeleton.app.cards.TurnLeftCard;
import inf112.skeleton.app.cards.TurnRightCard;
import inf112.skeleton.app.scenes.HudManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Player extends InputAdapter {

    private Board board;
    private HudManager hud;
    private Robot robot;
    private ArrayList<ICard> cardStorage = new ArrayList<>();
    private ArrayList<ICard> sortedCards = new ArrayList<>();
    private boolean cardsReady = false;

    public ICard[] getCardStorage(){
        ICard[] cardStorage = new ICard[9];
        for(int i = 0; i < this.cardStorage.size(); i++){
            cardStorage[i] = this.cardStorage.get(i);
        }
        return cardStorage;
    }

    public ICard[] getSortedCards(){
        while(sortedCards.size() < 5 || cardsReady) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //do sleep next iteration then...
            }
        }

        ICard[]  sortedCardsArray = new ICard[5];
        for(int i = 0; i < sortedCardsArray.length; i++){
            sortedCardsArray[i] = sortedCards.get(i);
        }
        return sortedCardsArray;
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
        sortedCards.clear();
        cardsReady = false;
        cardStorage = cards;
        hud.updateCardNumbers(sortedCards);
    }


    public void selectCard(){
        int toSelect = hud.getSelected();
        if(sortedCards.contains(cardStorage.get(toSelect))){
            sortedCards.remove(cardStorage.get(toSelect));
            hud.updateCardNumbers(sortedCards);
        }
        else {
            if(sortedCards.size() < 5) {
                sortedCards.add(cardStorage.get(toSelect));
                hud.updateCardNumbers(sortedCards);
            }
        }
    }

    @Override
    public boolean keyUp(int keycode){
        switch (keycode){
            case Input.Keys.RIGHT:
                hud.updateSelectedCard(hud.getSelected()+1);
                break;
            case Input.Keys.LEFT:
                hud.updateSelectedCard(hud.getSelected()-1);
                break;
            case Input.Keys.ENTER:
                selectCard();
                break;
            case Input.Keys.U:
                cardsReady = true;
        }
        return true;
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
