package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

public class LoginApp extends Application {
  private AnchorPane pane;
  private TextField txLogin;
  private PasswordField txSenha;
  private Button btnEntrar;
  private Button btnSair;
  private Scene scene;
  private static Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    initComponents();
    initListeners();

    Scene scene = new Scene(pane);
    scene.getStylesheets().add("login.css");
    stage.setScene(scene);
    stage.setTitle("Login de usuário");
    stage.show();
    initLayout();

    LoginApp.stage = stage;
  }

  private void initComponents() {
    pane = new AnchorPane();
    pane.setPrefSize(300, 250);

    txLogin = new TextField();
    txLogin.setPromptText("Digite seu login");
    txSenha = new PasswordField();
    txSenha.setPromptText("Digite sua senha");

    btnEntrar = new Button("Entrar");
    btnSair = new Button("Sair");

    pane.getStyleClass().add("pane");

    btnSair.getStyleClass().add("botao");
    btnEntrar.getStyleClass().add("botao");

    pane.getChildren().addAll(txLogin, txSenha, btnEntrar, btnSair);
  }

  private void initLayout() {
    txLogin.setLayoutX((pane.getWidth() - txLogin.getWidth()) / 2);
    txLogin.setLayoutY(50);

    txSenha.setLayoutX((pane.getWidth() - txSenha.getWidth()) / 2);
    txSenha.setLayoutY(100);

    btnEntrar.setLayoutX(93);
    btnEntrar.setLayoutY(150);

    btnSair.setLayoutX(160);
    btnSair.setLayoutY(150);
  }

  private void initListeners() {
    btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        logar();
      }
    });
    btnSair.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        fecharAplicacao();
      }
    });
    txLogin.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        if (!txLogin.getText().equals("")) {
          txSenha.requestFocus();
        }
      }
    });
    txSenha.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        logar();
      }
    });
  }

  private void logar() {
    try {
      if (txLogin.getText().equals("admin")
          && txSenha.getText().equals("1234")) {
        System.out.println("logou");
        new VitrineApp().start(new Stage());
        LoginApp.getStage().close();
      } else {
        JOptionPane.showMessageDialog(null,
            "Login e/ou senha inválidos", "Erro",
            JOptionPane.ERROR_MESSAGE);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void fecharAplicacao() {
    System.exit(0);
  }

  public static Stage getStage() {
    return stage;
  }

  public static void setStage(Stage stage) {
    LoginApp.stage = stage;
  }
}
