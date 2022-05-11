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
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.*;

//import javafx.util.Duration;
import java.lang.*;
import javafx.animation.AnimationTimer;
//import javafx.animation.FadeTransition;
//import javafx.animation.ParallelTransition;

import javafx.scene.paint.Color;
/**
 * Player plays a version of Flappy Bird with a Big Chungus Aesthetic.
 * Players press the space bar to jump in between pipes.
 *
 */
public class BigChungus extends Application { // did extend GAME
    private int DEF_X = 420;
    private int DEF_Y = 680;
    private int GAME_SCORE = 0;
    private int numFlops = 0;
    private double totalTime, momentTime;
    private boolean didStart, hitO, hitG, gameEnd, keyPressed;

    private long currentTime;

    private ImageView endGame;
    private Text readyText, scoreText, instructions;
    private Group root;
    private VBox other;
    private GraphicsContext backGC, chungusGC;

    private Sprite chungSprite, ground, ground2;
    private Chungus chungus;

    private AnimationTimer gravity;
    private ArrayList<Obstacle> obstacles;

/**
     * BigCHungus Play  method
     */
    @Override
    public void start(Stage stage) throws Exception {

        root = new Group();
        other = new VBox();

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
        // creates ground
        ground = new Sprite();
        ground2 = new Sprite();
        setSprites();
        // creates Obstacles
        obstacles = new ArrayList<>();
        setObstacles();
        // creates
        chungus = new Chungus();
        chungSprite = chungus.getChungus();
        chungSprite.drawImage( backGC );
        // setChungus();
        // setLabels();


        root.getChildren().addAll( background, canvas, chungusCan, scoreText, other);

        Scene scene = new Scene(root, DEF_X, DEF_Y );

        scene.setOnKeyPressed( e -> {

            if ( !hitO ) {

                if ( !didStart ) {
                    other.getChildren().clear();
                    System.out.println("Player is ready apparantly");
                    // start animation ()
                    didStart = true;


                } else {
                    Integer intJumps = new Integer( numFlops );
                    String num = intJumps.toString();
                    System.out.println("JUMP! - #" + num);
                    keyPressed = true;

                    currentTime = System.currentTimeMillis();

                    scoreText = new Text(num);
                    scoreText.setFont( Font.font("Impact", FontWeight.BOLD, 55) );
                    scoreText.setLayoutX( 190 );
                    scoreText.setLayoutY( 100 );
                    scoreText.setFill( Color.BEIGE );
                    scoreText.setStroke( Color.BLACK );

                    root.getChildren().remove( other );
                    root.getChildren().clear();
                    root.getChildren().addAll( background, canvas, chungusCan, scoreText);
                    // chungusSprite.setVelocity( 0, -250 );
                }
            }
            if ( gameEnd ) {
                // startNewGame()
                }

            getJumps(); // changes the numFlops
        });
        stage.setScene(scene);
        stage.show();

        startShit();

    } //start


    private long initialTime;
    /**
     * Starts the Animation loop of Everyrhing in the game.
     */
    private void startShit() {

        initialTime = System.nanoTime();

        gravity = new AnimationTimer() {
                public void handle( long present ) {
                    totalTime = ( present - initialTime ) / 1000000000.0;
                    initialTime = present;
                    backGC.clearRect( 0, 0, DEF_X, DEF_Y );
                    chungusGC.clearRect( 0, 0, DEF_X, DEF_Y );
                    // moving the ground sprites
                    ground.drawImage( backGC );
                    ground2.drawImage( backGC );
                    ground.move(2.5);
                    ground2.move(2.5);
                    if ( ground.getXpos() < -( DEF_X - 1 ) ) {
                        ground.setPos( ground2.getXpos() + ground2.getXlen(), 530 );
                    }
                    if ( ground2.getXpos() < -( DEF_X - 1 ) ) {
                        ground2.setPos( ground.getXpos() + ground.getXlen(), 530 );
                    } // if / else if

                    // Checking the Tine between Spaces
                    spaceTimer();

                    // creating the obstacles
                    if ( didStart ) {

                        for ( Obstacle obstacle : obstacles ) {
                            Sprite obs = obstacle.getObstacle();
                            obs.drawImage( backGC );
                            obs.move(5.0);
                        } // for

                        if ( obstacles.size() > 0 ) {
                            Sprite obs = obstacles.get( obstacles.size() - 1 ).getObstacle();
                            if ( obs.getXpos() == DEF_X / 2 - 100 ) {
                                setObstacles();
                            } else if ( obs.getXpos() <= -obs.getXlen() ) {
                                obstacles.remove(0);
                                obstacles.remove(0);
                            } // else if
                        } // IF


                    } // if start?
                } // handle
            }; // gravity AnimationTimer
        gravity.start();
    } // startSHit

    /**
     * Reads time Passed and enacts gravity on BigChungus.
     */
    private void spaceTimer() {
         long diff = ( System.currentTimeMillis() - currentTime ) / 300;
                    if ( diff >= .001 && keyPressed ) {
                        keyPressed = false;
                        chungSprite.setRate( 0, 175 );
                        chungSprite.drawImage( chungusGC );
                        chungSprite.move( totalTime );


                        System.out.println("BRUH");
                    } else {
                        // Animating the Chungus movement.

                        chungSprite.drawImage( chungusGC );
                        chungSprite.move( totalTime );
                        momentTime += 0.15; // accleration of gravity

                        if ( momentTime > 0.60  && keyPressed) {
                            //Sprite kamala = chungSprite;
                            //chungSprite = getFrame();
                            //chungSprite.setPos( kamala.getXpos(), kamala.getYpos() + 10 );
                            chungSprite.setRate( 0, -175 );

                            System.out.println("momentTime " + momentTime);
                            System.out.println("total " + totalTime);
                            momentTime = 0;
                        } // if
                    } // if/ else     done with timing Space presses
    } // spaceTimer

    /**
     * gets the number of key presses
     */
    private void getJumps() {
        numFlops += 1;
    }
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

        scoreText = new Text("0");
        scoreText.setFont( Font.font("Impact", FontWeight.BOLD, 55) );
        scoreText.setLayoutX( 190 );
        scoreText.setLayoutY( 100 );
        scoreText.setFill( Color.BEIGE );
        scoreText.setStroke( Color.BLACK );

        readyText = new Text("READY BUCKO?");
        readyText.setFont( Font.font("Impact", FontWeight.BOLD, 35) );
        readyText.setLayoutX( 100 );
        readyText.setLayoutY( 300 );
        readyText.setFill( Color.ORANGE );
        readyText.setStroke( Color.BLACK );

        instructions = new Text("Press the space bar to begin & make Big Chungus flop up");
        instructions.setFont( Font.font("Calibri", FontWeight.MEDIUM, 15) );
        instructions.setLayoutX( 30 );
        instructions.setLayoutY( 315 );
        instructions.setFill( Color.PURPLE );
        instructions.setStroke( Color.BLACK );

        Image gameOver = new Image( "file:/resources/gameEnd.png" );
        endGame = new ImageView( gameOver );
        endGame.setLayoutX( 100 );
        endGame.setLayoutY( 100 );
        endGame.setFitWidth( 200 );
        endGame.setFitHeight( 50 );

        other.getChildren().addAll( readyText, instructions );
        other.setLayoutX( 25 );
        other.setLayoutY( 280 );
        other.setMargin( readyText, new Insets( 0, 0, 0, 80 ) );
    } // setSprites

    /**
     * Constructs and Adds the Obstacles
     */
    private void setObstacles() {
        Random rand = new Random();
        int randnum = rand.nextInt(420);
        if ( randnum < 35 ) {
            randnum = randnum + 35;
        }
        Obstacle ground_obstacle = new Obstacle( true, randnum );
        Obstacle heli_obstacle = new Obstacle( false, 440 - randnum );

        ground_obstacle.getObstacle().setRate( -.4, 0 );
        heli_obstacle.getObstacle().setRate( -.4, 0 );

        ground_obstacle.getObstacle().drawImage( backGC );
        heli_obstacle.getObstacle().drawImage( backGC );

        obstacles.addAll( Arrays.asList( ground_obstacle, heli_obstacle ) );
    } //setObstacles

} // BigChungus
