package cs1302.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates a chungus sprite and its animation.
 */
public class Chungus {
    int thicc, tall, chungx;
    private Sprite chungus, chungus2, chungus3, chungus4;
    private double xpos, ypos;
    ArrayList<Sprite> frames;

    private Sprite[] frame;

    /**
     * Constructor for CHungus. Initializes each chungus frame.
     */
    public Chungus() {

        frames = new ArrayList<>();
        xpos = 100.0;
        ypos = 210.0;
        thicc = 55;
        tall = 55;
        chungx = 0;

        chungus = new Sprite();
        chungus.setImage( "file:resources/chungus.png", thicc, tall );
        chungus.setPos( xpos, ypos );
        chungus2 = new Sprite();
        chungus2.setImage( "file:resources/chungus2.png", thicc, tall );
        chungus2.setPos( xpos, ypos );
        frame = new Sprite[] { chungus, chungus2 };
        frames.addAll( Arrays.asList(frame) );
    }

    /**
     * Gets the Chungus Sprite.
     *
     * @return the chungus sprite.
     */
    public Sprite getChungus() {
        return chungus;
    }
} // Chungus

// potential method change the frames of the Chungus.
/**
   public Sprite getFrame() {

   if ( chungx > 1 ) {
   chungx = 0;
    }

    return frames.get( chungx++ );
    }
*/
