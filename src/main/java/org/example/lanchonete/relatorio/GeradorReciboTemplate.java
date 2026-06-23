package org.example.lanchonete.relatorio;

import org.example.lanchonete.pedido.Pedido;

public abstract class GeradorReciboTemplate {

    public final String gerarRecibo(Pedido pedido) {
        StringBuilder builder = new StringBuilder();

        builder.append(gerarCabecalho(pedido)).append("\n");
        builder.append(gerarCorpo(pedido)).append("\n");

        if (deveImprimirRodape()) {
            builder.append(gerarRodape(pedido)).append("\n");
        }

        return builder.toString();
    }


    protected abstract String gerarCabecalho(Pedido pedido);
    protected abstract String gerarCorpo(Pedido pedido);
    protected abstract String gerarRodape(Pedido pedido);

    protected boolean deveImprimirRodape() {
        return true;
    }
}