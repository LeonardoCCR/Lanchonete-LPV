package org.example.lanchonete;

import org.example.lanchonete.pedido.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoStrategyTest {

    @Test
    public void deveManterPrecoInalteradoComEstrategiaSemDesconto() {
        // Cenário: valor bruto de R$ 40,00
        Cidade cidadeFake = CidadeFactory.getCidade("Cidade Teste", "TS");
        Cliente cliente = new Cliente("999", "Cliente Teste", cidadeFake);
        Pedido pedido = new Pedido(cliente);

        ItemPedido item = new ItemPedidoBuilder(new Sanduiche("X-Tudo", 20.00)).comQuantidade(2).build();
        pedido.adicionarLinha(item);


        EstrategiaDesconto semDesconto = new SemDesconto();
        double valorFinal = pedido.getValorTotalFinal(semDesconto);

        assertEquals(40.00, valorFinal, 0.001,
                "A estratégia SemDesconto alterou indevidamente o valor original.");
    }

    @Test
    public void deveCalcularSubtracaoCorretaComEstrategiaDescontoPercentual() {
        // Cenário: Pedido bruto no valor de R$ 50,00
        Cidade cidadeFake = CidadeFactory.getCidade("Cidade Teste", "TS");
        Cliente cliente = new Cliente("999", "Cliente Teste", cidadeFake);
        Pedido pedido = new Pedido(cliente);

        ItemPedido item = new ItemPedidoBuilder(new Sanduiche("Combo", 50.00)).build();
        pedido.adicionarLinha(item);


        EstrategiaDesconto descontoVintePorCento = new DescontoPercentual(20.0);
        double valorFinal = pedido.getValorTotalFinal(descontoVintePorCento);

        assertEquals(40.00, valorFinal, 0.001,
                "O cálculo da estratégia DescontoPercentual divergiu do valor matemático esperado.");
    }

    @Test
    public void deveLancarExcecaoAoTentarPassarEstrategiaNula() {
        Cidade cidadeFake = CidadeFactory.getCidade("Cidade Teste", "TS");
        Cliente cliente = new Cliente("999", "Cliente Teste", cidadeFake);
        Pedido pedido = new Pedido(cliente);

        assertThrows(IllegalArgumentException.class, () -> {
            pedido.getValorTotalFinal(null);
        }, "O método deveria disparar uma exceção de argumento inválido para estratégias nulas.");
    }
}