package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.network.client.GameClient;

public class JoinGameScreen extends InputAdapter {

    private int playersJoined;
    private GameClient client;
    private Orchestrator parent;
    private Stage stage;
    private Table table;
    private Skin skin;
    private boolean ready = false;
    private Image highlight = new Image(new Texture(Gdx.files.internal("robots/HighlightedCharacter.png")));
    private Image selected = new Image(new Texture(Gdx.files.internal("robots/selectedCharacter.png")));

    public JoinGameScreen(Orchestrator orchestrator, GameClient client) {
        Gdx.input.setInputProcessor(this);
        this.client = client;
        parent = orchestrator;
        stage = new Stage(new ScreenViewport());
        create();
    }

    public void create(){
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        skin = new Skin(Gdx.files.internal("styles/glassy-ui.json"));
        TextField text = new TextField("Joined players", skin);
        table.add(text).expandX();
        table.row();
        showcasePlayerModels();
        highlightCharacter(false);

        Image logo = new Image(new Texture(Gdx.files.internal("other/logo.png")));
        logo.setPosition(stage.getWidth()/2 - 250, stage.getHeight()-200);
        stage.addActor(logo);
    }

    private void updateTable(){
        table.clear();
        TextField text = new TextField("Joined players", skin);
        table.add(text).expandX().padBottom(20);
        table.row();
        for(Object name :  client.getPlayerNames().values()){
            TextField textField = new TextField((String) name, skin);
            table.add(textField).expandX();
            table.row();
        }
    }


    public void render() {
        if(playersJoined < client.getPlayerNames().size()){
            updateTable();
        }
        if(client.getStartSignal()){
            parent.startGame(client.getPlayerAmount());
        }
        Gdx.gl.glClearColor(61/255f,36/255f,111/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage(){
        return stage;
    }

    public void showcasePlayerModels(){
        Label info1 = new Label("The game will start when every player has selected a character", skin);
        info1.setPosition(stage.getWidth()/2-290, 170);
        stage.addActor(info1);

        Label info2 = new Label("Please select character:", skin);
        info2.setPosition(stage.getWidth()/2-100, 150);
        stage.addActor(info2);


        Image pika = new Image(new Texture(Gdx.files.internal("robots/pika.png")));
        Image cha = new Image(new Texture(Gdx.files.internal("robots/charmander.png")));
        Image bulb = new Image(new Texture(Gdx.files.internal("robots/bulbasaur.png")));
        Image squ = new Image(new Texture(Gdx.files.internal("robots/squirtle.png")));
        Image geng = new Image(new Texture(Gdx.files.internal("robots/PixelGengar.png")));

        pika.setSize(100, 100);
        pika.setPosition(stage.getWidth()/2-290, 20);
        stage.addActor(pika);


        cha.setSize(100, 100);
        cha.setPosition(stage.getWidth()/2-170, 20);
        stage.addActor(cha);

        bulb.setSize(100, 100);
        bulb.setPosition(stage.getWidth()/2-50, 20);
        stage.addActor(bulb);

        squ.setSize(100, 100);
        squ.setPosition(stage.getWidth()/2+70,20);
        stage.addActor(squ);

        geng.setSize(100,100);
        geng.setPosition(stage.getWidth()/2+190,20);
        stage.addActor(geng);

        stage.addActor(highlight);
        stage.addActor(selected);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.ENTER:
                highlightCharacter(true);
                client.sendReadySignal(ready, currentModel());
                return true;
            case Input.Keys.RIGHT:
                if(!ready){
                    highlighted = (highlighted +1) % 5;}
                highlightCharacter(false);
                return true;
            case Input.Keys.LEFT:
                if(!ready){
                    highlighted -= 1;
                    if(highlighted < 0){
                        highlighted = 4;}}
                highlightCharacter(false);
                return true;
        }
        return false;
    }

    private int highlighted = 0;
    private void highlightCharacter(boolean selected){
        if(!ready) {
            this.selected.setPosition(-200, 0);
            switch (highlighted) {
                case 0:
                    highlight.setPosition(stage.getWidth() / 2 - 290, 20);
                    break;
                case 1:
                    highlight.setPosition(stage.getWidth() / 2 - 170, 20);
                    break;
                case 2:
                    highlight.setPosition(stage.getWidth() / 2 - 50, 20);
                    break;
                case 3:
                    highlight.setPosition(stage.getWidth() / 2 + 70, 20);
                    break;
                case 4:
                    highlight.setPosition(stage.getWidth()/2 + 190, 20);
                    break;
            }
        }
        else{
            this.selected.setPosition(stage.getWidth()/2-290 + 120 * highlighted, 20);
        }
        if(selected){ready = !ready; highlightCharacter(false);}
    }

    private String currentModel(){
        switch (highlighted){
            case 1:
                return "robots/charmander.png";
            case 2:
                return "robots/bulbasaur.png";
            case 3:
                return "robots/squirtle.png";
            case 4:
                return "robots/PixelGengar.png";
            default:
                return "robots/pika.png";
        }
    }
}
