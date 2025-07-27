import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.BitmapFont; // Explicit import for BitmapFont
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color; // Explicit import for Color
// Removed FreeTypeFontGenerator imports as we're not using it directly in BaseGame anymore
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public abstract class BaseGame extends Game {
    private static BaseGame gameInstance;
    public static Label.LabelStyle labelStyle;
    // Removed FreeTypeFontGenerator field as it's not managed here directly anymore
    // public static FreeTypeFontGenerator fontGenerator;

    public BaseGame() {
        gameInstance = this;
    }

    // Removed the commented-out create() method.
    // Initialization of labelStyle will now happen in CoinCatcherGame.create()

    public static void setActiveScreen(BaseScreen s) {
        gameInstance.setScreen(s);
    }

    // Dispose of assets when the game is closed
    @Override
    public void dispose() {
        super.dispose();
        // Removed fontGenerator dispose logic as it's not managed here directly anymore
        // if (fontGenerator != null) {
        //     fontGenerator.dispose();
        // }
    }
}