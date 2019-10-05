package sample;

public class Produto {
    private int codigo;
    private String produto;
    private double preco;
    private String caminhoImagem;

    public Produto(int codigo, String produto, double preco,
                   String caminhoImagem) {
        super();
        this.codigo = codigo;
        this.produto = produto;
        this.preco = preco;
        this.caminhoImagem = caminhoImagem;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public Produto() {
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
