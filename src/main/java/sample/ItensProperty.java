package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItensProperty {
    private SimpleIntegerProperty codigo;
    private SimpleStringProperty produto;
    private SimpleDoubleProperty preco;
    private SimpleStringProperty caminhoImagem;

    public ItensProperty(int codigo, String produto, double preco,
                         String caminhoImagem) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.produto = new SimpleStringProperty(produto);
        this.preco = new SimpleDoubleProperty(preco);
        this.caminhoImagem = new SimpleStringProperty(caminhoImagem);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getProduto() {
        return produto.get();
    }

    public void setProduto(String produto) {
        this.produto.set(produto);
    }

    public double getPreco() {
        return preco.get();
    }

    public void setPreco(double preco) {
        this.preco.set(preco);
    }

    public String getCaminhoImagem() {
        return caminhoImagem.get();
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem.set(caminhoImagem);
    }
}
