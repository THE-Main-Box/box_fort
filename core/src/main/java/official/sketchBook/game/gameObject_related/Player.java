package official.sketchBook.game.gameObject_related;

import com.badlogic.gdx.graphics.Texture;
import official.sketchBook.engine.animation_related.ObjectAnimationPlayer;
import official.sketchBook.engine.animation_related.Sprite;
import official.sketchBook.engine.animation_related.SpriteSheetDataHandler;
import official.sketchBook.engine.components_related.intefaces.integration_interfaces.StaticResourceDisposable;
import official.sketchBook.engine.dataManager_related.BaseWorldDataManager;
import official.sketchBook.engine.gameObject_related.RenderableGameObject;
import official.sketchBook.game.util_related.path.GameAssetsPaths;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends RenderableGameObject implements StaticResourceDisposable{

    private static Texture playerSheet;

    public Player(
        float x,
        float y,
        float z,
        float width,
        float height,
        boolean xAxisInverted,
        boolean yAxisInverted,
        BaseWorldDataManager worldDataManager
    ) {
        super(worldDataManager);
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.xAxisInverted = xAxisInverted;
        this.yAxisInverted = yAxisInverted;
    }

    @Override
    protected void initObject() {
        this.animationPlayerList = new ArrayList<>();
        this.spriteHandlerList = new ArrayList<>();

        initSpriteSheet();
        initAnimations();
    }

    private void initAnimations(){
        this.animationPlayerList.add(
            new ObjectAnimationPlayer()
        );

        this.animationPlayerList.get(0).addAnimation("player_idle", Arrays.asList(
            new Sprite(0, 0, 0.15f),
            new Sprite(1, 0, 0.15f),
            new Sprite(2, 0, 0.15f),
            new Sprite(3, 0, 0.15f)
        ));

        this.animationPlayerList.get(0).playAnimation("player_idle");
    }

    private void initSpriteSheet(){
        if(playerSheet == null){
            playerSheet = new Texture(GameAssetsPaths.EntitiesAssetsPaths.PLAYER_SHEET_PATH);
        }

        this.spriteHandlerList.add(
            new SpriteSheetDataHandler(
                this.x,
                this.y,
                8,
                0,
                5,
                4,
                xAxisInverted,
                yAxisInverted,
                playerSheet
            )
        );
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void postUpdate() {

    }

    @Override
    protected void onObjectDestruction() {
        System.out.println("Player iniciando destruição");
    }

    @Override
    protected void disposeData() {
        System.out.println("Player limpando dados de instancia");
    }

    public static void disposeStaticResources(){
        System.out.println("Player limpando dados estaticos");
        playerSheet.dispose();
    }

    @Override
    public int getZIndex() {
        return (int) this.z;
    }
}
