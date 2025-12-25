package official.sketchBook.engine.gameObject_related;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import official.sketchBook.engine.animation_related.ObjectAnimationPlayer;
import official.sketchBook.engine.animation_related.SpriteSheetDataHandler;
import official.sketchBook.engine.components_related.intefaces.integration_interfaces.RenderAbleObject;
import official.sketchBook.engine.dataManager_related.BaseWorldDataManager;

import java.util.List;

public abstract class RenderableGameObject extends BaseGameObject implements RenderAbleObject {

    protected List<SpriteSheetDataHandler> spriteHandlerList;
    protected List<ObjectAnimationPlayer> animationPlayerList;

    protected boolean isRenderDimensionEqualsToObject = true;

    public RenderableGameObject(BaseWorldDataManager worldDataManager) {
        super(worldDataManager);
    }

    @Override
    public void updateVisuals(float delta) {
        for (int i = 0; i < spriteHandlerList.size(); i++) {
            SpriteSheetDataHandler currentHandler = spriteHandlerList.get(i);
            ObjectAnimationPlayer currentAnimationPlayer = animationPlayerList.get(i);

            currentHandler.updatePosition(
                this.x,
                this.y
            );
            currentHandler.setxAxisInvert(
                this.xAxisInverted
            );
            currentHandler.setyAxisInvert(
                this.yAxisInverted
            );

            if(isRenderDimensionEqualsToObject){
                currentHandler.setRenderHeight(height);
                currentHandler.setRenderWidth(width);
            }

            if(currentAnimationPlayer != null){
                currentAnimationPlayer.update(delta);
            }

        }

    }

    @Override
    public void render(SpriteBatch batch) {
        if (!spriteHandlerList.isEmpty() && !animationPlayerList.isEmpty()) {
            //renderizamos primeiro tudo o que tivermos para renderizar do objeto do jogador
            for (int i = 0; i < spriteHandlerList.size(); i++) {
                spriteHandlerList.get(i).setxAxisInvert(xAxisInverted);
                spriteHandlerList.get(i).renderSprite(batch,
                    animationPlayerList.get(i).getCurrentSprite()
                );
            }
        }
    }
}
