package inf112.skeleton.app.scenes;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.cards.ICard;


public class HudManager {

    private Stage stage;
    private ICard[] cardList = new ICard[9];


    public HudManager(){
        stage = new Stage(new ScreenViewport());
    }

    public void createPos1(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 530, 0);
        stage.addActor(img.getImage());
        cardList[0] = img;
    }

    public void createPos2(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 410, 0);
        stage.addActor(img.getImage());
        cardList[1] = img;
    }

    public void createPos3(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 290, 0);
        stage.addActor(img.getImage());
        cardList[2] = img;
    }

    public void createPos4(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 170,0);
        stage.addActor(img.getImage());
        cardList[3] = img;
    }

    public void createPos5(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 50,0);
        stage.addActor(img.getImage());
        cardList[4] = img;
    }

    public void createPos6(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 70, 0);
        stage.addActor(img.getImage());
        cardList[5] = img;
    }

    public void createPos7(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 190, 0);
        stage.addActor(img.getImage());
        cardList[6] = img;
    }

    public void createPos8(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 310, 0);
        stage.addActor(img.getImage());
        cardList[7] = img;
    }

    public void createPos9(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 430, 0);
        stage.addActor(img.getImage());
        cardList[8] = img;
    }

    private void clearImages(){
        for(ICard card : cardList){
            if(card != null){
                card.getImage().setPosition(-200, 0);
            }
        }
    }


    public void recieveCards(ICard[] cards){
        clearImages();
        try{
            createPos1(cards[0]);
            createPos2(cards[1]);
            createPos3(cards[2]);
            createPos4(cards[3]);
            createPos5(cards[4]);
            createPos6(cards[5]);
            createPos7(cards[6]);
            createPos8(cards[7]);
            createPos9(cards[8]);
        }
        catch(NullPointerException e){
            System.out.println("Exception called");
            //continue, this is fine
        }

    }



    public Stage getStage(){
        return stage;
    }




}
