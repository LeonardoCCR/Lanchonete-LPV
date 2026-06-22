package org.example.lanchonete.entrega;

import org.example.lanchonete.Bebida;
import org.example.lanchonete.ItemConsumivel;
import org.example.lanchonete.Sanduiche;

public class ProvedorLocalDelivery implements ProvedorEmbalagem{
    @Override
    public Embalagem gerarEmbalagem(ItemConsumivel item) {
        if(item instanceof Bebida){
            return new Embalagem("Copo Descartável", 0.3);
        }
        else if(item instanceof Sanduiche){
            return new Embalagem("Bandeja", 0);
        }
        else{
            return new Embalagem("Bandeja", 0);
        }
    }
}
