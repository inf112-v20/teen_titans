package inf112.skeleton.app.scenes;

import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {

    private Orchestrator parent;

    public LoadingScreen(Orchestrator orchestrator){
        parent = orchestrator;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        parent.changeScreens(Orchestrator.MENU );
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
