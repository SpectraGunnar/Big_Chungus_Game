package cs1302.game;

/**
 * Initializes Obstacles: there loacations, sizes, and state are determined.
 */
public class Obstacle {
    private double xlen, ylen, xpos, ypos;
    private Sprite obstacle;

    /**
     * Obstacle constructor; Constructs the Obstacle sprite.
     *
     * @param isBottom - is true if the Obstacle points up, false otherwise.
     * @param size - the lenghth/position of the Obstacle
     */
    public Obstacle( boolean isBottom, int size ) {
        this.obstacle = new Sprite();
        if ( isBottom ) {
            this.obstacle.setImage( "file:resources/pipeUP.png", 70, size );
        } else {
            this.obstacle.setImage( "file:resource/pipeDOWN.png", 70, size );
        }
        this.xlen = 70;
        this.ylen = size;
        this.xpos = 420;
        if ( isBottom ) {
            this.ypos = 700 - size;
        } else {
            this.ypos = 0;
        } // if/else
        this.obstacle.setPos( xpos, ypos );
    } // Obstacle constructor

    /**
     * Gets the Obstacle sprite that was constrcuted in the {@code Obstacle} constructor.
     *
     * @return obstacle - the obstacle object.
     */
    public Sprite getObstacle() {
        return obstacle;
    }
} // Obstacle
