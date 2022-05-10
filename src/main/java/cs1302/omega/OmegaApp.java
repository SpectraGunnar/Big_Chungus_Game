package cs1302.omega;

import cs1302.game.DemoGame;
import cs1302.game.BigChungus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    public static boolean ready;

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        Rectangle background = new Rectangle(420, 680);
        background.setFill(Color.rgb( 80, 100, 200 ));

        // demonstrate how to load local asset using "file:resources/"
        Image bannerImage = new Image("file:resources/title.png");
        ImageView banner = new ImageView(bannerImage);
        banner.setLayoutX(5);
        banner.setLayoutY(15);
        banner.setFitHeight(200);
        banner.setFitWidth(400);
        banner.setPreserveRatio(false);

        Image chungusIMG = new Image("file:resources/chungus.png");
        ImageView bigChungus = new ImageView(chungusIMG);
        bigChungus.setLayoutX(160);
        bigChungus.setLayoutY(280);
        bigChungus.setFitHeight(110);
        bigChungus.setFitWidth(95);

        // some labels to display information
        Text notice = new Text("Press Space Bar to begin");
        notice.setLayoutX(40);
        notice.setLayoutY(520);
        notice.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 28));
        notice.setFill(Color.YELLOW);
        notice.setStroke(Color.BLACK);

        // demo game provided
        //DemoGame game = new DemoGame(640, 240);
        // setup scene
        root.getChildren().addAll( background, banner, bigChungus, notice);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed( e -> {
            if ( !ready ) {
                Stage gameStage = new Stage();
                BigChungus game = new BigChungus();
                ready = true;
                try {
                    game.start( stage );
                } catch (Exception shit) {
                    shit.printStackTrace();
                }
            }
        });

        // setup stage
        stage.setTitle("OmegaApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

        // play the game
        //game.play();

    } // start

} // OmegaApp
