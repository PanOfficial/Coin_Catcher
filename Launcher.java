import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher { // Changed to plain class, as it doesn't need to extend CoinCatcherGame
    public static void main(String[] args) {
        // Correct way to launch a LibGDX application
        new LwjglApplication(new CoinCatcherGame(), "Coin Catcher Game", 800, 600);
    }
}