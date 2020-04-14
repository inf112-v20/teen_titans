package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.network.client.GameClient;

public class JoinGameScreen {

    private int playersJoined;
    private GameClient client;
    private Orchestrator parent;
    private Stage stage;
    private Table table;
    private Skin skin;

    public JoinGameScreen(Orchestrator orchestrator, GameClient client) {
        this.client = client;
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
        for(Object name :  client.getPlayerNames().values()){
            if(name instanceof String){
                TextField textField = new TextField((String) name, skin);
                table.add(textField).expandX();
                table.row();
            }
        }
    }


    public void render() {
        if(playersJoined < client.getPlayerNames().size()){
            updateTable();
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


}
