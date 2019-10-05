package sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrinho {
    private static List<Produto> produtos = new ArrayList<Produto>();

    public void addProduto(Produto p) {
        produtos.add(p);
    }

    public void removeProduto(int codigo) {
        Iterator<Produto> itProduto = produtos.iterator();
        while (itProduto.hasNext()) {
            Produto produto = itProduto.next();
            if (produto.getCodigo() == codigo) {
                itProduto.remove();
            }
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
