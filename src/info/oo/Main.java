package info.oo;

import info.oo.control.LoginScene;
import info.oo.control.SignInScene;
import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    public static final String LOGIN = "/fxml/LoginScene.fxml";
    public static final String CADASTRO = "/fxml/SignInScene.fxml";
    private static StackPane stackPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stackPane = new StackPane();
        primaryStage.setScene(new Scene(stackPane, 801, 534));

        changeScene(Main.LOGIN,(aClass)->new LoginScene());

        primaryStage.show();
    }

    public static void changeSceneFade(String cena, Callback construtor) {
        try{
            var paneToRemove = stackPane.getChildren().get(0);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            stackPane.getChildren().add(paneToAdd);

            var fadeInTransition = new FadeTransition(Duration.millis(800));

            fadeInTransition.setOnFinished(evt -> {
                stackPane.getChildren().remove(paneToRemove);
            });
            fadeInTransition.setNode(paneToAdd);
            fadeInTransition.setFromValue(0);
            fadeInTransition.setToValue(1);
            fadeInTransition.play();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void changeScene(String cena, Callback construtor){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            if (stackPane.getChildren().stream().count() > 0) {
                stackPane.getChildren().remove(0);
            }
            stackPane.getChildren().add(paneToAdd);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
