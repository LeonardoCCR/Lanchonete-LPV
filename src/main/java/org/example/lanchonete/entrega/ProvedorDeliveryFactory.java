package org.example.lanchonete.entrega;

import org.example.lanchonete.produtos.ItemConsumivel;

public class ProvedorDeliveryFactory implements ProvedorEmbalagem {

    @Override
    public Embalagem gerarEmbalagem(ItemConsumivel item) {
        if (item.isLiquido()) {
            return new Embalagem("Copo Descartável Lacrado", 0.5);
        } else {
            return new Embalagem("Caixa de Isopor", 0.5);
        }
    }
}
