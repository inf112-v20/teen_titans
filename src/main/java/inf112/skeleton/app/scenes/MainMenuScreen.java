package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenuScreen extends InputAdapter {
    private Orchestrator parent;
    private Stage stage;
    private int SELECTED = 0;

    private TextButton newGame;
    private TextButton joinGame;
    private TextButton exit;

    private Image img0;
    private Image img1;
    private Image img2;

    public MainMenuScreen(Orchestrator orchestrator) {
        parent = orchestrator;
        stage = new Stage(new ScreenViewport());
        create();
    }

    public void create(){
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("styles/glassy-ui.json"));

        newGame = new TextButton("Host Game", skin);
        joinGame = new TextButton("Join Game", skin);
        exit = new TextButton("Exit", skin);
        table.add(newGame).expandX();
        table.add(img0);
        table.row().pad(20,0,0,0);
        table.add(joinGame).expandX();
        table.add(img1);
        table.row().pad(20,0,0,0);
        table.add(exit).expandX();
        table.add(img2);
    }

    public void render() {
        Gdx.gl.glClearColor(0.06f,0.04f,0.15f,1);
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
    public boolean keyUp(int keycode){
        switch(keycode){
            case Input.Keys.UP:
                if(SELECTED == 0){SELECTED = 2;}
                else {
                    SELECTED = (SELECTED - 1) % 3;
                }
                break;
            case Input.Keys.DOWN:
                SELECTED = (SELECTED + 1) % 3;
                break;
            case Input.Keys.ENTER:
                action();
                break;
        }
        updateArrows();
        return true;
    }

    private void updateArrows(){
        switch (SELECTED){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    private void action(){
        switch(SELECTED){
            case 0:
                parent.hostGame();
                //Music music = Gdx.audio.newMusic(Gdx.files.internal("assets/Sound effects/YouRepostedInTheWrongNeighbourhood.mp3"));
                //music.play();
                //music.setVolume(0.5f);
                break;
            case 1:
                parent.joinGame();
                break;
            case 2:
                Gdx.app.exit();
                break;
        }
    }
}
