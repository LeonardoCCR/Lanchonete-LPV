package org.example.lanchonete;

import org.example.lanchonete.entrega.TipoEntrega;
import org.example.lanchonete.pedido.Pedido;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoIteratorTest {

    @Test
    public void devePermitirLeituraSequencialUsandoLacoForEach() {
        //  Criar um cliente fictício e um pedido com 2 itens distintos
        Cidade cidadeFake = CidadeFactory.getCidade("Cidade Teste", "TS");
        Cliente cliente = new Cliente("999", "Cliente Teste", cidadeFake);
        Pedido pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);

        ItemPedido item1 = new ItemPedidoBuilder(new Salgado("Coxinha", 6.00)).comQuantidade(2).build(); // 12.00
        ItemPedido item2 = new ItemPedidoBuilder(new Sanduiche("X-Burguer", 15.00)).build(); // 15.00

        pedido.adicionarLinha(item1);
        pedido.adicionarLinha(item2);

        int totalItensContadosPeloIterator = 0;
        double valorAcumuladoPeloIterator = 0;

        for (ItemPedido linha : pedido) {
            totalItensContadosPeloIterator++;
            valorAcumuladoPeloIterator += linha.getSubtotal();
        }


        assertEquals(2, totalItensContadosPeloIterator,
                "O Iterator deveria ter percorrido exatamente 2 linhas do pedido.");
        assertEquals(27.00, valorAcumuladoPeloIterator, 0.001,
                "A soma dos subtotais obtidos pelo Iterator está incorreta.");
    }

    @Test
    public void deveAvancarPonteirosEIdentificarFimDaListaCorretamente() {
        Cidade cidadeFake = CidadeFactory.getCidade("Cidade Teste", "TS");
        Cliente cliente = new Cliente("999", "Cliente Teste", cidadeFake);
        Pedido pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);

        ItemPedido item = new ItemPedidoBuilder(new Salgado("Suco de Laranja", 8.00)).build();
        pedido.adicionarLinha(item);


        Iterator<ItemPedido> iterator = pedido.iterator();

        assertTrue(iterator.hasNext(), "O iterador deveria acusar que há um item na fila.");


        ItemPedido itemRecuperado = iterator.next();
        assertEquals("Suco de Laranja", itemRecuperado.getItem().getDescricao());

        assertFalse(iterator.hasNext(), "O iterador deveria indicar que chegou ao fim da lista.");
    }
}