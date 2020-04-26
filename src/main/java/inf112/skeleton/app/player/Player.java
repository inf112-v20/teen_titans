package inf112.skeleton.app.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.board.Board;
import inf112.skeleton.app.board.Robot;
import inf112.skeleton.app.cards.ICard;
import inf112.skeleton.app.scenes.game.HudManager;
import java.util.ArrayList;
import java.util.Arrays;

public class Player extends InputAdapter implements IPlayer {

    private int playerNumber;
    private Board board;
    private HudManager hud;
    private Robot robot;
    private ArrayList<ICard> cardStorage = new ArrayList<>();
    private ArrayList<ICard> sortedCards = new ArrayList<>();

    @Override
    public int getPlayerNumber(){
        return playerNumber;
    }

    @Override
    public ICard[] getCardStorage(){
        ICard[] cardStorage = new ICard[9];
        for(int i = 0; i < this.cardStorage.size(); i++){
            cardStorage[i] = this.cardStorage.get(i);
        }
        return cardStorage;
    }

    @Override
    public ICard[] getSortedCards(){
        while(sortedCards.size() < 5) {
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
        hud.clear();
        return sortedCardsArray;
    }

    @Override
    public Robot getRobot(){
        return robot;
    }

    public Player(int playerNumber, Robot robot, HudManager hud, Board board){
        this.playerNumber = playerNumber;
        this.board = board;
        this.robot = robot;
        robot.recieveHud(hud);
        this.hud = hud;
    }

    @Override
    public void recieveCards(ArrayList<ICard> cards){
        for(ICard card : cards){
            card.setPlayer(this);
        }
        sortedCards.clear();
        cardStorage = cards;
        hud.recieveCards(getCardStorage());
        hud.updateCardNumbers(sortedCards);
    }

    @Override
    public void selectCard(int i){
        if(sortedCards.contains(cardStorage.get(i))){
            sortedCards.remove(cardStorage.get(i));
            hud.updateCardNumbers(sortedCards);
        }
        else {
            if(sortedCards.size() < 5) {
                sortedCards.add(cardStorage.get(i));
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
            case Input.Keys.SPACE:
                selectCard(hud.getSelected());
                break;
            case Input.Keys.ENTER:
                robot.takeDamage(1);
                break;
        }
        return true;
    }





}
