package org.example.lanchonete.pagamento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyTest {

    @Test
    void deveProcessarPagamentoValido() {
        ProcessadorPagamento pagamento = new ProcessadorPagamentoProxy(new PagamentoAdapter(new GatewayPagamentoExterno(), "TOKEN_VALIDO"));

        assertTrue(pagamento.processar(100.0));
    }

    @Test
    void deveProcessarPagamentoComValorDecimal() {
        ProcessadorPagamento pagamento = new ProcessadorPagamentoProxy(new PagamentoAdapter(new GatewayPagamentoExterno(), "TOKEN_VALIDO"));

        assertTrue(pagamento.processar(49.90));
    }

    @Test
    void deveLancarExcecaoParaValorZero() {
        ProcessadorPagamento pagamento = new ProcessadorPagamentoProxy(new PagamentoAdapter(new GatewayPagamentoExterno(), "TOKEN_VALIDO"));

        assertThrows(IllegalArgumentException.class, () -> pagamento.processar(0));
    }

    @Test
    void deveLancarExcecaoParaValorNegativo() {
        ProcessadorPagamento pagamento = new ProcessadorPagamentoProxy(new PagamentoAdapter(new GatewayPagamentoExterno(), "TOKEN_VALIDO"));

        assertThrows(IllegalArgumentException.class, () -> pagamento.processar(-50.0));
    }

    @Test
    void naoDevePermitirProcessadorRealNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ProcessadorPagamentoProxy(null));
    }

    @Test
    void deveLancarExcecaoQuandoTokenForInvalido() {
        ProcessadorPagamento pagamento = new ProcessadorPagamentoProxy(new PagamentoAdapter(new GatewayPagamentoExterno(), "TOKEN_INVALIDO"));

        assertThrows(RuntimeException.class, () -> pagamento.processar(100.0));
    }
}