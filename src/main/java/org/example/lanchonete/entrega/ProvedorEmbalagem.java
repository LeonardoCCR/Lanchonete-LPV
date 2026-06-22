package org.example.lanchonete.entrega;

import org.example.lanchonete.produtos.ItemConsumivel;

public interface  ProvedorEmbalagem {

    Embalagem gerarEmbalagem(ItemConsumivel item);
}
