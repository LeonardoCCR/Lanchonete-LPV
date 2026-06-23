package org.example.lanchonete.pedido;

import org.example.lanchonete.entrega.TipoEntrega;
import org.example.lanchonete.notificacao.PreferenciasNotificacao;
import org.example.lanchonete.usuario.Cidade;
import org.example.lanchonete.usuario.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MementoTest {

    private Pedido pedido;
    private CentralComandos central;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido(new Cliente(
                "1",
                "1",
                new Cidade("a", "a"),
                new PreferenciasNotificacao(Arrays.asList("PEDIDO_RECEBIDO", "PEDIDO_SAIU_ENTREGA", "PEDIDO_CANCELADO", "PEDIDO_ENTREGUE", "PEDIDO_FINALIZADO"))
        ), TipoEntrega.COMER_NO_LOCAL);
        central = new CentralComandos();
    }

    @Test
    public void testSalvarERestaurarEstado() {
        PedidoMemento memento = pedido.salvar();
        Command iniciar = new IniciarPreparoCommand(pedido);
        iniciar.executar();
        pedido.restaurar(memento);
        assertInstanceOf(EstadoRecebido.class, pedido.getEstadoAtual());
    }

    @Test
    public void testExecutarComandoSalvaMementoEPermiteDesfazer() {
        Command iniciar = new IniciarPreparoCommand(pedido);
        central.executar(iniciar, pedido);
        assertInstanceOf(EstadoEmPreparo.class, pedido.getEstadoAtual());

        central.desfazer();
        assertInstanceOf(EstadoRecebido.class, pedido.getEstadoAtual());
    }

    @Test
    public void testDesfazerMultiplosComandosEmOrdem() {
        central.executar(new IniciarPreparoCommand(pedido), pedido);
        central.executar(new FinalizarPreparoCommand(pedido), pedido);

        assertInstanceOf(EstadoPronto.class, pedido.getEstadoAtual());

        central.desfazer();
        assertInstanceOf(EstadoEmPreparo.class, pedido.getEstadoAtual());

        central.desfazer();
        assertInstanceOf(EstadoRecebido.class, pedido.getEstadoAtual());
    }

    @Test
    public void testDesfazerSemHistoricoNaoGeraErro() {
        assertDoesNotThrow(() -> central.desfazer());
        assertInstanceOf(EstadoRecebido.class, pedido.getEstadoAtual());
    }

    @Test
    public void testUsoDoDesfazerCommandEncapsulado() {
        central.executar(new IniciarPreparoCommand(pedido), pedido);
        Command desfazer = new DesfazerCommand(central);
        central.executar(desfazer);

        assertInstanceOf(EstadoRecebido.class, pedido.getEstadoAtual());
    }

    @Test
    public void testClonagemGaranteIndependenciaDoMemento() {
        PedidoMemento memento = pedido.salvar();
        EstadoPedido estadoSalvo = memento.getEstado();
        EstadoPedido estadoAtual = pedido.getEstadoAtual();

        assertNotSame(estadoSalvo, estadoAtual);
    }

    @Test
    public void testDesfazerComMultiplosPedidos() {
        Pedido pedido2 = new Pedido(new Cliente(
                "1",
                "1",
                new Cidade("a", "a"),
                new PreferenciasNotificacao(Arrays.asList("PEDIDO_RECEBIDO", "PEDIDO_SAIU_ENTREGA", "PEDIDO_CANCELADO", "PEDIDO_ENTREGUE", "PEDIDO_FINALIZADO"))
        ), TipoEntrega.COMER_NO_LOCAL);

        central.executar(new IniciarPreparoCommand(pedido), pedido);
        central.executar(new IniciarPreparoCommand(pedido2), pedido2);
        central.executar(new FinalizarPreparoCommand(pedido2), pedido2);

        assertInstanceOf(EstadoEmPreparo.class, pedido.getEstadoAtual());
        assertInstanceOf(EstadoPronto.class, pedido2.getEstadoAtual());

        central.desfazer();
        assertInstanceOf(EstadoEmPreparo.class, pedido2.getEstadoAtual());

        central.desfazer();
        assertInstanceOf(EstadoRecebido.class, pedido2.getEstadoAtual());

        central.desfazer();
        assertInstanceOf(EstadoRecebido.class, pedido.getEstadoAtual());
    }
}