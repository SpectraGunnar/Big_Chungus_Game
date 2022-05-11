package cs1302.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

/**
 * Sprite Object class; used to create Sprite Objects.
 */
public class Sprite {
    private double xlen, ylen, xpos, ypos, xrate, yrate;
    private Image image;

    /**
     * Constructor for Sprite class.
     */
    public Sprite() {
        this.xpos = 0.0;
        this.xrate = 0.0;
        this.ypos = 0.0;
        this.yrate = 0.0;
    }

    /**
     * Sets the Image of the certain Sprite.
     * Given the filepath string, width, and length are set accordingly.
     *
     * @param file - the filepath as a string
     * @param x - the width
     * @param y - the height
     */
    // setImage & resizeImage
    public void setImage( String file, int x, int y ) {
        Image image = new Image( file, x, y, false, false);
        this.image = image;
        this.xlen = image.getWidth();
        this.ylen = image.getHeight();
    } // setImage

    /**
     * Gets X length of the sprite.
     */
    public double getXlen() {
        return xlen;
    } // getXlen

    /**
     * Sets the position of designated Sprite to a certain x and y.
     *
     * @param xpos - the x position.
     * @param ypos - the y position.
     */
    // setPositionXY
    public void setPos( double xpos, double ypos ) {
        this.xpos = xpos;
        this.ypos = ypos;
    } // setPos

    /**
     * Gets the X position of the Sprite.
     */
    public double getXpos() {
        return xpos;
    } // getXpos

    /**
     * Gets the Y position of the Sprite
     */
    public double getYpos() {
        return ypos;
    } // getYpos

    /**
     * Sets the rate at which the Sprite moves.
     *
     * @param xrate - the horizontal rate the sprite travels.
     * @param yrate - the vertical rate the sprite travels.
     */
    // setVelocity
    public void setRate( double xrate, double yrate ) {
        this.xrate = xrate;
        this.yrate = yrate;
    } // setRate

    /**
     * Gets the rate at which the Sprite moves horizontally.
     */
    public double getXrate() {
        return xrate;
    } // getXrate

    /**
     * Gets the rat at which the Sprite moves Vertically..
     */
    public double getYrate() {
        return yrate;
    } // getYrate

    /**
     * Increases the rate at which the Sprite moves.
     *
     * @param xrate - the horizontal rate which the Sprite moves.
     * @param yrate - the vertical rate which the Sprite moves.
     */
    public void increaseRate( double xrate, double yrate ) {
        this.xrate = this.xrate + xrate;
        this.yrate = this.yrate + yrate;
    } // changeRate

    /**
     * Creates the GraphicsContext for the Sprite by inheriting GraphicsContext
     *
     * @param spriteGC - the GraphicsContext for the sprite.
     */
    public void drawImage( GraphicsContext spriteGC ) {
        spriteGC.drawImage( image, xpos, ypos );
    } // drawImage

    /**
     * Changes sprite's position
     */
    public void move( double amount ) { // update
        xpos += xrate * amount;
        ypos += yrate * amount;
    } // move

} // Sprite
