package org.example.lanchonete.pagamento;

import org.example.lanchonete.entrega.TipoEntrega;
import org.example.lanchonete.pedido.Pedido;
import org.example.lanchonete.produtos.ItemPedidoBuilder;
import org.example.lanchonete.produtos.Sanduiche;
import org.example.lanchonete.usuario.Cidade;
import org.example.lanchonete.usuario.CidadeFactory;
import org.example.lanchonete.usuario.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoPagamentoAdapterTest {

    @Test
    public void deveProcessarPagamentoComSucesso() {
        GatewayPagamentoExterno apiExterna = new GatewayPagamentoExterno();
        ProcessadorPagamento adaptador = new PagamentoAdapter(apiExterna, "TOKEN_VALIDO");

        Cidade jf = CidadeFactory.getCidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo César", jf);
        Pedido pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);
        pedido.adicionarLinha(new ItemPedidoBuilder(new Sanduiche("X-Burguer", 15.00)).build());

        boolean sucesso = pedido.pagar(adaptador, new SemDesconto());
        assertTrue(sucesso);
    }

    @Test
    public void deveLancarExcecaoQuandoTokenForInvalido() {
        GatewayPagamentoExterno apiExterna = new GatewayPagamentoExterno();

        ProcessadorPagamento adaptadorComErroToken = new PagamentoAdapter(apiExterna, "TOKEN_ERRADO");

        Cidade jf = CidadeFactory.getCidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo César", jf);
        Pedido pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);
        pedido.adicionarLinha(new ItemPedidoBuilder(new Sanduiche("X-Burguer", 15.00)).build());

        assertThrows(RuntimeException.class, () -> {
            pedido.pagar(adaptadorComErroToken, new SemDesconto());
        });
    }

    @Test
    public void deveLancarExcecaoQuandoValorDoPedidoForInvalido() {
        GatewayPagamentoExterno apiExterna = new GatewayPagamentoExterno();
        ProcessadorPagamento adaptador = new PagamentoAdapter(apiExterna, "TOKEN_VALIDO");

        Cidade jf = CidadeFactory.getCidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo César", jf);

        Pedido pedidoVazio = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);

        assertThrows(RuntimeException.class, () -> {
            pedidoVazio.pagar(adaptador, new SemDesconto());
        });
    }
}
