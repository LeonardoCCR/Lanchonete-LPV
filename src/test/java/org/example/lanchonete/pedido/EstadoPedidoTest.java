package org.example.lanchonete.pedido;

import org.example.lanchonete.Cidade;
import org.example.lanchonete.Cliente;
import org.example.lanchonete.Notificacao;
import org.example.lanchonete.PreferenciasNotificacao;
import org.example.lanchonete.entrega.TipoEntrega;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoPedidoTest {

    private PedidoSpy pedidoSpy;


    static class PedidoSpy extends Pedido {
        public EstadoPedido ultimoEstadoAtribuido = null;
        public List<String> notificacoesRecebidas = new ArrayList<>();

        public PedidoSpy(){
            super(new Cliente(
                    "1",
                    "1",
                    new Cidade("a", "a"),
                    new PreferenciasNotificacao(Arrays.asList("PEDIDO_RECEBIDO", "PEDIDO_SAIU_ENTREGA", "PEDIDO_CANCELADO", "PEDIDO_ENTREGUE", "PEDIDO_FINALIZADO"))
            ), TipoEntrega.COMER_NO_LOCAL);
        }

        @Override
        public void setEstado(EstadoPedido estado) {
            this.ultimoEstadoAtribuido = estado;
            super.setEstado(estado);
        }


        public void notificar(Notificacao notificacao) {

            this.notificacoesRecebidas.add(notificacao.mensagem());
            super.notificar(notificacao);

        }
    }

    @BeforeEach
    void setUp() {
        pedidoSpy = new PedidoSpy();
    }


    private void assertAcaoInvalida(Executable executavel) {
        IllegalStateException exception = assertThrows(IllegalStateException.class, executavel);
        assertTrue(exception.getMessage().contains("A ação é inválida"));
    }


    @Test
    void dadoEstadoRecebido_quandoIniciarPreparo_entaoMudaParaEmPreparoENotifica() {
        EstadoPedido estado = new EstadoRecebido();
        estado.iniciarPreparo(pedidoSpy);

        assertInstanceOf(EstadoEmPreparo.class, pedidoSpy.ultimoEstadoAtribuido);
        assertTrue(pedidoSpy.notificacoesRecebidas.stream().anyMatch(n -> n.contains("Pedido em preparo")));
    }

    @Test
    void dadoEstadoRecebido_quandoCancelar_entaoMudaParaCanceladoENotifica() {
        EstadoPedido estado = new EstadoRecebido();
        estado.cancelar(pedidoSpy);

        assertInstanceOf(EstadoCancelado.class, pedidoSpy.ultimoEstadoAtribuido);
        assertTrue(pedidoSpy.notificacoesRecebidas.stream().anyMatch(n -> n.contains("Pedido cancelado")));
    }

    @Test
    void dadoEstadoRecebido_quandoAcoesInvalidas_entaoLancaExcecao() {
        EstadoPedido estado = new EstadoRecebido();
        assertAcaoInvalida(() -> estado.finalizarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.enviarEntrega(pedidoSpy));
        assertAcaoInvalida(() -> estado.confirmarEntrega(pedidoSpy));
    }

    @Test
    void dadoEstadoRecebido_quandoToString_entaoRetornaNomeCorreto() {
        assertEquals("Recebido", new EstadoRecebido().toString());
    }


    @Test
    void dadoEstadoEmPreparo_quandoFinalizarPreparo_entaoMudaParaPronto() {
        EstadoPedido estado = new EstadoEmPreparo();
        estado.finalizarPreparo(pedidoSpy);

        assertInstanceOf(EstadoPronto.class, pedidoSpy.ultimoEstadoAtribuido);

    }

    @Test
    void dadoEstadoEmPreparo_quandoCancelar_entaoMudaParaCanceladoENotifica() {
        EstadoPedido estado = new EstadoEmPreparo();
        estado.cancelar(pedidoSpy);

        assertInstanceOf(EstadoCancelado.class, pedidoSpy.ultimoEstadoAtribuido);
        assertTrue(pedidoSpy.notificacoesRecebidas.stream().anyMatch(n -> n.contains("Pedido cancelado")));
    }

    @Test
    void dadoEstadoEmPreparo_quandoAcoesInvalidas_entaoLancaExcecao() {
        EstadoPedido estado = new EstadoEmPreparo();
        assertAcaoInvalida(() -> estado.iniciarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.enviarEntrega(pedidoSpy));
        assertAcaoInvalida(() -> estado.confirmarEntrega(pedidoSpy));
    }

    @Test
    void dadoEstadoEmPreparo_quandoToString_entaoRetornaNomeCorreto() {
        assertEquals("Em Preparo", new EstadoEmPreparo().toString());
    }

    @Test
    void dadoEstadoPronto_quandoEnviarEntrega_entaoMudaParaEmEntregaENotifica() {
        EstadoPedido estado = new EstadoPronto();
        estado.enviarEntrega(pedidoSpy);

        assertInstanceOf(EstadoEmEntrega.class, pedidoSpy.ultimoEstadoAtribuido);
        assertTrue(pedidoSpy.notificacoesRecebidas.stream().anyMatch(n -> n.contains("Pedido em entrega")));
    }

    @Test
    void dadoEstadoPronto_quandoCancelar_entaoMudaParaCanceladoENotificaComMulta() {
        EstadoPedido estado = new EstadoPronto();
        estado.cancelar(pedidoSpy);

        assertInstanceOf(EstadoCancelado.class, pedidoSpy.ultimoEstadoAtribuido);
        assertTrue(pedidoSpy.notificacoesRecebidas.stream().anyMatch(n -> n.contains("multa aplicada")));
    }

    @Test
    void dadoEstadoPronto_quandoAcoesInvalidas_entaoLancaExcecao() {
        EstadoPedido estado = new EstadoPronto();
        assertAcaoInvalida(() -> estado.iniciarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.finalizarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.confirmarEntrega(pedidoSpy));
    }

    @Test
    void dadoEstadoPronto_quandoToString_entaoRetornaNomeCorreto() {
        assertEquals("Pronto para Entrega", new EstadoPronto().toString());
    }

    @Test
    void dadoEstadoEmEntrega_quandoConfirmarEntrega_entaoMudaParaFinalizadoENotifica() {
        EstadoPedido estado = new EstadoEmEntrega();
        estado.confirmarEntrega(pedidoSpy);

        assertInstanceOf(EstadoFinalizado.class, pedidoSpy.ultimoEstadoAtribuido);
        assertTrue(pedidoSpy.notificacoesRecebidas.stream().anyMatch(n -> n.contains("Pedido entregue")));
    }

    @Test
    void dadoEstadoEmEntrega_quandoAcoesInvalidas_entaoLancaExcecao() {
        EstadoPedido estado = new EstadoEmEntrega();
        assertAcaoInvalida(() -> estado.cancelar(pedidoSpy));
        assertAcaoInvalida(() -> estado.iniciarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.finalizarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.enviarEntrega(pedidoSpy));
    }

    @Test
    void dadoEstadoEmEntrega_quandoToString_entaoRetornaNomeCorreto() {
        assertEquals("Saiu para Entrega", new EstadoEmEntrega().toString());
    }

    @Test
    void dadoEstadoFinalizado_quandoQualquerAcao_entaoLancaExcecao() {
        EstadoPedido estado = new EstadoFinalizado();
        assertAcaoInvalida(() -> estado.cancelar(pedidoSpy));
        assertAcaoInvalida(() -> estado.iniciarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.finalizarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.enviarEntrega(pedidoSpy));
        assertAcaoInvalida(() -> estado.confirmarEntrega(pedidoSpy));
    }

    @Test
    void dadoEstadoFinalizado_quandoToString_entaoRetornaNomeCorreto() {
        assertEquals("Finalizado", new EstadoFinalizado().toString());
    }

    @Test
    void dadoEstadoCancelado_quandoQualquerAcao_entaoLancaExcecao() {
        EstadoPedido estado = new EstadoCancelado();
        assertAcaoInvalida(() -> estado.cancelar(pedidoSpy));
        assertAcaoInvalida(() -> estado.iniciarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.finalizarPreparo(pedidoSpy));
        assertAcaoInvalida(() -> estado.enviarEntrega(pedidoSpy));
        assertAcaoInvalida(() -> estado.confirmarEntrega(pedidoSpy));
    }

    @Test
    void dadoEstadoCancelado_quandoToString_entaoRetornaNomeCorreto() {
        assertEquals("Cancelado", new EstadoCancelado().toString());
    }
}