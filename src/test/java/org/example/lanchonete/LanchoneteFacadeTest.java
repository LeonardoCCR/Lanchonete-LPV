package org.example.lanchonete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.lanchonete.pedido.Pedido;
import static org.junit.jupiter.api.Assertions.*;

public class LanchoneteFacadeTest {

    private LanchoneteFacade facade;
    private Cliente cliente;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        facade = new LanchoneteFacade();
        cliente = facade.cadastrarCliente("10", "Leonardo", "Juiz de Fora", "MG");
        pedido = new Pedido(cliente);

        ItemConsumivel lanche = new Sanduiche("X-Burguer", 15.00);
        ItemPedido item = new ItemPedido(lanche, 1);
        pedido.adicionarLinha(item);
    }

    @Test
    public void testCadastrarClienteEFlyweightCidade() {
        assertNotNull(cliente);
        assertEquals("Leonardo", cliente.getNome());
        assertEquals("Juiz de Fora", cliente.getCidadeResidencia().getNome());

        Cidade mesmaCidade = CidadeFactory.getCidade("Juiz de Fora", "MG");
        assertSame(mesmaCidade, cliente.getCidadeResidencia());
    }

    @Test
    public void testProcessarFluxoPedidoComCartao() {
        ProcessadorPagamento proc = facade.getProcessadorConfigurado();
        Pagamento pagamentoCartao = new PagamentoCartao(proc);
        EstrategiaDesconto semDesconto = new SemDesconto();

        facade.processarFluxoPedido(pedido, pagamentoCartao, semDesconto);

        assertNotNull(pedido.getEstadoAtual());
        assertNotEquals("Recebido", pedido.getEstadoAtual().toString());
    }

    @Test
    public void testProcessarFluxoPedidoComPix() {
        ProcessadorPagamento proc = facade.getProcessadorConfigurado();
        Pagamento pagamentoPix = new PagamentoPix(proc);
        EstrategiaDesconto semDesconto = new SemDesconto();

        facade.processarFluxoPedido(pedido, pagamentoPix, semDesconto);

        assertNotNull(pedido.getEstadoAtual());
        assertNotEquals("Recebido", pedido.getEstadoAtual().toString());
    }
}