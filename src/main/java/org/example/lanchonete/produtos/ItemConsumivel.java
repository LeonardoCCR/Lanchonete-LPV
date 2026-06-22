package org.example.lanchonete.produtos;


import org.example.lanchonete.relatorio.Visitavel;

public interface ItemConsumivel extends Visitavel {
    String getDescricao();
    double getPreco();

    default boolean isLiquido() {
        return false;
    }
}
