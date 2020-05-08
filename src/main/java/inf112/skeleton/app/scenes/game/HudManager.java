package inf112.skeleton.app.scenes.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.cards.ICard;

import java.util.ArrayList;


public class HudManager {

    private Stage stage;
    private ICard[] cardList = new ICard[9];
    private Image selectedImage;
    private int selected;
    private Image[] numbers = new Image[5];
    private Image[] lifeNumbers = new Image[4];
    private Image[][] hearts = new Image[5][3];
    private ArrayList<Label> labels = new ArrayList<>();
    private Skin skin;

    public HudManager(){
        stage = new Stage(new ScreenViewport());
        selectedImage = new Image(new Texture(Gdx.files.internal("cards/SelectedCard.png")));
        createHearts();
        createNumbers();
        updateLives(3);
        skin = new Skin(Gdx.files.internal("styles/glassy-ui.json"));
    }

    private void createHearts(){
        for(int i = 0; i < hearts.length; i++){
            hearts[i][0] = new Image(new Texture(Gdx.files.internal("other/FullHeart.png")));
            hearts[i][0].setSize(60, 60);
            stage.addActor(hearts[i][0]);
            hearts[i][1] = new Image(new Texture(Gdx.files.internal("other/HalfHeart.png")));
            hearts[i][1].setSize(60, 60);
            hearts[i][1].setVisible(false);
            stage.addActor(hearts[i][1]);
            hearts[i][2] = new Image(new Texture(Gdx.files.internal("other/EmptyHeart.png")));
            hearts[i][2].setSize(60, 60);
            hearts[i][2].setVisible(false);
            stage.addActor(hearts[i][2]);
        }
        for(int i = 0; i < 3; i++){
            hearts[i][0].setPosition(i*60, stage.getHeight()-50);
            hearts[i][1].setPosition(i*60, stage.getHeight()-50);
            hearts[i][2].setPosition(i*60, stage.getHeight()-50);
        }
        for(int i = 0; i < 2; i++){
            hearts[i+3][0].setPosition(i*60+30, stage.getHeight()-90);
            hearts[i+3][1].setPosition(i*60+30, stage.getHeight()-90);
            hearts[i+3][2].setPosition(i*60+30, stage.getHeight()-90);

        }
    }
    private void createNumbers(){
        for(int i = 0; i < numbers.length; i++){
            String file = "numbers/Number";
            file = file.concat(String.valueOf(i+1));
            file = file.concat(".png");
            numbers[i] = new Image(new Texture(Gdx.files.internal(file)));
            stage.addActor(numbers[i]);
            if(i < 4){
                String file1 = "numbers/Number";
                file1 = file1.concat(String.valueOf(i));
                file1 = file1.concat(".png");
                lifeNumbers[i] = new Image(new Texture(Gdx.files.internal(file1)));
                lifeNumbers[i].setVisible(false);
                stage.addActor(lifeNumbers[i]);
            }
        }
    }

    public void updateLives(int l){
        if(l < 0) return;
        for(int i = 0; i < 4; i++){
            lifeNumbers[i].setPosition(80, stage.getHeight()-130);
            lifeNumbers[i].setVisible(false);
        }
        lifeNumbers[l].setVisible(true);
    }

    public void createPos1(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 530, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[0] = img;
    }

    public void createPos2(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 410, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[1] = img;
    }

    public void createPos3(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 290, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[2] = img;
    }

    public void createPos4(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 170,0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[3] = img;
    }

    public void createPos5(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 - 50,0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[4] = img;
    }

    public void createPos6(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 70, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[5] = img;
    }

    public void createPos7(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 190, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[6] = img;
    }

    public void createPos8(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 310, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[7] = img;
    }

    public void createPos9(ICard img){
        img.getImage().setPosition(stage.getWidth()/2 + 430, 0);
        stage.addActor(img.getImage());
        addPriorityLabel(img);
        cardList[8] = img;
    }

    private void addPriorityLabel(ICard img){
        Label field = new Label(Integer.toString(img.getPriority()), skin);
        field.setPosition(img.getImage().getX()+60, 10);
        labels.add(field);
        stage.addActor(field);
    }
    public int getSelected(){
        return selected;
    }
    public void updateSelectedCard(int selected){
        if(selected < 0){selected = 8;}
        this.selected = selected % 9;
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
        selectedImage.setZIndex(stage.getActors().size);
        //Move existing numbers out of the way in case of unselect
        for(Image number : numbers){
            number.setPosition(-200, 0);
        }
        //Move selected cards into position
        for(int i = 0; i < cards.size(); i++){
            for(ICard card : cardList){
                if(cards.get(i).equals(card)){
                    numbers[i].setPosition(card.getImage().getX(), card.getImage().getY());
                    stage.addActor(numbers[i]);
                    numbers[i].setZIndex(stage.getActors().size);

                }
            }
        }
    }

    public void updateHealth(int hp){
        hearts[hp/2-1][2].setVisible(true);
        for(int i = 0; i < hp/2; i++){
            hearts[i][0].setVisible(true);
            hearts[i][1].setVisible(false);
        }
        for(int i = hp/2; i < 5; i++){
            hearts[i][0].setVisible(false);
            hearts[i][1].setVisible(false);
        }
        if(hp%2 == 1){
            hearts[hp/2-1][0].setVisible(false);
            hearts[hp/2-1][1].setVisible(true);
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
        try {
            createPos1(cards[0]);
            createPos2(cards[1]);
            createPos3(cards[2]);
            createPos4(cards[3]);
            createPos5(cards[4]);
            createPos6(cards[5]);
            createPos7(cards[6]);
            createPos8(cards[7]);
            createPos9(cards[8]);
        } catch (NullPointerException e) {
            //continue, this is fine
        }
        selected = 0;
        updateSelectedCard(0);

    }
    public void clear(){
        selectedImage.setPosition(-200, 0);
        for(ICard img : cardList){
            img.getImage().setPosition(-200, 0);
        }
        for(Label label : labels){
            label.setPosition(-200, 0);
        }
        updateCardNumbers(new ArrayList<>());
    }
    public Stage getStage(){
        return stage;
    }

}
