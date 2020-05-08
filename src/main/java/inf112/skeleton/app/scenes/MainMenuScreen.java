package inf112.skeleton.app.scenes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

import javax.management.monitor.CounterMonitor;

class MainMenuScreen extends InputAdapter {
    private final Orchestrator parent;
    private final Stage stage;
    private int SELECTED = 0;
    private Thread loop;

    private TextButton newGame;
    private TextButton joinGame;
    private TextButton exit;

    public MainMenuScreen(Orchestrator orchestrator) {
        parent = orchestrator;
        stage = new Stage(new ScreenViewport());
        create();
    }

    private void create(){
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("styles/glassy-ui.json"));

        newGame = new TextButton("Host Game", skin);
        joinGame = new TextButton("Join Game", skin);
        exit = new TextButton("Exit", skin);

        table.add(newGame);
        table.row().pad(20,0,0,0);
        table.add(joinGame).expandX();
        table.row().pad(20,0,0,0);
        table.add(exit).expandX();

        highlightThread();
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
                loop.interrupt();
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
                break;
            case 1:
                parent.joinGame();
                break;
            case 2:
                Gdx.app.exit();
                break;
        }
    }

    private void highlightThread(){
        loop = new Thread(() ->{
            boolean visible = true;
            while(true){
                switch(SELECTED){
                    case 0:
                        newGame.setVisible(visible);
                        joinGame.setVisible(true);
                        exit.setVisible(true);
                        break;
                    case 1:
                        newGame.setVisible(true);
                        joinGame.setVisible(visible);
                        exit.setVisible(true);
                        break;
                    case 2:
                        newGame.setVisible(true);
                        joinGame.setVisible(true);
                        exit.setVisible(visible);
                        break;
                }
                if(visible){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
                else{
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                }
                visible = !visible;
            }
        });
        loop.setName("Main Menu Highlighter Thread");
        loop.start();
        Music music = Gdx.audio.newMusic(Gdx.files.internal("assets/Sound effects/PokBatRev-Theme.mp3"));
        music.play();
        music.setVolume(0.1f);
        music.setLooping(true);
    }
}
