package org.example.lanchonete.pedido;
import org.example.lanchonete.entrega.TipoEntrega;
import org.example.lanchonete.usuario.Cidade;
import org.example.lanchonete.usuario.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    void deveExecutarFluxoCompletoPedido() {

        Cidade cidadeCliente = new Cidade("Juiz de Fora", "MG");
        Cliente cliente = new Cliente("1", "Leonardo", cidadeCliente);

        Pedido pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);
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

        Pedido pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);
        CentralComandos central = new CentralComandos();

        central.executar(new EstadoCancelado.IniciarPreparoCommand(pedido));

        central.executar(new EstadoCancelado.FinalizarPreparoCommand(pedido));

        assertEquals(2, central.getHistorico().size());
    }
}
