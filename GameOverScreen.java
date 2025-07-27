import com.badlogic.gdx.scenes.scene2d.ui.Label; // Keep import for Label, though not used in this version
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class GameOverScreen extends BaseScreen {
    @Override
    public void initialize() {
        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadTexture("assets/you_lose.png"); // Ensure this image contains "Game Over" text
        background.setSize(WIDTH, HEIGHT);
        background.setPosition(0, 0);

        // All Label code commented out as requested
        /*
        Label label = new Label("Game Over", BaseGame.labelStyle);
        label.setFontScale(3);
        label.setPosition(WIDTH / 2 - label.getWidth() / 2, HEIGHT / 2 - label.getHeight() / 2);
        uiStage.addActor(label);

        Label restartPrompt = new Label("Press R to Restart", BaseGame.labelStyle);
        restartPrompt.setFontScale(1.5f);
        restartPrompt.setPosition(WIDTH / 2 - restartPrompt.getWidth() / 2, HEIGHT / 2 - label.getHeight() / 2 - 50);
        uiStage.addActor(restartPrompt);
        */
    }

    @Override
    public void update(float dt) {
        // No continuous updates needed for this screen
    }

    @Override // <--- Now correctly overrides BaseScreen's keyDown method
    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.R) {
            BaseGame.setActiveScreen(new LevelScreen()); // Restart the game
            return true;
        }
        return false;
    }
}