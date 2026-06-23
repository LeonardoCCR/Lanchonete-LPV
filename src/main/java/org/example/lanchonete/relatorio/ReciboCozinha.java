package org.example.lanchonete.relatorio;

import org.example.lanchonete.pedido.Pedido;
import org.example.lanchonete.produtos.ItemPedido;

public class ReciboCozinha extends GeradorReciboTemplate {

    @Override
    protected String gerarCabecalho(Pedido pedido) {
        return "=== ORDEM DE PREPARO | Pedido: " + pedido.toString() + " ===";
    }

    @Override
    protected String gerarCorpo(Pedido pedido) {
        StringBuilder corpo = new StringBuilder();
        for (ItemPedido item : pedido) {
            corpo.append("- ")
                    .append(item.getQuantidade()).append("x ")
                    .append(item.getItem().getDescricao())
                    .append("\n");
        }
        return corpo.toString();
    }

    @Override
    protected String gerarRodape(Pedido pedido) {
        return "-----------------------------------------";
    }

    @Override
    protected boolean deveImprimirRodape() {
        return false;
    }
}