import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends BaseActor {
    private boolean facingRight = true;

    public Player(float x, float y, Stage s) {
        super(x, y, s);

        Animation<TextureRegion> anim = loadAnimationFromSheetExact(
                "player.png", 4, 7, 27, 0.05f, true
        );
        setAnimation(anim);
        setBoundaryPolygon(8);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        float speed = 200;
        boolean moving = false;

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            moveBy(-speed * dt, 0);
            if (facingRight) {
                flipAnimation(true, false);
                facingRight = false;
            }
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            moveBy(speed * dt, 0);
            if (!facingRight) {
                flipAnimation(true, false);
                facingRight = true;
            }
            moving = true;
        }

        if (!moving) {
            setAnimationPaused(true);
        } else {
            setAnimationPaused(false);
        }

        // Clamp position within the screen bounds defined by BaseScreen's WIDTH and HEIGHT
        clampPosition(0, 0, WIDTH - getWidth(), HEIGHT - getHeight());
    }
}