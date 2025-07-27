import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class FallingObject extends BaseActor {
    private boolean isCoin;

    public FallingObject(float x, float y, Stage s, boolean coin) {
        super(x, y, s);
        isCoin = coin;
        if (coin) {
            loadTexture("assets/coin.png"); // Use .png as discussed
        } else {
            loadTexture("assets/bomb.png");
        }
        setVelocity(0, -150);
        setBoundaryPolygon(6);
    }

    public boolean isCoin() {
        return isCoin;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if (getY() + getHeight() < 0) {
            remove();
        }
    }
}