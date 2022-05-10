package cs1302.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates a chungus sprite and its animation
 */
public class Chungus {
    private int thicc, tall, chungx;;
    private double xpos, ypos;
    private ArrayList<Sprite> frames;
    private Sprite chungus, chungus2, chungus3, chungus4;
    private Sprite[] frame;

    /**
     * Constructor for CHungus. Initializes each chungus frame.
     */
    public Chungus() {

        frames = new ArrayList<>();
        xpos = 70.0;
        ypos = 210.0;
        thicc = 50;
        tall = 50;
        chungx = 0;

        chungus = new Sprite();
        chungus.setImage( "file:resources/chungus.png", thicc, tall );
        chungus.setPos( xpos, ypos );
        chungus2 = new Sprite();
        chungus2.setImage( "file:resources/chungus2.png", thicc, tall );
        chungus2.setPos( xpos, ypos );
        chungus3 = new Sprite();
        chungus3.setImage( "file:resources/chungus1.png", thicc, tall );
        chungus3.setPos( xpos, ypos );
        chungus4 = new Sprite();
        chungus4.setImage( "file:resources/chungus2.png", thicc, tall );
        chungus4.setPos( xpos, ypos );
        frame = new Sprite[] { chungus, chungus2, chungus3, chungus4 };
        frames.addAll( Arrays.asList(frame) );
    }

    public Sprite getChungus() {
        return chungus;
    }

    public Sprite getFrame() {

        if ( chungx == 3 ) {
            chungx = 0;
        }

        return frames.get( chungx++ );
    }



} // Chungus
