package br.com.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Criando um objeto da Classe Produto:

        Produto celular = new Produto();
        celular.setNome("Samsung A51");
        celular.setDescricao("Bom preço, boa performance");
        celular.setPreco( new BigDecimal("1700") );

        Produto notebook = new Produto();
        notebook.setNome("Dell g15");
        notebook.setDescricao("Bom preço, ótima performance");
        notebook.setPreco( new BigDecimal("3500") );

        //Criando um objeto da Classe Venda;
        Venda venda = new Venda();
        venda.setVendedor("Joaquim");
        venda.addProduto(celular);
        venda.attValorDaVenda(venda.getValorDaVenda());

        Venda segundaVenda = new Venda();
        segundaVenda.setVendedor("Carlos");
        segundaVenda.addProduto(celular);
        segundaVenda.addProduto(notebook);
        segundaVenda.attValorDaVenda(segundaVenda.getValorDaVenda());

        // Criando uma factory de EntityManager:

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");

        // Usando a factory acima pra criar o objeto EntityManager:

        EntityManager em = factory.createEntityManager();

        // Iniciando uma transação:
        em.getTransaction().begin();

        // Gravando o objeto no banco de dados:
        em.persist(celular);
        em.persist(notebook);
        em.persist(venda);
        em.persist(segundaVenda);

        // "Comitando" a transação:
        em.getTransaction().commit();

        // Fechando este EntityManager, já que não precisaremos mais dele:
        em.close();
    }

}
