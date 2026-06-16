package org.example.lanchonete;

//A ideia é que tenha uma classe que analise o gateway e retorne coisas esperadas

import org.example.lanchonete.GatewayPagamentoExterno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoPagamentoAdapterTest {

    @Test
    public void deveProcessarPagamentoComSucesso() {
        GatewayPagamentoExterno apiExterna = new GatewayPagamentoExterno();
        ProcessadorPagamento adaptador = new PagamentoAdapter(apiExterna, "TOKEN_VALIDO");

        Cidade jf = CidadeFactory.getCidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo César", jf);
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarLinha(new ItemPedidoBuilder(new Sanduiche("X-Burguer", 15.00)).build());

        boolean sucesso = pedido.pagar(adaptador, new SemDesconto());
        assertTrue(sucesso);
    }

    @Test
    public void deveLancarExcecaoQuandoTokenForInvalido() {
        GatewayPagamentoExterno apiExterna = new GatewayPagamentoExterno();

        // Forçando o erro de autenticação com token errado
        ProcessadorPagamento adaptadorComErroToken = new PagamentoAdapter(apiExterna, "TOKEN_ERRADO");

        Cidade jf = CidadeFactory.getCidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo César", jf);
        Pedido pedido = new Pedido(cliente);
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

        // Criando um pedido vazio (sem itens), cujo valor bruto resultará em 0.0
        Pedido pedidoVazio = new Pedido(cliente);

        // Deve estourar RuntimeException disparada pela validação de valor menor ou igual a zero
        assertThrows(RuntimeException.class, () -> {
            pedidoVazio.pagar(adaptador, new SemDesconto());
        });
    }
}
