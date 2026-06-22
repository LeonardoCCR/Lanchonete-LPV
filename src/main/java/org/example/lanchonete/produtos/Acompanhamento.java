package org.example.lanchonete.produtos;

import org.example.lanchonete.ItemConsumivel;

public class Acompanhamento implements ItemConsumivel {

    private String descricao;
    private double preco;

    public Acompanhamento(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public double getPreco() {
        return preco;
    }
}
