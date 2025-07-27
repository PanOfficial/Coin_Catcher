import com.badlogic.gdx.Game; // Explicit import, though inherited
import com.badlogic.gdx.graphics.g2d.BitmapFont; // Added for font generation
import com.badlogic.gdx.graphics.Color; // Added for font color
import com.badlogic.gdx.scenes.scene2d.ui.Label; // Needed for LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle; // Explicit import for LabelStyle
// FreeType imports are commented out as per our diagnostic plan
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
// import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.Gdx; // For Gdx.files.internal if using default font

public class CoinCatcherGame extends BaseGame {
    @Override
    public void create() {
        super.create(); // <--- Crucial: Call super.create() first!

        // Initialize labelStyle with a default BitmapFont.
        // This avoids NullPointerException in LevelScreen.
        // It's simple and doesn't require FreeType or a .ttf file.
        BitmapFont font = new BitmapFont(); // Creates a default, often pixelated, font.
        labelStyle = new Label.LabelStyle(font, Color.WHITE);

        setActiveScreen(new MenuScreen());
    }
}