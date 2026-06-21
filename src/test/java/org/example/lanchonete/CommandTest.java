package org.example.lanchonete;
import org.example.lanchonete.pedido.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    void deveExecutarFluxoCompletoPedido() {

        Cidade cidadeCliente = new Cidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo", cidadeCliente);

        Pedido pedido = new Pedido(cliente);
        CentralComandos central = new CentralComandos();

        central.executar(new EstadoCancelado.IniciarPreparoCommand(pedido));
        assertEquals(EstadoEmPreparo.class, pedido.getEstadoAtual().getClass());

        central.executar(new EstadoCancelado.FinalizarPreparoCommand(pedido));
        assertEquals(EstadoPronto.class, pedido.getEstadoAtual().getClass());

        central.executar(new EstadoEmEntrega.EnviarEntregaCommand(pedido));
        assertEquals(EstadoEmEntrega.class, pedido.getEstadoAtual().getClass());

        central.executar(new EstadoCancelado.ConfirmarEntregaCommand(pedido));
        assertEquals(EstadoFinalizado.class, pedido.getEstadoAtual().getClass());
    }

    @Test
    void deveRegistrarHistoricoComandos() {

        Cidade cidadeCliente = new Cidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo", cidadeCliente);

        Pedido pedido = new Pedido(cliente);
        //System.out.println(pedido.getEstadoAtual());
        CentralComandos central = new CentralComandos();

        central.executar(new EstadoCancelado.IniciarPreparoCommand(pedido));
        //System.out.println(pedido.getEstadoAtual());
        central.executar(new EstadoCancelado.FinalizarPreparoCommand(pedido));
        //System.out.println(pedido.getEstadoAtual());
        assertEquals(2, central.getHistorico().size());
    }
}
