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
import inf112.skeleton.app.network.server.GameServer;

import java.io.File;


public class HostGameScreen extends InputAdapter {

    private int playersJoined;
    private GameClient gameClient;
    private GameServer gameServer;
    private Orchestrator parent;
    private Stage stage;
    private Table table;
    private Skin skin;

    private Image highlight = new Image(new Texture(Gdx.files.internal("robots/HighlightedCharacter.png")));
    private Image selected = new Image(new Texture(Gdx.files.internal("robots/SelectedCharacter.png")));
    private boolean ready = false;

    public HostGameScreen(Orchestrator orchestrator, GameClient client, GameServer server) {
        Gdx.input.setInputProcessor(this);
        playersJoined = 0;
        gameClient = client;
        gameServer = server;
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
        for(Object name :  gameClient.getPlayerNames().values()){
            TextField textField = new TextField((String) name, skin);
            table.add(textField).expandX();
            table.row();
        }
        playersJoined = gameClient.getPlayerAmount();
    }

    public void render() {
        if(playersJoined < gameClient.getPlayerNames().size()){
            updateTable();
        }
        if(gameClient.getStartSignal()){
            parent.startGame(gameClient.getPlayerAmount(), hightlighted);
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


    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.ENTER:
                highlightCharacter(true);
                gameClient.sendReadySignal(ready);
                //gameServer.sendStartSignal();
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

    public void showcasePlayerModels(){
        highlight = new Image(new Texture(Gdx.files.internal("robots/HighlightedCharacter.png")));
        selected = new Image(new Texture(Gdx.files.internal("robots/SelectedCharacter.png")));
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
}
