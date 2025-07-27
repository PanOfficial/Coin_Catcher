import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class LevelScreen extends BaseScreen {
    private Player player;
    private float spawnTimer;
    private float audioVolume = 1.0f;
    private Label scoreLabel;
    private int score = 0;
    private Music backgroundMusic;
    private Sound coinSound;
    private Sound bombSound;

    @Override
    public void initialize() {
        player = new Player(400, 50, mainStage);

        // scoreLabel will now use the default BitmapFont from CoinCatcherGame.create()
        scoreLabel = new Label("Score: 0", BaseGame.labelStyle);
        scoreLabel.setFontScale(1.5f);
        scoreLabel.setPosition(10, HEIGHT - scoreLabel.getHeight() - 10);
        uiStage.addActor(scoreLabel);

        BaseActor muteButton = new BaseActor(WIDTH - 50, HEIGHT - 50, uiStage);
        muteButton.loadTexture("assets/audio.png");
        muteButton.setSize(40, 40);
        muteButton.setTouchable(Touchable.enabled);
        muteButton.setClickListener(() -> {
            audioVolume = (audioVolume == 0) ? 1.0f : 0;
            if (backgroundMusic != null) {
                backgroundMusic.setVolume(audioVolume);
            }
        });

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/theme.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(audioVolume);
        backgroundMusic.play();

        coinSound = Gdx.audio.newSound(Gdx.files.internal("sfx/coin.wav"));
        bombSound = Gdx.audio.newSound(Gdx.files.internal("sfx/bomb.wav"));
    }

    @Override
    public void update(float dt) {
        spawnTimer += dt;
        if (spawnTimer > 1.0f) {
            spawnTimer = 0;
            float x = MathUtils.random(50, WIDTH - 50);
            if (MathUtils.randomBoolean(0.8f)) {
                new FallingObject(x, HEIGHT, mainStage, true);
            } else {
                new FallingObject(x, HEIGHT, mainStage, false);
            }
        }

        for (BaseActor obj : BaseActor.getList(mainStage, "FallingObject")) {
            if (obj.overlaps(player)) {
                FallingObject fo = (FallingObject) obj;
                if (fo.isCoin()) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                    coinSound.play(audioVolume);
                    obj.remove();
                    if (score >= 10) {
                        backgroundMusic.stop();
                        BaseGame.setActiveScreen(new WinScreen());
                    }
                } else {
                    bombSound.play(audioVolume);
                    backgroundMusic.stop();
                    BaseGame.setActiveScreen(new GameOverScreen());
                }
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
        if (coinSound != null) {
            coinSound.dispose();
        }
        if (bombSound != null) {
            bombSound.dispose();
        }
    }
}