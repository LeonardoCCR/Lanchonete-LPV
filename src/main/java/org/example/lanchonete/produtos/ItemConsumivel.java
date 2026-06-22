package org.example.lanchonete.produtos;


public interface ItemConsumivel {
    String getDescricao();
    double getPreco();

    default boolean isLiquido() {
        return false;
    }
}
