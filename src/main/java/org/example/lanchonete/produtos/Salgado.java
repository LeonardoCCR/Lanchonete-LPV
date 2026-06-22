package org.example.lanchonete.produtos;

import org.example.lanchonete.relatorio.RelatorioVisitor;

public class Salgado implements ItemConsumivel {

    private String descricao;
    private double preco;

    public Salgado(String descricao, double preco) {
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

    @Override
    public void accept(RelatorioVisitor visitor) {
        visitor.visit(this);
    }
}
