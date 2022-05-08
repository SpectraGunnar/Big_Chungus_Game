package cs1302.omega;

import cs1302.game.DemoGame;

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

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        Rectangle background = new Rectangle(640, 350);
        background.setFill(Color.rgb( 80, 100, 200 ));

        // demonstrate how to load local asset using "file:resources/"
        Image bannerImage = new Image("file:resources/title.png");
        ImageView banner = new ImageView(bannerImage);
        banner.setLayoutX(85);
        banner.setLayoutY(10);
        banner.setFitHeight(120);
        banner.setFitWidth(360);

        Image chungusIMG = new Image("file:resources/chungus.png");
        ImageView bigChungus = new ImageView(chungusIMG);
        bigChungus.setLayoutX(260);
        bigChungus.setLayoutY(135);
        bigChungus.setFitHeight(80);
        bigChungus.setFitWidth(75);

        // some labels to display information
        Text notice = new Text("Press Space Bar to begin");
        notice.setLayoutX(150);
        notice.setLayoutY(280);
        notice.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 28));
        notice.setFill(Color.YELLOW);
        notice.setStroke(Color.BLACK);

        // demo game provided
        DemoGame game = new DemoGame(640, 240);
        // setup scene

        root.getChildren().addAll( background, banner, bigChungus, notice);
        Scene scene = new Scene(root);

        banner.setPreserveRatio(true);
        banner.setFitWidth(640);

        // setup stage
        stage.setTitle("OmegaApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

        // play the game
        game.play();

    } // start

} // OmegaApp
