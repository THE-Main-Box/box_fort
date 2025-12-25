package official.sketchBook.engine.gameObject_related;

import com.badlogic.gdx.utils.Disposable;
import official.sketchBook.engine.dataManager_related.BaseWorldDataManager;

public abstract class BaseGameObject implements Disposable {

    /// Posições em pixel
    protected float x, y, z;
    /// Dimensões em pixel
    protected float width, height;

    /// Inversão de percepção do objeto em relação ao eixo
    protected boolean xAxisInverted, yAxisInverted;

    /// Se deve eliminar por completo
    protected boolean pendingRemoval = false;
    protected boolean disposed = false;

    /// Manager único do object
    protected final BaseWorldDataManager worldDataManager;

    public BaseGameObject(BaseWorldDataManager worldDataManager) {
        this.worldDataManager = worldDataManager;
        this.initObject();
        this.worldDataManager.addGameObject(this);
    }

    /// Inicia os dados importantes antes de alocar ele no mundo
    protected abstract void initObject();

    /// Atualização manual
    public abstract void update(float delta);

    /// Pós-atualização manual
    public abstract void postUpdate();

    /// Sequência de destruição de objeto
    public final void destroy() {
        if (disposed) return;           //se já limpamos não podemos prosseguir nessa sequencia de eventos
        this.onObjectDestruction();     //Código personalizado antes de eliminarmos o objeto
        this.dispose();                 //Limpeza de recursos
    }

    /// Callback para lógica customizada de destruição
    protected abstract void onObjectDestruction();

    /// Dispose de dados gerais
    public final void dispose() {
        if (disposed) return;
        disposeData();
        disposed = true;
    }

    /// Pipeline interna para o dispose
    protected abstract void disposeData();

    public void markToDestroy() {
        this.pendingRemoval = true;
    }

    public boolean isPendingRemoval() {
        return pendingRemoval;
    }

    public boolean isyAxisInverted() {
        return yAxisInverted;
    }

    public boolean isxAxisInverted() {
        return xAxisInverted;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public boolean isDisposed() {
        return disposed;
    }
}
