package org.example.lanchonete.combo;

import org.example.lanchonete.produtos.ItemConsumivel;

public interface PerfilComboFactory {
    ItemConsumivel criarSanduichePrincipal();
    ItemConsumivel criarSanduicheSecundario();
    ItemConsumivel criarBebidaMedia();
    ItemConsumivel criarBebidaGrande();
    ItemConsumivel criarSalgado();
    ItemConsumivel criarAcompanhamento();
}
