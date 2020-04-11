package inf112.skeleton.app.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;
import java.util.Arrays;


public class HudManager {

    private Stage stage;
    private ICard[] cardList = new ICard[9];
    private Image selectedImage = new Image(new Texture(Gdx.files.internal("SelectedCard.png")));
    private int selected;
    private Image[] numbers = new Image[5];

    public HudManager(){
        stage = new Stage(new ScreenViewport());
        numbers[0] = new Image(new Texture(Gdx.files.internal("Number1.png")));
        numbers[1] = new Image(new Texture(Gdx.files.internal("Number2.png")));
        numbers[2] = new Image(new Texture(Gdx.files.internal("Number3.png")));
        numbers[3] = new Image(new Texture(Gdx.files.internal("Number4.png")));
        numbers[4] = new Image(new Texture(Gdx.files.internal("Number5.png")));
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

    public int getSelected(){
        return selected;
    }

    public void updateSelectedCard(int selected){
        this.selected = selected;
        switch (selected){
            case 1:
                selectedImage.setPosition(stage.getWidth()/2-410, 0);
                stage.addActor(selectedImage);
                break;
            case 2:
                selectedImage.setPosition(stage.getWidth()/2-290, 0);
                stage.addActor(selectedImage);
                break;
            case 3:
                selectedImage.setPosition(stage.getWidth()/2-170, 0);
                stage.addActor(selectedImage);
                break;
            case 4:
                selectedImage.setPosition(stage.getWidth()/2-50, 0);
                stage.addActor(selectedImage);
                break;
            case 5:
                selectedImage.setPosition(stage.getWidth()/2+70, 0);
                stage.addActor(selectedImage);
                break;
            case 6:
                selectedImage.setPosition(stage.getWidth()/2+190, 0);
                stage.addActor(selectedImage);
                break;
            case 7:
                selectedImage.setPosition(stage.getWidth()/2+310, 0);
                stage.addActor(selectedImage);
                break;
            case 8:
                selectedImage.setPosition(stage.getWidth()/2+430, 0);
                stage.addActor(selectedImage);
                break;
            default:
                selectedImage.setPosition(stage.getWidth()/2-530, 0);
                stage.addActor(selectedImage);
        }
    }

    public void updateCardNumbers(ArrayList<ICard> cards){
        //Move existing numbers out of the way in case of unselect
        for(Image number : numbers){
            number.setPosition(-200, 0);
        }
        System.out.println(Arrays.toString(cards.toArray()));
        for(int i = 0; i < cards.size(); i++){
            for(ICard card : cardList){
                if(cards.get(i).equals(card)){
                    numbers[i].setPosition(card.getImage().getX(), card.getImage().getY());
                    stage.addActor(numbers[i]);
                }
            }
        }
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
        selected = 0;
        updateSelectedCard(0);

    }



    public Stage getStage(){
        return stage;
    }




}
