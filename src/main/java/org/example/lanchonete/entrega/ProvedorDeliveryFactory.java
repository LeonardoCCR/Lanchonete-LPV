package org.example.lanchonete.entrega;

import org.example.lanchonete.Bebida;
import org.example.lanchonete.ItemConsumivel;
import org.example.lanchonete.Sanduiche;

public class ProvedorDeliveryFactory implements ProvedorEmbalagem{
    @Override
    public Embalagem gerarEmbalagem(ItemConsumivel item) {
        if(item instanceof Bebida){
            return new Embalagem("Copo Descartável Lacrado", 0.5);
        }
        else if(item instanceof Sanduiche){
            return new Embalagem("Caixa de Isopor", 0.5);
        }
        else{
            return new Embalagem("Caixa de Isopor", 0.5);
        }
    }
}
