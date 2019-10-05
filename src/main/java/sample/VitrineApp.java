package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VitrineApp extends Application {
    private static ItemApp itemApp;
    private AnchorPane pane;
    private Label lbPesquisa;
    private TextField txPesquisa;
    private TableView<ItensProperty> tbVitrine;
    private TableColumn<ItensProperty, String> columnProduto;
    private TableColumn<ItensProperty, Double> columnPreco;
    private static ObservableList<ItensProperty> listItens = FXCollections
            .observableArrayList();
    private static Carrinho carrinho;
    private Button btnVoltar;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        initComponents();
        initItens();
        initListeners();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Lista de Produtos");
        stage.show();
        initLayout();

    }

    private void initComponents() {
        pane = new AnchorPane();
        pane.setPrefSize(800, 600);

        lbPesquisa = new Label("Digite um item para pesquisa");
        lbPesquisa.setFont(new Font(14));

        txPesquisa = new TextField();
        txPesquisa.setPromptText("Nome do item");

        tbVitrine = new TableView<ItensProperty>();
        tbVitrine.setPrefSize(794, 548);

        btnVoltar = new Button("Voltar");

        columnProduto = new TableColumn<ItensProperty, String>("Produto");
        columnPreco = new TableColumn<ItensProperty, Double>("Preco");

        tbVitrine.getColumns().add(0, columnProduto);
        tbVitrine.getColumns().add(1, columnPreco);

        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, DimGrey 0%, LightSlateGray 100%);");

        btnVoltar.setStyle("-fx-color: black 25%");

        lbPesquisa.setTextFill(Color.web("#FFFF"));

        pane.getChildren().addAll(lbPesquisa, txPesquisa, btnVoltar, tbVitrine);

        carrinho = new Carrinho();
        itemApp = new ItemApp();

        columnProduto
                .setCellValueFactory(new PropertyValueFactory<ItensProperty, String>(
                        "produto"));
        columnPreco
                .setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>(
                        "preco"));
    }

    private ObservableList<ItensProperty> findItems() {
        ObservableList<ItensProperty> itensEncontrados = FXCollections
                .observableArrayList();
        for (ItensProperty itens : listItens) {
            String itenSelecionado = itens.getProduto().toUpperCase();
            String dadoPesquisa = txPesquisa.getText().toUpperCase();
            if (itenSelecionado.contains(dadoPesquisa)) {
                itensEncontrados.add(itens);
            }
        }
        return itensEncontrados;
    }

    private void initListeners() {
        txPesquisa.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                if (!txPesquisa.getText().equals("")) {
                    tbVitrine.setItems(findItems());
                } else {
                    tbVitrine.setItems(listItens);
                }
            }
        });
        tbVitrine.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ItensProperty>() {
                    public void changed(
                            ObservableValue<? extends ItensProperty> value,
                            ItensProperty oldItem, ItensProperty newItem) {
                        if (newItem != null) {
                            ItemApp.setProduto(new Produto(newItem.getCodigo(),
                                    newItem.getProduto(), newItem.getPreco(),
                                    newItem.getCaminhoImagem()));
                            ItemApp.setIndex(newItem.getCodigo());
                            try {
                                if (stage == null) {
                                    stage = new Stage();
                                }
                                new ItemApp().start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initItens() {
        Vitrine vitrine = new Vitrine();
        vitrine.addProduto(new Produto(1561, "NIFELAT 10/25MG 28 CPR", 32.5400,
                "../resources/images/nifelat.jpg"));
        vitrine.addProduto(new Produto(12358, "TYLENOL 500MG C/04 CPR", 2.3300,
                "../resources/images/tylenol.jpg"));
        vitrine.addProduto(new Produto(3973, "ENGOV C/06 CPR", 4.7500,
                "../resources/images/engov.jpg"));
        vitrine.addProduto(new Produto(5094, "IMOSEC C/12 CPR", 6.5000,
                "../resources/images/imosec.jpg"));
        vitrine.addProduto(new Produto(2610, "DORFLEX C/10 CPR", 4.5000,
                "../resources/images/dorflex.jpg"));

        for (Produto p : vitrine.getListaProdutos()) {
            listItens.add(new ItensProperty(p.getCodigo(), p.getProduto(), p
                    .getPreco(), p.getCaminhoImagem()));
        }

        tbVitrine.setItems(listItens);
    }

    private void initLayout() {
        lbPesquisa.setLayoutX(5);
        lbPesquisa.setLayoutY(15);

        txPesquisa.setLayoutX(200);
        txPesquisa.setLayoutY(14);

        btnVoltar.setLayoutX(650);
        btnVoltar.setLayoutY(12);

        tbVitrine.setLayoutY(50);
        tbVitrine.setLayoutX((pane.getWidth() - tbVitrine.getWidth()) / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static Carrinho getCarrinho() {
        return carrinho;
    }

    public static void setCarrinho(Carrinho carrinho) {
        VitrineApp.carrinho = carrinho;
    }
}
