package br.com.app;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String vendedor;
    @Column(name = "valor_venda")
    public BigDecimal valorDaVenda;
    @Column(name = "id_produto")
    @ManyToMany
    @JoinTable(name = "venda_produto",
            joinColumns = @JoinColumn(name = "id_venda"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    List<Produto> produtos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getValorDaVenda() {
        return produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void attValorDaVenda(BigDecimal valorDaVenda){
        this.valorDaVenda = valorDaVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }
}
