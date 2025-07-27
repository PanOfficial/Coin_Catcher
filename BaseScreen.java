import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor; // Crucial for InputMultiplexer and keyDown override
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

public abstract class BaseScreen implements Screen, InputProcessor { // <--- Added 'implements InputProcessor'
    protected Stage mainStage;
    protected Stage uiStage;
    protected final int WIDTH = 800;
    protected final int HEIGHT = 600;

    public BaseScreen() {
        Viewport viewport = new FitViewport(WIDTH, HEIGHT, new OrthographicCamera());
        mainStage = new Stage(viewport);
        uiStage = new Stage(viewport);
        initialize(); // <--- Uncommented: This MUST be called to set up the screen!
    }

    public abstract void initialize();
    public abstract void update(float dt);

    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.act(dt);
        uiStage.act(dt);

        update(dt);

        mainStage.draw();
        uiStage.draw();
    }

    @Override
    public void show() {
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(uiStage);
        im.addProcessor(this); // <--- Add 'this' screen as an InputProcessor
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
    @Override
    public void dispose() {
        mainStage.dispose();
        uiStage.dispose();
    }

    // --- InputProcessor Methods (REQUIRED to implement after 'implements InputProcessor') ---
    @Override
    public boolean keyDown(int keycode) {
        // Default implementation: do nothing and pass event on (return false)
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}