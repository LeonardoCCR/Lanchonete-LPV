package org.example.lanchonete;

import org.example.lanchonete.GatewayPagamentoExterno;

public class PagamentoAdapter implements ProcessadorPagamento {

    private final GatewayPagamentoExterno gatewayExterno;
    private final String tokenConfigurado;

    public PagamentoAdapter(GatewayPagamentoExterno gatewayExterno, String tokenConfigurado) {
        this.gatewayExterno = gatewayExterno;
        this.tokenConfigurado = tokenConfigurado;
    }

    @Override
    public boolean processar(double valor) {
        String resultado = gatewayExterno.executarCobrancaEletronica(valor, this.tokenConfigurado);

        // Avalia o retorno externo e converte em exceções do sistema local
        if ("ERROR_AUTHENTICATION_FAILED".equals(resultado)) {
            throw new RuntimeException("Falha na comunicação com o gateway: Token de segurança inválido.");
        }

        if ("ERROR_INVALID_AMOUNT".equals(resultado)) {
            throw new RuntimeException("Transação recusada: O valor do pedido deve ser maior que zero.");
        }

        if (!"SUCCESS_AUTH".equals(resultado)) {
            throw new RuntimeException("Transação recusada por erro desconhecido na operadora.");
        }

        return true;
    }
}