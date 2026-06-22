package org.example.lanchonete.combo;

import org.example.lanchonete.Bebida;
import org.example.lanchonete.ItemConsumivel;
import org.example.lanchonete.Sanduiche;
import org.example.lanchonete.produtos.Acompanhamento;

public class PerfilTradicionalFactory implements PerfilComboFactory {
    @Override
    public ItemConsumivel criarSanduichePrincipal() {
        return new Sanduiche("Hambúrguer de Carne Duplo", 18.0);
    }

    @Override
    public ItemConsumivel criarSanduicheSecundario() {
        return null;
    }


    @Override
    public ItemConsumivel criarSalgado() {
        return null;
    }

    @Override
    public ItemConsumivel criarAcompanhamento() {
        return new Acompanhamento("Batata Frita Média", 6.0);
    }

    @Override
    public ItemConsumivel criarBebidaMedia() {
        return new Bebida("Refrigerante Cola 500ml", 5.0);
    }

    @Override
    public ItemConsumivel criarBebidaGrande() {
        return null;
    }

}
