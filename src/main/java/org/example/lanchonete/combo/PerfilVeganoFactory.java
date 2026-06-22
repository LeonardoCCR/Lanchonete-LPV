package org.example.lanchonete.combo;

import org.example.lanchonete.produtos.Bebida;
import org.example.lanchonete.produtos.ItemConsumivel;
import org.example.lanchonete.produtos.Sanduiche;
import org.example.lanchonete.produtos.Acompanhamento;

public class PerfilVeganoFactory implements PerfilComboFactory {
    @Override
    public ItemConsumivel criarSanduichePrincipal() {
        return new Sanduiche("Hambúrguer de Soja", 15.0);
    }

    @Override
    public ItemConsumivel criarSanduicheSecundario() {
        return null;
    }

    @Override
    public ItemConsumivel criarBebidaMedia() {
        return new Bebida("Suco Natural de Laranja 500ml", 5.0);
    }

    @Override
    public ItemConsumivel criarBebidaGrande() {
        return null;
    }

    @Override
    public ItemConsumivel criarSalgado() {
        return null;
    }

    @Override
    public ItemConsumivel criarAcompanhamento() {
        return new Acompanhamento("Maçã Fatiada", 3.5);
    }


}

