package official.sketchBook.engine.screen_related;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import official.sketchBook.engine.AppMain;
import official.sketchBook.engine.camera_related.OrthographicCameraManager;

import static official.sketchBook.game.util_related.GameConstants.Debug.show_fps_ups_metrics;
import static official.sketchBook.game.util_related.GameConstants.Physics.FIXED_TIMESTAMP;
import static official.sketchBook.game.util_related.GameConstants.Physics.MAX_ACCUMULATOR;
import static official.sketchBook.game.util_related.GameConstants.Rendering.FPS_TARGET;

public abstract class BaseScreen implements Screen {

    /// Controle de tempo
    private float accumulator = 0;

    //Métrica de desempenho
    private int fps, ups;
    private int updatesCounter = 0;
    private float metricsTimer = 0;

    /// Referência ao Inicializador do app
    private final AppMain app;

    public BaseScreen(AppMain app) {
        this.app = app;

        Gdx.graphics.setForegroundFPS((int) FPS_TARGET);
    }

    /// Função para atualização geral
    public abstract void update(float delta);

    /// Atualização crítica para preparação pro próximo update
    public abstract void postUpdate();

    /// Função para atualização de visuais
    public abstract void updateVisuals(float delta);

    /// Limpa a tela com uma cor em específico
    protected void cleanScreen() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    }

    /**
     * Renderiza tudo da UserInterface
     *
     * @param batch referencia ao batch para renderização
     */
    public abstract void drawUI(SpriteBatch batch);

    /**
     * Renderiza tudo do jogo
     *
     * @param batch referencia ao batch para renderização
     */
    public abstract void drawGame(SpriteBatch batch);

    /// Prepara para renderizar o que precisa ser renderizado do jogo
    protected void prepareGameBatch() {
        app.gameBatch.begin();
        drawGame(app.gameBatch);
        app.gameBatch.end();
    }

    /// Prepara para renderizar o que precisa ser renderizado da ui
    protected void prepareUIBatch() {
        app.uiBatch.begin();
        drawUI(app.uiBatch);
        app.uiBatch.end();
    }

    /// Organiza o gameLoop de um modo funcional e granular
    @Override
    public void render(float delta) {
        //Lida com atualização isolada separando das outras partes do gameloop
        accumulator += Math.min(delta, MAX_ACCUMULATOR);
        //Enquanto tivermos tempo para atualizar atualizamos
        while (accumulator >= FIXED_TIMESTAMP) {
            //Atualizamos tudo o que podemos
            update(FIXED_TIMESTAMP);
            //Diminuimos pois acabamos de atualizar
            accumulator -= FIXED_TIMESTAMP;
            //Atualizamos preparando para a próxima atualização
            postUpdate();

            updatesCounter++;
        }

        updateMetrics(delta);

        updateVisuals(delta);

        cleanScreen();

        prepareGameBatch();
        prepareUIBatch();

    }

    private void updateMetrics(float delta) {
        metricsTimer += delta;

        if (metricsTimer >= 1.0f) {
            fps = Gdx.graphics.getFramesPerSecond();
            ups = updatesCounter;
            updatesCounter = 0;
            metricsTimer = 0;
        }
    }

    protected OrthographicCameraManager createUICameraManager() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        OrthographicCameraManager cameraManager = new OrthographicCameraManager(width, height);
        cameraManager.getCamera().position.set(width / 2f, height / 2f, 0);
        cameraManager.getCamera().update();
        return cameraManager;
    }

    public int getFps() {
        return fps;
    }

    public int getUps() {
        return ups;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
