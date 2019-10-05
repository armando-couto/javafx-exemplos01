package br.com.infarma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    String fxmlFile = "/fxml/validador.fxml";
    FXMLLoader loader = new FXMLLoader();
    Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

    Scene scene = new Scene(rootNode, 400, 200);
    scene.getStylesheets().add("/styles/validador.css");

    stage.setTitle("Valida Código de Barras");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
