package sample;

import java.util.ArrayList;
import java.util.List;

public class Vitrine {
    private static List<Produto> produtos = new ArrayList<Produto>();

    public void addProduto(Produto p) {
        produtos.add(p);
    }

    public List<Produto> getListaProdutos() {
        return produtos;
    }
}
