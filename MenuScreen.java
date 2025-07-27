import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label; // Keep import, even if Labels are commented out
import com.badlogic.gdx.Gdx;

public class MenuScreen extends BaseScreen {
    @Override
    public void initialize() {
        BaseActor title = new BaseActor(0, 0, mainStage);
        title.loadTexture("assets/MenuImage.png"); // Ensure this image contains "Press S to Start" text
        title.setSize(WIDTH, HEIGHT); // Make it cover the full screen
        title.setPosition(0, 0);

        // All Label code commented out as requested
        /*
        Label prompt = new Label("Press S to Start", BaseGame.labelStyle);
        prompt.setFontScale(2);
        prompt.setPosition(WIDTH / 2 - prompt.getWidth() / 2, 100);
        uiStage.addActor(prompt);
        */
    }

    @Override
    public void update(float dt) {
        // No continuous updates needed for this screen
    }

    @Override // <--- Now correctly overrides BaseScreen's keyDown method
    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.S) {
            BaseGame.setActiveScreen(new LevelScreen());
            return true; // Indicate that the event was handled
        }
        return false;
    }
}