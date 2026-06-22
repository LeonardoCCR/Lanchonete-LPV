package org.example.lanchonete.entrega;

import org.example.lanchonete.ItemConsumivel;

public interface  ProvedorEmbalagem {

    Embalagem gerarEmbalagem(ItemConsumivel item);
}
