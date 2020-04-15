package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.network.client.GameClient;
import inf112.skeleton.app.network.server.GameServer;


public class HostGameScreen extends InputAdapter {

    private int playersJoined;
    private GameClient gameClient;
    private GameServer gameServer;
    private Orchestrator parent;
    private Stage stage;
    private Table table;
    private Skin skin;

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
        table.setDebug(true);
        stage.addActor(table);
        skin = new Skin(Gdx.files.internal("glassy-ui.json"));
        TextField text = new TextField("Joined players", skin);
        table.add(text).expandX();
        table.row();
    }

    private void updateTable(){
        table.clear();

        TextField text = new TextField("Joined players", skin);
        table.add(text).expandX().padBottom(20);
        table.row();
        //System.out.println("1");
        for(Object name :  gameClient.getPlayerNames().values()){

            if(name instanceof String){
                TextField textField = new TextField((String) name, skin);
                table.add(textField).expandX();
                table.row();
            }
        }
        playersJoined = gameClient.getPlayerAmount();
    }

    public void render() {
        if(playersJoined < gameClient.getPlayerNames().size()){
            updateTable();
        }
        if(gameClient.getStartSignal()){
            parent.startGame(gameClient.getPlayerAmount());
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
                gameServer.sendStartSignal();
                return true;
        }
        return false;
    }
}
