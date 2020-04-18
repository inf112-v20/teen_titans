package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
            parent.startGame(client.getPlayerAmount(), 0);
        }

        Gdx.gl.glClearColor(0f,0f,0f,1);
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
        Image pika = new Image(new Texture(Gdx.files.internal("robots/pika.png")));
        Image cha = new Image(new Texture(Gdx.files.internal("robots/charmander.png")));
        Image bulb = new Image(new Texture(Gdx.files.internal("robots/bulbasaur.png")));
        Image ham2 = new Image(new Texture(Gdx.files.internal("robots/marsvin2.png")));
        Image ham = new Image(new Texture(Gdx.files.internal("robots/marsvin.png")));
        bulb.setSize(100, 100);
        bulb.setPosition(stage.getWidth()/2-50, 20);
        stage.addActor(bulb);
        cha.setSize(100, 100);
        cha.setPosition(stage.getWidth()/2-170, 20);
        stage.addActor(cha);
        pika.setSize(100, 100);
        pika.setPosition(stage.getWidth()/2-290, 20);
        stage.addActor(pika);
        ham.setSize(100, 100);
        ham.setPosition(stage.getWidth()/2+70,20);
        stage.addActor(ham);
        ham2.setSize(100, 100);
        ham2.setPosition(stage.getWidth()/2+190, 20);
        stage.addActor(ham2);
        stage.addActor(highlight);
        stage.addActor(selected);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.ENTER:
                highlightCharacter(true);
                client.sendReadySignal(ready);
                return true;
            case Input.Keys.RIGHT:
                if(!ready){hightlighted = (hightlighted+1) % 5;}
                highlightCharacter(false);
                return true;
            case Input.Keys.LEFT:
                if(!ready){hightlighted -= 1;
                    if(hightlighted < 0){hightlighted = 4;}}
                highlightCharacter(false);
                return true;
        }
        return false;
    }



    private int hightlighted = 0;
    private void highlightCharacter(boolean selected){
        if(!ready) {
            this.selected.setPosition(-200, 0);
            switch (hightlighted) {
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
                    highlight.setPosition(stage.getWidth()/2+190, 20);
                    break;
            }
        }
        else{
            highlight.setPosition(-200,0);
            this.selected.setPosition(stage.getWidth()/2-290 + 120 * hightlighted, 20);
        }
        if(selected){ready = !ready; highlightCharacter(false);}
    }


}
