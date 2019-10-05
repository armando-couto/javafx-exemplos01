package sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ItemApp extends Application {
  private static AnchorPane pane;
  private ImageView imgItem;
  private Label lbPreco;
  private Label lbDescricao;
  private Button btnAddCarrinho;
  private Scene scene;
  private static Stage stage;
  private static Produto produto;
  private static CarrinhoApp carrinhoApp;

  private static int index;
  private static Map<Integer, String> produtos = new HashMap<Integer, String>();

  @Override
  public void start(Stage stage) throws Exception {
    initListaProdutos();
    initComponents();
    initListeners();

    scene = new Scene(pane);
    stage.setScene(scene);
    stage.setTitle("Lista de Produtos");
    stage.show();
    initLayout();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void initComponents() throws IOException {
    pane = new AnchorPane();
    pane.setPrefSize(600, 400);

    lbPreco = new Label("Preco:  " + produto.getPreco());
    lbPreco.setTextFill(Color.web("#FFFF"));

    lbDescricao = new Label("Produto:  " + produto.getProduto());
    lbDescricao.setTextFill(Color.web("#FFFF"));

    btnAddCarrinho = new Button("Adicionar ao Carrinho");
    btnAddCarrinho.setStyle("-fx-color: black 25%");

    pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, DimGrey 0%, LightSlateGray 100%);");

    BufferedImage bufferedImage;
    bufferedImage = ImageIO.read(new File(retornaCaminho(index)));
    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    imgItem = new ImageView(image);

    pane.getChildren()
        .addAll(lbPreco, lbDescricao, btnAddCarrinho, imgItem);

    carrinhoApp = new CarrinhoApp();
  }

  public void initListaProdutos() {
    Vitrine vitrine = new Vitrine();

    for (Produto p : vitrine.getListaProdutos()) {
      produtos.put(p.getCodigo(), p.getCaminhoImagem());
    }
  }

  private String retornaCaminho(int codigo) {
    return produtos.get(codigo);
  }

  private void initLayout() {
    lbDescricao.setLayoutX(25);
    lbDescricao.setLayoutY(50);

    lbPreco.setLayoutX(25);
    lbPreco.setLayoutY(125);

    btnAddCarrinho.setLayoutX(25);
    btnAddCarrinho.setLayoutY(200);

    imgItem.setLayoutX(300);
    imgItem.setLayoutY(10);

  }

  private void initListeners() {
    btnAddCarrinho.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        VitrineApp.getCarrinho().addProduto(produto);
        try {
          if (stage == null) {
            stage = new Stage();
          }
          carrinhoApp.start(stage);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    });
  }

  public static Stage getStage() {
    return stage;
  }

  public static Produto getProduto() {
    return produto;
  }

  public static void setProduto(Produto p) {
    ItemApp.produto = p;
  }

  public static int getIndex() {
    return index;
  }

  public static void setIndex(int index) {
    ItemApp.index = index;
  }
}
