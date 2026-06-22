package org.example.lanchonete;
import org.example.lanchonete.entrega.TipoEntrega;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.lanchonete.pedido.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChainOfResponsibilityTest {

    private AvaliadorDesconto cadeia;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        Cidade cidade = CidadeFactory.getCidade("Juiz de Fora", "MG");

        Funcionario funcionarioHugo = new Funcionario("1", "Hugo", cidade, "Atendente");
        Funcionario funcionarioLeo = new Funcionario("2", "Leonardo", cidade, "Gerente");

        AvaliadorDesconto eloAtendente = new AvaliadorAtendente(funcionarioHugo);
        AvaliadorDesconto eloGerente = new AvaliadorGerente(funcionarioLeo);

        eloAtendente.setSupervisorSuperior(eloGerente);
        this.cadeia = eloAtendente;

        Cliente cliente = new Cliente("10", "Adailton", cidade);
        this.pedido = new Pedido(cliente, TipoEntrega.COMER_NO_LOCAL);
    }

    @Test
    public void testAtendenteAssinaDescontoBaixoNoInicioDaCadeia() {

        String cargoResponsavel = cadeia.assinarDesconto(pedido, 5.0);
        assertEquals("Atendente", cargoResponsavel);
    }

    @Test
    public void testGerenteAssinaDescontoAltoRepassadoPeloAtendente() {
        String cargoResponsavel = cadeia.assinarDesconto(pedido, 15.0);
        assertEquals("Gerente", cargoResponsavel);
    }

    @Test
    public void testRetornaSemAssinaturaParaDescontoNaoMapeado() {

        String cargoResponsavel = cadeia.assinarDesconto(pedido, 50.0);
        assertEquals("Sem assinatura", cargoResponsavel);
    }
}