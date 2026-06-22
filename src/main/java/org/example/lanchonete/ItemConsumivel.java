package org.example.lanchonete;


public interface ItemConsumivel {
    String getDescricao();
    double getPreco();

    default boolean isLiquido() {
        return false;
    }
}
