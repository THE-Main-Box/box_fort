package official.sketchBook.game.util_related;

public class GameConstants {

    /**
     * Classe de constantes de física para auxiliar a gestão de dados
     */
    public static class Physics{
        /// Pixels Per Meters, constante que determina o quantos pixels correspondem a 1 metro
        public static final float PPM = 100;

        /// Taxa de atualização que tentamos seguir
        public static float UPS_TARGET = 120;

        /// Faixa de tempo que o sistema de física irá tentar seguir
        public static float FIXED_TIMESTAMP = 1 / UPS_TARGET;

        /// Acumulador máximo para evitar travamento acidental
        public static final float MAX_ACCUMULATOR = 0.25f;// Evita travar o PC se o frame demorar muito
    }

    /**
     * Classe de constantes de renderização, para auxiliar na gestão de dados de render
     */
    public static class Rendering{

        /// Taxa de fps que tentaremos seguir
        public static final float FPS_TARGET = 120;

        /// Quantidade de tiles que podemos visualizar na largura
        public static final int TILES_VIEW_WIDTH = 20;

        /// Quantidade de tiles que podemos visualizar na altura
        public static final int TILES_VIEW_HEIGHT = 12;

        /// Tamanho da largura da janela em metros
        public static final float VIRTUAL_WIDTH_PX = World.TILE_SIZE_PX * TILES_VIEW_WIDTH;
        /// Tamanho da altura da janela em metros
        public static final float VIRTUAL_HEIGHT_PX = World.TILE_SIZE_PX * TILES_VIEW_HEIGHT;
    }

    /**
     * Classe de constantes relacionadas ao mundo, para auxiliar na gestão de dados gerais ao world
     */
    public static class World{
        /// Tamanho das tiles em pixels
        public static final int TILE_SIZE_PX = 16;
    }

    /**
     * Classe de variáveis para auxiliar na depuração
     */
    public static class Debug{

        public static boolean show_fps_ups_metrics = true;

    }

}
