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
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("styles/glassy-ui.json"));

        TextButton newGame = new TextButton("Host Game", skin);
        newGame.setName("New Game");
        img0 = new Image(new Texture(Gdx.files.internal("cards/MoveForwardCard.png")));
        img0.rotateBy(90);

        TextButton preferences = new TextButton("Join Game", skin);
        preferences.setName("Preferences");
        img1 = new Image(new Texture(Gdx.files.internal("cards/MoveForwardCard.png")));
        img1.rotateBy(90);
        img1.setVisible(false);

        TextButton exit = new TextButton("Exit", skin);
        exit.setName("Exit");
        img2 = new Image(new Texture(Gdx.files.internal("cards/MoveForwardCard.png")));
        img2.rotateBy(90);
        img2.setVisible(false);

        table.add(newGame).expandX();
        table.add(img0);
        table.row().pad(10,0,0,0);
        table.add(preferences).expandX();
        table.add(img1);
        table.row().pad(10,0,0,0);
        table.add(exit).expandX();
        table.add(img2);
    }

    public void render() {
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
                img0.setVisible(true);
                img1.setVisible(false);
                img2.setVisible(false);
                break;
            case 1:
                img0.setVisible(false);
                img1.setVisible(true);
                img2.setVisible(false);
                break;
            case 2:
                img0.setVisible(false);
                img1.setVisible(false);
                img2.setVisible(true);
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
