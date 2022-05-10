package cs1302.game;

import javafx.application.Application;
import java.util.Random;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Player plays a version of Flappy Bird with a Big Chungus Aesthetic.
 * Players press the space bar to jump in between pipes.
 *
 */
public class BigChungus extends Application { // did extend GAME
    private int DEF_X = 420;
    private int DEF_Y = 680;
    private int GAME_SCORE = 0;
    private boolean didStart, hitSum, gameEnd;

    private long currentTime;
    private ImageView gameOver;

    private Group root;
    private GraphicsContext backGC, chungusGC;

    private Sprite chungSprite, ground, ground2;
    private Chungus chungus;

    private ArrayList<Obstacle> obstacles;
    /**
     * BigCHungus Play  method
     */
    @Override
    public void start(Stage stage) throws Exception {

        root = new Group();


        stage.setTitle("BigChungus Game");
        stage.setResizable(false);

        Canvas canvas = new Canvas( DEF_X, DEF_Y );
        Canvas chungusCan = new Canvas( DEF_X, DEF_Y );
        backGC = canvas.getGraphicsContext2D();
        chungusGC = chungusCan.getGraphicsContext2D();
        Image backgroundImage = new Image("file:resources/background.png");
        ImageView background = new ImageView( backgroundImage );
        background.setFitWidth( DEF_X );
        background.setFitHeight( DEF_Y );
        background.setPreserveRatio( false );
        // setFloor();  method
        ground = new Sprite();
        ground2 = new Sprite();
        setSprites();

        obstacles = new ArrayList<>();
        setObstacles();
        // setPipes();

        chungus = new Chungus();
        chungSprite = chungus.getChungus();
        chungSprite.drawImage( backGC );
        // setChungus();
        // setLabels();

        root.getChildren().addAll( background, canvas, chungusCan );
        Scene scene = new Scene(root, DEF_X, DEF_Y );
        scene.setOnKeyPressed( e -> {
            if ( e.getCode() == KeyCode.SPACE ) {
                if ( !hitSum ) {
                    if ( !didStart ) {
                        //root.getChildren().remove( startGame );
                        // start animation ()
                        didStart = true;
                    } else {
                        // wing animation
                        currentTime = System.currentTimeMillis();
                        // chungusSprite.setVelocity( 0, -250 );
                    }
                }
                if ( gameEnd ) {
                    // startNewGame()
                }
            }
        });
        stage.setScene(scene);
        stage.show();
        // startGame();
    } //start

    /**
     * Constructs and intitializes the Sprites apearing in Game.
     */
    private void setSprites() {
        ground.setImage("file:resources/floor.png", 420, 150);
        ground.setPos( 0, 530 );
        ground.setRate( -.4, 0);
        ground.drawImage( chungusGC );
        ground2.setImage("file:resources/floor.png", 420, 150);
        ground2.setPos( 420, 530 );
        ground2.setRate( -.4, 0);
        ground2.drawImage( backGC );

        } // setSprites

    /**
     * Constructs and Adds the Obstacles
     */
    private void setObstacles() {
        Random rand = new Random();
        int randnum = rand.nextInt(400);
        if ( randnum < 35 ) {
            randnum = randnum + 35;
        }
        Obstacle obstacle = new Obstacle( true, randnum );
        Obstacle heli_obstacle = new Obstacle( false, randnum );

        obstacle.getObstacle().setRate( -.4, 0 );
        heli_obstacle.getObstacle().setRate( -.4, 0 );

        obstacle.getObstacle().drawImage( backGC );
        heli_obstacle.getObstacle().drawImage( backGC );

        obstacles.addAll( Arrays.asList( obstacle, heli_obstacle ) );
    } //setObstacles

} // BigChungus
