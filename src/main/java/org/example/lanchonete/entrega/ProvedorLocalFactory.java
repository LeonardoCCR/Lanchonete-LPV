package org.example.lanchonete.entrega;

import org.example.lanchonete.ItemConsumivel;
public class ProvedorLocalFactory implements ProvedorEmbalagem {

    @Override
    public Embalagem gerarEmbalagem(ItemConsumivel item) {
        if (item.isLiquido()) {
            return new Embalagem("Copo Descartável", 0.3);
        } else {
            return new Embalagem("Bandeja", 0.0);
        }
    }
}
