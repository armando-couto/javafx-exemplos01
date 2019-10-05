package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarrinhoApp extends Application {
    private AnchorPane pane;
    private TableView<ItensProperty> tbCarrinho;
    private TableColumn<ItensProperty, String> columnProduto;
    private TableColumn<ItensProperty, Double> columnPreco;
    private Button btnExcluirItem;
    private Button btnVoltarVitrine;
    private Button btnConfirmarCompra;
    private static ObservableList<ItensProperty> listItens = FXCollections
            .observableArrayList();

    @Override
    public void start(Stage stage) throws Exception {
        initComponents();
        initItens();
        initListeners();

        if (stage.isShowing()) {
            stage.close();
        }

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Lista de Produtos");
        stage.show();
        initLayout();
    }

    private void initComponents() {
        pane = new AnchorPane();
        pane.setPrefSize(800, 600);

        tbCarrinho = new TableView<ItensProperty>();
        tbCarrinho.setPrefSize(794, 500);

        columnProduto = new TableColumn<ItensProperty, String>("Produto");
        columnPreco = new TableColumn<ItensProperty, Double>("Preco");

        btnVoltarVitrine = new Button("Voltar");
        btnConfirmarCompra = new Button("Confirmar Compra");
        btnExcluirItem = new Button("Excluir Item");

        tbCarrinho.getColumns().add(0, columnProduto);
        tbCarrinho.getColumns().add(1, columnPreco);

        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, DimGrey 0%, LightSlateGray 100%);");

        btnExcluirItem.setStyle("-fx-color: black 25%");
        btnVoltarVitrine.setStyle("-fx-color: black 25%");
        btnConfirmarCompra.setStyle("-fx-color: black 25%");

        pane.getChildren().addAll(tbCarrinho, btnExcluirItem, btnVoltarVitrine,
                btnConfirmarCompra);

        columnProduto
                .setCellValueFactory(new PropertyValueFactory<ItensProperty, String>(
                        "produto"));
        columnPreco
                .setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>(
                        "preco"));
    }

    private void initLayout() {
        tbCarrinho.setLayoutX((pane.getWidth() - tbCarrinho.getWidth()) / 2);

        btnVoltarVitrine.setLayoutX(50);
        btnVoltarVitrine.setLayoutY(550);

        btnConfirmarCompra.setLayoutX(520);
        btnConfirmarCompra.setLayoutY(550);

        btnExcluirItem.setLayoutX(650);
        btnExcluirItem.setLayoutY(550);
    }

    private void initListeners() {
        btnExcluirItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                VitrineApp.getCarrinho().removeProduto(
                        tbCarrinho.getSelectionModel().getSelectedItem()
                                .getCodigo());
                tbCarrinho.getItems().remove(
                        tbCarrinho.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void initItens() {
        for (Produto p : VitrineApp.getCarrinho().getProdutos()) {
            listItens.add(new ItensProperty(p.getCodigo(), p.getProduto(), p
                    .getPreco(), p.getCaminhoImagem()));
        }
        tbCarrinho.setItems(listItens);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
