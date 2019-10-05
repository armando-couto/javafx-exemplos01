package main.java.sample;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Imagem extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = new AnchorPane();

        ImageView imgView = new ImageView();

        BufferedImage bufferedImage;
        bufferedImage = ImageIO.read(new File("bolaTopper.jpg"));
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imgView.setImage(image);

        pane.getChildren().add(imgView);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Imagem");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
