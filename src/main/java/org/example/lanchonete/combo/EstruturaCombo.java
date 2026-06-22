package org.example.lanchonete.combo;

import org.example.lanchonete.ItemPedido;

import java.util.List;

@FunctionalInterface
public interface EstruturaCombo {
    List<ItemPedido> montar(PerfilComboFactory perfil);
}
