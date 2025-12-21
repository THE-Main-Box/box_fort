package official.sketchBook.game.screen_related;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import official.sketchBook.engine.AppMain;
import official.sketchBook.engine.camera_related.OrthographicCameraManager;
import official.sketchBook.engine.screen_related.BaseScreen;

import static official.sketchBook.game.util_related.GameConstants.Debug.show_fps_ups_metrics;
import static official.sketchBook.game.util_related.GameConstants.World.VIRTUAL_HEIGHT_METERS;

public class MenuScreen extends BaseScreen {

    private final OrthographicCameraManager uiCameraManager;
    private final BitmapFont font;

    public MenuScreen(AppMain app) {
        super(app);

        uiCameraManager = this.createUICameraManager();
        font = new BitmapFont();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void postUpdate() {
        //Garante que a matrix esteja atualizada
        uiCameraManager.getCamera().update();
    }

    @Override
    public void updateVisuals(float delta) {

    }

    @Override
    protected void prepareGameBatch() {
        //mantemos vazia pois não iremos preparar para renderizar nada do jogo
    }

    @Override
    public void drawGame(SpriteBatch batch) {
        //Não renderizamos jogo
    }

    @Override
    protected void prepareUIBatch() {
        super.prepareUIBatch();
    }

    @Override
    public void drawUI(SpriteBatch batch) {
        batch.setProjectionMatrix(uiCameraManager.getCamera().combined);

        if(show_fps_ups_metrics){

            float screenHeight = Gdx.graphics.getHeight();
            font.draw(batch, "FPS: " + getFps(), 10, screenHeight - 10);
            font.draw(batch, "UPS: " + getUps(), 10, screenHeight - 30);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        uiCameraManager.updateViewport(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();

        this.font.dispose();
    }
}
