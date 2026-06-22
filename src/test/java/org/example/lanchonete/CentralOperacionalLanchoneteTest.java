

package org.example.lanchonete;

import org.example.lanchonete.pedido.CentralOperacionalLanchonete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.lanchonete.pedido.Pedido;
import static org.junit.jupiter.api.Assertions.*;

public class CentralOperacionalLanchoneteTest {  //teste mediator

    private GerenciadorPedidosMediator mediator;
    private Cidade cidade;
    private Funcionario cozinheiro;
    private Pedido pedido1;
    private Pedido pedido2;

    @BeforeEach
    public void setUp() {
        mediator = new CentralOperacionalLanchonete();
        cidade = CidadeFactory.getCidade("Juiz de Fora", "MG");
        cozinheiro = new Funcionario("1", "Hugo", cidade, "Cozinheiro");

        Cliente cliente = new Cliente("100", "Adailton", cidade);
        pedido1 = new Pedido(cliente);
        pedido2 = new Pedido(cliente);
    }

    @Test
    public void testAlocacaoImediataETrabalhoDoCommand() {
        mediator.registrarFuncionario(cozinheiro);
        mediator.enviarParaFilaGlobal(pedido1);

        assertTrue(cozinheiro.isOcupado());
        assertEquals("Em Preparo", pedido1.getEstadoAtual().toString());
    }

    @Test
    public void testFilaDeEsperaENotificacaoComCommands() {
        mediator.registrarFuncionario(cozinheiro);

        mediator.enviarParaFilaGlobal(pedido1);
        mediator.enviarParaFilaGlobal(pedido2);

        assertEquals("Recebido", pedido2.getEstadoAtual().toString());

        mediator.notificarConclusaoTarefa(cozinheiro, pedido1);

        assertNotEquals("Em Preparo", pedido1.getEstadoAtual().toString());
        assertTrue(cozinheiro.isOcupado());
        assertEquals("Em Preparo", pedido2.getEstadoAtual().toString());
    }
}