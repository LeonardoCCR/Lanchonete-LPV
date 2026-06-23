package org.example.lanchonete.relatorio;

import org.example.lanchonete.pedido.Pedido;
import org.example.lanchonete.produtos.ItemPedidoBuilder;
import org.example.lanchonete.produtos.Bebida;
import org.example.lanchonete.produtos.Sanduiche;
import org.example.lanchonete.usuario.Cliente;
import org.example.lanchonete.usuario.Cidade;
import org.example.lanchonete.entrega.TipoEntrega;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeradorReciboTest {

    private Pedido pedido;

    @BeforeEach
    void setup() {

        Cidade cidade = new Cidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "João Silva", cidade);


        pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);


        pedido.adicionarLinha(new ItemPedidoBuilder(new Sanduiche("Hambúrguer", 20.0))
                .comQuantidade(1).build());

        pedido.adicionarLinha(new ItemPedidoBuilder(new Bebida("Refrigerante", 5.0))
                .comQuantidade(2).build());
    }

    @Test
    void deveGerarNotaFiscalClienteComRodape() {
        NotaFiscalCliente nf = new NotaFiscalCliente();
        String resultado = nf.gerarRecibo(pedido);

        assertTrue(resultado.contains("=== LANCHONETE FACADE ==="));
        assertTrue(resultado.contains("Cliente: João Silva"));
        assertTrue(resultado.contains("TOTAL A PAGAR:"));
        assertTrue(resultado.contains("Obrigado pela preferência"));
    }

    @Test
    void deveGerarReciboCozinhaSemRodape() {
        ReciboCozinha cozinha = new ReciboCozinha();
        String resultado = cozinha.gerarRecibo(pedido);

        assertTrue(resultado.contains("=== ORDEM DE PREPARO"));
        assertTrue(resultado.contains("Hambúrguer"));

        assertFalse(resultado.contains("TOTAL A PAGAR:"));
    }
}