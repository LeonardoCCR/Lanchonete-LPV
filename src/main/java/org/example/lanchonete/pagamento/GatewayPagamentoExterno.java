package org.example.lanchonete.pagamento;

public class GatewayPagamentoExterno{

    public String executarCobrancaEletronica(double quantiaDinheiro, String tokenSeguranca) {

        if (!"TOKEN_VALIDO".equals(tokenSeguranca)) {
            return "ERROR_AUTHENTICATION_FAILED";
        }

        if(quantiaDinheiro <= 0) {
            return "ERROR_INVALID_AMOUNT";
        }
        return "SUCCESS_AUTH";
    }
}