package org.example.lanchonete.relatorio;

import org.example.lanchonete.pagamento.SemDesconto; // Exemplo de uso
import org.example.lanchonete.pedido.Pedido;
import org.example.lanchonete.produtos.ItemPedido;

public class NotaFiscalCliente extends GeradorReciboTemplate {

    @Override
    protected String gerarCabecalho(Pedido pedido) {
        return "=== LANCHONETE FACADE ===\n" +
                "Cliente: " + pedido.getCliente().getNome() + "\n" +
                "ID Pedido: " + pedido.toString() + "\n" +
                "=========================";
    }

    @Override
    protected String gerarCorpo(Pedido pedido) {
        StringBuilder corpo = new StringBuilder();
        for (ItemPedido item : pedido) {
            corpo.append(item.getQuantidade()).append("x ")
                    .append(item.getItem().getDescricao())
                    .append(" ......... R$ ").append(String.format("%.2f", item.getSubtotal()))
                    .append("\n");
        }
        return corpo.toString();
    }

    @Override
    protected String gerarRodape(Pedido pedido) {
        double total = pedido.getValorTotalFinal(new SemDesconto());
        return "=========================\n" +
                "TOTAL A PAGAR: R$ " + String.format("%.2f", total) + "\n" +
                "Obrigado pela preferência e volte sempre!";
    }
}