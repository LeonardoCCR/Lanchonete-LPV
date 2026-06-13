package org.example.lanchonete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ContadorPedidoTest {

    private ContadorPedido contador;

    @BeforeEach
    void setUp() {
        contador = ContadorPedido.get();
    }

    @Test
    void dadoMesmoDia_quandoGerarCodigo_entaoIncrementaSequencialmente() throws NoSuchFieldException, IllegalAccessException {
        Field dataField = ContadorPedido.class.getDeclaredField("dataAtual");
        dataField.setAccessible(true);
        dataField.set(contador, LocalDate.now().plusDays(1));
        String dataHoje = LocalDate.now().toString();

        Field contagemField = ContadorPedido.class.getDeclaredField("contagemPedidos");
        contagemField.setAccessible(true);
        contagemField.set(contador, 0);

        String codigo1 = contador.gerarCodigo();
        String codigo2 = contador.gerarCodigo();
        String codigo3 = contador.gerarCodigo();

        assertEquals(dataHoje + "-1", codigo1);
        assertEquals(dataHoje + "-2", codigo2);
        assertEquals(dataHoje + "-3", codigo3);
    }

    @Test
    void dadoMudancaDeDia_quandoGerarCodigo_entaoResetaContagemAtualizaData() throws Exception {
        Field dataField = ContadorPedido.class.getDeclaredField("dataAtual");
        dataField.setAccessible(true);
        dataField.set(contador, LocalDate.now().minusDays(1));

        Field contagemField = ContadorPedido.class.getDeclaredField("contagemPedidos");
        contagemField.setAccessible(true);
        contagemField.set(contador, 50);

        String dataHoje = LocalDate.now().toString();
        String novoCodigo = contador.gerarCodigo();

        assertEquals(dataHoje + "-1", novoCodigo);
    }

    @Test
    void dadoChamadasAoMetodoGet_quandoRequisitarInstancia_entaoRetornaMesmoObjetoSingleton() {
        ContadorPedido instancia1 = ContadorPedido.get();
        ContadorPedido instancia2 = ContadorPedido.get();

        assertSame(instancia1, instancia2);
    }
}